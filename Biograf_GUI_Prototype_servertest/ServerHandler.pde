import java.net.Socket; //<>//
import java.io.*;

class ServerHandler {
  BufferedReader br;
  PrintStream ps;
  Socket socket;

  ServerHandler() {
    try {
      socket = new Socket("192.168.43.71", 8777);
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
        String[] cinemas = args[1].split(";");
        biograf_dropList.setItems(cinemas, biograf_dropList.getSelectedIndex());
        break;

      case "halls":
        //film og sal
        if (args.length < 2) {
          String[] error = {"Ingen film i biograf"};
          film_dropList.setItems(error, film_dropList.getSelectedIndex());
        } else {
          String[] halls = args[1].split(";");

          String[] hValues = new String[halls.length*2];
          
          for (int i = 0; i < halls.length; i++) {
            String[] s = halls[i].split(",");
            
            for (int j = 0; j < s.length; j++) {
              hValues[i+j] = s[j];
            }
          }
          
          for (int i = 0; i < hValues.length; i++) {
            println(hValues[i]);
          }

          film_dropList.setItems(halls, film_dropList.getSelectedIndex());

          break;
        }
      case "seats":

        //currentHall = new Hall(3, 3);
        //currentHall.createSeats();
        break;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  void reserve(int username, String password) {
    String login = new String("r%");

    login += username + "%";

    login += password;

    ps.println(login);
  }

  void createUser(String username, String password) {
    String createUser = new String("c%");

    createUser += username + "%";

    createUser += password;

    ps.println(createUser);
  }
}
