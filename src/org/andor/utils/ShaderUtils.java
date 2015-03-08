/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import android.opengl.GLES20;

public class ShaderUtils {
	
	/* The static method used to load the shaders and create a program */
	public static Shader createShader(String path, boolean external) {
		int vertexShader = ShaderUtils.createShader(path + ".vs", external, Shader.VERTEX_SHADER);
		int fragmentShader = ShaderUtils.createShader(path + ".fs", external, Shader.FRAGMENT_SHADER);
		//Create the shader
		Shader shader = new Shader();
		//Attach the shaders
		shader.vertexShader = vertexShader;
		shader.fragmentShader = fragmentShader;
		//Create the shader
		shader.create();
		//Return the shader
		return shader;
	}
	
	
	/* The static method to create a shader from a file */
	public static int createShader(String path, boolean external, int shaderType) {
		//Return the shader
		return createShader(FileUtils.read(path, external), shaderType);
	}
	
	/* The static method used to create a shader, when joining together two different pieces of shader code */
	public static Shader createShader(List<String> vertexCode1, List<String> vertexCode2, List<String> fragmentCode1, List<String> fragmentCode2) {
		//Second code is added to the first one
		
		//Create the shader
		Shader shader = new Shader();
		//Assign the shaders
		shader.vertexShader = ShaderUtils.createShader(combine(vertexCode1, vertexCode2), Shader.VERTEX_SHADER);
		shader.fragmentShader = ShaderUtils.createShader(combine(fragmentCode1, fragmentCode2), Shader.FRAGMENT_SHADER);
		//Create the shader
		shader.create();
		//Return the shader
		return shader;
	}
	
	/* The static method to create a shader */
	public static int createShader(List<String> shaderCode, int shaderType) {
		//Convert the shader type
		if (shaderType == Shader.VERTEX_SHADER)
			shaderType = GL20.GL_VERTEX_SHADER;
		else if (shaderType == Shader.FRAGMENT_SHADER)
			shaderType = GL20.GL_FRAGMENT_SHADER;
		//Create the shader
		int shader = GLUtils.createShader(shaderType);
		//Check the shader was created
		if (shader == 0) {
			//Log an error
			Logger.log("Andor - ShaderUtils createShader()" , "Error when creating shader with the type: " + shaderType , Log.ERROR);
			//Return 0
			return 0;
		}
		//The shader source
		StringBuilder shaderSource = new StringBuilder();
		//Look at all of the shader file text
		for (int a = 0; a < shaderCode.size(); a++)
			//Add onto the shader source
			shaderSource.append(shaderCode.get(a)).append('\n');
		//Load the shader file
		GLUtils.shaderSource(shader, shaderSource.toString());
		//Compile the shader
		GLUtils.compileShader(shader);
		if (! Settings.AndroidMode) {
			//Check for an error
			if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
				//Log an error message
				Logger.log("Andor - ShaderUtils createShader()", "Error compiling the shader", Log.ERROR);
				Logger.log("Andor - ShaderInformation", getShaderLogInformation(shader), Log.ERROR);
			}
		} else {
			//Check for errors
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
				//Log an error
				Logger.log("Andor - ShaderUtils createShader()" , "Error when creating shader with the type: " + shaderType + " could not compile the shader" , Log.ERROR);
				Logger.log("Andor - ShaderInformation", GLES20.glGetShaderInfoLog(shader), Log.ERROR);
            }
		}

		//Check to see whether the shader was created
		if (shader == 0)
			//Log an error
			Logger.log("Andor - ShaderUtils createShader()", "Error creating shader", Log.ERROR);

		//Return the shader
		return shader;
	}

	/* The static method used to get any log information from a shader */
	public static String getShaderLogInformation(int shader) {
		//Return the information
		return GL20.glGetShaderInfoLog(shader, GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH));
	}
	
	/* The static method used to get any log information from a program */
	public static String getProgramLogInformation(int shader) {
		//Return the information
		return GL20.glGetProgramInfoLog(shader, GL20.glGetProgrami(shader, GL20.GL_INFO_LOG_LENGTH));
	}
	
	/* The static method used to combine two pieces of shader code */
	public static List<String> combine(List<String> source1, List<String> source2) {
		//Create the list
		List<String> list = new ArrayList<String>();
		list.addAll(source1);
		list.addAll(source2);
		return list;
	}
	
}
