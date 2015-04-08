/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.GLUtils;
import org.andor.utils.shader.ShaderUtils;
import org.andor.utils.shader.Uniforms;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {
	
	/* The two types of shaders */
	public static final int VERTEX_SHADER = 1;
	public static final int FRAGMENT_SHADER = 2;
	
	/* The two types of deferred shaders */
	public static final int GEOMETRY_SHADER = 3;
	public static final int LIGHT_SHADER = 4;
	public static final int DEFAULT_SHADER = 5;
	
	/* The shader program this shader uses */
	public int program;
	
	/* The shaders within the program */
	public int vertexShader;
	public int fragmentShader;
	
	/* The uniforms */
	public Uniforms uniforms;
	
	/* The constructor */
	public Shader() {
		
	}
	
	/* The method used to create the shader program */
	public void create() {
		//Create the program
		this.program = GLUtils.createProgram();
		//Attach the shaders
		this.attach(this.vertexShader);
		this.attach(this.fragmentShader);
	}
	
	/* The method used to use this shader */
	public void use() {
		//Bind this shader
		this.bind();
		//Check to see whether the uniforms have been assigned
		if (this.uniforms != null)
			//Assign the uniforms
			this.uniforms.assign(this);
	}
	
	/* The method used to stop using this shader */
	public void stopUsing() {
		//Unbind this shader
		this.unbind();
	}
	
	/* The method used to bind this shader */
	public void bind() {
		//Bind this shader program
		GLUtils.useProgram(this.program);
	}
	
	/* The method used to unbind this shader */
	public void unbind() {
		//Bind nothing
		GLUtils.useProgram(0);
	}
	
	/* The method used to delete this shader program */
	public void delete() {
		GLUtils.deleteShader(this.vertexShader);
		GLUtils.deleteShader(this.fragmentShader);
		GLUtils.deleteProgram(this.program);
	}
	
	/* The method used to attach a shader */
	public void attach(int shader) {
		//Attach the shader to the program
		GLUtils.attachShader(this.program , shader);
		GLUtils.linkProgram(this.program);
		//Make sure this is not on Android
		if (! Settings.AndroidMode) {
			//Check for an error
			if (GL20.glGetProgrami(this.program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
				//Log an error message
				Logger.log("Andor - ShaderUtils createShader()", "Error linking the shader", Log.ERROR);
				Logger.log("Andor - ShaderInformation", ShaderUtils.getProgramLogInformation(this.program), Log.ERROR);
			}
		}
		GLUtils.validateProgram(this.program);
	}
	
	/* The method used to detach a shader */
	public void detach(int shader) {
		//Detach the shader from the program
		GLUtils.detachShader(this.program , shader);
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1) {
		//Set the value in the shader
		GLUtils.uniform1f(this.getUniformLocation(variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1, float v2) {
		//Set the value in the shader
		GLUtils.uniform2f(this.getUniformLocation(variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1, float v2, float v3) {
		//Set the value in the shader
		GLUtils.uniform3f(this.getUniformLocation(variableName), v1, v2, v3);
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1, float v2, float v3, float v4) {
		//Set the value in the shader
		GLUtils.uniform4f(this.getUniformLocation(variableName), v1, v2, v3, v4);
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, Vector2D v) {
		//Set the value in the shader
		GLUtils.uniform2f(this.getUniformLocation(variableName), v.x, v.y);
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, Vector3D v) {
		//Set the value in the shader
		GLUtils.uniform3f(this.getUniformLocation(variableName), v.x, v.y, v.z);
	}
	
	
	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, Colour colour) {
		//Set the values
		setUniformf(variableName, colour.r, colour.g, colour.b, colour.a);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int v1) {
		//Set the value in the shader
		GLUtils.uniform1i(this.getUniformLocation(variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int v1, int v2) {
		//Set the value in the shader
		GLUtils.uniform2i(this.getUniformLocation(variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int v1, int v2, int v3) {
		//Set the value in the shader
		GLUtils.uniform3i(this.getUniformLocation(variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int[] arrayValues) {
		//Set the value in the shader
		GLUtils.uniform1(this.getUniformLocation(variableName), arrayValues);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float[] arrayValues) {
		//Set the value in the shader
		GLUtils.uniform1(this.getUniformLocation(variableName), arrayValues);
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformMatrix(String variableName, Matrix4D matrix) {
		//Set the value in the shader
		GLUtils.uniformMatrix4(this.getUniformLocation(variableName), false, matrix.getValues());
	}
	
	/* The method used to get the location of a uniform variable */
	public int getUniformLocation(String name) {
		return GLUtils.getUniformLocation(this.program, name);
	}
	
	/* The method used to get the location of a attribute variable */
	public int getAttributeLocation(String name) {
		return GLUtils.getAttributeLocation(this.program, name);
	}
	
	/* The getters and setters */
	public void setUniforms(Uniforms uniforms) { this.uniforms = uniforms; }
	public Uniforms getUniforms() { return this.uniforms; }
	
}