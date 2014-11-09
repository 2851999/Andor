/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import org.andor.core.Matrix;
import org.andor.core.Settings;
import org.andor.core.android.AndroidRenderer;
import org.lwjgl.opengl.GL11;

import android.opengl.GLES20;

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
		else {
			AndroidRenderer.texture = null;
			GLES20.glDisable(GLES20.GL_TEXTURE_2D);
		}
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
		Matrix.loadIdentity(Matrix.modelMatrix);
		Matrix.loadIdentity(Matrix.viewMatrix);
		Matrix.projectionMatrix = Matrix.ortho(0, width, height, 0, znear, zfar);
	}
	
	/* The static method used to setup a perspective view given the
	 * fov, z near and z far value */
	public static void setupPerspective(float fov, float zNear, float zFar) {
		setupPerspective(fov, (float) (Settings.Window.Width / Settings.Window.Height), zNear, zFar);
	}
	
	/* The static method used to setup a perspective view given the
	 * fov, aspect ratio, z near and z far values */
	public static void setupPerspective(float fov, float aspect, float zNear, float zFar) {
		Matrix.loadIdentity(Matrix.modelMatrix);
		Matrix.loadIdentity(Matrix.viewMatrix);
		Matrix.projectionMatrix = Matrix.perspective(fov, aspect, zNear, zFar);
	}
	
	/* The static method to get the OpenGL version */
	public static String getVersion() {
		//Check to see whether using Android mode
		if (! Settings.AndroidMode)
			return GL11.glGetString(GL11.GL_VERSION);
		else
			return GLES20.glGetString(GLES20.GL_VERSION);
	}
	
}