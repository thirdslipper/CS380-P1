/**
 * Author: Colin Koo
 * Professor: Davarpanah
 * Description: This program emulates a chat client to a server.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ChatClient{
	/**
	 * This method connects to the public codebank.xyz server at port 38001, representing our class'.
	 * It writes to the server stream through a Socket, and a PrintWriter, reading from the ServerSocket stream
	 * with the same Socket, and BufferedReader.
	 * The first input the user makes represents their user name, and the program continues depending on 
	 * whether or not the name is available.  If the user name is available, every message the user types
	 * 
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException{

		try (Socket socket = new Socket("codebank.xyz", 38001)){
			System.out.println("Connected to: " + socket.getInetAddress() + ":" + socket.getPort());

			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			BufferedReader br = new BufferedReader
					(new InputStreamReader(socket.getInputStream(), "UTF-8"));	


			Scanner kb = new Scanner(System.in);
			System.out.print("Enter a user name: ");
			String input = kb.nextLine();
			pw.println(input);

			if (!(br.ready() && br.readLine().equals("Name in use."))){
				new Listener(socket, br).start();
//				while (!input.toLowerCase().equals("exit")){
				while (true){
					pw.flush();
					input = kb.nextLine();
					pw.println(input);
				}
			}
			kb.close();
		}
	}

	/**
	 * This nested class is a thread that runs conditionally in the main method of ChatClient.
	 * If the user enters a free user name, then the Listener thread starts, waiting for either a client
	 * to submit responses to the server to display.
	 * @author Colin
	 *
	 */
	public static class Listener extends Thread implements Runnable{
		Socket socket;
		BufferedReader br;
		public Listener(Socket socket, BufferedReader br){
			this.socket = socket;
			this.br = br;
		}
		public void run(){
			while (true){
				try {
					if (br.ready()){
						System.out.println(br.readLine());
					}
				} catch (Exception E){
					E.printStackTrace();
				}
			}
		}
	}
}


