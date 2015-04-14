/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.RenderableObject3D;

public class RenderablePhysicsObject3D extends PhysicsObject3D {
	
	/* The renderable object */
	private RenderableObject3D object;
	
	/* The constructor */
	public RenderablePhysicsObject3D(RenderableObject3D object)  {
		super(object);
		//Assign the variables
		this.object = object;
	}
	
	/* The method used to render this object */
	public void render() {
		//Render the object
		this.object.render();
	}
	
	/* The getters and setters */
	public void setObject(RenderableObject3D object) { this.object = object; }
	public RenderableObject3D getObject() { return this.object; }
	
}