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
import org.andor.core.Vector2D;
import org.andor.core.Vector3D;
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
	public void prepare(boolean calculateTangents) {
		//The current positions
		int cV = 0;
		int cN = 0;
		int cT = 0;
		int cT2 = 0;
		//Figure out what values should be used
		boolean useNormals = getFace(0).getVertex(0).hasNormal();
		boolean useTextureCoordinates = getFace(0).getVertex(0).hasTextureCoordinate();
		//Create the arrays if necessary
		float vertices[] = new float[this.faces.size() * 3 * 3];
		float normals[] = new float[vertices.length];
		float textureCoordinates[] = new float[this.faces.size() * 3 * 2];
		float tangents[] = new float[vertices.length];
		//Calculate the tangents if necessary
		if (calculateTangents)
			this.calculateTangents();
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
				
				//Check to see whether the texture coordinates should be used
				if (calculateTangents) {
					//Assign the tangent values
					tangents[cT2] = currentVertex.getTangent().getX();
					tangents[cT2 + 1] = currentVertex.getTangent().getY();
					tangents[cT2 + 2] = currentVertex.getTangent().getZ();
					cT2 += 3;
				}
			}
		}
		//Assign the values
		this.vertices = vertices;
		if (useNormals)
			this.normals = normals;
		if (useTextureCoordinates)
			this.textureCoords = textureCoordinates;
		if (calculateTangents)
			this.tangents = tangents;
		//Assign the colours
		this.colours = Object3DBuilder.createColourArray(this.vertices.length, Colour.WHITE);
		//Setup this renderer
		this.setupBuffers();
	}
	
	/* The method used to calculate the tangents */
	public void calculateTangents() {
		//Go through the faces
		for (int a = 0; a < this.faces.size(); a++) {
			//Get the 3 vertices for the current face
			ModelVertex vertex0 = this.faces.get(a).getVertex(0);
			ModelVertex vertex1 = this.faces.get(a).getVertex(1);
			ModelVertex vertex2 = this.faces.get(a).getVertex(2);
			//Get the values
			Vector3D v0 = vertex0.getVertex();
			Vector3D v1 = vertex1.getVertex();
			Vector3D v2 = vertex2.getVertex();
			
			Vector2D uv0 = vertex0.getTextureCoordinate();
			Vector2D uv1 = vertex1.getTextureCoordinate();
			Vector2D uv2 = vertex2.getTextureCoordinate();
			
			Vector3D edge1 = v1.subtractNew(v0);
			Vector3D edge2 = v2.subtractNew(v0);
			
			float deltaU1 = uv1.x - uv0.x;
			float deltaV1 = uv1.y - uv0.y;
			float deltaU2 = uv2.x - uv0.x;
			float deltaV2 = uv2.y - uv0.y;
			
			float f = 1.0f / (deltaU1 * deltaV2 - deltaU2 * deltaV1);
			Vector3D tangent = new Vector3D();
			
			tangent.x = f * (deltaV2 * edge1.x - deltaV1 * edge2.x);
			tangent.y = f * (deltaV2 * edge1.y - deltaV1 * edge2.y);
			tangent.z = f * (deltaV2 * edge1.z - deltaV1 * edge2.z);
			
			if (vertex0.tangent == null)
				vertex0.tangent = new Vector3D();
			if (vertex1.tangent == null)
				vertex1.tangent = new Vector3D();
			if (vertex2.tangent == null)
				vertex2.tangent = new Vector3D();
			
			vertex0.tangent.add(tangent);
			vertex1.tangent.add(tangent);
			vertex2.tangent.add(tangent);
		}
		for (int a = 0; a < this.faces.size(); a++) {
			for (int b = 0; b < this.faces.get(a).vertices.size(); b++) {
				this.faces.get(a).vertices.get(b).tangent = this.faces.get(a).vertices.get(b).tangent.normalised();
			}
		}
	}
	
	/* The setter and getters */
	public void addFace(ModelFace face) { this.faces.add(face); }
	public List<ModelFace> getFaces() { return this.faces; }
	public ModelFace getFace(int index) { return this.faces.get(index); }
	
}