/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.Settings;
import org.andor.core.Window;
import org.andor.utils.ScreenResolution;
import org.andor.utils.ScreenUtils;

public class WindowTest {
	
	/* The constructor */
	public WindowTest() {
		Settings.Video.Resolution = new ScreenResolution(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
		//Create the window
		Window.create();
		while (! Window.shouldClose()) {
			Window.updateDisplay();
		}
	}
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new WindowTest();
	}
	
}