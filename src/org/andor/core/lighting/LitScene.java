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

import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.render.Renderer;
import org.andor.utils.GLUtils;
import org.andor.utils.shader.ShaderCode;
import org.lwjgl.opengl.GL11;

public class LitScene {
	
	/* The ambient shader code */
	public static ShaderCode shaderCode;
	
	/* The default shader */
	public static Shader defaultShader;
	
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
		Renderer.getPass().customShader = defaultShader;
		Renderer.useAmbient = true;
		scene.renderObjects();
		Renderer.useAmbient = false;
		GLUtils.enable(GL11.GL_BLEND);
		GLUtils.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
		GLUtils.depthMask(false);
		GLUtils.depthFunc(GL11.GL_EQUAL);
		//Go through the light objects
		for (int a = 0; a < this.lights.size(); a++) {
			//Use the current light
			this.lights.get(a).use();
			//Render the scene
			scene.renderObjects();
		}
		//Stop using lighting
		Renderer.light = null;
		//Reset the custom shader
		Renderer.getPass().customShader = null;
		GLUtils.depthFunc(GL11.GL_LESS);
		GLUtils.depthMask(true);
		GLUtils.disable(GL11.GL_BLEND);
	}
	
	/* The static method used to setup the shaders necessary */
	public static void setupShaders() {
		//Check to see whether the shader needs to be setup
		if (shaderCode == null) {
			//Create the shader code
			shaderCode = new ShaderCode(Settings.Resources.Shaders.FORWARD_AMBIENT_LIGHT);
			shaderCode.load();
		}
		if (defaultShader == null)
			defaultShader = shaderCode.createDefault();
	}
	
}