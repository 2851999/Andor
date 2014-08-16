/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import org.andor.core.Settings;
import org.andor.core.input.Input;
import org.andor.utils.ScreenUtils;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class AndroidDisplay extends GLSurfaceView {
	
	/* The game activity */
	public BaseActivity activity;
	
	/* The boolean that states whether the game has already been created */
	public boolean gameAlreadyCreated;
	
	/* The constructor */
	public AndroidDisplay(BaseActivity activity) {
		//Call the super constructor
		super(activity);
		//Assign the variables
		this.activity = activity;
		this.gameAlreadyCreated = false;
		//Add the callback
		this.getHolder().addCallback(this);
		//Create an OpenGL ES 2.0 context
		this.setEGLContextClientVersion(2);
		//Create the renderer
		this.setRenderer(new AndroidDisplayRenderer(this));
		//Set the surface view focusable
		this.setFocusable(true);
		
		//Set the size of the screen in the settings based on the value of fullscreen
		if (Settings.Window.Fullscreen) {
			Settings.Window.Width = ScreenUtils.getScreenWidth();
			Settings.Window.Height = ScreenUtils.getScreenHeight();
		} else {
			//Set the size of the screen in the settings
			Settings.Window.Width = this.getWidth();
			Settings.Window.Height = this.getHeight();
		}
		//Make sure the game hasn't already been created
		if (! this.gameAlreadyCreated) {
			//Assign 'gameAlreadyCreated' to true
			this.gameAlreadyCreated = true;
			//Add the game to the input listeners
			Input.addListener(this.activity.game);
			//Create the game
			this.activity.game.create();
		}
	}
	
	/* Called to change the display */
	public void changeDisplay(int width , int height) {
		//Set the size of the screen in the settings based on the value of fullscreen
		if (Settings.Window.Fullscreen) {
			Settings.Window.Width = ScreenUtils.getScreenWidth();
			Settings.Window.Height = ScreenUtils.getScreenHeight();
		} else {
			//Set the size of the screen in the settings
			Settings.Window.Width = this.getWidth();
			Settings.Window.Height = this.getHeight();
		}
	}
	
	/* Called when the surface is destroyed */
	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		
	}
	
	/* The on touch method NOTE: DO NOT MOVE, IF YOU MOVE THIS THIS
	 * to AndroidActivity, the coordinates given will include the title
	 * bar and the engine will not work properly when fullscreen = false */
	public boolean onTouchEvent(MotionEvent e) {
		//The event was handled
		return true;
	}
	
}