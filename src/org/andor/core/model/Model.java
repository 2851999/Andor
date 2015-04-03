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

import org.andor.core.RenderableObject3D;

public class Model extends RenderableObject3D {
	
	/* The model parts in this model */
	public List<ModelPart> parts;
	
	/* The constructor */
	public Model() {
		//Assign the variables
		this.parts = new ArrayList<ModelPart>();
	}
	
	/* The constructor */
	public Model(List<ModelPart> parts) {
		//Assign the variables
		this.parts = parts;
	}
	
	/* The method used to setup this model for rendering */
	public void prepare() {
		this.prepare(false);
	}
	
	/* The method used to setup this model for rendering */
	public void prepare(boolean calculateTangents) {
		//Go through the model parts
		for (int a = 0; a < this.parts.size(); a++)
			//Render the current part
			this.parts.get(a).prepare(calculateTangents);
	}
	
	/* The method used to render this model */
	public void render() {
		//Update the view matrix
		this.updateViewMatrix();
		//Go through each part
		for (int a = 0; a < this.parts.size(); a++)
			//Render the current part
			this.parts.get(a).render();
		//Restore the view matrix
		this.restoreViewMatrix();
	}
	
	/* The setters and getters */
	public void addPart(ModelPart part) { this.parts.add(part); }
	public List<ModelPart> getParts() { return this.parts; }
	public ModelPart getPart(int index) { return this.parts.get(index); }
	
	public int getNumberOfPolygons() {
		//The count
		int count = 0;
		//Go through each part
		for (int a = 0; a < this.parts.size(); a++)
			//Add the number of polygons in the current face to the counter
			count += this.parts.get(a).getFaces().size();
		//Return the counter
		return count;
	}
	
}