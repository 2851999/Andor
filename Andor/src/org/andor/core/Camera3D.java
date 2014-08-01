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
	
	/* The sky box */
	private SkyBox skyBox;
	
	/* Should the sky box be used if available */
	public boolean useSkyBoxIfAvailable;
	
	/* The constructor */
	public Camera3D() {
		//Assign the variables
		this.flying = false;
		this.skyBox = null;
		this.useSkyBoxIfAvailable = true;
	}
	
	/* The constructor with the position values given */
	public Camera3D(float x, float y, float z) {
		//Assign the variables
		this.position = new Vector3D(x, y, z);
		this.flying = false;
		this.skyBox = null;
		this.useSkyBoxIfAvailable = true;
	}
	
	/* The constructor with the position values and the rotation values given */
	public Camera3D(float x, float y, float z, float rx, float ry, float rz) {
		//Assign the variables
		this.position = new Vector3D(x, y, z);
		this.flying = false;
		this.skyBox = null;
		this.useSkyBoxIfAvailable = true;
	}
	
	/* The constructor with the position vector given */
	public Camera3D(Vector3D position) {
		//Assign the variables
		this.position = position;
		this.flying = false;
		this.skyBox = null;
		this.useSkyBoxIfAvailable = true;
	}
	
	/* The constructor with the position vector and the rotation vector given */
	public Camera3D(Vector3D position, Vector3D rotation) {
		//Assign the variables
		this.position = position;
		this.rotation = rotation;
		this.flying = false;
		this.skyBox = null;
		this.useSkyBoxIfAvailable = true;
	}
	
	/* The method used to use this camera's view */
	public void useView() {
		//Get the rotation
		Vector3D r = this.getRotation();
		
		//Rotate by the specified amount
		GL11.glRotatef(r.x, 1, 0, 0);
		GL11.glRotatef(r.y, 0, 1, 0);
		GL11.glRotatef(r.z, 0, 0, 1);
		
		//Get the position
		Vector3D p = this.getPosition();
		
		//Move to the correct position
		GL11.glTranslatef(p.x, p.y, p.z);
		
		//Get the scale
		Vector3D s = this.getScale();
		
		GL11.glScalef(s.x, s.y, s.z);
		
		//Check to see whether the skybox should be used
		if (this.skyBox != null && this.useSkyBoxIfAvailable) {
			//Get the position
			Vector3D pos = this.getPosition().clone();
			//Multiply the position by -1
			pos.multiply(-1f);
			//Set the sky box's position
			this.skyBox.box.position = pos;
			//Render the skybox based on this camera's position
			this.skyBox.renderSkybox();
		}
	}
	
	/* The method used to move the camera forwards relative to its rotation */
	public void moveForward(float amount) {
		//Move the camera 'forwards'
		this.position.x -= amount * (float) Math.sin(Math.toRadians(this.getRotation().y));
		this.position.z += amount * (float) Math.cos(Math.toRadians(this.getRotation().y));
		//Check whether flying is enabled
		if (this.flying)
			this.position.y += amount * (float) Math.tan(Math.toRadians(this.getRotation().x));
	}
	
	/* The method used to move the camera backwards relative to its rotation */
	public void moveBackward(float amount) {
		//Move the camera 'backwards'
		this.position.x += amount * (float) Math.sin(Math.toRadians(this.getRotation().y));
		this.position.z -= amount * (float) Math.cos(Math.toRadians(this.getRotation().y));
		//Check whether flying is enabled
		if (this.flying)
			this.position.y -= amount * (float) Math.tan(Math.toRadians(this.getRotation().x));
	}
	
	/* The method used to move the camera left relative to its rotation */
	public void moveLeft(float amount) {
		//Move the camera 'left'
		this.position.x -= amount * (float) Math.sin(Math.toRadians(this.getRotation().y - 90));
		this.position.z += amount * (float) Math.cos(Math.toRadians(this.getRotation().y - 90));
	}
	
	/* The method used to move the camera right relative to its rotation */
	public void moveRight(float amount) {
		//Move the camera 'right'
		this.position.x += amount * (float) Math.sin(Math.toRadians(this.getRotation().y - 90));
		this.position.z -= amount * (float) Math.cos(Math.toRadians(this.getRotation().y - 90));
	}
	
	/* The method used to set this camera's skybox */
	public void setSkyBox(SkyBox skyBox) {
		//Assign the skybox
		this.skyBox = skyBox;
	}
	
}