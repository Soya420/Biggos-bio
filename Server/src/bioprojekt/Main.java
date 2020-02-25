package bioprojekt;
import java.io.IOException;

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
	public static Applet applet;

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> applet = new Applet());

		server = new Server();


	}

	public static void exit() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (applet.getSQLHandler() != null) {
			applet.getSQLHandler().close();
		}
		
		System.exit(0);
	}
	
	//hej

}
