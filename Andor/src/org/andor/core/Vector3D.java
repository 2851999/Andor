/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.lwjgl.util.vector.Vector3f;

public class Vector3D {
	
	/* The different values (x, y, z) */
	public float x;
	public float y;
	public float z;
	
	/* The default constructor */
	public Vector3D() {
		//Assign the variables
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/* The constructor with the values given */
	public Vector3D(float x, float y, float z) {
		//Assign the variables
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/* The constructor with another vector given */
	public Vector3D(Vector3D vector) {
		//Assign the variables
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	/* The methods used to perform calculations given other vectors */
	public void add(Vector3D vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
	}
	
	public void subtract(Vector3D vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
	}
	
	public void multiply(Vector3D vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
	}
	
	public void divide(Vector3D vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
	}
	
	/* The methods used to perform calculations given a single value */
	public void add(float amount) {
		this.x += amount;
		this.y += amount;
		this.z += amount;
	}
	
	public void subtract(float amount) {
		this.x -= amount;
		this.y -= amount;
		this.z -= amount;
	}
	
	public void multiply(float amount) {
		this.x *= amount;
		this.y *= amount;
		this.z *= amount;
	}
	
	public void divide(float amount) {
		this.x /= amount;
		this.y /= amount;
		this.z /= amount;
	}
	
	/* The method used to return a normalised version of this vector */
	public Vector3D normalise() {
		//The Vector
		Vector3f out = new Vector3f();
		//This vector
		Vector3f in = new Vector3f(this.x, this.y, this.z);
		//Normalise the vector
		in.normalise(out);
		//Return the result
		return new Vector3D(out.x, out.y, out.z);
	}
	
	/* The method used to create a new vector with the same values as
	 * this one and then return it (Clones this vector) */
	public Vector3D clone() {
		return new Vector3D(this);
	}
	
	/* The method used to set and get the different values */
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setZ(float z) { this.z = z; }
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }
	public float[] getValues() { return new float[] { this.x, this.y, this.z }; }
	
}