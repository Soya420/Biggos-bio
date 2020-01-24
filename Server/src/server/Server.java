package server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import handling.ClientHandler;

public class Server implements Runnable, Closeable{
	
	private boolean stop;
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	private List<ClientHandler> clients;
	
	public Server() throws Exception {
		serverSocket = new ServerSocket(8777);
		socket = serverSocket.accept();
		
		clients = new ArrayList<>();
		
		new Thread("ServerThread").start();
		
		/*PrintStream ps = new PrintStream(socket.getOutputStream(), false, Charset.forName("UTF-8"));
		ps.println("det virker");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println(br.readLine());*/
	}

	@Override
	public void run() {
		try {
			clients.add(new ClientHandler(this, serverSocket.accept(), clients.size()));
			System.out.println("added new client to client list");
		}catch (IOException e) {
			if(stop == true) {
				return;
			}
			e.printStackTrace();
		}
		
	}
	
	public boolean getStop() {
		return stop;
	}
	
	public void requestRemoval(ClientHandler c) {
		clients.remove(c);
	}

	@Override
	public void close() throws IOException {
		stop = true;
		
		serverSocket.close();
		
		for(ClientHandler c : clients) {
			c.close();
		}
		
	}
	
}