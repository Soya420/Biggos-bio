package bioprojekt.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;

import bioprojekt.Main;
import bioprojekt.util.ResultSetHelper;

// The SQLHandler handles all the SQL-related queries and executions
public class SQLHandler {

	// Strings for safe insertions of data into the database
	public static final String addHallString = "INSERT INTO hall VALUES (DEFAULT, ?, ?, ?, ?);";
	public static final String addCinemaString = "INSERT INTO cinema VALUES (DEFAULT, ? );";


	// The database connection and prepared statements for the safe execution of SQL statements. Meant to protect from SQL injections
	private Connection connection;
	private PreparedStatement addHall, addCinema;

	public SQLHandler(){
		try {

			// Connection to the database
			Class.forName("com.mysql.jdbc.Driver");
			SQLLogin login = new SQLLogin();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?verifyServerCertificate=false&useSSL=true", login.getUsername(), login.getPassword());

			addHall = connection.prepareStatement(addHallString);
			addCinema = connection.prepareStatement(addCinemaString);

		}catch(FileNotFoundException e) {
			System.out.println("Could't find login file");
		}catch(SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Executes a string and returns a ResultSet
	public ResultSet executeQ(String s) throws SQLException{
		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(s);
		return rs;
	}

	// Executes a string as SQL code
	public void execute(String s) throws SQLException{
		Statement stmt = connection.createStatement();
		stmt.execute(s);
	}

	// Closes the connection to the database
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Gets all cinemas from the database
	public Vector<Cinema> getAllCinemas() throws SQLException {
		return ResultSetHelper.toCinemas(executeQ("SELECT * FROM cinema.cinema;"));
	}

	// Adds a cinema to the database
	public void addCinema(Cinema c) throws SQLException {
		if (!containsIllegalChar(c.name)) {
			synchronized(addCinema) {
				String search = c.name;

				addCinema.setString(1, search);
				addCinema.execute();
			}
		} else System.out.println("Error: Illegal char");
	}

	// Removes a cinema from the database
	public void removeCinema(Cinema c) throws SQLException {
		execute("DELETE FROM cinema WHERE ID = " + c.id + ";");
	}

	// Removes all seats reserved within the halls of a cinema. Thereafter removes all halls from the same cinema
	public void removeAllHallsFromCinema(Cinema c) throws SQLException {
		Vector<Hall> halls = getHallsFromCinema(c);
		for (Hall h: halls) {
			execute("DELETE FROM seat WHERE hall_ID = " + h.id + ";");
		}
		execute("DELETE FROM hall WHERE cinema_ID = " + c.id + ";");
	}

	// Removes all seats reserved in within the hall and then proceeds to delete the hall
	public void removeHall(Hall h) throws SQLException {
		execute("DELETE FROM seat WHERE hall_ID = " + h.id + ";");
		execute("DELETE FROM hall WHERE ID = " + h.id + ";");
	}

	// Gets all halls from the database
	public Vector<Hall> getAllHalls() throws SQLException {
		return ResultSetHelper.toHalls(executeQ("SELECT * FROM cinema.hall;"));
	}

	// Gets all halls from a specific cinema
	public Vector<Hall> getHallsFromCinema(Cinema c) throws SQLException {
		return ResultSetHelper.toHalls(executeQ("SELECT * FROM hall WHERE cinema_ID = " + c.id + ";"));
	}

	// Creates a hall in the database
	public void addHall(Hall h) throws SQLException {
		if (!containsIllegalChar(h.movie)) {
			synchronized(addHall) {
				String movie = h.movie;
				int rows = h.rows;
				int coloumns = h.coloumns;
				int cID = h.cinemaID;


				addHall.setInt(1, rows);
				addHall.setInt(2, coloumns);
				addHall.setInt(3, cID);
				addHall.setString(4, movie);
				addHall.execute();
			}
		} else System.out.println("Error: Illegal char");
	}

	// Creates a reservation ID/login in the database
	public void addLogin(Reservation r) throws SQLException {
		execute("INSERT INTO reservation VALUES (DEFAULT, " + r.phoneNumber + ", \"" + r.password + "\");");
	}

	// Checks if the login exists in the database. Returns null if it doesn't exist, otherwise returns the same reservation
	public Reservation getLogin(Reservation r) throws SQLException {
		Vector<Reservation> reservations = ResultSetHelper.toReservations(executeQ("SELECT * FROM reservation;"));
		for (Reservation res: reservations) {
			if (r.phoneNumber == res.phoneNumber && r.password.equals(res.password)) {
				return res;
			}
		}
		return null;
	}

	// Checks if a login with the given phone number already exists in the database. Returns null if it doesn't exist
	public Reservation checkLoginValid(Reservation r) throws SQLException {
		Vector<Reservation> reservations = ResultSetHelper.toReservations(executeQ("SELECT * FROM reservation;"));
		for (Reservation res: reservations) {
			if (r.phoneNumber == res.phoneNumber) {
				return res;
			}
		}
		return null;
	}

	// Gets a specific hall, given the ID
	public Hall getHall(int hID) throws SQLException {
		Vector<Hall> hall = ResultSetHelper.toHalls(executeQ("SELECT * FROM hall WHERE ID = " + hID + ";"));
		return hall.get(0);
	}

	// Creates a reservation as a seat, given the reservation ID, hall ID and a string, which should consist of an even length and every other index is a matching set of the row and column of the seats
	public void reserveSeats(Reservation r, int hID, String seats) throws SQLException {
		Hall h = getHall(hID);

		String[] args = seats.split(",");
		for(int i = 0; i < args.length*0.5; i++) {
			execute("INSERT INTO seat VALUES (" + Integer.parseInt(args[(i*2)+1]) + ", " + Integer.parseInt(args[i*2]) + ", " + r.id + ", " + h.id + ");");
		}


	}

	// Gets all reserved seats from a given hall, given a hall ID
	public Vector<Seat> getSeatsFromHall(int hID) throws SQLException {
		Hall h = getHall(hID);
		return ResultSetHelper.toSeats(executeQ("SELECT * FROM seat WHERE hall_ID = " + h.id + ""));
	}

	// Deletes all reserved seats, which are connected to the reservation ID
	public void deleteReservation(Reservation r) throws SQLException {
		Reservation res = getLogin(r);
		execute("DELETE FROM seat WHERE reservation_ID = " + res.id + ";");

	}

	public boolean containsIllegalChar(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '%' || s.charAt(i) == ',') return true;
		}
		return false;
	}
}

// This class gets the login for the database, which is contained in a .txt file
class SQLLogin {
	private static final File LOGIN_FILE = new File(System.getProperty("user.home") + File.separator + "SQLLogins" + File.separator + "bio.txt");

	private String username;
	private String password;

	public SQLLogin() throws FileNotFoundException, IOException {
		System.out.println("Looking SQL login at: " + LOGIN_FILE.getAbsolutePath());

		try(
				BufferedReader loginReader = new BufferedReader(new FileReader(LOGIN_FILE));
				){
			username = loginReader.readLine();
			password = loginReader.readLine();
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}