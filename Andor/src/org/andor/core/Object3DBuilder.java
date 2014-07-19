/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.lwjgl.opengl.GL11;

public class Object3DBuilder {
	
	/* The static method used to create a textured cube renderable object array given the
	 * width, height and colours */
	public static RenderableObject3D createTexturedCube(float width, float height, float depth, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colours), createCubeT());
	}
	
	/* The static method used to create a cube quad renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createTexturedCube(float width, float height, float depth, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colour), createCubeT());
	}
	
	/* The static method used to create a cube renderable object array given the
	 * width, height and colours */
	public static RenderableObject3D createCube(float width, float height, float depth, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colours));
	}
	
	/* The static method used to create a cube renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createCube(float width, float height, float depth, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colour));
	}
	
	/* The static method used to create a cube renderable object array given the
	 * width and height */
	public static RenderableObject3D createCube(float width, float height, float depth) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth));
	}
	
	/* The static method used to create a cube's texture coordinates */
	public static float[] createCubeT() {
		return new float[] {
				//Front face
				0, 0,
				1, 0,
				1, 1,
				0, 1,
				
				//Left face
				1, 1,
				0, 1,
				0, 0,
				1, 0,
				
				//Back face
				1, 0,
				0, 0,
				0, 1,
				1, 1,
				
				//Bottom face
				1, 0,
				1, 1,
				0, 1,
				0, 0,
				
				//Right face
				1, 1,
				0, 1,
				0, 0,
				1, 0,
				
				//Top face
				1, 0,
				1, 1,
				0, 1,
				0, 0
		};
	}
	
	/* The static method used to create a cube's vertices array given the width, height and depth */
	public static float[] createCubeV(float width, float height, float depth) {
		return new float[] {
				//Front face
				-width, height, depth,
				width, height, depth,
				width, -height, depth,
				-width, -height, depth,
				
				//Left face
				-width, -height, depth,
				-width, -height, -depth,
				-width, height, -depth,
				-width, height, depth,
				
				//Back face
				-width, height, -depth,
				width, height, -depth,
				width, -height, -depth,
				-width, -height, -depth,
				
				//Bottom face
				width, -height, -depth,
				width, -height, depth,
				-width, -height, depth,
				-width, -height, -depth,
				
				//Right face
				width, -height, -depth,
				width, -height, depth,
				width, height, depth,
				width, height, -depth,
				
				//Top face
				-width, height, -depth,
				-width, height, depth,
				width, height, depth,
				width, height, -depth
			};
	}
	
	/* The static method used to create a textured quad renderable object array given the
	 * width, height and colours */
	public static RenderableObject3D createTexturedQuad(float width, float height, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height), createColourArray(4, colours), createQuadT());
	}
	
	/* The static method used to create a textured quad renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createTexturedQuad(float width, float height, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height), createColourArray(4, colour), createQuadT());
	}
	
	/* The static method used to create a quad renderable object array given the
	 * width, height and colours */
	public static RenderableObject3D createQuad(float width, float height, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height), createColourArray(4, colours));
	}
	
	/* The static method used to create a quad renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createQuad(float width, float height, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height), createColourArray(4, colour));
	}
	
	/* The static method used to create a quad renderable object array given the
	 * width and height */
	public static RenderableObject3D createQuad(float width, float height) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height));
	}
	
	/* The static method used to create a vertices array given the width and height */
	public static float[] createQuadV(float width, float height) {
		return createQuadV(
				new Vector3D(width, height, 0),
				new Vector3D(-width, height, 0),
				new Vector3D(-width, -height, 0),
				new Vector3D(width, -height, 0)
		);
	}
	
	/* The static method used to create a quads vertices array given the position of each vertex */
	public static float[] createQuadV(Vector3D v1, Vector3D v2, Vector3D v3, Vector3D v4) {
		return new float[] {
			v1.x, v1.y, v1.z,
			v2.x, v2.y, v2.z,
			v3.x, v3.y, v3.z,
			v4.x, v4.y, v4.z
		};
	}
	
	/* The static method used to create a quads texture coordinates */
	public static float[] createQuadT() {
		return new float[] {
				0, 0,
				1, 0,
				1, 1,
				0, 1
		};
	}
	
	/* The static method used to create the colour array for each vertices given one colour,
	 * and the number of vertices */
	public static float[] createColourArray(int numberOfVertices, Colour colour) {
		//Get the colours float array
		float[] currentColourArray = colour.getValuesRGBA();
		//Create the float array with the right amount of values
		float[] colourArray = new float[numberOfVertices * 4];
		//Go through the colour array
		for (int a = 0; a < colourArray.length; a += 4) {
			//Set the current colour values
			colourArray[a] = currentColourArray[0];
			colourArray[a + 1] = currentColourArray[1];
			colourArray[a + 2] = currentColourArray[2];
			colourArray[a + 3] = currentColourArray[3];
		}
		//Return the colour array
		return colourArray;
	}
	
	/* The static method used to create the colour array for each vertices given one colour,
	 * and the number of vertices */
	public static float[] createColourArray(int numberOfVertices, Colour[] colours) {
		//The current colour
		int currentColour = 0;
		//Create the float array with the right amount of values
		float[] colourArray = new float[numberOfVertices * 4];
		//Go through the colour array
		for (int a = 0; a < colourArray.length; a += 4) {
			//Get the current colour
			float[] currentColourArray = colours[currentColour].getValuesRGBA();
			//Set the current colour values
			colourArray[a] = currentColourArray[0];
			colourArray[a + 1] = currentColourArray[1];
			colourArray[a + 2] = currentColourArray[2];
			colourArray[a + 3] = currentColourArray[3];
			//Check to see whether there is another colour
			if (currentColour >= colours.length - 1)
				//Reset the current colour
				currentColour = 0;
			else
				//Increment the current colour
				currentColour++;
		}
		//Return the colour array
		return colourArray;
	}
	
}