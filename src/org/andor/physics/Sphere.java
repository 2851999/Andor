/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector3D;

public class Sphere {
	
	/* The radius */
	private float radius;
	
	/* The centre of this circle */
	private Vector3D centre;
	
	/* The constructor */
	public Sphere(Vector3D centre, float radius) {
		//Assign the variables
		this.centre = centre;
		this.radius = radius;
	}
	
	/* The getters and setters */
	public void setRadius(float radius) { this.radius = radius; }
	public void setCentre(Vector3D centre) { this.centre = centre; }
	public void setCentre(float x, float y, float z) { this.centre = new Vector3D(x, y, z); }
	public float getRadius() { return this.radius; }
	public Vector3D getCentre() { return this.centre; }
	public float getDiameter() { return this.radius + this.radius; }
	
}