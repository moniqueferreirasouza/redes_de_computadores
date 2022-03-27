//
//  TCPServer.java
//
//  Kurose & Ross
//
import java.io.*;
import java.net.*;

class TCPServer extends Thread{

	public Socket s;

    public static void main (String args[]) throws Exception {
		// throws Exception here because don't want to deal
		// with errors in the rest of the code for simplicity.
		// (Note: NOT a good practice).
		
         //Welcome socket  ---- SOCKET 1
		 ServerSocket serverSocket = new ServerSocket(9000);
		 // waits for a new connection. Accepts connection from multiple clients
		 while (true) {
			 System.out.println("Waiting for connection at port 9000.");
             //Connection socket  --- SOCKET 2
			 Socket s = serverSocket.accept();
			 System.out.println("Connection established from " + s.getInetAddress());
			 
			 TCPServer server = new TCPServer(s);
			 server.start();

		 }
    }
    
    public TCPServer (Socket s){
    	this.s = s;
    }
    public void run() {
    	try {
		 // create a BufferedReader object to read strings from
		 // the socket. (read strings FROM CLIENT)
		 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		 String input = br.readLine();
		 
		 //create output stream to write to/send TO CLINET
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
		 
		 // keep repeating until an empty line is read.
		 while (input.compareTo("tchau") != 0) {
			 // convert input to upper case and echo back to
			 // client.
			 output.writeBytes(input.toUpperCase() + "\n");
			 input = br.readLine();
		}
		// close current connection
		s.close();
    	} catch (Exception e) {e.printStackTrace();}
    }
}
