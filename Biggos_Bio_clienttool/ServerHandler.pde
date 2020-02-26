import java.net.Socket; //<>//
import java.io.*;

class ServerHandler {
  BufferedReader br;
  PrintStream ps;
  Socket socket;

  ServerHandler() {
    try {
      //socket = new Socket("192.168.43.71", 8777);
      socket = new Socket("127.0.0.1", 8777);
      ps = new PrintStream(socket.getOutputStream());
      br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  void getData(String table) {
    String data = new String("g%");

    data += table;

    ps.println(data);


    //respons
    String message;

    try {
      while ((message = br.readLine()) == null) {
        //venter på respons
      }

      println(message);

      String[] args = message.split("%");

      switch(args[0]) {
      case "cinemas":

        //biografer
        if (args.length < 2) {
          String[] error = {"Ingen biografer"};
          biograf_dropList.setItems(error, biograf_dropList.getSelectedIndex());
        } else {
          String[] cinemas = args[1].split(";");
          String[] cValues = splitString(cinemas);

          allCinemas = new Cinema[cinemas.length];

          for (int i = 0; i < cValues.length; i += 2) {
            allCinemas[int(i*0.5)] = new Cinema(cValues[i], int(cValues[i+1]));
          }

          String[] names = new String[allCinemas.length];
          for (int i = 0; i < allCinemas.length; i++) {
            names[i] = allCinemas[i].name;
          }

          biograf_dropList.setItems(names, biograf_dropList.getSelectedIndex());
        }
        break;

      case "halls":
        //film og sal

        if (args.length < 2) {
          String[] error = {"Ingen film i biograf"};
          film_dropList.setItems(error, film_dropList.getSelectedIndex());
        } else {
          String[] halls = args[1].split(";");
          String[] hValues = splitString(halls);

          allHalls = new Hall[halls.length];

          for (int i = 0; i < hValues.length; i += 2) {
            allHalls[int(i*0.5)] = new Hall(hValues[i], int(hValues[i+1]));
          }

          String[] movies = new String[allHalls.length];
          for (int i = 0; i < allHalls.length; i++) {
            movies[i] = allHalls[i].movie;
          }

          film_dropList.setItems(movies, film_dropList.getSelectedIndex());
        }
        break;
      case "seats":

        currentHall = new Hall(int(args[1]), int(args[2]));
        currentHall.createSeats();

        if (args.length > 3) {

          String[] seats = args[3].split(";");
          String[] sValues = splitString(seats);

          for (int i = 0; i < seats.length; i++) {
            currentHall.seats[int(sValues[i*2])][int(sValues[i*2+1])].status = 2;
          }
        }

        //G4P laver en bug hvis man scroller i droplisten
        //så den skal kun opdatere hvis den ikke er blevet kaldt fra billet droplisten
        if (seatsUpdated) {
          String[] tickets = new String[currentHall.cols];
          for (int i = 0; i < tickets.length; i++) tickets[i] = (i+1)+"";

          billetter_dropList.setItems(tickets, 0);
        }

        currentHall.findEmptySeats();

        break;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }


  boolean reserve(String username, String password) {
    String login = new String("r%");

    login += username + "%";

    login += password + "%";

    login += allHalls[film_dropList.getSelectedIndex()].ID + "%";

    for (int i = 0; i < currentHall.selected.length; i++) {
      login += currentHall.selected[i].colNum + "," + +currentHall.selected[i].rowNum + ",";
    }


    ps.println(login);


    //respons
    String message;

    try {
      while ((message = br.readLine()) == null) {
        //venter på respons
      }

      println(message);

      String[] args = message.split("%");

      if (args[0].equals("reservation")) {
        if (args[1].equals("Reservation made")) return true;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  boolean cancel(String username, String password) {
    String login = new String("u%");

    login += username + "%";

    login += password;

    ps.println(login);


    //respons
    String message;

    try {
      while ((message = br.readLine()) == null) {
        //venter på respons
      }

      println(message);

      String[] args = message.split("%");

      if (args[0].equals("undoReservation")) {
        if (args[1].equals("Reservation deleted")) return true;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }


  boolean createUser(String username, String password) {
    String createUser = new String("c%");

    createUser += username + "%";

    createUser += password;

    ps.println(createUser);

    //respons
    String message;

    try {
      while ((message = br.readLine()) == null) {
        //venter på respons
      }

      println(message);

      String[] args = message.split("%");

      if (args[0].equals("createUser")) {
        if (args[1].equals("User was created")) return true;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
}
