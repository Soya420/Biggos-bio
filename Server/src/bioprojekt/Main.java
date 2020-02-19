package bioprojekt;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bioprojekt.database.Cinema;
import bioprojekt.database.Hall;
import bioprojekt.database.SQLHandler;
import bioprojekt.server.Server;
import bioprojekt.ui.Applet;

public class Main {
	public static Server server;
	public static SQLHandler sqlh;
	public static Applet applet;
	
	public static void main(String[] args) throws Exception {
		
		SwingUtilities.invokeLater(() -> applet = new Applet());
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
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
