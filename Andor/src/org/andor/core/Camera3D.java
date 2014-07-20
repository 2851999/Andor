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
		//Rotate the view
		GL11.glRotatef(this.getRotation().x, 1, 0, 0);
		GL11.glRotatef(this.getRotation().y, 0, 1, 0);
		GL11.glRotatef(this.getRotation().z, 0, 0, 1);
		
		//Translate afterwards otherwise it wont look like the camera is rotating
		//it would instead look like everything else is rotating
		GL11.glTranslatef(this.getPosition().x, this.getPosition().y, this.getPosition().z);
		
		//Check to see whether the skybox should be used
		if (this.skyBox != null && this.useSkyBoxIfAvailable)
			//Render the skybox based on this camera's position
			this.skyBox.renderSkybox();
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
		//Link the skybox to this camera's position
		this.link(this.skyBox.box);
	}
	
}