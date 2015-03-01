/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.ObjectBuilderUtils;

public class Object2DBuilder {
	
	/* The static method used to create a textured quad renderable object given the
	 * width, height and colours */
	public static RenderableObject2D createQuad(Image texture, float width, float height, Colour[] colours) {
		return new RenderableObject2D(Renderer.TRIANGLES, createQuadV(width, height), createColourArray(4, colours), createQuadT(texture), createQuadDO(), width, height);
	}
	
	/* The static method used to create a textured quad renderable object given the
	 * width, height and colour */
	public static RenderableObject2D createQuad(Image texture, float width, float height, Colour colour) {
		return new RenderableObject2D(Renderer.TRIANGLES, createQuadV(width, height), createColourArray(4, colour), createQuadT(texture), createQuadDO(), width, height);
	}
	
	/* The static method used to create a quad renderable object given the
	 * width, height and colours */
	public static RenderableObject2D createQuad(float width, float height, Colour[] colours) {
		return new RenderableObject2D(Renderer.TRIANGLES, createQuadV(width, height), createColourArray(4, colours), createQuadDO(), width, height);
	}
	
	/* The static method used to create a quad renderable object given the
	 * width, height and colour */
	public static RenderableObject2D createQuad(float width, float height, Colour colour) {
		return new RenderableObject2D(Renderer.TRIANGLES, createQuadV(width, height), createColourArray(4, colour), createQuadDO(), width, height);
	}
	
	/* The static method used to create a quad renderable object given the
	 * width and height */
	public static RenderableObject2D createQuad(float width, float height) {
		return new RenderableObject2D(Renderer.TRIANGLES, createQuadV(width, height), createQuadDO(), width, height);
	}
	
	/* The static method used to create a quad's vertices array given the width and height */
	public static float[] createQuadV(float width, float height) {
		return createQuadV(
				new Vector2D(0, 0),
				new Vector2D(width, 0),
				new Vector2D(width, height),
				new Vector2D(0, height)
		);
	}
	
	/* The static method used to create a quad's vertices array given the position of each vertex */
	public static float[] createQuadV(Vector2D v1, Vector2D v2, Vector2D v3, Vector2D v4) {
		return new float[] {
			v1.x, v1.y,
			v2.x, v2.y,
			v3.x, v3.y,
			v4.x, v4.y,
		};
	}
	
	/* The static method used to create a quad's texture coordinates */
	public static float[] createQuadT(Image t) {
		return new float[] {
				t.left, t.top,
				t.right, t.top,
				t.right, t.bottom,
				t.left, t.bottom
		};
	}
	
	/* The static method used to create a quad's draw order array given the width and height */
	public static short[] createQuadDO() {
		return new short[] {
			//Bottom-Left triangle
			0, 1, 2,
			//Top-Right triangle
			2, 3, 0
		};
	}
	
	/* The static method used to create the colour array for each vertices given one colour,
	 * and the number of vertices */
	public static float[] createColourArray(int numberOfVertices, Colour colour) {
		return ObjectBuilderUtils.createColourArray(numberOfVertices, colour);
	}
	
	/* The static method used to create the colour array for each vertices given one colour,
	 * and the number of vertices */
	public static float[] createColourArray(int numberOfVertices, Colour[] colours) {
		return ObjectBuilderUtils.createColourArray(numberOfVertices, colours);
	}
	
}