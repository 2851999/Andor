/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureImpl;

public class ImageEntity2D extends Object2D {
	
	/* The boolean that states whether this image should be binded */
	public boolean bind;
	
	/* The image */
	public Image image;
	
	/* The object */
	public RenderableObject2D object;
	
	/* The constructor */
	public ImageEntity2D(Image image) {
		//Assign the variables
		this.image = image;
		this.object = Object2DBuilder.createQuad(this.image, this.image.getWidth(), this.image.getHeight(), Colour.WHITE);
		this.width = object.width;
		this.height = object.height;
		this.bind = true;
		//Link the object to this
		this.link(this.object);
	}
	
	/* The constructor */
	public ImageEntity2D(Image image, RenderableObject2D object) {
		//Assign the variables
		this.image = image;
		this.object = object;
		this.width = object.width;
		this.height = object.height;
		this.bind = true;
		//Link the object to this
		this.link(this.object);
	}
	
	/* The method used to update this entity */
	public void update() {
		
	}
	
	/* The method used to render this entity */
	public void render() {
		if (bind) {
			GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			//Bind the image
			this.image.bind();
			//Render the object
			this.object.render();
			TextureImpl.unbind();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		} else {
			//Render the object
			this.object.render();
		}
	}
	
	/* The set/get methods */
	public void setImage(Image image) {
		//Set the image
		this.image = image;
		//Update the texture coordinates
		this.object.renderer.updateTextures(Object2DBuilder.createQuadT(this.image));
	}
	
	public Image getImage() { return this.image; }
	public RenderableObject2D getObject() { return this.object; }
	
}