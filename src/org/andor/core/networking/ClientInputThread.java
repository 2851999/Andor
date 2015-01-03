/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.networking;

import java.io.IOException;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ClientInputThread extends Thread {
	
	/* The client */
	public Client client;
	
	/* The boolean that states whether this is closing */
	public boolean closing;
	
	/* The constructor */
	public ClientInputThread(Client client) {
		//Assign the variables
		this.client = client;
	}
	
	/* The method called to run this thread */
	public void run() {
		//Catch any errors
		try {
			//Keep going until the client disconnects
			while (this.isAlive() && ! this.closing) {
				//Get any input from the client
				String input = this.client.in.readUTF();
				//Call messageReceived() in the listeners
				this.client.callMessageRecieved(input);
			}
		} catch (IOException e) {
			//Make sure this error isn't caused just because the server is closing
			if (! this.closing) {
				//Log an error
				Logger.log("Andor - ClientInputThread", "Failed to recieve input from the server", Log.ERROR);
				e.printStackTrace();
			}
		}
	}
	
}