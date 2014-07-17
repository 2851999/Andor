/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.lwjgl.opengl.GL11;

public class Camera3D extends Object3D {
	
	/* The variable used to stop the movement up and down when looking (Flying) */
	public boolean flying;
	
	/* The constructor */
	public Camera3D() {
		//Assign the variables
		this.flying = false;
	}
	
	/* The constructor with the position values given */
	public Camera3D(float x, float y, float z) {
		//Assign the variables
		this.position = new Vector3D(x, y, z);
		this.flying = false;
	}
	
	/* The constructor with the position values and the rotation values given */
	public Camera3D(float x, float y, float z, float rx, float ry, float rz) {
		//Assign the variables
		this.position = new Vector3D(x, y, z);
		this.flying = false;
	}
	
	/* The constructor with the position vector given */
	public Camera3D(Vector3D position) {
		//Assign the variables
		this.position = position;
		this.flying = false;
	}
	
	/* The constructor with the position vector and the rotation vector given */
	public Camera3D(Vector3D position, Vector3D rotation) {
		//Assign the variables
		this.position = position;
		this.rotation = rotation;
		this.flying = false;
	}
	
	/* The method used to use this camera's view */
	public void useView() {
		//Rotate the view
		GL11.glRotatef(this.rotation.x, 1, 0, 0);
		GL11.glRotatef(this.rotation.y, 0, 1, 0);
		GL11.glRotatef(this.rotation.z, 0, 0, 1);
		
		//Translate afterwards otherwise it wont look like the camera is rotating
		//it would instead look like everything else is rotating
		GL11.glTranslatef(this.position.x, this.position.y, this.position.z);
	}
	
	/* The method used to move the camera forwards relative to its rotation */
	public void moveForward(float amount) {
		//Move the camera 'forwards'
		this.position.x -= amount * (float) Math.sin(Math.toRadians(this.rotation.y));
		this.position.z += amount * (float) Math.cos(Math.toRadians(this.rotation.y));
		//Check whether flying is enabled
		if (this.flying)
			this.position.y += amount * (float) Math.tan(Math.toRadians(this.rotation.x));
	}
	
	/* The method used to move the camera backwards relative to its rotation */
	public void moveBackward(float amount) {
		//Move the camera 'backwards'
		this.position.x += amount * (float) Math.sin(Math.toRadians(this.rotation.y));
		this.position.z -= amount * (float) Math.cos(Math.toRadians(this.rotation.y));
		//Check whether flying is enabled
		if (this.flying)
			this.position.y -= amount * (float) Math.tan(Math.toRadians(this.rotation.x));
	}
	
	/* The method used to move the camera left relative to its rotation */
	public void moveLeft(float amount) {
		//Move the camera 'left'
		this.position.x -= amount * (float) Math.sin(Math.toRadians(this.rotation.y - 90));
		this.position.z += amount * (float) Math.cos(Math.toRadians(this.rotation.y - 90));
	}
	
	/* The method used to move the camera right relative to its rotation */
	public void moveRight(float amount) {
		//Move the camera 'right'
		this.position.x += amount * (float) Math.sin(Math.toRadians(this.rotation.y - 90));
		this.position.z -= amount * (float) Math.cos(Math.toRadians(this.rotation.y - 90));
	}
	
}