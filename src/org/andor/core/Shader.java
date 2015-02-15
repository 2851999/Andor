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
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {
	
	/* The two types of shaders */
	public static final int VERTEX_SHADER = 1;
	public static final int FRAGMENT_SHADER = 2;
	
	/* The two types of deferred shaders */
	public static final int GEOMETRY_SHADER = 3;
	public static final int FINAL_SHADER = 4;
	
	/* The shader program this shader uses */
	public int program;
	
	/* The shaders within the program */
	public int vertexShader;
	public int fragmentShader;
	
	/* The constructor */
	public Shader() {
		
	}
	
	/* The method used to create the shader program */
	public void create() {
		//Create the program
		this.program = GL20.glCreateProgram();
		//Attach the shaders
		this.attach(this.vertexShader);
		this.attach(this.fragmentShader);
	}
	
	/* The method used to use this shader */
	public void use() {
		//Use the shader program
		GL20.glUseProgram(this.program);
	}
	
	/* The method used to stop using this shader */
	public void stopUsing() {
		//Use the shader program
		GL20.glUseProgram(0);
	}
	
	/* The method used to delete this shader program */
	public void delete() {
		GL20.glDeleteShader(this.vertexShader);
		GL20.glDeleteShader(this.fragmentShader);
		GL20.glDeleteProgram(this.program);
	}
	
	/* The method used to attach a shader */
	public void attach(int shader) {
		//Attach the shader to the program
		GL20.glAttachShader(this.program , shader);
		GL20.glLinkProgram(this.program);
		//Check for an error
		if (GL20.glGetProgrami(this.program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			//Log an error message
			Logger.log("Andor - ShaderUtils createShader()", "Error linking the shader", Log.ERROR);
			Logger.log("Andor - ShaderInformation", ShaderUtils.getProgramLogInformation(this.program), Log.ERROR);
		}
		GL20.glValidateProgram(this.program);
	}
	
	/* The method used to detach a shader */
	public void detach(int shader) {
		//Detach the shader from the program
		GL20.glDetachShader(this.program , shader);
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1) {
		//Set the value in the shader
		GL20.glUniform1f(this.getUniformLocation(variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1, float v2) {
		//Set the value in the shader
		GL20.glUniform2f(this.getUniformLocation(variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float v1, float v2, float v3) {
		//Set the value in the shader
		GL20.glUniform3f(this.getUniformLocation(variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int v1) {
		//Set the value in the shader
		GL20.glUniform1i(this.getUniformLocation(variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int v1, int v2) {
		//Set the value in the shader
		GL20.glUniform2i(this.getUniformLocation(variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int v1, int v2, int v3) {
		//Set the value in the shader
		GL20.glUniform3i(this.getUniformLocation(variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setUniformi(String variableName, int[] arrayValues) {
		//Set the value in the shader
		GL20.glUniform1(this.getUniformLocation(variableName), BufferUtils.createFlippedBuffer(arrayValues));
	}

	/* The method used to set a specific value in this shader */
	public void setUniformf(String variableName, float[] arrayValues) {
		//Set the value in the shader
		GL20.glUniform1(this.getUniformLocation(variableName), BufferUtils.createFlippedBuffer(arrayValues));
	}
	
	/* The method used to set a specific value in this shader */
	public void setUniformMatrix(String variableName, Matrix4D matrix) {
		//Set the value in the shader
		GL20.glUniformMatrix4(this.getUniformLocation(variableName), false, BufferUtils.createFlippedBuffer(matrix.getValues()));
	}
	
	/* The method used to get the location of a uniform variable */
	public int getUniformLocation(String name) {
		return GL20.glGetUniformLocation(this.program, name);
	}
	
	/* The method used to get the location of a attribute variable */
	public int getAttributeLocation(String name) {
		return GL20.glGetAttribLocation(this.program, name);
	}
	
}