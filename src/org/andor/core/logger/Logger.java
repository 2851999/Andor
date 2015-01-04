/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.logger;

import java.util.ArrayList;
import java.util.List;

import org.andor.utils.Console;

public class Logger {
	
	/* The booleans that state whether each type of
	 * log should be enabled */
	public static boolean debugEnabled = true;
	public static boolean informationEnabled = true;
	public static boolean warningEnabled = true;
	public static boolean errorEnabled = true;
	
	/* The booleans that state whether each type of
	 * log should be recorded */
	public static boolean recordDebug = false;
	public static boolean recordInformation = false;
	public static boolean recordWarning = false;
	public static boolean recordError = false;
	
	/* The booleans that state whether each part of a
	 * log should be enabled */
	public static boolean timeEnabled = true;
	public static boolean sourceEnabled = true;
	public static boolean typeEnabled = true;
	
	/* The lists that record each type of log */
	public static List<String> logs = new ArrayList<String>();
	public static List<String> debugLogs = new ArrayList<String>();
	public static List<String> informationLogs = new ArrayList<String>();
	public static List<String> warningLogs = new ArrayList<String>();
	public static List<String> errorLogs = new ArrayList<String>();
	
	/* The static method used to quickly print a debug message */
	public static void debug(String message) {
		//Log the log
		log(new Log("DEBUG MESSAGE", message));
	}
	
	/* The static method used to log a message
	 * assumes the log is supposed to be a debug */
	public static void log(String source, String message) {
		//Log the log
		log(new Log(source, message));
	}
	
	/* The static method used to log something */
	public static void log(String source, String message, int logType) {
		//Log the log
		log(new Log(source, message, logType));
	}
	
	/* The static method used to log something */
	public static void log(Log log) {
		//Get the logs message
		String message = log.getStringMessage();
		//Check to see whether the log type is enabled
		if (isEnabled(log.type))
			//Print the message
			Console.println(message);
		//Check to see whether the log should be recorded
		if (shouldRecord(log.type)) {
			//Add the message to the logs
			logs.add(message);
			//Check the log type and add the message to the correct list of logs
			if (log.type == Log.DEBUG)
				debugLogs.add(message);
			else if (log.type == Log.INFORMATION)
				informationLogs.add(message);
			else if (log.type == Log.WARNING)
				warningLogs.add(message);
			else if (log.type == Log.ERROR)
				errorLogs.add(message);
		}
	}
	
	/* The static method used to check if a log type is enabled */
	public static boolean isEnabled(int type) {
		//Check the log type
		if (type == Log.DEBUG)
			return debugEnabled;
		else if (type == Log.INFORMATION)
			return informationEnabled;
		else if (type == Log.WARNING)
			return warningEnabled;
		else if (type == Log.ERROR)
			return errorEnabled;
		else
			return false;
	}
	
	/* The static method used to check if a log type should be recorded enabled */
	public static boolean shouldRecord(int type) {
		//Check the log type
		if (type == Log.DEBUG)
			return recordDebug;
		else if (type == Log.INFORMATION)
			return recordInformation;
		else if (type == Log.WARNING)
			return recordWarning;
		else if (type == Log.ERROR)
			return recordError;
		else
			return false;
	}
	
}