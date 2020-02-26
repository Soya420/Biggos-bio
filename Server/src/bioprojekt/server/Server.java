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
import bioprojekt.database.Hall;
import bioprojekt.database.Reservation;
import bioprojekt.database.Seat;

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
	
	public String[] splitString(String[] s) {
		  //splitter en for at se hvor mange værdier der er
		  String[] t = s[0].split(",");
		  String[] split = new String[s.length*t.length];


		  for (int i = 0; i < s.length; i++) {
		    String[] temp = s[i].split(",");

		    for (int j = 0; j < temp.length; j++) {
		      //lægger alle String[] sammen til én
		      split[(i*t.length)+j] = temp[j];
		    }
		  }

		  return split;
		}

	public String handleMessage(String input) throws SQLException {
		String[] args = input.split("%");

		switch(args[0]) {
		case("g"):
			switch(args[1]) {
			case("cinemas"):
				Vector<Cinema> cinemas = Main.applet.getSQLHandler().getAllCinemas();
			String cResponse = "cinemas%";
			for(Cinema c: cinemas) {
				cResponse += c.name + "," + c.id + ";";
			}
			return cResponse;

			case("halls"):

				String hResponse = "halls%";
			Vector<Hall> halls = Main.applet.getSQLHandler().getHallsFromCinema(new Cinema (Integer.parseInt(args[2]), args[3]));
			for(Hall h: halls) {
				hResponse +=  h.movie + "," + h.id + ";";
			}
			return hResponse;

			case("seats"):

				String sResponse = "seats%";
			
			Hall h = Main.applet.getSQLHandler().getHall(Integer.parseInt(args[2]));
			sResponse += h.coloumns + "%" + h.rows + "%";
			
			Vector<Seat> seats = Main.applet.getSQLHandler().getSeatsFromHall(Integer.parseInt(args[2]));
			for(Seat s: seats) {
				sResponse +=  s.cNumber + "," + s.rNumber + "," + s.rID + ";";
			}
			
			return sResponse;
			}
		case("r"):

			String rResponse = "reservation%";
		Reservation r = Main.applet.getSQLHandler().getLogin(new Reservation(Integer.parseInt(args[1]), args[2]));
		if (r != null) {
			Main.applet.getSQLHandler().reserveSeats(r, Integer.parseInt(args[3]));
			rResponse += "Reservation made";
		} else {
			rResponse += "Wrong login credentials";
		}
		return rResponse;
		case("c"):

			String cResponse = "createUser%";
		if (Main.applet.getSQLHandler().checkLoginValid(new Reservation(Integer.parseInt(args[1]), args[2])) == null) {
			Main.applet.getSQLHandler().addLogin(new Reservation(Integer.parseInt(args[1]), args[2]));
			cResponse += "User was created";
		} else {
			cResponse += "Phonenumber already in use";
		}

		return cResponse;
		}
		return "";
	}

}