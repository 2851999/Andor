/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.networking.Client;
import org.andor.core.networking.Server;
import org.andor.utils.Console;

public class NetworkingTest {
	
	public Server server;
	public Client client;
	
	public NetworkingTest(String clientOrServer) {
		if (clientOrServer.equals("client")) {
			client();
		} else if (clientOrServer.equals("server")) {
			server();
		}
	}
	
	public void server() {
		this.server = new Server(7777);
		String input = Console.getInput();
		while (! input.equals("/stop")) {
			for (int a = 0; a < this.server.clients.size(); a++)
				this.server.clients.get(a).send(input);
			input = Console.getInput();
		}
		this.server.stop();
	}
	
	public void client() {
		this.client = new Client("localhost", 7777);
		String input = Console.getInput();
		while (! input.equals("/stop")) {
			this.client.send(input);
			input = Console.getInput();
		}
		this.client.disconnect();
	}
	
	public static void main(String[] args) {
		String clientOrServer = Console.ask("Client or Server?").toLowerCase();
		new NetworkingTest(clientOrServer);
	}
	
}