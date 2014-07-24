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
import java.util.ArrayList;
import java.util.List;

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
	public ClientInputThread inputThread;
	
	/* The client listeners */
	public List<ClientListener> listeners;
	
	/* The default constructor */
	public Client() {
		//Assign the variables
		this.listeners = new ArrayList<ClientListener>();
	}
	
	/* The method used to connect to a server given its ip and port */
	public void connect(String ip, int port) {
		//Catch any errors
		try {
			//Connect to the server
			this.socket = new Socket(ip, port);
			//Assign the variables
			this.in = new DataInputStream(this.socket.getInputStream());
			this.out = new DataOutputStream(this.socket.getOutputStream());
			//Call connected() in the listeners
			this.callConnected(ip, port);
			//Start the input thread
			this.inputThread = new ClientInputThread(this);
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
			//Call disconnecting() in the listeners
			this.callDisconnecting();
			//Set 'closing' to true
			this.inputThread.closing = true;
			//Close all of the streams and sockets
			this.in.close();
			this.out.close();
			this.socket.close();
			//Wait for the thread to close
			this.inputThread.join();
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
	
	/* The method used to add a listener */
	public void addListener(ClientListener listener) {
		//Add the listener to the list of listeners
		this.listeners.add(listener);
	}
	
	/* The method used to call connected() in all of the listeners */
	public void callConnected(String ip, int port) {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).connected(ip, port);
	}
	
	/* The method used to call messageRecieved() in all of the listeners */
	public void callMessageRecieved(String message) {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).messageReceived(message);
	}
	
	/* The method used to call disconnecting() in all of the listeners */
	public void callDisconnecting() {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).disconnecting();
	}
	
}