/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import javax.swing.JOptionPane;

public class MessageBox {
		
	/* The static method used to show an information box */
	public static void showInformationMessage(String title , String message) {
		JOptionPane.showMessageDialog(null , message , title , JOptionPane.INFORMATION_MESSAGE);
	}
	
	/* The static method used to show a warning box */
	public static void showWarningMessage(String title , String message) {
		JOptionPane.showMessageDialog(null , message , title , JOptionPane.WARNING_MESSAGE);
	}
	
	/* The static method used to show an error box */
	public static void showErrorMessage(String title , String message) {
		JOptionPane.showMessageDialog(null , message , title , JOptionPane.ERROR_MESSAGE);
	}
	
	/* The static method used to show a yes/no option and returns whether the answer was yes */
	public static boolean showYesNoOption(String title , String message) {
		return JOptionPane.showConfirmDialog(null , message , title , JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION;
	}
	
	/* The static method used to get input from the user */
	public static String getInput(String message) {
		return JOptionPane.showInputDialog(message);
	}
	
}