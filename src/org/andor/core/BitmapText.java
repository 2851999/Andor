/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.render.Renderer;
import org.andor.utils.ArrayUtils;

public class BitmapText extends RenderableObject2D {
	
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
	
	/* The colours */
	public Colour[] colour;
	
	/* The constructor of the font */
	public BitmapText(Image image, int gridSize, float fontSize, Colour[] colour) {
		//Set the colour
		this.colour = colour;
		//Set the grid size
		this.gridWidth = gridSize;
		this.gridHeight = gridSize;
		//Set the size
		this.fontSize = fontSize;
		//Create the renderer
		this.renderer = new Renderer(Renderer.TRIANGLES, Renderer.VERTEX_VALUES_COUNT_2D);
		this.renderer.setValues(Object2DBuilder.createQuadV(1, 1), Object2DBuilder.createColourArray(4, Colour.WHITE), Object2DBuilder.createQuadT(image), Object2DBuilder.createQuadDO());
		this.renderer.setupBuffers();
		//Load the font image
		this.setTexture(image);
	}
	
	/* The constructor of the font */
	public BitmapText(Image image, int gridSize, float fontSize, Colour colour) {
		this(image, gridSize, fontSize, new Colour[] { colour });
	}
	
	/* The constructor of the font */
	public BitmapText(Image image, int gridSize, float fontSize) {
		this(image, gridSize, fontSize, new Colour[] { Colour.WHITE });
	}
	
	/* The method that updates the text */
	public void update(String text) {
		//Set the current text
		this.currentText = text;
		List<Float> vertices = new ArrayList<Float>();
		List<Float> textures = new ArrayList<Float>();
		List<Short> drawOrder = new ArrayList<Short>();
		float x = 0;
		//Loop though the text
		for (int a = 0; a < text.length(); a++) {
			//Get the ASCII code of the character
			int asciiCode = (int) text.charAt(a);
			//Calculate the cell's size and set it
			this.cellWidth = this.renderer.material.diffuseTextureMap.getWidth() / this.gridWidth;
			this.cellHeight = this.renderer.material.diffuseTextureMap.getHeight() / this.gridHeight;
			
			//Calculate the cell's x position
			float cellX = ((int) asciiCode % this.gridWidth) * this.cellWidth;
			//Calculate the cell's y position
			float cellY = (float) ((Math.floor((int) asciiCode / this.gridHeight)) * this.cellHeight);
			
			//Add the vertices
			vertices.add(x);
			vertices.add(0f);
			vertices.add(x + (this.cellWidth / this.cellHeight) * this.fontSize);
			vertices.add(0f);
			vertices.add(x + (this.cellWidth / this.cellHeight) * this.fontSize);
			vertices.add(this.fontSize);
			vertices.add(x);
			vertices.add(this.fontSize);
			
			//Add the textures
			textures.add(cellX / this.renderer.material.diffuseTextureMap.getWidth());
			textures.add(cellY / this.renderer.material.diffuseTextureMap.getHeight());
			textures.add((cellX + cellWidth) / this.renderer.material.diffuseTextureMap.getWidth());
			textures.add(cellY / this.renderer.material.diffuseTextureMap.getHeight());
			textures.add((cellX + cellWidth) / this.renderer.material.diffuseTextureMap.getWidth());
			textures.add((cellY + cellHeight) / this.renderer.material.diffuseTextureMap.getHeight());
			textures.add(cellX / this.renderer.material.diffuseTextureMap.getWidth());
			textures.add((cellY + cellHeight) / this.renderer.material.diffuseTextureMap.getHeight());
			
			//Get the draw order
			short[] cdrawOrder = Object2DBuilder.createQuadDO();
			//Add the draw order onto the draw order list
			drawOrder.add((short) ((a * 4) + cdrawOrder[0]));
			drawOrder.add((short) ((a * 4) + cdrawOrder[1]));
			drawOrder.add((short) ((a * 4) + cdrawOrder[2]));
			drawOrder.add((short) ((a * 4) + cdrawOrder[3]));
			drawOrder.add((short) ((a * 4) + cdrawOrder[4]));
			drawOrder.add((short) ((a * 4) + cdrawOrder[5]));
			
			x += ((this.cellWidth / this.cellHeight) * this.fontSize) / 1.5f;
		}
		//Update the renderer
		this.renderer.updateVertices(ArrayUtils.toFloatArray(vertices));
		this.renderer.updateColours(Object2DBuilder.createColourArray(text.length() * 4, this.colour));
		this.renderer.updateTextures(ArrayUtils.toFloatArray(textures));
		this.renderer.updateDrawOrder(ArrayUtils.toShortArray(drawOrder));
	}
	
	/* The method to get the width of a string */
	public float getWidth(String text) {
		//The width of the text
		float width = 0;
		//Calculate the cell's size and set it
		this.cellWidth = this.renderer.material.diffuseTextureMap.getWidth() / this.gridWidth;
		this.cellHeight = this.renderer.material.diffuseTextureMap.getHeight() / this.gridHeight;
		//Set the width to the cellSize times the number of letters
		width = (text.length() *  (((this.cellWidth / this.cellHeight) * this.fontSize) / 1.5f));
		return width;
	}
	
	/* The method to get the height of a string */
	public float getHeight(String text) {
		//Return the height of the text
		return this.fontSize;
	}
	
	/* The get/set methods */
	public void setColour(Colour[] colour) {
		this.colour = colour;
		this.renderer.updateColours(Object2DBuilder.createColourArray(this.currentText.length() * 4, this.colour));
	}
	public void setColour(Colour colour) {
		this.setColour(new Colour[] { colour });
	}
	public String getCurrentText() { return this.currentText; }
	public Colour[] getColour() { return this.colour; }
	
}