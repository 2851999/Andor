/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.MathUtils;

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
	
	/* The constructor with a value given */
	public Vector3D(float value) {
		//Assign the variables
		this.x = value;
		this.y = value;
		this.z = value;
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
	
	/* The constructor with a 2D vector given */
	public Vector3D(Vector2D vector) {
		//Assign the variables
		this.x = vector.x;
		this.y = vector.y;
		this.z = 0;
	}
	
	/* The constructor with a 2D vector and z value given */
	public Vector3D(Vector2D vector, float z) {
		//Assign the variables
		this.x = vector.x;
		this.y = vector.y;
		this.z = z;
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
	
	/* The methods used to perform calculations given other vectors when returning the result */
	public Vector3D addNew(Vector3D vector) {
		return new Vector3D(this.x + vector.x, this.y + vector.y, this.z + vector.z);
	}
	
	public Vector3D subtractNew(Vector3D vector) {
		return new Vector3D(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}
	
	public Vector3D multiplyNew(Vector3D vector) {
		return new Vector3D(this.x * vector.x, this.y * vector.y, this.z * vector.z);
	}
	
	public Vector3D divideNew(Vector3D vector) {
		return new Vector3D(this.x / vector.x, this.y / vector.y, this.z / vector.z);
	}
	
	/* The methods used to perform calculations given a single value when returning the result */
	public Vector3D addNew(float amount) {
		return new Vector3D(this.x + amount, this.y + amount, this.z + amount);
	}
	
	public Vector3D subtractNew(float amount) {
		return new Vector3D(this.x - amount, this.y - amount, this.z - amount);
	}
	
	public Vector3D multiplyNew(float amount) {
		return new Vector3D(this.x * amount, this.y * amount, this.z * amount);
	}
	
	public Vector3D divideNew(float amount) {
		return new Vector3D(this.x / amount, this.y / amount, this.z / amount);
	}
	
	/* The method used to get the length of this vector */
	public float getLength() {
		//Return the length
		return (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
	}
	
	/* The method used to get the normalised vector */
	public Vector3D normalised() {
		//Get the length
		float length = this.getLength();
		//Return the new vector
		return new Vector3D(this.x / length, this.y / length, this.z / length);
	}
	
	/* The method used to get the dot product */
	public float dot(Vector3D vector) {
		//Return the value
		return x * vector.x + y * vector.y + z * vector.z;
	}
	
	/* The method used to get the crossed vector product */
	public Vector3D cross(Vector3D vector) {
		//Return the vector
		return new Vector3D(
			y * vector.z - z * vector.y,
			z * vector.x - x * vector.z,
			x * vector.y - y * vector.x
		);
	}
	
	/* The method used to get the minimum value of this vector */
	public float min() {
		return MathUtils.min(MathUtils.min(this.x, this.y), this.z);
	}
	
	/* The method used to get the maximum value of this vector */
	public float max() {
		return MathUtils.max(MathUtils.max(this.x, this.y), this.z);
	}
	
	/* The method used to get the minimum value out of this and another given vector */
	public Vector3D min(Vector3D other) {
		return new Vector3D(MathUtils.min(this.x, other.x), MathUtils.min(this.y, other.y), MathUtils.min(this.z, other.z));
	}
	
	/* The method used to get the maximum value out of this and another given vector */
	public Vector3D max(Vector3D other) {
		return new Vector3D(MathUtils.max(this.x, other.x), MathUtils.max(this.y, other.y), MathUtils.max(this.z, other.z));
	}
	
	/* The method used to create a new vector with the same values as
	 * this one and then return it (Clones this vector) */
	public Vector3D clone() {
		return new Vector3D(this);
	}
	
	/* The method used to return a string representation of this object */
	public String toString() {
		return "(" + this.x + "," + this.y + "," + this.z + ")";
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