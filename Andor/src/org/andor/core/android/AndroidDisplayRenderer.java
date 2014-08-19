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
import android.opengl.Matrix;

public class AndroidDisplayRenderer implements GLSurfaceView.Renderer {
	
	/* The display */
	public AndroidDisplay display;
	
	public float mVMatrix[] = new float[16];
	public float mProjMatrix[] = new float[16];
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
		//float ratio = (float) width / height;
		//Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
		//Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		Matrix.orthoM(mProjMatrix, 0, 0, width, height, 0, -1, 1);
		Matrix.setLookAtM(mVMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		//Matrix.perspectiveM(mProjMatrix, 0, 60, ratio, 0.1f, 100);
		//Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		//Combine the projection and camera view matrices
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);
	}
	
	/* The method called to draw the frame */
	public void onDrawFrame(GL10 gl10) {
		//Update and render the game
		this.display.activity.game.tick();
	}
	
}