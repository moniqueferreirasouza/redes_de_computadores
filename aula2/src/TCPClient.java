//
//  TCPClient.java
//  Kurose & Ross
//

import java.io.*;
import java.net.*;

public class TCPClient {

	public static void main (String args[]) throws Exception {
		// throws Exception here because don't want to deal
		// with errors in the rest of the code for simplicity.
		// (note: NOT a good practice!)
		// Connect to the server process running at localhost:9000
		Socket s = new Socket("localhost", 9000);
	    // The next 2 lines create a output stream we can
		// write to.  (To write TO SERVER)
		OutputStream os= s.getOutputStream();
		DataOutputStream serverWriter = new DataOutputStream(os);
		// The next 2 lines create a buffer reader that
		// reads from the standard input. (to read stream FROM SERVER)
		InputStreamReader isrServer = new InputStreamReader(s.getInputStream());
		BufferedReader serverReader = new BufferedReader(isrServer);
        //create buffer reader to read input from user. Read the user input to string 'sentence'
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String sentence;  
        sentence = inFromUser.readLine();
        // keep repeating until an empty line is read.
		while (sentence.compareTo("tchau") != 0) {
           // Send a user input to server
           serverWriter.writeBytes(sentence +"\n");
		   // Server should convert to upper case and reply.
		   // Read server's reply below and output to screen.
           String response = serverReader.readLine();
		   System.out.println(response);
           //read user input again
           sentence = inFromUser.readLine();
        }
		// Send an empty line to server to end communication.
		serverWriter.writeBytes("tchau"+"\n");
		//Close the socket
		s.close();
	}
}
