/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Matrix;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.TextureParameters;
import org.andor.core.Vector3D;
import org.andor.core.render.FBO;
import org.andor.core.render.RenderPass;
import org.andor.core.render.RenderTexture;
import org.andor.core.render.Renderer;
import org.andor.utils.shader.ShaderCode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class ShadowData {
	
	/* The shader code for this kind of light */
	public static ShaderCode shaderCode;
	
	/* The default shader */
	public static Shader defaultShader;
	
	/* The FBO for this shadow map */
	public FBO fbo;
	
	/* The boolean value that determines whether shadows should be cast */
	public boolean castShadows;
	
	/* The bias value (For shadow acne) */
	public float bias;
	
	/* The boolean that determines whether the faces will be flipped
	 * when generating the shadow map */
	public boolean flipFaces;
	
	/* The constructor */
	public ShadowData() {
		//Assign the variables
		this.fbo = new FBO(GL30.GL_FRAMEBUFFER);
		TextureParameters parameters = new TextureParameters().setFilter(GL11.GL_NEAREST).setClamp(true);
		this.fbo.add(new RenderTexture(1024, 1024, GL14.GL_DEPTH_COMPONENT32, GL11.GL_DEPTH_COMPONENT, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_FLOAT, parameters));
		this.fbo.setup();
		this.castShadows = true;
		this.bias = 4.0f;
		this.flipFaces = true;
	}
	
	/* The method used to use this data (Setup the matrix) */
	public void use(BaseLight light) {
		//Setup the light matrix
		Matrix.lightProjectionMatrix = Matrix.ortho(-20, 20, -20, 20, -20, 20); //Matrix.perspective(180, 1, 1, 100);
		//Transform to the light's position
		//Get the rotation
		Vector3D r = light.getRotation();
		Vector3D p = light.getPosition();
		Vector3D s = light.getScale();
		Matrix.loadIdentity(Matrix.lightViewMatrix);
		Matrix.lightViewMatrix = Matrix.scale(Matrix.lightViewMatrix, s);
		
		Matrix.lightViewMatrix = Matrix.rotate(Matrix.lightViewMatrix, r.x + 90, 1, 0, 0);
		Matrix.lightViewMatrix = Matrix.rotate(Matrix.lightViewMatrix, r.y, 0, 1, 0);
		Matrix.lightViewMatrix = Matrix.rotate(Matrix.lightViewMatrix, r.z, 0, 0, 1);
		
		Matrix.lightViewMatrix = Matrix.translate(Matrix.lightViewMatrix, p);
	}
	
	/* The method used to assign the needed values in the shaders */
	public void assignValues(RenderPass pass, Shader shader) {
		shader.setUniformMatrix("andor_lightMatrix", Matrix.lightMatrix);
		shader.setUniformi("andor_shadowMap", pass.bindTexture(Renderer.light.shadowData.fbo.textures.get(0).getPointer()));
		shader.setUniformf("andor_shadowMapTexelSize", new Vector3D(1.0f / 1024.0f, 1.0f / 1024f, 0.0f));
		shader.setUniformf("andor_shadowBias", this.bias / 1024f);
	}
	
	/* The getter and setters */
	public void setCastShadows(boolean castShadows) { this.castShadows = castShadows; }
	public void setBias(float bias) { this.bias = bias; }
	public void setFlipFaces(boolean flipFaces) { this.flipFaces = flipFaces; }
	public boolean shouldCastShadows() { return this.castShadows; }
	public float getBias() { return this.bias; }
	public boolean shouldFlipFaces() { return this.flipFaces; }
	
	/* The static method used to setup the shaders necessary */
	public static void setupShaders() {
		//Check to see whether the shader needs to be setup
		if (shaderCode == null) {
			//Create the shader code
			shaderCode = new ShaderCode(Settings.Resources.Shaders.SHADOWMAP_GENERATOR);
			shaderCode.load();
		}
		if (defaultShader == null)
			defaultShader = shaderCode.createDefault();
	}
	
}