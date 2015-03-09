/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.examples;

/*
 * Import all of the required classes
 */
import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

/*
 * This example demonstrates how you load and render
 * a textured quad
 */

public class TextureExample extends BaseGame {
	
	public RenderableObject2D quad;
	
	public void create() {
		//To create apply a texture to a quad, you first need to load the image
		//that you want to apply to it. You can do this by using the ImageLoader
		//class
		//The code below will load the Andor icon from the resources folder
		//The first value is the path to the location of it, and the second
		//represents whether the texture is 'external' i.e. not in the project
		//files. On Android, when false, the image will be loaded from the
		//assets
		Image texture = ImageLoader.loadImage("/resources/andor/Icon32.png", false);
		
		//Create the quad with the texture, if you want the texture to render
		//with its original colours, you should always make sure the colour
		//of the quad is white
		quad = Object2DBuilder.createQuad(texture, 100, 100, Colour.WHITE);
		
		//Next, to make rendering simple we set the quad's texture
		//This can also be done by using texture.bind() just before rendering
		//the quad
		quad.setTexture(texture);
		
		quad.setPosition(200, 200);
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		//This is the same as giving the window's width/height values
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.setupRemoveAlpha();
		
		//One thing to note when rendering a texture, is that in order to
		//see the whole thing render, you must make sure textures are
		//enabled by using
		OpenGLUtils.enableTexture2D();
		//However if you then render something without a texture, you
		//should disable it otherwise the colour will still be affected
		//by the texture
		
		quad.render();
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Texture Example";
		new TextureExample();
	}
	
}