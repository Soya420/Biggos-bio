package bioprojekt.server;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable, Closeable {

	Socket socket;
	Server server;
	int clientHandlerNumber;

	public ClientHandler(Server server, Socket socket, int clientHandlerNumber) {
		this.server = server;
		this.socket = socket;
		this.clientHandlerNumber = clientHandlerNumber;

		new Thread(this, "Client handler " + clientHandlerNumber).start();
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}

	@Override
	public void run() {
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream ps = new PrintStream(socket.getOutputStream());
				){
			
			//ps.println("You have connected!");

			while(!server.getStop()) {
				
				String strLine = br.readLine();
				System.out.println(strLine);
				if (strLine == null) {
					Thread.sleep(100);
					continue;
				}
				String response = server.handleMessage(strLine);
				System.out.println(response);
				ps.println(response);
			}

		} catch (IOException e) {
			System.out.println("Client " + clientHandlerNumber + " disconnected");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!socket.isClosed()) {
			try {
				close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}