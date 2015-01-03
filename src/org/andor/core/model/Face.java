/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import java.util.ArrayList;
import java.util.List;

public class Face {
	
	/* The vertices in this face */
	public List<Float> vertices;
	
	/* The normals in this face */
	public List<Float> normals;
	
	/* The texture coordinates in this face */
	public List<Float> textures;
	
	/* The material of this face */
	public Material material;
	
	/* The default constructor */
	public Face() {
		//Assign the variables
		this.vertices = new ArrayList<Float>();
		this.normals = new ArrayList<Float>();
		this.textures = new ArrayList<Float>();
	}
	
	/* The methods used to set and return certain values */
	public void setVertices(List<Float> vertices) { this.vertices = vertices; }
	public void setNormals(List<Float> normals) { this.normals = normals; }
	public void setTextures(List<Float> textures) { this.textures = textures; }
	public void setMaterial(Material material) { this.material = material; }
	public List<Float> getVertices() { return this.vertices; }
	public List<Float> getNormals() { return this.normals; }
	public List<Float> getTextures() { return this.textures; }
	public Material getMaterial() { return this.material; }
	
}