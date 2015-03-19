/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

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
	
	/* The method used to get the normalised vector */
	public Vector2D normalise() {
		//Get the length
		float length = this.getLength();
		//Return the new vector
		return new Vector2D(this.x / length, this.y / length);
	}
	
	/* The method used to create a new vector with the same values as
	 * this one and then return it (Clones this vector) */
	public Vector2D clone() {
		return new Vector2D(this);
	}
	
	/* The method used to set and get the different values */
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float[] getValues() { return new float[] { this.x, this.y }; }
	
}