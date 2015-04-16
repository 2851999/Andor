/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.MathUtils;

public class Vector2D {
	
	/* The different values (x, y) */
	public float x;
	public float y;
	
	/* The default constructor */
	public Vector2D() {
		//Assign the variables
		this.x = 0;
		this.y = 0;
	}
	
	/* The constructor with a value given */
	public Vector2D(float value) {
		//Assign the variables
		this.x = value;
		this.y = value;
	}
	
	/* The constructor with the values given */
	public Vector2D(float x, float y) {
		//Assign the variables
		this.x = x;
		this.y = y;
	}
	
	/* The constructor with another vector given */
	public Vector2D(Vector2D vector) {
		//Assign the variables
		this.x = vector.x;
		this.y = vector.y;
	}
	
	/* The methods used to perform calculations given other vectors */
	public void add(Vector2D vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	public void subtract(Vector2D vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	public void multiply(Vector2D vector) {
		this.x *= vector.x;
		this.y *= vector.y;
	}
	
	public void divide(Vector2D vector) {
		this.x /= vector.x;
		this.y /= vector.y;
	}
	
	/* The methods used to perform calculations given a single value */
	public void add(float amount) {
		this.x += amount;
		this.y += amount;
	}
	
	public void subtract(float amount) {
		this.x -= amount;
		this.y -= amount;
	}
	
	public void multiply(float amount) {
		this.x *= amount;
		this.y *= amount;
	}
	
	public void divide(float amount) {
		this.x /= amount;
		this.y /= amount;
	}
	
	/* The methods used to perform calculations given other vectors when returning the result */
	public Vector2D addNew(Vector2D vector) {
		return new Vector2D(this.x + vector.x, this.y + vector.y);
	}
	
	public Vector2D subtractNew(Vector2D vector) {
		return new Vector2D(this.x - vector.x, this.y - vector.y);
	}
	
	public Vector2D multiplyNew(Vector2D vector) {
		return new Vector2D(this.x * vector.x, this.y * vector.y);
	}
	
	public Vector2D divideNew(Vector2D vector) {
		return new Vector2D(this.x / vector.x, this.y / vector.y);
	}
	
	/* The methods used to perform calculations given a single value when returning the result */
	public Vector2D addNew(float amount) {
		return new Vector2D(this.x + amount, this.y + amount);
	}
	
	public Vector2D subtractNew(float amount) {
		return new Vector2D(this.x - amount, this.y - amount);
	}
	
	public Vector2D multiplyNew(float amount) {
		return new Vector2D(this.x * amount, this.y * amount);
	}
	
	public Vector2D divideNew(float amount) {
		return new Vector2D(this.x / amount, this.y / amount);
	}
	
	/* The method used to get the length of this vector */
	public float getLength() {
		//Return the length
		return (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	/* The method used to get the dot product */
	public float dot(Vector2D vector) {
		//Return the value
		return x * vector.x + y * vector.y ;
	}
	
	/* The method used to get the crossed vector product */
	public float cross(Vector2D vector) {
		//Return the value
		return x * vector.y - y * vector.x;
	}
	
	/* The method used to get the normalised vector */
	public Vector2D normalised() {
		//Get the length
		float length = this.getLength();
		//Return the new vector
		return new Vector2D(this.x / length, this.y / length);
	}
	
	/* The method used to get the minimum value of this vector */
	public float min() {
		return MathUtils.min(this.x, this.y);
	}
	
	/* The method used to get the maximum value of this vector */
	public float max() {
		return MathUtils.max(this.x, this.y);
	}
	
	/* The method used to get the minimum value out of this and another given vector */
	public Vector2D min(Vector2D other) {
		return new Vector2D(MathUtils.min(this.x, other.x), MathUtils.min(this.y, other.y));
	}
	
	/* The method used to get the maximum value out of this and another given vector */
	public Vector2D max(Vector2D other) {
		return new Vector2D(MathUtils.max(this.x, other.x), MathUtils.max(this.y, other.y));
	}
	
	/* The method used to reflect this value given the normal */
	public Vector2D reflect(Vector2D normal) {
		return this.subtractNew(normal.multiplyNew(this.dot(normal) * 2));
	}
	
	/* The method used to create a new vector with the same values as
	 * this one and then return it (Clones this vector) */
	public Vector2D clone() {
		return new Vector2D(this);
	}
	
	/* The method used to return a string representation of this object */
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	public static Vector2D fromString(String string) {
		String[] values = string.substring(1, string.length() - 2).split(",");
		return new Vector2D(Float.parseFloat(values[0]), Float.parseFloat(values[1]));
	}
	
	/* The method used to set and get the different values */
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float[] getValues() { return new float[] { this.x, this.y }; }
	
}