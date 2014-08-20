package org.andor.utils;
import java.util.List;

import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import android.opengl.GLES20;

public class ShaderUtils {
	
	/* The static method to create a shader from a file */
	public static int createShader(String path, boolean external, int shaderType) {
		//Return the shader
		return createShader(FileUtils.read(path, external), shaderType);
	}
	
	/* The static method to create a shader */
	public static int createShader(List<String> shaderCode, int shaderType) {
		//The shader
		int shader = 0;
		//Try and catch statement
		try {
			//Make sure Andor isn't currently running on Android
			if (! Settings.AndroidMode) {
				//Convert the shader type
				if (shaderType == Shader.VERTEX_SHADER)
					shaderType = GL20.GL_VERTEX_SHADER;
				else if (shaderType == Shader.FRAGMENT_SHADER)
					shaderType = GL20.GL_FRAGMENT_SHADER;
				//Create the shader
				shader = GL20.glCreateShader(shaderType);
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
				GL20.glShaderSource(shader , shaderSource);
				//Compile the shader
				GL20.glCompileShader(shader);

				//Check for an error
				if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
					//Log an error message
					Logger.log("Andor - ShaderUtils createShader()", "Error compiling the shader", Log.ERROR);
					Logger.log("Andor - ShaderInformation", getLogInformation(shader), Log.ERROR);
				}
			} else {
				//Convert the shader type
				if (shaderType == Shader.VERTEX_SHADER)
					shaderType = GLES20.GL_VERTEX_SHADER;
				else if (shaderType == Shader.FRAGMENT_SHADER)
					shaderType = GLES20.GL_FRAGMENT_SHADER;
				//Create the shader
				shader = GLES20.glCreateShader(shaderType);
				//Check the shader was created
				if (shader == 0) {
					//Log an error
					Logger.log("Andor - ShaderUtils createShader()" , "Error when creating shader with the type: " + shaderType , Log.ERROR);
					Logger.log("Andor - ShaderInformation", GLES20.glGetShaderInfoLog(shader), Log.ERROR);
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
				GLES20.glShaderSource(shader , shaderSource.toString());
				//Compile the shader
				GLES20.glCompileShader(shader);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//Log an error
			Logger.log("Andor - ShaderUtils createShader()" , "Error when creating shader with the file" , Log.ERROR);
		}

		//Check to see whether the shader was created
		if (shader == 0)
			//Log an error
			Logger.log("Andor - ShaderUtils createShader()", "Error creating shader", Log.ERROR);

		//Return the shader
		return shader;
	}

	/* The static method used to get any log information */
	public static String getLogInformation(int shader) {
		//Return the information
		return GL20.glGetShaderInfoLog(shader, 1000);
	}
	
}
