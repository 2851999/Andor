/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.core.android.AndroidDisplayRenderer;
import org.lwjgl.opengl.GL11;

import android.opengl.Matrix;

public class Camera2D extends Object2D {
	
	/* The constructor */
	public Camera2D() {
		
	}
	
	/* The constructor with the position values given */
	public Camera2D(float x, float y) {
		//Assign the variables
		this.position = new Vector2D(x, y);
	}
	
	/* The constructor with the position values and the rotation values given */
	public Camera2D(float x, float y, float z, float rotation) {
		//Assign the variables
		this.position = new Vector2D(x, y);
		this.rotation = rotation;
	}
	
	/* The constructor with the position vector given */
	public Camera2D(Vector2D position) {
		//Assign the variables
		this.position = position;
	}
	
	/* The constructor with the position vector and the rotation vector given */
	public Camera2D(Vector2D position, float rotation) {
		//Assign the variables
		this.position = position;
		this.rotation = rotation;
	}
	
	/* The method used to use this camera's view */
	public void useView() {
		//Get the rotation
		float r = this.getRotation();
		//Get the position
		Vector2D p = this.getPosition();
		//Get the scale
		Vector2D s = this.getScale();
		
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode) {
			//Rotate by the specified amount
			GL11.glRotatef(r, 0, 0, 1);
			
			//Move to the correct position
			GL11.glTranslatef(p.x, p.y, 0);
			
			//Scale by the correct amount
			GL11.glScalef(s.x, s.y, 0);
		} else {
			//Rotate by the specified amount
			Matrix.rotateM(AndroidDisplayRenderer.mMVPMatrix, 0, r, 0, 0, 1);
			
			//Move to the correct position
			Matrix.translateM(AndroidDisplayRenderer.mMVPMatrix, 0, p.x, p.y, 0);
			
			//Scale by the correct amount
			Matrix.scaleM(AndroidDisplayRenderer.mMVPMatrix, 0, s.x, s.y, 0);
		}
	}
	
}