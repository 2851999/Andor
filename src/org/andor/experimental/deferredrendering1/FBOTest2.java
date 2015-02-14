/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering1;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Object2DBuilder;
import org.andor.utils.OpenGLUtils;

public class FBOTest2 extends BaseGame {
	
	/* The pointer to the buffer */
	public int pointer;
	
	/* The test values */
	public int colourBufferPointer;
	public int depthBufferPointer;
	
	public DeferredRenderer renderer;
	
	/* The constructor */
	public FBOTest2() {
		
	}
	
	/* The method used to setup this frame buffer */
	public void create() {
		renderer = new DeferredRenderer();
		renderer.vertices = Object2DBuilder.createQuadV(100, 100);
		renderer.colours = Object2DBuilder.createColourArray(4, Colour.WHITE);
		renderer.drawOrder = Object2DBuilder.createQuadDO();
		renderer.setupBuffers();
	}
	
	/* The method used to render the test */
	public void render() {
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupOrtho(-1, 1);
		renderer.render();
	}
	
	public static void main(String[] args) {
		new FBOTest2();
	}
	
}