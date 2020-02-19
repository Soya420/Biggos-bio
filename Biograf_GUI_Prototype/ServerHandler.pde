import java.net.Socket;
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
    //finally {
    //  try {
    //    ps.close();
    //    br.close();
    //  }
    //  catch(IOException e) {
    //    e.printStackTrace();
    //  }
    //}
  }

  void getData(String table) {
    String data = new String("g ");

    data += table;

    ps.println(data);
  }

  void reserve(int username, String password) {
    String login = new String("r ");

    login += username + " ";

    login += password;

    ps.println(login);
  }

  void createUser(int username, String password) {
    String createUser = new String("c ");

    createUser += username + " ";

    createUser += password;

    ps.println(createUser);
  }

  void handleMessages() {
    String message;

    try {
      while ((message = br.readLine()) != null) {
        println(message);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
