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

public class Object3D {
	
	/* The position of this object */
	public Vector3D position;
	
	/* The rotation of this object */
	public Vector3D rotation;
	
	/* The scale of this object */
	public Vector3D scale;
	
	/* The width, height and depth of this object */
	public float width;
	public float height;
	public float depth;
	
	/* The boolean value that represents whether this
	 * object is linked to another, by default this
	 * should be false */
	public boolean linked = false;
	
	/* The object this is linked to (if any) */
	public Object3D parent = null;
	
	/* Other 3D objects linked with this one */
	private List<Object3D> linkedObjects;
	
	/* The default constructor */
	public Object3D() {
		//Assign the variables
		this.position = new Vector3D();
		this.rotation = new Vector3D();
		this.scale = new Vector3D(1, 1, 1);
		this.width = 0;
		this.height = 0;
		this.depth = 0;
		this.linkedObjects = new ArrayList<Object3D>();
	}
	
	/* The constructor with the width, height and depth given */
	public Object3D(float width, float height, float depth) {
		//Assign the variables
		this.position = new Vector3D();
		this.rotation = new Vector3D();
		this.scale = new Vector3D(1, 1, 1);
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.linkedObjects = new ArrayList<Object3D>();
	}
	
	/* The constructor with the position, width, height and depth given */
	public Object3D(Vector3D position, float width, float height, float depth) {
		//Assign the variables
		this.position = position;
		this.rotation = new Vector3D();
		this.scale = new Vector3D(1, 1, 1);
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.linkedObjects = new ArrayList<Object3D>();
	}
	
	/* The constructor with the position, rotation, width, height and depth given */
	public Object3D(Vector3D position, Vector3D rotation, float width, float height, float depth) {
		//Assign the variables
		this.position = position;
		this.rotation = rotation;
		this.scale = new Vector3D(1, 1, 1);
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.linkedObjects = new ArrayList<Object3D>();
	}
	
	/* The method used to link an object to this one */
	public void link(Object3D object) {
		//Set the linked value in the object to true
		object.linked = true;
		//Set this object as the 
		object.parent = this;
		//Add the object to the list of linked objects
		this.linkedObjects.add(object);
	}
	
	/* The methods used to set and return the position, rotation and scale */
	public void setPosition(Vector3D position) { this.position = position; }
	public void setRotation(Vector3D rotation) { this.rotation = rotation; }
	public void setScale(Vector3D scale) { this.scale = scale; }
	
	public Vector3D getPosition() {
		//Make sure this isn't linked to another object
		if (! this.linked)
			return this.position;
		else {
			//Get the position of the parent object
			Vector3D parentPosition = this.parent.getPosition().clone();
			//Add this position onto the parent position
			parentPosition.add(this.position);
			//Return the new position
			return parentPosition;
		}
	}
	
	public Vector3D getRotation() {
		//Make sure this isn't linked to another object
		if (! this.linked)
			return this.rotation;
		else {
			//Get the rotation of the parent object
			Vector3D parentRotation = this.parent.getRotation().clone();
			//Add this rotation onto the parent rotation
			parentRotation.add(this.rotation);
			//Return the new position
			return parentRotation;
		}
	}
	
	public Vector3D getScale() {
		//Make sure this isn't linked to another object
		if (! this.linked)
			return this.scale;
		else {
			//Get the scale of the parent object
			Vector3D parentScale = this.parent.getScale().clone();
			//Multiply this scale by the parent scale
			parentScale.multiply(this.scale);
			//Return the new scale
			return parentScale;
		}
	}
	
}