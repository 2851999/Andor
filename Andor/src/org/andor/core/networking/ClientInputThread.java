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

public class ClientInputThread implements Runnable {
	
	/* The client */
	public Client client;
	
	/* The boolean that states whether this is running */
	public boolean running;
	
	/* The constructor */
	public ClientInputThread(Client client) {
		//Assign the variables
		this.client = client;
		this.running = true;
	}
	
	/* The method called to run this thread */
	public void run() {
		//Catch any errors
		try {
			//Keep going until the client disconnects
			while (this.running) {
				//Get any input from the client
				String input = this.client.in.readUTF();
				//Print out the output
				System.out.println(input);
			}
			//Disconnect the client
			this.client.disconnect();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ClientInputThread", "Failed to recieve input from the server", Log.ERROR);
			e.printStackTrace();
		}
	}
	
}