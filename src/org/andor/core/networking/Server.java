/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class Server {
	
	/* The servers socket */
	public ServerSocket socket;
	
	/* The clients on this server */
	public List<ServerClient> clients;
	
	/* The server connection thread */
	public ServerConnectionThread connectionThread;
	
	/* The port to use */
	public int port;
	
	/* The server listeners */
	public List<ServerListener> listeners;
	
	/* The default constructor */
	public Server() {
		//Assign the variables
		this.listeners = new ArrayList<ServerListener>();
	}
	
	/* The method used to start this server on a certain port */
	public void start(int port) {
		//Assign the port
		this.port = port;
		//Catch any errors
		try {
			//Assign the variables
			this.clients = new ArrayList<ServerClient>();
			//Setup the sockets
			this.socket = new ServerSocket(this.port);
			//Start the server connection thread
			this.connectionThread = new ServerConnectionThread(this);
			this.connectionThread.start();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Server", "Failed to setup the server", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to send all the clients a message */
	public void sendAll(String message) {
		//Go through all of the clients
		for (int a = 0; a < this.clients.size(); a++)
			//Send the current client the message
			this.clients.get(a).send(message);
	}
	
	/* The method used to stop this server */
	public void stop() {
		//Catch any errors
		try {
			//Call serverStopping() in the listeners
			this.callServerStopping();
			//Set 'closing' to true
			this.connectionThread.closing = true;
			//Disconnect all of the clients
			for (int a = 0; a < this.clients.size(); a++)
				//Disconnect the current client
				this.clients.get(a).disconnect();
			//Close this server's socket
			this.socket.close();
			//Wait for the thread to close
			this.connectionThread.join();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Client disconnect()", "Failed to close the server's socket", Log.ERROR);
			e.printStackTrace();
		} catch (InterruptedException e) {
			//Log an error
			Logger.log("Andor - Client disconnect()", "Failed to stop the input thread for the client", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to add a listener */
	public void addListener(ServerListener listener) {
		//Add the listener to the list of listeners
		this.listeners.add(listener);
	}
	
	/* The method used to call serverStarted() in all of the listeners */
	public void callServerStarted() {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).serverStarted();
	}
	
	/* The method used to call clientConnected() in all of the listeners */
	public void callClientConnected(ServerClient client) {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).clientConnected(client);
	}
	
	/* The method used to call messageRecieved() in all of the listeners */
	public void callMessageRecieved(ServerClient client, String message) {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).messageReceived(client, message);
	}
	
	/* The method used to call clientDisconnected() in all of the listeners */
	public void callClientDisconnected(ServerClient client) {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).clientDisconnected(client);
	}
	
	/* The method used to call serverStopping() in all of the listeners */
	public void callServerStopping() {
		//Go through all of the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method
			this.listeners.get(a).serverStopping();
	}
	
}