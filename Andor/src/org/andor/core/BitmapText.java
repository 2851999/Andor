/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

import org.andor.utils.ArrayUtils;
import org.lwjgl.opengl.GL11;

public class BitmapText extends RenderableObject2D {
	
	/* The image */
	public Image image;
	
	/* The font size */
	public float fontSize;
	
	/* The number of cells on the width */
	public int gridWidth;
	
	/* The number of cells on the height */
	public int gridHeight;
	
	/* The width of a cell */
	public float cellWidth;
	
	/* The height of a cell */
	public float cellHeight;
	
	/* The current text */
	public String currentText;
	
	/* The constructor of the font */
	public BitmapText(Image image , float fontSize, int gridSize) {
		//Load the font image
		this.image = image;
		//Set the grid size
		this.gridWidth = gridSize;
		this.gridHeight = gridSize;
		//Set the size
		this.fontSize = fontSize;
		//Create the renderer
		this.renderer = new Renderer(GL11.GL_QUADS, Renderer.VERTEX_VALUES_COUNT_2D);
		this.renderer.setValues(Object2DBuilder.createQuadV(1, 1), Object2DBuilder.createColourArray(4, Colour.WHITE), Object2DBuilder.createQuadT(image));
		this.renderer.setupBuffers();
	}
	
	/* The method that updates the text */
	public void update(String text) {
		//Set the current text
		this.currentText = text;
		List<Float> vertices = new ArrayList<Float>();
		float[] colours = Object2DBuilder.createColourArray(text.length() * 4, Colour.WHITE);
		List<Float> textures = new ArrayList<Float>();
		float x = 0;
		//Loop though the text
		for (int a = 0; a < text.length(); a++) {
			//Get the ASCII code of the character
			int asciiCode = (int) text.charAt(a);
			//Calculate the cell's size and set it
			this.cellWidth = this.image.getWidth() / this.gridWidth;
			this.cellHeight = this.image.getHeight() / this.gridHeight;
			
			//Calculate the cell's x position
			float cellX = ((int) asciiCode % this.gridWidth) * this.cellWidth;
			//Calculate the cell's y position
			float cellY = (float) ((Math.floor((int) asciiCode / this.gridHeight)) * this.cellHeight);
			
			//Add the vertices
			vertices.add(this.position.x + x);
			vertices.add(this.position.y);
			vertices.add(this.position.x + x + (this.cellWidth / this.cellHeight) * this.fontSize);
			vertices.add(this.position.y);
			vertices.add(this.position.x + x + (this.cellWidth / this.cellHeight) * this.fontSize);
			vertices.add(this.position.y + this.fontSize);
			vertices.add(this.position.x + x);
			vertices.add(this.position.y + this.fontSize);
			
			//Add the textures
			textures.add(cellX / this.image.getWidth());
			textures.add(cellY / this.image.getHeight());
			textures.add((cellX + cellWidth) / this.image.getWidth());
			textures.add(cellY / this.image.getHeight());
			textures.add((cellX + cellWidth) / this.image.getWidth());
			textures.add((cellY + cellHeight) / this.image.getHeight());
			textures.add(cellX / this.image.getWidth());
			textures.add((cellY + cellHeight) / this.image.getHeight());
			
			x += ((this.cellWidth / this.cellHeight) * this.fontSize) / 1.5f;
		}
		//Update the renderer
		this.renderer.updateVertices(ArrayUtils.toFloatArray(vertices));
		this.renderer.updateColours(colours);
		this.renderer.updateTextures(ArrayUtils.toFloatArray(textures));
	}
	
	/* The method to get the width of a string */
	public float getWidth(String text) {
		//The width of the text
		float width = 0;
		//Calculate the cell's size and set it
		this.cellWidth = this.image.getWidth() / this.gridWidth;
		this.cellHeight = this.image.getHeight() / this.gridHeight;
		//Set the width to the cellSize times the number of letters
		width = (text.length() *  (((this.cellWidth / this.cellHeight) * this.fontSize) / 1.5f));
		return width;
	}
	
	/* The method to get the height of a string */
	public float getHeight(String text) {
		//The height of the text
		float height = 0;
		//The height is the font size
		height = this.fontSize;
		return height;
	}
	
	/* The get/set methods */
	public String getCurrentText() { return this.currentText; }
	
}