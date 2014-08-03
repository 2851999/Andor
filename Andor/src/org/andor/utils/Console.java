/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.util.Scanner;

public class Console {
	
	/* The static method used to print out a message */
	public static <T> void print(T message) {
		//Print out the message
		System.out.print(message);
	}
	
	/* The static method used to print out a message and start a new line */
	public static <T> void println(T message) {
		//Print out the message
		System.out.println(message);
	}
	
	/* The static method used to get input from the console */
	@SuppressWarnings("resource")
	public static String getInput() {
		//Create the scanner object
		Scanner scanner = new Scanner(System.in);
		//Return the input
		return scanner.nextLine();
	}
	
	/* The static method used to print out a message and return
	 * input from the console */
	public static String ask(String message) {
		//Print out the message
		print(message + " ");
		//Return the input
		return getInput();
	}
	
}