/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.RenderableObject2D;

public class RenderablePhysicsObject2D extends PhysicsObject2D {
	
	/* The renderable object */
	private RenderableObject2D object;
	
	/* The constructor */
	public RenderablePhysicsObject2D(RenderableObject2D object)  {
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
	public void setObject(RenderableObject2D object) { this.object = object; }
	public RenderableObject2D getObject() { return this.object; }
	
}