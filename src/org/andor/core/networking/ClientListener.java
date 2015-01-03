/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.networking;

public interface ClientListener {
	
	/* The method called when the client connects to a server */
	public void connected(String ip, int port);
	
	/* The method called when the client receives a message */
	public void messageReceived(String message);
	
	/* The method called when the client disconnects */
	public void disconnecting();
	
}