/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.Object2D;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class GUIComponentRenderer {
	
	/* The colours */
	public Colour[] colours;
	
	/* The images */
	public Image[] images;
	
	/* The inactive colour */
	public Colour inactiveColour;
	
	/* The inactive image */
	public Image inactiveImage;
	
	/* The index value for the images / colours */
	public int componentIndex;
	
	/* The entity this renders */
	public RenderableObject2D entity;
	
	/* The default font */
	public Font font;
	
	/* The constructor */
	public GUIComponentRenderer(RenderableObject2D entity) {
		//Assign the variables
		this.entity = entity;
		this.font = FontUtils.createFont("Arial", 12);
		//Make sure the entity has been set
		if (this.entity != null) {
			//Setup the entity
			this.entity.renderer.usage = GL15.GL_DYNAMIC_DRAW;
			this.entity.renderer.setupBuffers();
		}
	}
	
	/* The method used to render the entity given a Object2D to base its position off of */
	public void render(Object2D object, boolean active) {
		//Make sure the entity has been set
		if (this.entity != null) {
			//Assign the entities values
			this.entity.position = object.position;
			this.entity.rotation = object.rotation;
			this.entity.scale = object.scale;
			this.entity.width = object.width;
			this.entity.height = object.height;
			
			//Save the current enabled features
			GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			
			//Make sure the component is active
			if (active) {
				//Check whether images or colours should be used
				if (this.shouldUseImages()) {
					//Enable textures
					OpenGLUtils.enableTexture2D();
					//Bind the correct image
					this.images[this.componentIndex].bind();
					//Check to see whether the colours have also been set
					if (this.shouldUseColours())
						//Update the colours (Assumes the GUI component is 4 - Sided)
						this.entity.renderer.updateColours(Object2DBuilder.createColourArray(4, this.colours[this.componentIndex]));
				} else if (this.shouldUseColours()) {
					//Update the colours (Assumes the GUI component is 4 - Sided)
					this.entity.renderer.updateColours(Object2DBuilder.createColourArray(4, this.colours[this.componentIndex]));
					//Disable textures
					GL11.glDisable(GL11.GL_TEXTURE_2D);
				}
			} else {
				//Check to see whether the inactive texture has been set
				if (this.inactiveImage != null) {
					//Enable textures
					OpenGLUtils.enableTexture2D();
					//Bind the inactive image
					this.inactiveImage.bind();
					//Check to see whether the colours have also been set
					if (this.shouldUseColours())
						//Update the colour (Assumes the GUI component is 4 - Sided)
						this.entity.renderer.updateColours(Object2DBuilder.createColourArray(4, this.inactiveColour));
				} else if (this.inactiveColour != null) {
					//Update the colour (Assumes the GUI component is 4 - Sided)
					this.entity.renderer.updateColours(Object2DBuilder.createColourArray(4, this.inactiveColour));
					//Disable textures
					GL11.glDisable(GL11.GL_TEXTURE_2D);
				}
			}
			//Render the entity
			this.entity.render();
			
			//Revert the enabled features
			GL11.glPopAttrib();
		}
	}
	
	/* The methods used to set/return values */
	public void set(Image[] images) { this.images = images; }
	public void set(Colour[] colours) { this.colours = colours; }
	public void setComponentIndex(int componentIndex) { this.componentIndex = componentIndex; }
	public void setFont(Font font) { this.font = font; }
	public Image[] getImages() { return this.images; }
	public Colour[] getColours() { return this.colours; }
	public int getComponentIndex() { return this.componentIndex; }
	public Font getFont() { return this.font; }
	public boolean shouldUseImages() { return this.images != null; }
	public boolean shouldUseColours() { return this.colours != null; }
	
	public int getTotalComponents() {
		if (this.shouldUseImages())
			return this.images.length;
		else if (this.shouldUseColours())
			return this.colours.length;
		else
			return 0;
	}
	
}