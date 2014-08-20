/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import org.andor.core.Settings;
import org.andor.core.android.AndroidDisplayRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class OpenGLUtils {
	
	/* The static method used to clear the colour buffer */
	public static void clearColourBuffer() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		else
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
	}
	
	/* The static method used to clear the depth buffer */
	public static void clearDepthBuffer() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		else
			GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT);
	}
	
	/* The static method used to enable depth testing */
	public static void enableDepthTest() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		else
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);
	}
	
	/* The static method used to disable depth testing */
	public static void disableDepthTest() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		else
			GLES20.glDisable(GLES20.GL_DEPTH_TEST);
	}
	
	/* The static method used to enable texture 2D */
	public static void enableTexture2D() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		else
			GLES20.glEnable(GLES20.GL_TEXTURE_2D);
	}

	/* The static method used to disabled texture 2D */
	public static void disableTexture2D() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glDisable(GL11.GL_TEXTURE_2D);
		else GLES20.glDisable(GLES20.GL_TEXTURE_2D);
	}

	/* The static method used to enable wireframe mode */
	public static void enableWireframeMode() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
	}
	
	/* The static method used to disable wireframe mode */
	public static void disableWireframeMode() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
	}
	
	/* The static method to setup blending to remove alpha values */
	public static void setupRemoveAlpha() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA , GL11.GL_ONE_MINUS_SRC_ALPHA);
		} else {
			GLES20.glEnable(GLES20.GL_BLEND);
			GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		}
	}
	
	/* The static method to setup an orthographic view given the znear and zfar values */
	public static void setupOrtho(float znear , float zfar) {
		setupOrtho(Settings.Window.Width, Settings.Window.Height, znear, zfar);
	}
	
	/* The static method to setup an orthographic view given the width, height
	 * znear and zfar values */
	public static void setupOrtho(float width, float height, float znear , float zfar) {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode) {
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0 , width , height ,
					0 , znear , zfar);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
		} else {
			Matrix.orthoM(AndroidDisplayRenderer.mProjMatrix, 0, 0, width, height, 0, -1, 1);
			Matrix.setLookAtM(AndroidDisplayRenderer.mVMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
			Matrix.multiplyMM(AndroidDisplayRenderer.mMVPMatrix, 0, AndroidDisplayRenderer.mProjMatrix, 0, AndroidDisplayRenderer.mVMatrix, 0);
		}
	}
	
	/* The static method used to setup a perspective view given the
	 * fov, z near and z far value */
	public static void setupPerspective(float fov, float zNear, float zFar) {
		setupPerspective(fov, (float) (Settings.Window.Width / Settings.Window.Height), zNear, zFar);
	}
	
	/* The static method used to setup a perspective view given the
	 * fov, aspect ratio, z near and z far values */
	public static void setupPerspective(float fov, float aspect, float zNear, float zFar) {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode) {
			//Setup the perspective view
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GLU.gluPerspective(fov, aspect, zNear, zFar);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
		} else {
			perspectiveMatrix(AndroidDisplayRenderer.mProjMatrix, fov, aspect, zNear, zFar);
			Matrix.setLookAtM(AndroidDisplayRenderer.mVMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
			Matrix.multiplyMM(AndroidDisplayRenderer.mMVPMatrix, 0, AndroidDisplayRenderer.mProjMatrix, 0, AndroidDisplayRenderer.mVMatrix, 0);
		}
	}
	
	/* The static method to get the OpenGL version */
	public static String getVersion() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			return GL11.glGetString(GL11.GL_VERSION);
		else
			return GLES20.glGetString(GLES20.GL_VERSION);
	}
	
	/* Helper method, does the same thing as Matrix.perespectiveM, stops the need for using Android API Level 14
	 * and above (Ice Cream Sandwich) */
	public static void perspectiveMatrix(float[] matrix, float fov, float aspect, float zNear, float zFar) {
		//Calculate the values that need to be calculated the most frequently
		float f = 1.0f / (float) Math.tan(fov * (Math.PI / 360.0));
		float rangeReciprocal = 1.0f / (zNear - zFar);
		
		//Set the matrix values
		matrix[0] = f / aspect;
		matrix[1] = 0.0f;
		matrix[2] = 0.0f;
		matrix[3] = 0.0f;
		
		matrix[4] = 0.0f;
		matrix[5] = f;
		matrix[6] = 0.0f;
		matrix[7] = 0.0f;
		
		matrix[8] = 0.0f;
		matrix[9] = 0.0f;
		matrix[10] = (zFar + zNear) * rangeReciprocal;
		matrix[11] = -1.0f;
		
		matrix[12] = 0.0f;
		matrix[13] = 0.0f;
		matrix[14] = 2.0f * zFar * zNear * rangeReciprocal;
		matrix[15] = 0.0f;
	}
	
}