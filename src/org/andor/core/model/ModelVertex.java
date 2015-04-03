/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import org.andor.core.Vector2D;
import org.andor.core.Vector3D;

public class ModelVertex {
	
	/* The data stored in this vertex */
	public Vector3D vertex;
	public Vector3D normal;
	public Vector2D textureCoordinate;
	public Vector3D tangent;
	
	/* The constructor */
	public ModelVertex() {
		//Assign the default values
		this.vertex = new Vector3D();
	}
	
	/* The constructor */
	public ModelVertex(Vector3D vertex) {
		//Assign the variables
		this.vertex = vertex;
	}
	
	/* The methods used to check whether certain data about this vertex exists */
	public boolean hasNormal() { return this.normal != null; }
	public boolean hasTextureCoordinate() { return this.textureCoordinate != null; }
	
	/* The getter and setter methods */
	public void setVertex(Vector3D vertex) { this.vertex = vertex; }
	public void setNormal(Vector3D normal) { this.normal = normal; }
	public void setTextureCoordinate(Vector2D textureCoordinate) { this.textureCoordinate = textureCoordinate; }
	public Vector3D getVertex() { return this.vertex; }
	public Vector3D getNormal() { return this.normal; }
	public Vector2D getTextureCoordinate() { return this.textureCoordinate; }
	public Vector3D getTangent() { return this.tangent; }
	
}