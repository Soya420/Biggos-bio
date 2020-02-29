package bioprojekt;
import java.io.IOException;

import javax.swing.SwingUtilities;

import bioprojekt.server.Server;
import bioprojekt.ui.Applet;

public class Main {
	public static Server server;
	public static Applet applet;

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> applet = new Applet());

		server = new Server();


	}
	
	// Exit closes the program properly, by first closing the server and the SQLHandler before terminating the program
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

}
