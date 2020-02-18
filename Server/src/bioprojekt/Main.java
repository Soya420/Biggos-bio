package bioprojekt;
import bioprojekt.database.Cinema;
import bioprojekt.database.Hall;
import bioprojekt.database.SQLHandler;
import bioprojekt.server.Server;

public class Main {
	public static Server server;
	public static SQLHandler sqlh;
	
	public static void main(String[] args) throws Exception {
		
		Cinema c = new Cinema(100, "Værløse");
		Hall h = new Hall(100, 10, 10, 2, "100tyve");
		
		server = new Server();
		
		sqlh = new SQLHandler();
		
		sqlh.addCinema(c);
		sqlh.addHall(h);
		
	}
	
	public static void exit() {
		if (sqlh != null) {
			sqlh.close();
		}
		System.exit(0);
	}

}
