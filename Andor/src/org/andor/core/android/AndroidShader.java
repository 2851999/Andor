/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import org.andor.core.Shader;
import org.andor.utils.ShaderUtils;

import android.opengl.GLES20;

public class AndroidShader extends Shader {
	
	/* The constructor */
	public AndroidShader() {
		
	}
	
	/* The method used to create the shader program */
	public void create() {
		//Create the program
		this.program = GLES20.glCreateProgram();
		//Attach the shaders
		this.attach(this.vertexShader);
		this.attach(this.fragmentShader);
	}
	
	/* The method used to use this shader */
	public void use() {
		//Use the shader program
		GLES20.glUseProgram(this.program);
	}
	
	/* The method used to stop using this shader */
	public void stopUsing() {
		//Use the shader program
		GLES20.glUseProgram(0);
	}
	
	/* The method used to delete this shader program */
	public void delete() {
		GLES20.glDeleteShader(this.vertexShader);
		GLES20.glDeleteShader(this.fragmentShader);
		GLES20.glDeleteProgram(this.program);
	}
	
	/* The method used to attach a shader */
	public void attach(int shader) {
		GLES20.glAttachShader(this.program , shader);
		GLES20.glLinkProgram(this.program);
		GLES20.glValidateProgram(this.program);
	}
	
	/* The method used to detach a shader */
	public void detach(int shader) {
		//Detach the shader from the program
		GLES20.glDetachShader(this.program, shader);
	}
	
	/* The method used to load the shaders and attach it to this program */
	public void load(String path, boolean external) {
		this.vertexShader = ShaderUtils.createShader(path + ".vs", external, GLES20.GL_VERTEX_SHADER);
		this.fragmentShader = ShaderUtils.createShader(path + ".fs", external, GLES20.GL_FRAGMENT_SHADER);
	}
	
	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1) {
		//Set the value in the shader
		GLES20.glUniform1f(GLES20.glGetUniformLocation(this.program, variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1, float v2) {
		//Set the value in the shader
		GLES20.glUniform2f(GLES20.glGetUniformLocation(this.program, variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1, float v2, float v3) {
		//Set the value in the shader
		GLES20.glUniform3f(GLES20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1) {
		//Set the value in the shader
		GLES20.glUniform1i(GLES20.glGetUniformLocation(this.program, variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1, int v2) {
		//Set the value in the shader
		GLES20.glUniform2i(GLES20.glGetUniformLocation(this.program, variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1, int v2, int v3) {
		//Set the value in the shader
		GLES20.glUniform3i(GLES20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int[] arrayValues) {
		
	}
	
	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float[] arrayValues) {
		
	}
	
}