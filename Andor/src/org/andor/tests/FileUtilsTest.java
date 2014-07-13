/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.utils.FileUtils;
import org.andor.utils.ThreadUtils;

public class FileUtilsTest {
	
	/* The constructor */
	public FileUtilsTest() {
		//The directory
		String directory = "C:/Users/Joel/Desktop/";
		//Get all of the text in the ChangeLog file
		Object[] text = FileUtils.readInternal("/ChangeLog").toArray();
		//Go through the file text
		for (Object o : text)
			//Print out the current line
			System.out.println(o);
		//Write a file
		FileUtils.write(directory + "AndorFileUtilsTest.txt", new String[] {
			"Hello World"
		});
		//Copy a file
		FileUtils.copy(directory + "AndorFileUtilsTest.txt", directory + "AndorFileUtilsTest-Copy.txt");
		//Wait 5 seconds
		ThreadUtils.sleepSeconds(5);
		//Delete a file
		FileUtils.delete(directory + "AndorFileUtilsTest-Copy.txt");
		//Move a file
		FileUtils.move(directory + "AndorFileUtilsTest.txt", directory + "AndorFileUtilsTest-Move.txt");
		//Wait 5 seconds
		ThreadUtils.sleepSeconds(5);
		//Delete a file
		FileUtils.delete(directory + "AndorFileUtilsTest-Move.txt");
		//Copy a directory
		FileUtils.copyDirectory(directory + "TestDir", directory + "TestDir-Copy");
		//Wait 5 seconds
		ThreadUtils.sleepSeconds(5);
		//Delete a directory
		FileUtils.deleteDirectory(directory + "TestDir");
		//Wait 5 seconds
		ThreadUtils.sleepSeconds(5);
		//Move a directory
		FileUtils.moveDirectory(directory + "TestDir-Copy", directory + "TestDir");
		//Wait 5 seconds
		ThreadUtils.sleepSeconds(5);
		//Delete a directory
		FileUtils.deleteDirectory(directory + "TestDir-Copy");
	}
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new FileUtilsTest();
	}
	
}