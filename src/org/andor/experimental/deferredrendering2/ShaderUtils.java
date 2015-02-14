/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering2;

import java.util.List;

import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.android.AndroidShader;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.ArrayUtils;
import org.andor.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderUtils {
	
	/* The static method used to load the shaders and create a program */
	public static Shader createShader(String path, boolean external) {
		int vertexShader = ShaderUtils.createShader(path + ".vs", external, Shader.VERTEX_SHADER);
		int fragmentShader = ShaderUtils.createShader(path + ".fs", external, Shader.FRAGMENT_SHADER);
		//The shader
		Shader shader;
		//Check the Android setting
		if (Settings.AndroidMode)
			//Assign the shader
			shader = new AndroidShader();
		else
			//Assign the shader
			shader = new Shader();
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
				//Join together both pieces of code
				String[] source1 = ArrayUtils.toStringArray(shaderCode);
				String[] shaderSourceArray = null;
				if (shaderType == GL20.GL_VERTEX_SHADER)
					shaderSourceArray = source1;
				else if (shaderType == GL20.GL_FRAGMENT_SHADER)
					shaderSourceArray = source1;
				//The shader source
				StringBuilder shaderSource = new StringBuilder();
				//Look at all of the shader file text
				for (int a = 0; a < shaderSourceArray.length; a++)
					//Add onto the shader source
					shaderSource.append(shaderSourceArray[a]).append('\n');
				//Load the shader file
				GL20.glShaderSource(shader , shaderSource);
				//Compile the shader
				GL20.glCompileShader(shader);

				//Check for an error
				if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
					//Log an error message
					Logger.log("Andor - ShaderUtils createShader()", "Error compiling the shader", Log.ERROR);
					Logger.log("Andor - ShaderInformation", getShaderLogInformation(shader), Log.ERROR);
				}
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
	
}
