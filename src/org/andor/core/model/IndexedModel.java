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

import org.andor.core.Vector2D;
import org.andor.core.Vector3D;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.core.model.Material;

public class IndexedModel {
	
	/* The data within this model */
	private List<Vector3D> vertices;
	private List<Vector3D> normals;
	private List<Vector2D> textureCoordinates;
	public List<Material> materials;
	
	/* The constructor */
	public IndexedModel() {
		//Assign the variables
		this.vertices = new ArrayList<Vector3D>();
		this.normals = new ArrayList<Vector3D>();
		this.textureCoordinates = new ArrayList<Vector2D>();
		this.materials = new ArrayList<Material>();
	}
	
	/* The methods used to determine whether a certain part of the model is defined or not */
	public boolean hasNormals() { return this.normals.size() > 0; }
	public boolean hasTextureCoordinates() { return this.textureCoordinates.size() > 0; }
	
	/* The methods used to add data to this model */
	public void addVertex(Vector3D vertex) { this.vertices.add(vertex); }
	public void addNormal(Vector3D normal) { this.normals.add(normal); }
	public void addTextureCoordinate(Vector2D textureCoordinate) { this.textureCoordinates.add(textureCoordinate); }
	public void addMaterial(Material material) { this.materials.add(material); }
	
	/* The methods used to get a certain piece of data given its index */
	public Vector3D getVertex(int index) { return this.vertices.get(index); }
	public Vector3D getNormal(int index) { return this.normals.get(index); }
	public Vector2D getTextureCoordinate(int index) { return this.textureCoordinates.get(index); }
	public Material getMaterial(int index) { return this.materials.get(index); }
	
	/* The method used to get the location of a material given its name */
	public int getMaterialLocation(String name) {
		//The location
		int location = -1;
		//Go through the materials
		for (int a = 0; a < this.materials.size(); a++) {
			//Check the name of the current material
			if (this.materials.get(a).name.equals(name)) {
				//Assign the location
				location = a;
				//Exit the loop
				break;
			}
		}
		//Check the location
		if (location == -1)
			//Log an error
			Logger.log("IndexedModel getMaterialLocation()", "Could not find the material with the name " + name, Log.ERROR);
		//Return the value
		return location;
	}
	
	/* The method used to get a material given its name */
	public Material getMaterial(String name) {
		return this.getMaterial(this.getMaterialLocation(name));
	}
	
}