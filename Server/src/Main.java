import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket( 8777 );
		Socket s = ss.accept();
		
		PrintStream ps = new PrintStream( s.getOutputStream(), false, Charset.forName("UTF-8") );
		ps.println("det virker");
		
		BufferedReader br = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
		System.out.println(br.readLine());
	}

}
