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
	
	/* The static method used to create a muti-textured cube renderable object array given the
	 * width, height and colours */
	public static RenderableObject3D createCube(Image[] textures, float width, float height, float depth, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colours), createCubeT(textures));
	}
	
	/* The static method used to create a multi-cube renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createCube(Image[] textures, float width, float height, float depth, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colour), createCubeT(textures));
	}
	
	/* The static method used to create a textured cube renderable object array given the
	 * width, height and colours */
	public static RenderableObject3D createCube(Image texture, float width, float height, float depth, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colours), createCubeT(texture));
	}
	
	/* The static method used to create a textured cube renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createCube(Image texture, float width, float height, float depth, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createCubeV(width, height, depth), createColourArray(24, colour), createCubeT(texture));
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
	
	/* The static method used to create a cube's texture coordinates with multiple textures */
	public static float[] createCubeT(Image[] t) {
		return new float[] {
				//Front face
				t[0].left, t[0].top,
				t[0].right, t[0].top,
				t[0].right, t[0].bottom,
				t[0].left, t[0].bottom,
				
				//Left face
				t[2].right, t[2].bottom,
				t[2].left, t[2].bottom,
				t[2].left, t[2].top,
				t[2].right, t[2].top,
				
				//Back face
				t[1].right, t[1].top,
				t[1].left, t[1].top,
				t[1].left, t[1].bottom,
				t[1].right, t[1].bottom,
				
				//Bottom face
				t[5].right, t[5].top,
				t[5].right, t[5].bottom,
				t[5].left, t[5].top,
				t[5].left, t[5].bottom,
				
				//Right face
				t[3].right, t[3].bottom,
				t[3].left, t[3].bottom,
				t[3].left, t[3].top,
				t[3].right, t[3].top,
				
				//Top face
				t[4].right, t[4].top,
				t[4].right, t[4].bottom,
				t[4].left, t[4].bottom,
				t[4].left, t[4].top
		};
	}
	
	/* The static method used to create a cube's texture coordinates with a single texture */
	public static float[] createCubeT(Image t) {
		return new float[] {
				//Front face
				t.left, t.top,
				t.right, t.top,
				t.right, t.bottom,
				t.left, t.bottom,
				
				//Left face
				t.right, t.bottom,
				t.left, t.bottom,
				t.left, t.top,
				t.right, t.top,
				
				//Back face
				t.right, t.top,
				t.left, t.top,
				t.left, t.bottom,
				t.right, t.bottom,
				
				//Bottom face
				t.right, t.top,
				t.right, t.bottom,
				t.left, t.top,
				t.left, t.bottom,
				
				//Right face
				t.right, t.bottom,
				t.left, t.bottom,
				t.left, t.top,
				t.right, t.top,
				
				//Top face
				t.right, t.top,
				t.right, t.bottom,
				t.left, t.bottom,
				t.left, t.top
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
	public static RenderableObject3D createQuad(Image texture, float width, float height, Colour[] colours) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height), createColourArray(4, colours), createQuadT(texture));
	}
	
	/* The static method used to create a textured quad renderable object array given the
	 * width, height and colour */
	public static RenderableObject3D createQuad(Image texture, float width, float height, Colour colour) {
		return new RenderableObject3D(GL11.GL_QUADS, createQuadV(width, height), createColourArray(4, colour), createQuadT(texture));
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
	public static float[] createQuadT(Image t) {
		return new float[] {
				t.left, t.top,
				t.right, t.top,
				t.right, t.bottom,
				t.left, t.bottom
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