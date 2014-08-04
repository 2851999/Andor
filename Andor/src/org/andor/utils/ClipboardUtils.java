/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ClipboardUtils {
	
	/* The static method to get text off of the clip board */
	public String getText() {
		try {
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (HeadlessException e) {
			//Log an error
			Logger.log("Andor - ClipboardUtils getText()", "An exception has occurred when getting text from  the system clipboard", Log.ERROR);
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			//Log an error
			Logger.log("Andor - ClipboardUtils getText()", "An exception has occurred when getting text from  the system clipboard", Log.ERROR);
			e.printStackTrace();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ClipboardUtils getText()", "An exception has occurred when getting text from  the system clipboard", Log.ERROR);
			e.printStackTrace();
		}
		return "";
	}
	
	/* The static method to set the text of the clip board */
	public void setText(String text) {
		//Set the contents of the clip board
		StringSelection selection = new StringSelection(text);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
	}
	
}