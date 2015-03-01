/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

public class SystemInformation {
	
	/* The static method to get the number of available processors */
	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	/* The static method to get the total memory */
	public static long getTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}
	
	/* The static method to get the free memory */
	public static long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}
	
	/* The static method to get the maximum memory that can be used */
	public static long getMaxMemory() {
		return Runtime.getRuntime().maxMemory();
	}
	
	/* The static method used to get the current user's username */
	public static String getUsername() {
		return System.getenv("USERNAME");
	}
	
	/* The static method used to get the java class path */
	public static String getJavaClassPath() {
		return System.getProperty("java.class.path");
	}
	
	/* The static method used to get the java home */
	public static String getJavaHome() {
		return System.getProperty("java.home");
	}
	
	/* The static method used to get the java vendor */
	public static String getJavaVendor() {
		return System.getProperty("java.vendor");
	}
	
	/* The static method used to get the java vendor url */
	public static String getJavaVendorURL() {
		return System.getProperty("java.vendor.url");
	}
	
	/* The static method used to get the java version */
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}
	
	/* The static method used to get the os architecture */
	public static String getOSArchitecture() {
		return System.getProperty("os.arch");
	}
	
	/* The static method used to get the os name */
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	/* The static method used to get the os version */
	public static String getOSVersion() {
		return System.getProperty("os.version");
	}
	
	/* The static method used to get the users working directory */
	public static String getUserDirectory() {
		return System.getProperty("user.dir");
	}
	
	/* The static method used to get the users home directory */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	/* The static method used to get the users home directory */
	public static String getUserAccountName() {
		return System.getProperty("user.name");
	}
	
	/* The static method used to get the users home directory */
	public static String getUserAppData() {
		return System.getenv("AppData");
	}
	
}