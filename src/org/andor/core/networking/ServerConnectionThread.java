/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.networking;

import java.io.IOException;
import java.net.Socket;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ServerConnectionThread extends Thread {
	
	/* The server instance */
	public Server server;
	
	/* The boolean that states whether this is closing */
	public boolean closing;
	
	/* The constructor */
	public ServerConnectionThread(Server server) {
		//Assign the variables
		this.server = server;
		this.closing = false;
	}
	
	/* The method called to run this thread */
	public void run() {
		//Call serverStarted() in the listeners
		this.server.callServerStarted();
		//Catch any errors
		try {
			//Keep going until this thread stops running
			while (this.isAlive() && ! this.closing) {
				//Accept any sockets
				Socket socket = this.server.socket.accept();
				//Create the client instance
				ServerClient client = new ServerClient(this.server, socket);
				//Add the new client
				this.server.clients.add(client);
				//Call clientConnected() in the listeners
				this.server.callClientConnected(client);
			}
		} catch (IOException e) {
			//Make sure this error isn't caused just because the server is closing
			if (! this.closing) {
				//Log an error
				Logger.log("Andor - ServerConnectionThread", "Failed to accept a socket", Log.ERROR);
				e.printStackTrace();
			}
		}
	}
	
}