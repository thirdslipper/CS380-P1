import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ChatClient extends Thread implements Runnable{
	public static void main(String[] args) throws UnknownHostException, IOException{


		try (Socket socket = new Socket("codebank.xyz", 38001)){
			System.out.println("Connected to: " + socket.getInetAddress() + ": " + socket.getPort());
			
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			BufferedReader br = new BufferedReader
					(new InputStreamReader(socket.getInputStream(), "UTF-8"));	
			
			
			Scanner kb = new Scanner(System.in);
			System.out.print("Enter a user name: ");
			String username = kb.next();
			
			pw.println(username);	//write to output

			String input = "";
			if (br.readLine().equals("Name in use.")){
				System.out.println(br.readLine());
			}
/*			else{
				while (!input.toLowerCase().equals("exit")){
					pw.flush();
					
					input = kb.nextLine();
					pw.write(input);
					System.out.println(br.readLine());
				}
			}*/
		}
	}
	
/*	public class Listener extends Thread implements Runnable{
		Socket socket;
		BufferedReader br;
		public Listener(Socket socket, BufferedReader br){
			this.socket = socket;
			this.br = br;
		}
		public void run(){
			
		}
	}*/
}

