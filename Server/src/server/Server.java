package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

import handling.ClientHandler;

public class Server {
	
	private ServerSocket serverSocket;
	
	private List<ClientHandler> clients;
	
	public Server() throws Exception {
		serverSocket = new ServerSocket( 8777 );
		Socket socket = serverSocket.accept();
		
		PrintStream ps = new PrintStream( socket.getOutputStream(), false, Charset.forName("UTF-8") );
		ps.println("det virker");
		
		BufferedReader br = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		System.out.println(br.readLine());
	}
	
}
