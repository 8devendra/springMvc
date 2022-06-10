package in.ameya.restController;

import java.io.*;
import java.net.*;

//http://www.bogotobogo.com/Java/tutorial/tcp_socket_server_client.php
public class Tcp {
  public void run() {
	try {
		
		
		int serverPort = 4020;
		InetAddress host = InetAddress.getByName("192.168.1.182"); 
		Socket socket = new Socket(host,serverPort); 
		PrintWriter toServer = 
			new PrintWriter(socket.getOutputStream(),true);
		BufferedReader fromServer = 
			new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		 
		toServer.println("Devendra".toString());
		//response from server
		String line = fromServer.readLine();
		System.out.println("Response From Server-->" + line);
		
		toServer.close();
		fromServer.close();
		socket.close();
		
	}
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }
	
  public static void main(String[] args) {
	  Tcp client = new Tcp();
		client.run();
		
  }
}