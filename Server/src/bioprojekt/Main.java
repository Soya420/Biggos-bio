package bioprojekt;
import bioprojekt.database.Cinema;
import bioprojekt.database.SQLHandler;
import bioprojekt.server.Server;

public class Main {
	public static Server server;
	static SQLHandler sqlh;
	
	public static void main(String[] args) throws Exception {
		server = new Server();
		
		sqlh = new SQLHandler();
	}
	
	public static void exit() {
		if (sqlh != null) {
			sqlh.close();
		}
		System.exit(0);
	}

}
