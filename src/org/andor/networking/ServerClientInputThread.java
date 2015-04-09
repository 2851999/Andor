/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.networking;

import java.io.IOException;

public class ServerClientInputThread extends Thread {
	
	/* The client */
	public ServerClient client;
	
	/* The boolean that states whether this is closing */
	public boolean closing;
	
	/* The constructor */
	public ServerClientInputThread(ServerClient client) {
		//Assign the variables
		this.client = client;
		this.closing = false;
	}
	
	/* The method called to run this thread */
	public void run() {
		//Catch any errors
		try {
			//Keep going until the client disconnects
			while (this.isAlive() && ! this.closing) {
				//Get any input from the client
				String input = this.client.in.readUTF();
				//Call messageRecieved() in the listeners
				this.client.server.callMessageRecieved(this.client, input);
			}
		} catch (IOException e) {
			//There was an error receiving input from the client, so assume they lost connection /
			//disconnected from the server
			
			//Call clientDisconnecting() in the listeners
			this.client.server.callClientDisconnected(this.client);
			//Disconnect this client
			this.client.disconnect();
		}
	}
	
}