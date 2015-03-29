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

import org.andor.core.Colour;
import org.andor.core.Object3DBuilder;
import org.andor.core.model.Material;
import org.andor.core.render.Renderer;

public class ModelPart extends Renderer {
	
	/* The faces in this part of the model */
	public List<ModelFace> faces;
	
	/* The constructor */
	public ModelPart(int renderMode) {
		//Invoke the super constructor
		super(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Assign the default values
		this.faces = new ArrayList<ModelFace>();
		this.material = new Material("Default");
	}
	
	/* The method used to prepare this face for rendering */
	public void prepare() {
		//The current positions
		int cV = 0;
		int cN = 0;
		int cT = 0;
		//Figure out what values should be used
		boolean useNormals = getFace(0).getVertex(0).hasNormal();
		boolean useTextureCoordinates = getFace(0).getVertex(0).hasTextureCoordinate();
		//Create the arrays if necessary
		float vertices[] = new float[this.faces.size() * 3 * 3];
		float normals[] = new float[vertices.length];
		float textureCoordinates[] = new float[this.faces.size() * 3 * 2];
		//Go through each face
		for (int a = 0; a < this.faces.size(); a ++) {
			//Get the current face
			ModelFace currentFace = this.faces.get(a);
			//Go through the current face's vertices
			for (int b = 0; b < currentFace.getVertices().size(); b++) {
				//Get the current vertex
				ModelVertex currentVertex = currentFace.getVertex(b);
				//Assign the vertices values
				vertices[cV] = currentVertex.getVertex().getX();
				vertices[cV + 1] = currentVertex.getVertex().getY();
				vertices[cV + 2] = currentVertex.getVertex().getZ();
				//Increment the current vertex
				cV += 3;
				
				//Check to see whether the normals should be used
				if (useNormals) {
					//Assign the normal values
					normals[cN] = currentVertex.getNormal().getX();
					normals[cN + 1] = currentVertex.getNormal().getY();
					normals[cN + 2] = currentVertex.getNormal().getZ();
					//Increment the current normal
					cN += 3;
				}
				
				//Check to see whether the texture coordinates should be used
				if (useTextureCoordinates) {
					//Assign the texture coordinate values
					textureCoordinates[cT] = currentVertex.getTextureCoordinate().getX();
					textureCoordinates[cT + 1] = currentVertex.getTextureCoordinate().getY();
					//Increment the current texture coordinate
					cT += 2;
				}
			}
		}
		//Assign the values
		this.vertices = vertices;
		if (useNormals)
			this.normals = normals;
		if (useTextureCoordinates)
			this.textureCoords = textureCoordinates;
		//Assign the colours
		this.colours = Object3DBuilder.createColourArray(this.vertices.length, Colour.WHITE);
		//Setup this renderer
		this.setupBuffers();
	}
	
	/* The setter and getters */
	public void addFace(ModelFace face) { this.faces.add(face); }
	public List<ModelFace> getFaces() { return this.faces; }
	public ModelFace getFace(int index) { return this.faces.get(index); }
	
}