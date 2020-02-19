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
import java.util.Vector;

import bioprojekt.util.ResultSetHelper;

public class SQLHandler {


	private Connection connection;

	public SQLHandler(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			SQLLogin login = new SQLLogin();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?verifyServerCertificate=false&useSSL=true", login.getUsername(), login.getPassword());
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
		if (c.id == -1) {
			execute("INSERT INTO cinema VALUES (DEFAULT, \"" + c.name + "\");");
		} else {
			execute("INSERT INTO cinema VALUES (" + c.id + ", \"" + c.name + "\");");
		}
	}
	
	public Vector<Hall> getAllHalls() throws SQLException {
		return ResultSetHelper.toHalls(executeQ("SELECT * FROM cinema.cinema;"));
	}
	
	public void addHall(Hall h) throws SQLException {
		if (h.id == -1) {
			execute("INSERT INTO hall VALUES (DEFAULT, " + h.rows + ", " + h.coloumns + ", " + h.cinemaID + ", \"" + h.movie + "\");");
		} else {
			execute("INSERT INTO hall VALUES (" + h.id + ", " + h.rows + ", " + h.coloumns + ", " + h.cinemaID + " \"" + h.movie + "\");");
		}
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