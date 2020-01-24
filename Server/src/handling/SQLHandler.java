package handling;

import java.sql.*;
import java.io.*;

public class SQLHandler {
	
	
	private Connection connection;
	
	public SQLHandler(){
		try {
		Class.forName("com.mysql.jdbc.Driver");
		SQLLogin login = new SQLLogin();
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", login.getUsername(), login.getPassword());
		}catch(FileNotFoundException e) {
			System.out.println("Could't find login file");
		}catch(SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
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