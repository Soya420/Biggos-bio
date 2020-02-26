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

public class SQLHandler {

	public static final String addHallString = "INSERT INTO hall VALUES (DEFAULT, ?, ?, ?, ?);";
	public static final String addCinemaString = "INSERT INTO cinema VALUES (DEFAULT, ? );";


	private Connection connection;
	private PreparedStatement addHall, addCinema;

	public SQLHandler(){
		try {
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

	public ResultSet executeQ(String s) throws SQLException{
		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery(s);
		return rs;
	}

	public void execute(String s) throws SQLException{
		Statement stmt = connection.createStatement();
		stmt.execute(s);
	}

	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<Cinema> getAllCinemas() throws SQLException {
		return ResultSetHelper.toCinemas(executeQ("SELECT * FROM cinema.cinema;"));
	}

	public void addCinema(Cinema c) throws SQLException {
		synchronized(addCinema) {
			String search = c.name;

			addCinema.setString(1, search);
			addCinema.execute();
		}
	}

	public void removeCinema(Cinema c) throws SQLException {
		execute("DELETE FROM cinema WHERE ID = " + c.id + ";");
	}

	public void removeAllHallsFromCinema(Cinema c) throws SQLException {
		execute("DELETE FROM hall WHERE cinema_ID = " + c.id + ";");
	}

	public void removeHall(Hall h) throws SQLException {
		execute("DELETE FROM hall WHERE ID = " + h.id + ";");
	}

	public Vector<Hall> getAllHalls() throws SQLException {
		return ResultSetHelper.toHalls(executeQ("SELECT * FROM cinema.hall;"));
	}

	public Vector<Hall> getHallsFromCinema(Cinema c) throws SQLException {
		return ResultSetHelper.toHalls(executeQ("SELECT * FROM hall WHERE cinema_ID = " + c.id + ";"));
	}

	public void addHall(Hall h) throws SQLException {
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
	}

	public void addLogin(Reservation r) throws SQLException {
		execute("INSERT INTO reservation VALUES (DEFAULT, " + r.phoneNumber + ", \"" + r.password + "\");");
	}

	public Reservation getLogin(Reservation r) throws SQLException {
		Vector<Reservation> reservations = ResultSetHelper.toReservations(executeQ("SELECT * FROM reservation;"));
		for (Reservation res: reservations) {
			//System.out.println(res.phoneNumber);
			//System.out.println(res.password);
			if (r.phoneNumber == res.phoneNumber && r.password.equals(res.password)) {
				return res;
			}
		}
		//System.out.println(r.phoneNumber);
		//System.out.println(r.password);
		return null;
	}

	public Reservation checkLoginValid(Reservation r) throws SQLException {
		Vector<Reservation> reservations = ResultSetHelper.toReservations(executeQ("SELECT * FROM reservation;"));
		for (Reservation res: reservations) {
			if (r.phoneNumber == res.phoneNumber) {
				return res;
			}
		}
		return null;
	}

	public Hall getHall(int hID) throws SQLException {
		Vector<Hall> hall = ResultSetHelper.toHalls(executeQ("SELECT * FROM hall WHERE ID = " + hID + ";"));
		return hall.get(0);
	}

	public void reserveSeats(Reservation r, int hID, String seats) throws SQLException {
		Hall h = getHall(hID);
		
		String[] args = seats.split(",");
		for(int i = 0; i < args.length*0.5; i++) {
			execute("INSERT INTO seat VALUES (" + Integer.parseInt(args[(i*2)+1]) + ", " + Integer.parseInt(args[i*2]) + ", " + r.id + ", " + h.id + ");");
		}
		
		
	}

	public Vector<Seat> getSeatsFromHall(int hID) throws SQLException {
		Hall h = getHall(hID);
		return ResultSetHelper.toSeats(executeQ("SELECT * FROM seat WHERE hall_ID = " + h.id + ""));
	}
	
	public void deleteReservation(Reservation r) throws SQLException {
		Reservation res = getLogin(r);
		execute("DELETE FROM seat WHERE reservation_ID = " + res.id + ";");
		
	}
}

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