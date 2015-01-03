/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.logger;

import org.andor.utils.Time;

public class Log {
	
	/* The different types of log */
	public static final int DEBUG = 1;
	public static final int INFORMATION = 2;
	public static final int WARNING = 3;
	public static final int ERROR = 4;
	
	/* The source of this log */
	public String source;
	
	/* The message of this log */
	public String messsage;
	
	/* The type of this log */
	public int type;
	
	/* The time this log was created */
	public String time;
	
	/* The constructor - source, message
	 * Will assume the message type is DEBUG */
	public Log(String source, String message) {
		//Assign the variables
		this.source = source;
		this.messsage = message;
		this.type = DEBUG;
		this.time = Time.getTimeHHMMSS();
	}
	
	/* The constructor - source, message and type */
	public Log(String source, String message, int type) {
		//Assign the variables
		this.source = source;
		this.messsage = message;
		this.type = type;
		this.time = Time.getTimeHHMMSS();
	}
	
	/* The method used to return the string message */
	public String getStringMessage() {
		//The log message
		String logMessage = "";
		//Check to see what parts of the log are enabled
		if (Logger.timeEnabled)
			logMessage += "[" + this.time + "]";
		if (Logger.sourceEnabled)
			logMessage += "[" + this.source + "]";
		if (Logger.typeEnabled) {
			//The string representation of the log type
			String logType = "UNKNOWN";
			//Check the type
			if (this.type == DEBUG)
				logType = "DEBUG";
			else if (this.type == INFORMATION)
				logType = "INFORMATION";
			else if (this.type == WARNING)
				logType = "WARNING";
			else if (this.type == ERROR)
				logType = "ERROR";
			logMessage += "[" + logType + "]";
		}
		if (logMessage.length() == 0)
			logMessage = this.messsage;
		else
			logMessage += " " + this.messsage;
		//Return the message
		return logMessage;
	}
	
}