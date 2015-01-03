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
import java.net.InetAddress;
import java.net.Socket;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ServerClient {
	
	/* The client's inet address */
	public InetAddress inetAddress;
	
	/* The client's socket */
	public Socket socket;
	
	/* The client's input stream */
	public DataInputStream in;
	
	/* The client's output stream */
	public DataOutputStream out;
	
	/* The server instance */
	public Server server;
	
	/* The client's input thread */
	public ServerClientInputThread inputThread;
	
	/* The constructor */
	public ServerClient(Server server, Socket socket) {
		//Catch any errors
		try {
			//Assign the variables
			this.server = server;
			this.socket = socket;
			this.inetAddress = socket.getInetAddress();
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			//Start the client's input thread
			this.inputThread = new ServerClientInputThread(this);
			this.inputThread.start();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ServerClient", "Failed to setup the client with the address of " + inetAddress.getHostAddress(), Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to send this client a message */
	public void send(String message) {
		//Catch any errors
		try {
			//Send the message
			this.out.writeUTF(message);
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ServerClient sendMessage()", "Failed to send the client with the address of " + inetAddress.getHostAddress() + " the message " + message, Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to disconnect the client */
	public void disconnect() {
		//Catch any errors
		try {
			//Prevent errors
			this.inputThread.closing = true;
			
			//Remove this client
			server.clients.remove(this);
			
			//Close all of the streams and sockets
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ServerClient disconnect()", "Failed to disconnect the client with the address of " + inetAddress.getHostAddress(), Log.ERROR);
			e.printStackTrace();
		}
	}
	
}