/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.networking;

public interface ServerListener {
	
	/* The method called when the server starts */
	public void serverStarted();
	
	/* The method called when a client connects */
	public void clientConnected(ServerClient client);
	
	/* The method called when a client disconnects */
	public void clientDisconnected(ServerClient client);
	
	/* The method called when the server receives a message */
	public void messageReceived(ServerClient client, String message);
	
	/* The method called when the server is stopping */
	public void serverStopping();
	
}