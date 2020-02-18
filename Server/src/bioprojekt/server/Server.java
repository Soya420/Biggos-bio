package bioprojekt.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import bioprojekt.Main;
import bioprojekt.database.Cinema;

public class Server implements Runnable, Closeable{

	private boolean stop;

	private ServerSocket serverSocket;

	private List<ClientHandler> clients;

	public Server() throws Exception {
		serverSocket = new ServerSocket(8777);

		clients = new ArrayList<>();

		new Thread(this, "ServerThread").start();

		/*PrintStream ps = new PrintStream(socket.getOutputStream(), false, Charset.forName("UTF-8"));
		ps.println("det virker");

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println(br.readLine());*/
	}

	@Override
	public void run() {
		while(!stop) {
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

	public String handleMessage(String input) throws SQLException {
		String[] args = input.split(" ");

		switch(args[0]) {
		case("g"):
			switch(args[1]) {
			case("cinemas"):
				Vector<Cinema> cinemas = Main.sqlh.getAllCinemas();
			String cResponse = "";
			for(Cinema c: cinemas) {
				cResponse += c.name + "," + c.id + ";";
			}
			return cResponse;

			case("halls"):
				
				String hResponse = "";
			
				return hResponse;
			}
		case("r"):

			break;
		case("c"):

			break;
		}
		return "";
	}

}