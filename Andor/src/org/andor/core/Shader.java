/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;
import org.lwjgl.opengl.GL20;

public class Shader {
	
	/* The two types of shaders */
	public static final int VERTEX_SHADER = 1;
	public static final int FRAGMENT_SHADER = 2;
	
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
		GL20.glValidateProgram(this.program);
	}
	
	/* The method used to detach a shader */
	public void detach(int shader) {
		//Detach the shader from the program
		GL20.glDetachShader(this.program , shader);
	}
	
	/* The method used to load the shaders and attach it to this program */
	public void load(String path, boolean external) {
		this.vertexShader = ShaderUtils.createShader(path + ".vs", external, GL20.GL_VERTEX_SHADER);
		this.fragmentShader = ShaderUtils.createShader(path + ".fs", external, GL20.GL_FRAGMENT_SHADER);
	}
	
	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1) {
		//Set the value in the shader
		GL20.glUniform1f(GL20.glGetUniformLocation(this.program, variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1, float v2) {
		//Set the value in the shader
		GL20.glUniform2f(GL20.glGetUniformLocation(this.program, variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1, float v2, float v3) {
		//Set the value in the shader
		GL20.glUniform3f(GL20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1) {
		//Set the value in the shader
		GL20.glUniform1i(GL20.glGetUniformLocation(this.program, variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1, int v2) {
		//Set the value in the shader
		GL20.glUniform2i(GL20.glGetUniformLocation(this.program, variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1, int v2, int v3) {
		//Set the value in the shader
		GL20.glUniform3i(GL20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int[] arrayValues) {
		//Set the value in the shader
		GL20.glUniform1(GL20.glGetUniformLocation(this.program, variableName), BufferUtils.createBuffer(arrayValues));
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float[] arrayValues) {
		//Set the value in the shader
		GL20.glUniform1(GL20.glGetUniformLocation(this.program, variableName), BufferUtils.createBuffer(arrayValues));
	}
	
}