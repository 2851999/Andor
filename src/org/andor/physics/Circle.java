/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;

public class Circle {
	
	/* The radius */
	private float radius;
	
	/* The centre of this circle */
	private Vector2D centre;
	
	/* The constructor */
	public Circle(Vector2D centre, float radius) {
		//Assign the variables
		this.centre = centre;
		this.radius = radius;
	}
	
	/* The getters and setters */
	public void setRadius(float radius) { this.radius = radius; }
	public void setCentre(Vector2D centre) { this.centre = centre; }
	public void setCentre(float x, float y) { this.centre = new Vector2D(x, y); }
	public float getRadius() { return this.radius; }
	public Vector2D getCentre() { return this.centre; }
	public float getDiameter() { return this.radius + this.radius; }
	
}