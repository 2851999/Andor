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

import org.andor.core.Matrix;
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
	
	/* The shadow map */
	public ShadowMap shadowMap;
	
	/* The constructor */
	public LitScene(LitSceneInterface scene) {
		//Assign the variables
		this.scene = scene;
		this.lights = new ArrayList<BaseLight>();
		this.shadowMap = new ShadowMap(1024, 1024);
	}
	
	/* The method used to add a light */
	public void add(BaseLight light) {
		light.shadowData.shadowMap = this.shadowMap;
		this.lights.add(light);
	}
	
	/* The method used to render this scene */
	public void render() {
		//Render the ambient
		Renderer.getPass().customShader = defaultShader;
		Renderer.useAmbient = true;
		scene.renderObjects();
		Renderer.useAmbient = false;
		//Go through the light objects
		for (int a = 0; a < this.lights.size(); a++) {
			//Get the shadow data for the light
			ShadowData shadowData = this.lights.get(a).getShadowData();
			
			//Bind the shadow map
			this.shadowMap.bind();
			
			//Check to see whether the current light should cast shadows
			if (shadowData.shouldCastShadows()) {
				
				//Save the current view matrix
				Matrix.push(Matrix.viewMatrix);
				Matrix.push(Matrix.projectionMatrix);
				
				//Use the shadow data
				shadowData.use(lights.get(a));
				
				//Assign the matrix
				Matrix.viewMatrix.values = Matrix.lightViewMatrix.getValues();
				Matrix.projectionMatrix.values = Matrix.lightProjectionMatrix.getValues();
				
				//Replace the shader in the render pass
				Renderer.getPass().customShader = ShadowData.defaultShader;
				
				if (shadowData.shouldFlipFaces()) GLUtils.frontFace(GL11.GL_FRONT);
				
				//Render the scene
				this.scene.renderObjects();
								
				if (shadowData.shouldFlipFaces()) GLUtils.frontFace(GL11.GL_BACK);
				
				//Reset the custom shader
				Renderer.getPass().customShader = null;
				
				//Restore the view matrix
				Matrix.projectionMatrix.values = Matrix.pop();
				Matrix.viewMatrix.values = Matrix.pop();
			}
			
			//Unbind the shadow map
			this.shadowMap.unbind();
			
			this.shadowMap.applyGaussianBlur(shadowData.getShadowSoftness());
			
			GLUtils.enable(GL11.GL_BLEND);
			GLUtils.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GLUtils.depthMask(false);
			GLUtils.depthFunc(GL11.GL_EQUAL);
			//Use the current light
			this.lights.get(a).use();
			//Render the scene
			scene.renderObjects();
			//Stop using lighting
			Renderer.light = null;
			GLUtils.depthFunc(GL11.GL_LESS);
			GLUtils.depthMask(true);
			GLUtils.disable(GL11.GL_BLEND);
		}
		//Reset the custom shader
		Renderer.getPass().customShader = null;
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