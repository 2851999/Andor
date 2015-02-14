/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering3;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.ImageLoader;
import org.andor.core.Object2DBuilder;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

public class DeferredTest extends BaseGame {
	
	public DeferredRenderer renderer;
	public DeferredRenderer renderer2;
	
	public void create() {
		DeferredRenderer.texture = ImageLoader.loadImage("H:/Andor/Grass_Side.png", true);
		renderer = new DeferredRenderer();
		renderer.vertices = Object2DBuilder.createQuadV(300, 300);
		renderer.textureCoords = Object2DBuilder.createQuadT(DeferredRenderer.texture);
		renderer.colours = Object2DBuilder.createColourArray(4, Colour.ARRAY_RGB);
		renderer.drawOrder = Object2DBuilder.createQuadDO();
		renderer.setupBuffers();
		
		renderer2 = new DeferredRenderer();
		renderer2.vertices = Object2DBuilder.createQuadV(Settings.Window.Width, Settings.Window.Height);
		renderer2.textureCoords = new float[] {
			0, 1,
			1, 1,
			1, 0,
			0, 0
		};
		renderer2.colours = Object2DBuilder.createColourArray(4, Colour.WHITE);
		renderer2.drawOrder = Object2DBuilder.createQuadDO();
		renderer2.setupBuffers();
	}
	
	public void render() {
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.enableTexture2D();
		renderer.renderGeom();
		int a = DeferredRenderer.texture.textureId;
		DeferredRenderer.texture.textureId = renderer.gBuffer.textures[1];
		renderer2.render();
		DeferredRenderer.texture.textureId = a;
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Andor - Deferred Rendering";
		new DeferredTest();
	}
	
}