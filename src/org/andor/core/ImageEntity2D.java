/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class ImageEntity2D extends Object2D {
	
	/* The object */
	public RenderableObject2D object;
	
	/* The constructor */
	public ImageEntity2D(Image image) {
		//Assign the variables
		this.object = Object2DBuilder.createQuad(image, image.getWidth(), image.getHeight(), Colour.WHITE);
		this.setImage(image);
		this.width = object.width;
		this.height = object.height;
		//Link the object to this
		this.link(this.object);
	}
	
	/* The method used to update this entity */
	public void update() {
		
	}
	
	/* The method used to render this entity */
	public void render() {
		//Render the object
		this.object.render();
	}
	
	/* The set/get methods */
	public void setImage(Image image) {
		//Set the image
		this.object.setTexture(image);
		//Update the texture coordinates
		this.object.renderer.updateTextures(Object2DBuilder.createQuadT(image));
	}
	
	public RenderableObject2D getObject() { return this.object; }
	
}