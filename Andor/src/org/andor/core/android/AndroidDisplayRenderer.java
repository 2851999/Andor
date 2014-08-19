/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.andor.core.Settings;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

public class AndroidDisplayRenderer implements GLSurfaceView.Renderer {
	
	/* The display */
	public AndroidDisplay display;
	
	public static float mVMatrix[] = new float[16];
	public static float mProjMatrix[] = new float[16];
	public static float mMVPMatrix[] = new float[16];
	
	/* The constructor */
	public AndroidDisplayRenderer(AndroidDisplay display) {
		//Assign the variables
		this.display = display;
	}
	
	/* The method called when this surface is created */
	public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
		//Set the settings values
		Settings.Window.Width = this.display.getWidth();
		Settings.Window.Height = this.display.getHeight();
		//Create the game
		this.display.activity.game.create();
	}
	
	/* The method called when this surface is changed */
	public void onSurfaceChanged(GL10 gl10, int width, int height) {
		//Change the display
		this.display.changeDisplay(width, height);
		//Setup the view port
		GLES20.glViewport(0, 0, width, height);
	}
	
	/* The method called to draw the frame */
	public void onDrawFrame(GL10 gl10) {
		//Update and render the game
		this.display.activity.game.tick();
	}
	
}