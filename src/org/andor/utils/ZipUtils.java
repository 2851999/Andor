/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ZipUtils {
	
	/* The static method used to unzip a file into a specified directory */
	public static void unzip(String source, String destination) {
		//Make sure the source file is a zip file
		if (FileUtils.isFile(source) && source.endsWith(".zip")) {
			//Make sure the destination directory exists, otherwise create it
			if (! FileUtils.doesExist(destination))
				//Create the directory
				FileUtils.createDirectories(destination);
			//Specify the buffer size
			int bufferSize = 1024;
			//Catch any errors
			try {
				//Get the zip file
				@SuppressWarnings("resource")
				ZipFile zip = new ZipFile(FileUtils.getFile(source));
				//Get the entries in the zip file
				Enumeration<? extends ZipEntry> zipEntries = zip.entries();
				//Process each entry
				while (zipEntries.hasMoreElements()) {
					//Get the next file entry
					ZipEntry entry = (ZipEntry) zipEntries.nextElement();
					//Create any file structure if needed
					FileUtils.createDirectories(FileUtils.getFile(destination + "/" + entry.getName()).getParent());
					//Make sure the current entry is a file
					if (! entry.isDirectory()) {
						//Create the input stream
						BufferedInputStream inputStream = new BufferedInputStream(zip.getInputStream(entry));
						//Create the buffer
						byte buffer[] = new byte[bufferSize];
						//Create the output stream
						BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FileUtils.getPath(destination + "/" + entry.getName())), bufferSize);
						//The current byte
						int currentByte = 0;
						//Read and write the files
						while ((currentByte = inputStream.read(buffer, 0, bufferSize)) != -1)
							//Write the data
							outputStream.write(buffer, 0, currentByte);
						//Close everything
						outputStream.flush();
						outputStream.close();
						inputStream.close();
					}
				}
			} catch (ZipException e) {
				//Log an error
				Logger.log("ZipUtils unzip()", "An error has occurred when unzipping the file " + source, Log.ERROR);
				e.printStackTrace();
			} catch (IOException e) {
				//Log an error
				Logger.log("ZipUtils unzip()", "An error has occurred when unzipping the file " + source, Log.ERROR);
				e.printStackTrace();
			}
		} else
			//Log an error
			Logger.log("ZipUtils unzip()", "Zip file " + source + " is either not a file, or is an unsupported type", Log.ERROR);
	}
	
	/* The static method used to zip a folder's contents into a zip file */
	public static void zip(String source, String destination) {
		//Make sure the destination ends with .zip
		if (destination.endsWith(".zip")) {
			//The buffer size
			int bufferSize = 1024;
			//Try and catch any errors
			try {
				//The output stream
				ZipOutputStream output = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(destination)));
				//The list of files and directories
				List<String> files = new ArrayList<String>();
				List<String> directories = new ArrayList<String>();
				//Get all of the files in the directory
				FileUtils.listAll(source, "", files, directories);
				//Go through each file
				for (int a = 0; a < files.size(); a++) {
					System.out.println(files.get(a));
					//Create the buffer
					byte buffer[] = new byte[bufferSize];
					//Create the input stream
					BufferedInputStream input = new BufferedInputStream(new FileInputStream(FileUtils.getPath(source + files.get(a))), bufferSize);
					//Create the zip entry
					ZipEntry entry = new ZipEntry(files.get(a).substring(1));
					//Start writing a new entry
					output.putNextEntry(entry);
					//The current byte
					int currentByte = 0;
					//Read and write the files
					while ((currentByte = input.read(buffer, 0, bufferSize)) != -1)
						//Write the data
						output.write(buffer, 0, currentByte);
					//Close the input file
					input.close();
				}
				//Close everything
				output.flush();
				output.close();
			} catch (ZipException e) {
				//Log an error
				Logger.log("ZipUtils unzip()", "An error has occurred when unzipping the file " + source, Log.ERROR);
				e.printStackTrace();
			} catch (IOException e) {
				//Log an error
				Logger.log("ZipUtils unzip()", "An error has occurred when unzipping the file " + source, Log.ERROR);
				e.printStackTrace();
			}
		} else
			//Log an error
			Logger.log("ZipUtils zip()", "The destination " + destination + " is not a zip file make sure it ends with .zip");
	}
	
}