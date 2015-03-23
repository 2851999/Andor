/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.render.Renderer;
import org.lwjgl.opengl.GL11;

public class LitScene {
	
	/* The light objects in this scene */
	public List<BaseLight> lights;
	
	/* The interface */
	public LitSceneInterface scene;
	
	/* The constructor */
	public LitScene(LitSceneInterface scene) {
		//Assign the variables
		this.scene = scene;
		this.lights = new ArrayList<BaseLight>();
	}
	
	/* The method used to render this scene */
	public void render() {
		//Render the ambient
		Renderer.useAmbient = true;
		scene.renderObjects();
		Renderer.useAmbient = false;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		GL11.glDepthMask(false);
		GL11.glDepthFunc(GL11.GL_EQUAL);
		//Go through the light objects
		for (int a = 0; a < this.lights.size(); a++) {
			//Use the current light
			this.lights.get(a).use();
			//Render the scene
			scene.renderObjects();
		}
		//Stop using lighting
		Renderer.light = null;
		GL11.glDepthFunc(GL11.GL_LESS);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
}