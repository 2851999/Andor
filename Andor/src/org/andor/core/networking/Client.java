/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class Client {
	
	/* The client's socket */
	public Socket socket;
	
	/* The client's input stream */
	public DataInputStream in;
	
	/* The client's output stream */
	public DataOutputStream out;
	
	/* The client's input thread */
	public Thread inputThread;
	
	/* The constructor */
	public Client(String ip, int port) {
		//Catch any errors
		try {
			//Connect to the server
			this.socket = new Socket(ip, port);
			//Assign the variables
			this.in = new DataInputStream(this.socket.getInputStream());
			this.out = new DataOutputStream(this.socket.getOutputStream());
			//Start the input thread
			this.inputThread = new Thread(new ClientInputThread(this));
			this.inputThread.start();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Client", "Failed to connect to the server", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to send the server a message */
	public void send(String message) {
		//Catch any errors
		try {
			//Send the message
			this.out.writeUTF(message);
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Client sendMessage()", "Failed to send the server the message " + message, Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to disconnect the client */
	public void disconnect() {
		//Catch any errors
		try {
			//Stop the input thread
			this.inputThread.join();
			//Close all of the streams and sockets
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Client disconnect()", "Failed to disconnect the client", Log.ERROR);
			e.printStackTrace();
		} catch (InterruptedException e) {
			//Log an error
			Logger.log("Andor - Client disconnect()", "Failed to stop the input thread for the client", Log.ERROR);
			e.printStackTrace();
		}
	}
	
}