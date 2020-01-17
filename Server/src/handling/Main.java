package handling;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import server.Server;

public class Main {
	public static Server server;
	
	public static void main(String[] args) throws Exception {
		server = new Server();
		
	}

}
