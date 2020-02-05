import java.net.Socket;
import java.io.*;

class ServerHandler {
  String startData;
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
    finally {
      try {
        ps.close();
        br.close();
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
    startData = new String("");
  }

  void getStartData() {
    ps.println(startData);
  }
  
  void search() {
    
  }
  
  void reserve() {
    
  }
}
