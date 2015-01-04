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
import org.andor.core.Renderer;
import org.andor.core.Vector3D;

public class Model extends RenderableObject3D {
	
	/* The vertices in the model */
	public List<Vector3D> vertices;

	/* The normals in the model */
	public List<Vector3D> normals;

	/* The texture coordinates */
	public List<Vector3D> textures;

	/* The faces in the model */
	public List<Face> faces;

	/* The materials in the model */
	public List<Material> materials;
	
	/* The current values */
	private int currentVertex;
	private int currentNormal;
	private int currentTexture;
	
	/* The default constructor */
	public Model() {
		//Assign the variables
		this.vertices = new ArrayList<Vector3D>();
		this.normals = new ArrayList<Vector3D>();
		this.textures = new ArrayList<Vector3D>();
		this.faces = new ArrayList<Face>();
		this.materials = new ArrayList<Material>();
	}

	/* The constructor with the vertices, normals, texture coordinate and faces given */
	public Model(List<Vector3D> vertices, List<Vector3D> normals, List<Vector3D> textures, List<Face> faces, List<Material> materials) {
		//Assign the variables
		this.vertices = vertices;
		this.normals = normals;
		this.textures = textures;
		this.faces = faces;
		this.materials = materials;
	}
	
	/* The method used to prepare this model */
	public void prepare() {
		//Set the current values
		this.currentVertex = 0;
		this.currentNormal = 0;
		this.currentTexture = 0;
		//Create the renderer
		this.renderer = Renderer.create(Renderer.TRIANGLES, Renderer.VERTEX_VALUES_COUNT_3D);
		//Prepare the values
		this.renderer.verticesData = new float[this.calculateNumberOfVertices() * Renderer.VERTEX_VALUES_COUNT_3D];
		//Check to see whether the normals have been set
		if (this.normals.size() > 0)
			this.renderer.normalsData = new float[this.calculateNumberOfNormals() * Renderer.VERTEX_VALUES_COUNT_3D];
		//Check to see whether the textures have been set
		if (this.textures.size() > 0)
			this.renderer.textureData = new float[this.calculateNumberOfVertices() * 2];
		//Go through the faces
		for (int a = 0; a < this.faces.size(); a++)
			//Prepare the current face
			this.prepareFace(this.faces.get(a), a);
		//Setup the buffers
		this.renderer.setupBuffers();
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
			Vector3D n = this.normals.get(face.normals.get(a).intValue() - 1);
			//Set the normal values
			this.renderer.normalsData[this.currentNormal] = n.x;
			this.renderer.normalsData[this.currentNormal + 1] = n.y;
			this.renderer.normalsData[this.currentNormal + 2] = n.z;
		}
		
		//Check to see whether the textures for this face exists
		if (face.textures != null) {
			//Get the texture
			Vector3D t = this.textures.get(face.textures.get(a).intValue() - 1);
			//Set the texture values
			this.renderer.textureData[this.currentTexture] = t.x;
			this.renderer.textureData[this.currentTexture + 1] = t.y;
		}
		
		//Get the vertex
		Vector3D v = this.vertices.get(face.vertices.get(a).intValue() - 1);
		//Set the vertex values
		this.renderer.verticesData[this.currentVertex] = v.x;
		this.renderer.verticesData[this.currentVertex + 1] = v.y;
		this.renderer.verticesData[this.currentVertex + 2] = v.z;
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
	
	/* The method used to get a material using its name */
	public Material getMaterial(String name) {
		//The material
		Material material = null;
		//Go through the list
		loop: for (int a = 0; a < this.materials.size(); a++) {
			//Check the name of the current material in the list
			if (this.materials.get(a).name.equals(name)) {
				//Set the material
				material = this.materials.get(a);
				//Break out of the loop
				break loop;
			}
		}
		//Return the material
		return material;
	}
	
	/* The methods used to add a certain value */
	public void addVertex(Vector3D vertex) { this.vertices.add(vertex); }
	public void addNormal(Vector3D normal) { this.normals.add(normal); }
	public void addTexture(Vector3D texture) { this.textures.add(texture); }
	public void addFace(Face face) { this.faces.add(face); }
	public void addMaterial(Material material) { this.materials.add(material); }

	/* The 'setter' and 'getter' methods */
	public void setVertices(List<Vector3D> vertices) { this.vertices = vertices; }
	public void setNormals(List<Vector3D> normals) { this.normals = normals; }
	public void setTextures(List<Vector3D> textures) { this.textures = textures; }
	public void setFaces(List<Face> faces) { this.faces = faces; }
	public void setMaterials(List<Material> materials) { this.materials = materials; }
	public List<Vector3D> getVertices() { return this.vertices; }
	public List<Vector3D> getNormals() { return this.normals; }
	public List<Vector3D> getTextures() { return this.textures; }
	public List<Face> getFaces() { return this.faces; }
	public List<Material> getMaterials() { return this.materials; }

	
}