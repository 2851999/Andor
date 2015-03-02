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
import org.andor.core.Renderer;
import org.andor.core.Vector3D;

public class ModelPart {
	
	/* The model */
	public Model model;
	
	/* The material */
	public Material material;

	/* The faces in the model */
	public List<Face> faces;
	
	/* The current values */
	private int currentVertex;
	private int currentNormal;
	private int currentTexture;
	
	/* The renderer */
	public Renderer renderer;
	
	/* The default constructor */
	public ModelPart(Model model) {
		//Assign the variables
		this.model = model;
		this.faces = new ArrayList<Face>();
	}

	/* The constructor with the vertices, normals, texture coordinate and faces given */
	public ModelPart(Model model, List<Face> faces, Material material) {
		//Assign the variables
		this.model = model;
		this.faces = faces;
		this.material = material;
	}
	
	/* The method used to render this object */
	public void render() {
		this.renderer.render();
	}
	
	/* The method used to prepare this model */
	public void prepare() {
		//Create the renderer
		this.renderer = Renderer.create(Renderer.TRIANGLES, Renderer.VERTEX_VALUES_COUNT_3D);
		//Prepare the values
		this.renderer.vertices = new float[this.calculateNumberOfVertices() * Renderer.VERTEX_VALUES_COUNT_3D];
		//Check to see whether the normals have been set
		if (this.model.normals.size() > 0)
			this.renderer.normals = new float[this.calculateNumberOfNormals() * Renderer.VERTEX_VALUES_COUNT_3D];
		//Check to see whether the textures have been set
		if (this.model.textures.size() > 0)
			this.renderer.textureCoords = new float[this.calculateNumberOfVertices() * 2];
		//Assign the colours
		this.renderer.colours = Object3DBuilder.createColourArray(this.renderer.vertices.length, Colour.WHITE);
		//Go through the faces
		for (int a = 0; a < this.faces.size(); a++)
			//Prepare the current face
			this.prepareFace(this.faces.get(a), a);
		//Setup the buffers
		this.renderer.setupBuffers();
		//Make sure the material has been assigned
		if (this.material != null)
			//Assign the material
			this.renderer.setMaterial(this.material);
	}
	
	/* The method used to prepare a face */
	public void prepareFace(Face face, int current) {
		//Go through the vertices in this face
		for (int a = 0; a < face.vertices.size(); a++) {
			//Prepare the current vertex
			prepareFaceVertex(face, a);
			//Add on to the current values
			this.currentVertex += Renderer.VERTEX_VALUES_COUNT_3D;
			this.currentNormal += Renderer.VERTEX_VALUES_COUNT_3D;
			this.currentTexture += 2;
		}
	}
	
	/* The method used to prepare a face's vertex */
	public void prepareFaceVertex(Face face, int a) {
		//Check to see whether the normals for this face exists
		if (face.normals != null) {
			//Get the normal
			Vector3D n = this.model.normals.get(face.normals.get(a).intValue() - 1);
			//Set the normal values
			this.renderer.normals[this.currentNormal] = n.x;
			this.renderer.normals[this.currentNormal + 1] = n.y;
			this.renderer.normals[this.currentNormal + 2] = n.z;
		}
		
		//Check to see whether the textures for this face exists
		if (face.textures != null) {
			//Get the texture
			Vector3D t = this.model.textures.get(face.textures.get(a).intValue() - 1);
			//Set the texture values
			this.renderer.textureCoords[this.currentTexture] = t.x;
			this.renderer.textureCoords[this.currentTexture + 1] = t.y;
		}
		
		//Get the vertex
		Vector3D v = this.model.vertices.get(face.vertices.get(a).intValue() - 1);
		//Set the vertex values
		this.renderer.vertices[this.currentVertex] = v.x;
		this.renderer.vertices[this.currentVertex + 1] = v.y;
		this.renderer.vertices[this.currentVertex + 2] = v.z;
	}
	
	/* The method used to find out the total amount of vertices that are needed */
	public int calculateNumberOfVertices() {
		//The current total
		int total = 0;
		//Go though each face
		for (int a = 0; a < this.faces.size(); a++)
			//Add onto the total
			total += this.faces.get(a).getVertices().size();
		//Return the total
		return total;
	}
	
	/* The method used to find out the total amount of normals that are needed */
	public int calculateNumberOfNormals() {
		//The current total
		int total = 0;
		//Go though each face
		for (int a = 0; a < this.faces.size(); a++)
			//Add onto the total
			total += this.faces.get(a).getNormals().size();
		//Return the total
		return total;
	}
	
	/* The method used to find out the total amount of textures that are needed */
	public int calculateNumberOfTextures() {
		//The current total
		int total = 0;
		//Go though each face
		for (int a = 0; a < this.faces.size(); a++)
			//Add onto the total
			total += this.faces.get(a).getTextures().size();
		//Return the total
		return total;
	}
	
	/* The methods used to add a certain value */
	public void addFace(Face face) { this.faces.add(face); }

	/* The 'setter' and 'getter' methods */
	public void setFaces(List<Face> faces) { this.faces = faces; }
	public void setMaterial(Material material) { this.material = material; }
	public List<Face> getFaces() { return this.faces; }
	public Material getMaterial() { return this.material; }
	
}