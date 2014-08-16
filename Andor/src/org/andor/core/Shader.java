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

import android.opengl.GLES20;

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
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Create the program
			this.program = GL20.glCreateProgram();
		else
			this.program = GLES20.glCreateProgram();
		//Attach the shaders
		this.attach(this.vertexShader);
		this.attach(this.fragmentShader);
	}
	
	/* The method used to use this shader */
	public void use() {
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Use the shader program
			GL20.glUseProgram(this.program);
		else
			GLES20.glUseProgram(this.program);
	}
	
	/* The method used to stop using this shader */
	public void stopUsing() {
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Use the shader program
			GL20.glUseProgram(0);
		else
			GLES20.glUseProgram(0);
	}
	
	/* The method used to delete this shader program */
	public void delete() {
		//Check if android is enabled or not
		if (! Settings.AndroidMode) {
			GL20.glDeleteShader(this.vertexShader);
			GL20.glDeleteShader(this.fragmentShader);
			GL20.glDeleteProgram(this.program);
		} else {
			GLES20.glDeleteShader(this.vertexShader);
			GLES20.glDeleteShader(this.fragmentShader);
			GLES20.glDeleteProgram(this.program);
		}
	}
	
	/* The method used to attach a shader */
	public void attach(int shader) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode) {
			//Attach the shader to the program
			GL20.glAttachShader(this.program , shader);
			GL20.glLinkProgram(this.program);
			GL20.glValidateProgram(this.program);
		} else {
			GLES20.glAttachShader(this.program , shader);
			GLES20.glLinkProgram(this.program);
			GLES20.glValidateProgram(this.program);
		}
	}
	
	/* The method used to detach a shader */
	public void detach(int shader) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Detach the shader from the program
			GL20.glDetachShader(this.program , shader);
		else
			GLES20.glDetachShader(this.program, shader);
	}
	
	/* The method used to load the shaders and attach it to this program */
	public void load(String path, boolean external) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode) {
			this.vertexShader = ShaderUtils.createShader(path + ".vs", external, GL20.GL_VERTEX_SHADER);
			this.fragmentShader = ShaderUtils.createShader(path + ".fs", external, GL20.GL_FRAGMENT_SHADER);
		} else {
			this.vertexShader = ShaderUtils.createShader(path + ".vs", external, GLES20.GL_VERTEX_SHADER);
			this.fragmentShader = ShaderUtils.createShader(path + ".fs", external, GLES20.GL_FRAGMENT_SHADER);
		}
	}
	
	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform1f(GL20.glGetUniformLocation(this.program, variableName), v1);
		else
			GLES20.glUniform1f(GLES20.glGetUniformLocation(this.program, variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1, float v2) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform2f(GL20.glGetUniformLocation(this.program, variableName), v1, v2);
		else
			GLES20.glUniform2f(GLES20.glGetUniformLocation(this.program, variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float v1, float v2, float v3) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform3f(GL20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
		else
			GLES20.glUniform3f(GLES20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform1i(GL20.glGetUniformLocation(this.program, variableName), v1);
		else
			GLES20.glUniform1i(GLES20.glGetUniformLocation(this.program, variableName), v1);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1, int v2) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform2i(GL20.glGetUniformLocation(this.program, variableName), v1, v2);
		else
			GLES20.glUniform2i(GLES20.glGetUniformLocation(this.program, variableName), v1, v2);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int v1, int v2, int v3) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform3i(GL20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
		else
			GLES20.glUniform3i(GLES20.glGetUniformLocation(this.program, variableName), v1, v2, v3);
	}

	/* The method used to set a specific value in this shader */
	public void setValuei(String variableName, int[] arrayValues) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform1(GL20.glGetUniformLocation(this.program, variableName), BufferUtils.createBuffer(arrayValues));
	}

	/* The method used to set a specific value in this shader */
	public void setValuef(String variableName, float[] arrayValues) {
		//Check if android is enabled or not
		if (! Settings.AndroidMode)
			//Set the value in the shader
			GL20.glUniform1(GL20.glGetUniformLocation(this.program, variableName), BufferUtils.createBuffer(arrayValues));
	}
	
}