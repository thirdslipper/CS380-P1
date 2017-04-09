import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException{
		try (Socket socket = new Socket("codebank.xyz", 38001)) {
			System.out.println("Server Test");
		}
	}
}
