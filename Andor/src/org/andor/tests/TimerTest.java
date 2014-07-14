/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import java.util.List;

import org.andor.utils.Console;
import org.andor.utils.FileUtils;
import org.andor.utils.ThreadUtils;
import org.andor.utils.Timer;

public class TimerTest {
	
	/* The constructor */
	public TimerTest() {
		//The timer object
		Timer timer = new Timer();
		//The input
		String input = "";
		//Keep going until 'exit' as been entered
		while (! input.equals("exit")) {
			//Ask the user for input
			input = Console.ask("Please enter a command for the timer:");
			//Run the command
			runCommand(input, timer);
		}
	}
	
	/* The method used to check a command and execute it */
	public void runCommand(String input, Timer timer) {
		//Check the command and execute it
		if (input.equals("start"))
			timer.start();
		else if (input.equals("pause"))
			timer.pause();
		else if (input.equals("resume"))
			timer.resume();
		else if (input.equals("stop"))
			timer.stop();
		else if (input.equals("reset"))
			timer.reset();
		else if (input.equals("output"))
			Console.println(timer.getTime());
		else if (input.startsWith("wait"))
			ThreadUtils.sleepSeconds(Integer.parseInt(input.split(" ")[1]));
		else if (input.startsWith("println"))
			Console.println(input.split(" ")[1]);
		else if (input.startsWith("run")) {
			//Read the file specified
			List<String> fileText = FileUtils.read(input.split(" ")[1], false);
			//Go through each command
			for (int a = 0; a < fileText.size(); a++) {
				//Run the current command
				runCommand(fileText.get(a), timer);
			}
		}
	}
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new TimerTest();
	}
	
}