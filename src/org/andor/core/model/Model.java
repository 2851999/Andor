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

	/* The pars in the model */
	public List<ModelPart> parts;

	/* The materials in the model */
	public List<Material> materials;
	
	/* The default constructor */
	public Model() {
		//Assign the variables
		this.vertices = new ArrayList<Vector3D>();
		this.normals = new ArrayList<Vector3D>();
		this.textures = new ArrayList<Vector3D>();
		this.parts = new ArrayList<ModelPart>();
		this.materials = new ArrayList<Material>();
	}

	/* The constructor with the vertices, normals, texture coordinate and faces given */
	public Model(List<Vector3D> vertices, List<Vector3D> normals, List<Vector3D> textures, List<ModelPart> parts, List<Material> materials) {
		//Assign the variables
		this.vertices = vertices;
		this.normals = normals;
		this.textures = textures;
		this.parts = parts;
		this.materials = materials;
	}
	
	/* The method used to prepare this model */
	public void prepare() {
		//Create the renderer
		this.renderer = Renderer.create(Renderer.TRIANGLES, Renderer.VERTEX_VALUES_COUNT_3D);
		//Prepare the values
		this.renderer.vertices = new float[this.calculateNumberOfVertices() * Renderer.VERTEX_VALUES_COUNT_3D];
		//Check to see whether the normals have been set
		if (this.normals.size() > 0)
			this.renderer.normals = new float[this.calculateNumberOfNormals() * Renderer.VERTEX_VALUES_COUNT_3D];
		//Check to see whether the textures have been set
		if (this.textures.size() > 0)
			this.renderer.textureCoords = new float[this.calculateNumberOfVertices() * 2];
		//Go through the parts
		for (int a = 0; a < this.parts.size(); a++)
			//Prepare the current part
			this.parts.get(a).prepare();
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to render this object */
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
	
	/* The method used to find out the total amount of vertices that are needed */
	public int calculateNumberOfVertices() {
		//The current total
		int total = 0;
		//Go though each part
		for (int a = 0; a < this.parts.size(); a++)
			//Add onto the total
			total += this.parts.get(a).calculateNumberOfVertices();
		//Return the total
		return total;
	}
	
	/* The method used to find out the total amount of normals that are needed */
	public int calculateNumberOfNormals() {
		//The current total
		int total = 0;
		//Go though each part
		for (int a = 0; a < this.parts.size(); a++)
			//Add onto the total
			total += this.parts.get(a).calculateNumberOfNormals();
		//Return the total
		return total;
	}
	
	/* The method used to find out the total amount of textures that are needed */
	public int calculateNumberOfTextures() {
		//The current total
		int total = 0;
		//Go though each part
		for (int a = 0; a < this.parts.size(); a++)
			//Add onto the total
			total += this.parts.get(a).calculateNumberOfTextures();
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
	public void addPart(ModelPart part) { this.parts.add(part); }
	public void addMaterial(Material material) { this.materials.add(material); }

	/* The 'setter' and 'getter' methods */
	public void setVertices(List<Vector3D> vertices) { this.vertices = vertices; }
	public void setNormals(List<Vector3D> normals) { this.normals = normals; }
	public void setTextures(List<Vector3D> textures) { this.textures = textures; }
	public void setParts(List<ModelPart> parts) { this.parts = parts; }
	public void setMaterials(List<Material> materials) { this.materials = materials; }
	public List<Vector3D> getVertices() { return this.vertices; }
	public List<Vector3D> getNormals() { return this.normals; }
	public List<Vector3D> getTextures() { return this.textures; }
	public List<ModelPart> getParts() { return this.parts; }
	public List<Material> getMaterials() { return this.materials; }
	public int getNumberOfFaces() {
		int total = 0;
		for (int a = 0; a < this.parts.size(); a++)
			total += this.parts.get(a).faces.size();
		return total;
	}

	
}