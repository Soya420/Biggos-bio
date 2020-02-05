package bioprojekt;
import bioprojekt.database.Cinema;
import bioprojekt.database.SQLHandler;

public class Main {
	//public static Server server;
	static SQLHandler sqlh;
	
	public static void main(String[] args) throws Exception {
		//server = new Server();
		
		sqlh = new SQLHandler();
		Cinema c = new Cinema("hej", 2);
		sqlh.addCinema(c);
	}
	
	public static void exit() {
		if (sqlh != null) {
			sqlh.close();
		}
		System.exit(0);
	}

}
