/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.BaseGame;
import org.andor.core.Settings;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public abstract class BaseActivity extends Activity implements ActivityListenerInterface {
	
	/* The static instance */
	public static BaseActivity instance = null;
	
	/* The list of listeners */
	public static List<ActivityListenerInterface> activityListeners = new ArrayList<ActivityListenerInterface>();
	
	/* The game instance */
	public BaseGame game;
	
	/* The display */
	public AndroidDisplay display;
	
	/* The method called when Android creates this activity
	 * to be called from the class that extends this */
	protected void onCreate(BaseGame game , Bundle savedInstanceState) {
		//Assign the instance
		instance = this;
		//Call the onCreate() method
		super.onCreate(savedInstanceState);
		//Set the title
		this.setTitle(Settings.Window.Title);
		//Assign the game
		this.game = game;
		//Create the input
		InputManagerAndroid.create();
		//Check the orientation and request it
		if (Settings.Android.ScreenOrientation == Settings.Android.SCREEN_ORIENTATION_PORTRAIT)
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		else if (Settings.Android.ScreenOrientation == Settings.Android.SCREEN_ORIENTATION_LANDSCAPE)
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//Check to see whether the Activity should be fullscreen
		if (Settings.Window.Fullscreen) {
			//Request to remove the title bar
			this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			//Make the window fullscreen
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		//Make sure the display hasn't already been created
		if (this.display == null) {
			//Create the display
			this.display = new AndroidDisplay(this);
			//Set the content view
			this.setContentView(this.display);
		}
		//Add this to the list of listeners
		activityListeners.add(this);
		//Call the method in the interfaces
		this.callOnActivityCreated();
	}
	
	/* Called when the activity is paused */
	protected void onPause() {
		//Call the onPause() method
		super.onPause();
		
		/* NOTE
		 * The error: IllegalStateException cause by EGLSurface not being valid
		 * seems to be fixed by removing the surface when paused and then adding
		 * it again when resumed */
		
		//Get the frame layout which has the AndroidDisplayOpenGLES instance in it
		FrameLayout layout = (FrameLayout) this.display.getParent();
		//Remove the instance of AndroidDisplayOpenGLES
		layout.removeView(this.display);
		//Pause the OpenGL ES display
		this.display.onPause();
		//Call the method in the interfaces
		this.callOnActivityPaused();
	}
	
	/* Called when the activity is resumed */
	protected void onResume() {
		//Call the onResume() method
		super.onResume();
		this.setContentView(this.display);
		//Reset the times in the FPSCalculator to avoid
		//a massive delta / low FPS
		this.game.fpsCalculator.reset();
		//Resume the OpenGL ES display
		this.display.onResume();
		//Call the method in the interfaces
		this.callOnActivityResumed();
	}
	
	/* Called when the activity is stopped */
	protected void onStop() {
		//Call the onStop() method
		super.onStop();
		//Call the method in the interfaces
		this.callOnActivityStopped();
	}
	
	/* Called when the activity is restarted */
	protected void onRestart() {
		//Call the onRestart() method
		super.onRestart();
		//Call the method in the interfaces
		this.callOnActivityRestarted();
	}
	
	/* Called when the activity is destroyed */
	protected void onDestroy() {
		//Call the onResume() method
		super.onDestroy();
		//Call the method in the interfaces
		this.callOnActivityDestroy();
		//Destroy
		this.finish();
	}
	
	/* The method used to call an onActivityCreated() event */
	public void callOnActivityCreated() {
		//Go through each listener
		for (int a = 0; a < activityListeners.size(); a++)
			//Call the method in the current listener
			activityListeners.get(a).onActivityCreated();
	}
	
	/* The method used to call an onActivityPaused() event */
	public void callOnActivityPaused() {
		//Go through each listener
		for (int a = 0; a < activityListeners.size(); a++)
			//Call the method in the current listener
			activityListeners.get(a).onActivityPaused();
	}
	
	/* The method used to call an onActivityResumed() event */
	public void callOnActivityResumed() {
		//Go through each listener
		for (int a = 0; a < activityListeners.size(); a++)
			//Call the method in the current listener
			activityListeners.get(a).onActivityResumed();
	}
	
	/* The method used to call an onActivityStopped() event */
	public void callOnActivityStopped() {
		//Go through each listener
		for (int a = 0; a < activityListeners.size(); a++)
			//Call the method in the current listener
			activityListeners.get(a).onActivityStopped();
	}
	
	/* The method used to call an onActivityRestarted() event */
	public void callOnActivityRestarted() {
		//Go through each listener
		for (int a = 0; a < activityListeners.size(); a++)
			//Call the method in the current listener
			activityListeners.get(a).onActivityRestarted();
	}
	
	/* The method used to call an onActivityDestroy() event */
	public void callOnActivityDestroy() {
		//Go through each listener
		for (int a = 0; a < activityListeners.size(); a++)
			//Call the method in the current listener
			activityListeners.get(a).onActivityDestroy();
	}
	
	/* The static method used to add an ActivityListenerInterface to the list */
	public static void addListener (ActivityListenerInterface listener) {
		//Add the listener to the list of listeners
		activityListeners.add(listener);
	}
	
}