/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.awt.Toolkit;

import org.andor.core.Settings;
import org.andor.core.android.BaseActivity;

import android.util.DisplayMetrics;

public class ScreenUtils {
	
	/* The static  method to get the current screens width */
	public static int getScreenWidth() {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Return the displays width
			return Toolkit.getDefaultToolkit().getScreenSize().width;
		else {
			//The display metrics
			DisplayMetrics displayMetrics = new DisplayMetrics();
			//Set the display metrics
			BaseActivity.instance.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			//Return the width of the screen
			return displayMetrics.widthPixels;
		}
	}
	
	/* The static method to get the current screens height */
	public static int getScreenHeight() {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Return the displays height
			return Toolkit.getDefaultToolkit().getScreenSize().height;
		else {
			//The display metrics
			DisplayMetrics displayMetrics = new DisplayMetrics();
			//Set the display metrics
			BaseActivity.instance.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			//Return the height of the screen
			return displayMetrics.heightPixels;
		}
	}
	
}