/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.andor.core.Matrix;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.render.Renderer;
import org.andor.utils.GLUtils;
import org.andor.utils.OpenGLUtils;
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
	
	/* The method used to add a light */
	public void add(BaseLight light) { this.lights.add(light); }
	
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
			
			//Bind the fbo
			shadowData.fbo.bind();
			//GL11.glViewport(0, 0, 1024, 1024);
			
			//Clear the depth buffer
			OpenGLUtils.clearDepthBuffer();
			OpenGLUtils.enableDepthTest();
			OpenGLUtils.setupRemoveAlpha();
			OpenGLUtils.enableTexture2D();
			
			//Check to see whether the current light should cast shadows
			if (shadowData.shouldCastShadows()) {
				
				//Save the current view matrix
				float[] clone = Arrays.copyOf(Matrix.viewMatrix.getValues(), 16);
				float[] clone2 = Arrays.copyOf(Matrix.projectionMatrix.getValues(), 16);
				
				//Use the shadow data
				shadowData.use(lights.get(a));
				
				//Assign the matrix
				Matrix.viewMatrix.values = Matrix.lightViewMatrix.getValues();
				Matrix.projectionMatrix.values = Matrix.lightProjectionMatrix.getValues();
				
				//Replace the shader in the render pass
				Renderer.getPass().customShader = ShadowData.defaultShader;
				
				if (shadowData.shouldFlipFaces()) GL11.glCullFace(GL11.GL_FRONT);
				
				GL11.glViewport(0, 0, 1024, 1024);
				
				//Render the scene
				this.scene.renderObjects();
				
				GL11.glViewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
				
				if (shadowData.shouldFlipFaces()) GL11.glCullFace(GL11.GL_BACK);
				
				//Reset the custom shader
				Renderer.getPass().customShader = null;
				
				//Restore the view matrix
				Matrix.viewMatrix.values = clone;
				Matrix.projectionMatrix.values = clone2;
			}
			
			//Unbind the fbo
			shadowData.fbo.unbind();
			
			GLUtils.enable(GL11.GL_BLEND);
			GLUtils.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
			GLUtils.depthMask(false);
			GLUtils.depthFunc(GL11.GL_EQUAL);
			//Use the current light
			this.lights.get(a).use();
			//Render the scene
			scene.renderObjects();
			GLUtils.depthFunc(GL11.GL_LESS);
			GLUtils.depthMask(true);
			GLUtils.disable(GL11.GL_BLEND);
		}
		//Stop using lighting
		Renderer.light = null;
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