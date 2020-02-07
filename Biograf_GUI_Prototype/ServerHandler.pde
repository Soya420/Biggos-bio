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

  void reserve(String username, String password) {
    String login = new String("r ");

    login += username + " ";

    login += password;

    ps.println(login);
  }

  void createLogin(String username, String password) {
    String createLogin = new String("c ");

    createLogin += username + " ";

    createLogin += password;

    ps.println(createLogin);
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
