/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

public class Object2D {
	
	/* The position of this object */
	public Vector2D position;
	
	/* The rotation of this object */
	public float rotation;
	
	/* The scale of this object */
	public Vector2D scale;
	
	/* The width and height of this object */
	public float width;
	public float height;
	
	/* The boolean value that represents whether this
	 * object is linked to another, by default this
	 * should be false */
	public boolean linked = false;
	
	/* The object this is linked to (if any) */
	public Object2D parent = null;
	
	/* Other 3D objects linked with this one */
	private List<Object2D> linkedObjects;
	
	/* The default constructor */
	public Object2D() {
		//Assign the variables
		this.position = new Vector2D();
		this.rotation = 0;
		this.scale = new Vector2D(1, 1);
		this.width = 0;
		this.height = 0;
		this.linkedObjects = new ArrayList<Object2D>();
	}
	
	/* The constructor with the width and height given */
	public Object2D(float width, float height) {
		//Assign the variables
		this.position = new Vector2D();
		this.rotation = 0;
		this.scale = new Vector2D(1, 1);
		this.width = width;
		this.height = height;
		this.linkedObjects = new ArrayList<Object2D>();
	}
	
	/* The constructor with the position, width and height given */
	public Object2D(Vector2D position, float width, float height) {
		//Assign the variables
		this.position = position;
		this.rotation = 0;
		this.scale = new Vector2D(1, 1);
		this.width = width;
		this.height = height;
		this.linkedObjects = new ArrayList<Object2D>();
	}
	
	/* The constructor with the position, rotation width, and height given */
	public Object2D(Vector2D position, float rotation, float width, float height) {
		//Assign the variables
		this.position = position;
		this.rotation = rotation;
		this.scale = new Vector2D(1, 1);
		this.width = width;
		this.height = height;
		this.linkedObjects = new ArrayList<Object2D>();
	}
	
	/* The method used to link an object to this one */
	public void link(Object2D object) {
		//Set the linked value in the object to true
		object.linked = true;
		//Set this object as the 
		object.parent = this;
		//Add the object to the list of linked objects
		this.linkedObjects.add(object);
	}
	
	/* The methods used to set and return the position, rotation, scale, width and height */
	public void setPosition(Vector2D position) { this.position = position; }
	public void setRotation(float rotation) { this.rotation = rotation; }
	public void setScale(Vector2D scale) { this.scale = scale; }
	public void setWidth(float width) { this.width = width; }
	public void setHeight(float height) { this.height = height; }
	
	public Vector2D getPosition() {
		//Make sure this isn't linked to another object
		if (! this.linked)
			return this.position;
		else {
			//Get the position of the parent object
			Vector2D parentPosition = this.parent.getPosition().clone();
			//Multiply by -1 (Invert the parent's position
			parentPosition.multiply(-1f);
			//Add this position onto the parent position
			parentPosition.add(this.position);
			//Return the new position
			return parentPosition;
		}
	}
	
	public float getRotation() {
		return this.rotation;
	}
	
	public Vector2D getScale() {
		return this.scale;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
}