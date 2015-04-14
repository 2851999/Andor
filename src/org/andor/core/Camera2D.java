/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

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
		//Apply this cameras transform
		Matrix.viewMatrix = Matrix.transformR(Matrix.viewMatrix, this.getPosition(), this.getRotation(), this.getScale());
	}
	
}