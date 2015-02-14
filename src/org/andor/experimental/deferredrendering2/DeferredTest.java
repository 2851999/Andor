/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering2;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.ImageLoader;
import org.andor.core.Object2DBuilder;

import org.andor.utils.OpenGLUtils;

public class DeferredTest extends BaseGame {
	
	public DeferredRenderer renderer;
	
	public void create() {
		DeferredRenderer.texture = ImageLoader.loadImage("H:/Andor/Grass_Side.png", true);
		renderer = new DeferredRenderer();
		renderer.vertices = Object2DBuilder.createQuadV(100, 100);
		renderer.textureCoords = Object2DBuilder.createQuadT(DeferredRenderer.texture);
		renderer.colours = Object2DBuilder.createColourArray(4, Colour.WHITE);
		renderer.drawOrder = Object2DBuilder.createQuadDO();
		renderer.setupBuffers();
	}
	
	public void render() {
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.enableTexture2D();
		renderer.render();
	}
	
	public static void main(String[] args) {
		new DeferredTest();
	}
	
}