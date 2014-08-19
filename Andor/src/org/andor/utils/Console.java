/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.util.Scanner;

import org.andor.core.Settings;

import android.util.Log;

public class Console {
	
	/* The static method used to print out a message */
	public static <T> void print(T message) {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			//Print out the message
			System.out.print(message);
		else
			Log.d("Andor", "" + message);
	}
	
	/* The static method used to print out a message and start a new line */
	public static <T> void println(T message) {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			//Print out the message
			System.out.println(message);
		else
			Log.d("Andor", "" + message);
	}
	
	/* The static method used to get input from the console */
	@SuppressWarnings("resource")
	public static String getInput() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode) {
			//Create the scanner object
			Scanner scanner = new Scanner(System.in);
			//Return the input
			return scanner.nextLine();
		} else
			return "";
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