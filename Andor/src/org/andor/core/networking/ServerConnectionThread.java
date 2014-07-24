/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.networking;

import java.io.IOException;
import java.net.Socket;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ServerConnectionThread implements Runnable {
	
	/* The server instance */
	public Server server;
	
	/* The boolean that states whether this is running */
	public boolean running;
	
	/* The constructor */
	public ServerConnectionThread(Server server) {
		//Assign the variables
		this.server = server;
		this.running = true;
	}
	
	/* The method called to run this thread */
	public void run() {
		//Catch any errors
		try {
			//Keep going until this thread stops running
			while (this.running) {
				//Accept any sockets
				Socket socket = this.server.socket.accept();
				//Add a new client
				this.server.clients.add(new ServerClient(this.server, socket));
			}
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ServerConnectionThread", "Failed to accept a socket", Log.ERROR);
			e.printStackTrace();
		}
	}
	
}