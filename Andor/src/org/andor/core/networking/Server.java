/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
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
	public Thread connectionThread;
	
	/* The constructor */
	public Server(int port) {
		//Catch any errors
		try {
			//Assign the variables
			this.clients = new ArrayList<ServerClient>();
			//Setup the sockets
			this.socket = new ServerSocket(port);
			//Start the server connection thread
			this.connectionThread = new Thread(new ServerConnectionThread(this));
			this.connectionThread.start();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Server", "Failed to setup the server", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to stop this server */
	public void stop() {
		//Catch any errors
		try {
			//Stop the connection thread
			this.connectionThread.join();
			//Disconnect all of the clients
			for (int a = 0; a < this.clients.size(); a++)
				//Disconnect the current client
				this.clients.get(a).disconnect();
			//Close this servers socket
			this.socket.close();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - Server stop()", "Failed to close the server socket", Log.ERROR);
			e.printStackTrace();
		} catch (InterruptedException e) {
			//Log an error
			Logger.log("Andor - Server stop()", "Failed to join the connection thread", Log.ERROR);
			e.printStackTrace();
		}
	}
	
}