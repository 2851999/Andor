/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import java.util.ArrayList;
import java.util.List;

import org.andor.networking.Client;
import org.andor.networking.ClientListener;
import org.andor.networking.Server;
import org.andor.networking.ServerClient;
import org.andor.networking.ServerListener;
import org.andor.utils.Console;

public class NetworkingTest implements ClientListener, ServerListener {
	
	public List<User> users;
	public Server server;
	public Client client;
	
	public NetworkingTest(String clientOrServer) {
		if (clientOrServer.equals("client")) {
			client();
		} else if (clientOrServer.equals("server")) {
			this.users = new ArrayList<User>();
			server();
		}
	}
	
	public void server() {
		this.server = new Server();
		this.server.addListener(this);
		this.server.start(7777);
		String input = Console.getInput();
		while (! input.equals("/stop")) {
			this.server.sendAll("[SERVER] " + input);
			input = Console.getInput();
		}
		this.server.stop();
	}
	
	public void client() {
		this.client = new Client();
		this.client.addListener(this);
		this.client.connect(Console.ask("Whats the IP of the server:"), 7777);
		String input = Console.getInput();
		while (! input.equals("/stop")) {
			this.client.send(input);
			input = Console.getInput();
		}
		this.client.disconnect();
	}
	
	/* The method called when the client connects to a server */
	public void connected(String ip, int port) {
		Console.println("Client connected to " + ip + ":" + port);
		this.client.send("@forserver username " + Console.ask("Enter the name you want to be displayed as:"));
	}
	
	/* The method called when the client receives a message */
	public void messageReceived(String message) {
		Console.println(message);
	}
	
	/* The method called when the client disconnects */
	public void disconnecting() {
		Console.println("Disconnecting...");
	}
	
	/* The method called when the server starts */
	public void serverStarted() {
		Console.println("Server started");
	}
	
	/* The method called when a client connects */
	public void clientConnected(ServerClient client) {
		String message = "Client connected " + this.getUserName(client);
		this.server.sendAll(message);
		Console.println(message);
	}
	
	/* The method called when a client disconnects */
	public void clientDisconnected(ServerClient client) {
		String message = "Client disconnected " + this.getUserName(client);
		this.users.remove(getUser(client));
		this.server.sendAll(message);
		Console.println(message);
	}
	
	/* The method called when the server receives a message */
	public void messageReceived(ServerClient client, String message) {
		if (! message.startsWith("@forserver")) {
			String messageToSend = "[" + this.getUserName(client) + "] " + message;
			Console.println(messageToSend);
			this.server.sendAll(messageToSend);
		} else {
			String[] split = message.split(" ");
			if (split[1].equals("username")) {
				this.users.add(new User(client, message.substring(split[0].length() + split[1].length() + 2)));
			}
		}
	}
	
	/* The method called when the server is stopping */
	public void serverStopping() {
		String message = "Server Stopping";
		this.server.sendAll(message);
		Console.println(message);
	}
	
	public String getUserName(ServerClient client) {
		User user = getUser(client);
		if (user == null)
			return "";
		else
			return user.name;
	}
	
	public User getUser(ServerClient client) {
		User user = null;
		for (int a = 0; a < this.users.size(); a++) {
			if (this.users.get(a).client.equals(client)) {
				user = this.users.get(a);
				break;
			}
		}
		return user;
	}
	
	public static void main(String[] args) {
		String clientOrServer = Console.ask("Client or Server?").toLowerCase();
		new NetworkingTest(clientOrServer);
	}
	
	private class User {
		public ServerClient client;
		public String name;
		public User(ServerClient client, String name) {
			this.client = client;
			this.name = name;
		}
	}
	
}