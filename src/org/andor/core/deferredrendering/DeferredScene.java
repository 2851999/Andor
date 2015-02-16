/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.deferredrendering;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.RenderableObject3D;
import org.andor.core.Renderer;
import org.andor.core.Settings;
import org.andor.core.Vector2D;

public class DeferredScene {
	
	/* The objects in the scene */
	public List<RenderableObject2D> objects2D;
	public List<RenderableObject3D> objects3D;

	/* The screen quad */
	public RenderableObject2D screenQuad;
	
	/* The other debugging textured quads for the other buffers */
	public RenderableObject2D positionQuad;
	public RenderableObject2D diffuseQuad;
	public RenderableObject2D normalQuad;
	public RenderableObject2D depthQuad;
	
	/* The constructor */
	public DeferredScene() {
		//Assign the values
		this.objects2D = new ArrayList<RenderableObject2D>();
		this.objects3D = new ArrayList<RenderableObject3D>();
		//Make sure deferred rendering is turned on
		if (Settings.Video.DeferredRendering) {
			this.screenQuad = Object2DBuilder.createQuad(Settings.Window.Width, Settings.Window.Height, Colour.WHITE);
			this.screenQuad.renderer.updateTextures(new float[] {
				0, 1,
				1, 1,
				1, 0,
				0, 0
			});
			this.screenQuad.renderer.texture = new Image(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_DIFFUSE]);
			//Check to see whether the other quads should be set up
			if (Settings.Debugging.ShowDeferredRenderingBuffers) {
				//Setup the quads
				this.positionQuad = Object2DBuilder.createQuad(Settings.Window.Width / 4, Settings.Window.Height / 4, Colour.WHITE);
				this.positionQuad.renderer.updateTextures(new float[] {
					0, 1,
					1, 1,
					1, 0,
					0, 0
				});
				this.positionQuad.renderer.texture = new Image(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_POSITION]);
				this.positionQuad.position = new Vector2D(Settings.Window.Width - (Settings.Window.Width / 4), 0);
				
				this.diffuseQuad = Object2DBuilder.createQuad(Settings.Window.Width / 4, Settings.Window.Height / 4, Colour.WHITE);
				this.diffuseQuad.renderer.updateTextures(new float[] {
					0, 1,
					1, 1,
					1, 0,
					0, 0
				});
				this.diffuseQuad.renderer.texture = new Image(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_DIFFUSE]);
				this.diffuseQuad.position = new Vector2D(Settings.Window.Width - (Settings.Window.Width / 4), Settings.Window.Height / 4);
				
				this.normalQuad = Object2DBuilder.createQuad(Settings.Window.Width / 4, Settings.Window.Height / 4, Colour.WHITE);
				this.normalQuad.renderer.updateTextures(new float[] {
					0, 1,
					1, 1,
					1, 0,
					0, 0
				});
				this.normalQuad.renderer.texture = new Image(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_NORMAL]);
				this.normalQuad.position = new Vector2D(Settings.Window.Width - (Settings.Window.Width / 4), 2 * (Settings.Window.Height / 4));
				
				this.depthQuad = Object2DBuilder.createQuad(Settings.Window.Width / 4, Settings.Window.Height / 4, Colour.WHITE);
				this.depthQuad.renderer.updateTextures(new float[] {
					0, 1,
					1, 1,
					1, 0,
					0, 0
				});
				this.depthQuad.renderer.texture = new Image(Renderer.geometryBuffer.depthTexture);
				this.depthQuad.position = new Vector2D(Settings.Window.Width - (Settings.Window.Width / 4), 3 * (Settings.Window.Height / 4));
			}
		}
	}
	
	/* The methods used to add objects */
	public void add(RenderableObject2D object2D) { this.objects2D.add(object2D); }
	public void add(RenderableObject3D object3D) { this.objects3D.add(object3D); }
	
	/* The method used to start rendering */
	public void startRendering() {
		//Check to see whether deferred rendering is enabled and that this is not on Android
		if (! Settings.AndroidMode && Settings.Video.DeferredRendering)
			//Bind the geometry buffer
			Renderer.geometryBuffer.bindWriting();
	}
	
	/* The method used to stop rendering */
	public void stopRendering() {
		//Check to see whether deferred rendering is enabled and that this is not on Android
		if (! Settings.AndroidMode && Settings.Video.DeferredRendering)
			//Unbind the geometry buffer
			Renderer.geometryBuffer.unbind();
	}
	
	/* The methods used to render the objects to the geometry buffer */
	public void render2DObjects() {
		for (int a = 0; a < this.objects2D.size(); a++)
			this.objects2D.get(a).render();
	}
	
	public void render3DObjects() {
		for (int a = 0; a < this.objects3D.size(); a++)
			this.objects3D.get(a).render();
	}
	
	/* The method used to render the geometry buffer to the screen */
	public void renderToScreen() {
		//Turn on deferred rendering mode
		Renderer.deferredRender = true;
		//Make sure the quad has been defined
		if (this.screenQuad != null) {
			//Render the screen quad
			this.screenQuad.render();
			Renderer.deferredNormalRender = true;
			//Check to see whether the other quads should be rendered
			if (Settings.Debugging.ShowDeferredRenderingBuffers) {
				this.positionQuad.render();
				this.diffuseQuad.render();
				this.normalQuad.render();
				this.depthQuad.render();
			}
			Renderer.deferredNormalRender =  false;
		}
		//Turn off deferred rendering mode
		Renderer.deferredRender = false;
	}
	
}
