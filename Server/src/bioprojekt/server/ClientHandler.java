package bioprojekt.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable, Closeable {
	
	Socket socket;
	Server server;
	
	public ClientHandler(Server server, Socket socket, int clientHandlerNumber) {
		this.server = server;
		this.socket = socket;
		
		new Thread("Client handler " + clientHandlerNumber).start();
	}
	
	public Socket getSocket() {
		return socket;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		
		
	}
}