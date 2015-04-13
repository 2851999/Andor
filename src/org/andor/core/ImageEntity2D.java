/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.physics.PhysicsObject2D;

public class ImageEntity2D extends PhysicsObject2D {
	
	/* The object */
	public RenderableObject2D object;
	
	/* The constructor */
	public ImageEntity2D(Image image) {
		this(image, Colour.WHITE);
	}
	
	/* The constructor */
	public ImageEntity2D(Image image, Colour colour) {
		this(image, colour, image.getWidth(), image.getHeight());
	}
	
	/* The constructor */
	public ImageEntity2D(Image image, float width, float height) {
		this(image, Colour.WHITE, width, height);
	}
	
	/* The constructor */
	public ImageEntity2D(Image image, Colour colour, float width, float height) {
		//Assign the variables
		this.object = Object2DBuilder.createQuad(image, width, height, colour);
		this.setup(this.object);
		this.setImage(image);
		this.width = object.width;
		this.height = object.height;
	}
	
	/* The method used to update this entity */
	public void update() {
		super.update();
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
	
	public void setColour(Colour colour) {
		this.object.setColour(colour);
	}
	
	public RenderableObject2D getObject() { return this.object; }
	
}