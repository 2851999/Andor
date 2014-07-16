/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Object3D {
	
	/* The position of this object */
	public Vector3D position;
	
	/* The rotation of this object */
	public Vector3D rotation;
	
	/* The default constructor */
	public Object3D() {
		//Assign the variables
		this.position = new Vector3D();
		this.rotation = new Vector3D();
	}
	
	/* The constructor with the position given */
	public Object3D(Vector3D position) {
		//Assign the variables
		this.position = position;
		this.rotation = new Vector3D();
	}
	
	/* The constructor with the position and rotation given */
	public Object3D(Vector3D position, Vector3D rotation) {
		//Assign the variables
		this.position = position;
		this.rotation = rotation;
	}
	
	/* The methods used to set and return the position and rotation */
	public void setPosition(Vector3D position) { this.position = position; }
	public void setRotation(Vector3D rotation) { this.rotation = rotation; }
	public Vector3D getPosition() { return this.position; }
	public Vector3D getRotation() { return this.rotation; }
	
}