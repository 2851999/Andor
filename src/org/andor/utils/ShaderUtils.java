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
import org.andor.core.android.AndroidShader;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import android.opengl.GLES20;

public class ShaderUtils {
	
	/* The shader code */
	public static List<String> forwardVertexShaderCode;
	public static List<String> forwardFragmentShaderCode;
	public static List<String> deferredGeometryVertexShaderCode;
	public static List<String> deferredGeometryFragmentShaderCode;
	public static List<String> deferredDefaultVertexShaderCode;
	public static List<String> deferredDefaultFragmentShaderCode;
	public static List<String> deferredLightVertexShaderCode;
	public static List<String> deferredLightFragmentShaderCode;
	
	public static final String[] vertexAndorMain = new String[] {
		"void andor_main() {","}"
	};
	
	public static final String[] fragmentAndorMain = new String[] {
		"void andor_main() {","}"
	};
	
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
	
	/* The static method used to load the render shaders and create a program */
	public static Shader createRenderShader(String path, boolean external) {
		int vertexShader = ShaderUtils.createRenderShader(path + ".vs", external, Shader.VERTEX_SHADER);
		int fragmentShader = ShaderUtils.createRenderShader(path + ".fs", external, Shader.FRAGMENT_SHADER);
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
	
	/* The static method to create a render shader from a file */
	public static int createRenderShader(String path, boolean external, int shaderType) {
		//Return the shader
		return createRenderShader(FileUtils.read(path, external), shaderType);
	}
	
	/* The static method used to create a shader for rendering */
	public static int createRenderShader(List<String> shaderCode, int type) {
		//Load the default shaders
		loadDefaultShaders();
		//Check the settings
		if (! Settings.Video.DeferredRendering) {
			//Check the type
			if (type == Shader.VERTEX_SHADER)
				return createShader(combine(forwardVertexShaderCode, shaderCode), type);
			else if (type == Shader.FRAGMENT_SHADER)
				return createShader(combine(forwardFragmentShaderCode, shaderCode), type);
		} else
			return createDeferredRenderShader(shaderCode, type, Shader.GEOMETRY_SHADER);
		return -2;
	}
	
	/* The static method used to create a shader for deferred rendering */
	public static int createDeferredRenderShader(List<String> shaderCode, int type, int deferredType) {
		//Load the default shaders
		loadDefaultShaders();
		//Check the settings
		if (Settings.Video.DeferredRendering) {
			//Check the type
			if (type == Shader.VERTEX_SHADER) {
				//Check the deferred type
				if (deferredType == Shader.GEOMETRY_SHADER)
					return createShader(combine(deferredGeometryVertexShaderCode, shaderCode), type);
				else if (deferredType == Shader.DEFAULT_SHADER)
					return createShader(combine(deferredDefaultVertexShaderCode, shaderCode), type);
				else
					return createShader(combine(deferredLightVertexShaderCode, shaderCode), type);
			} else if (type == Shader.FRAGMENT_SHADER) {
				//Check the deferred type
				if (deferredType == Shader.GEOMETRY_SHADER)
					return createShader(combine(deferredGeometryFragmentShaderCode, shaderCode), type);
				else if (deferredType == Shader.DEFAULT_SHADER)
					return createShader(combine(deferredDefaultFragmentShaderCode, shaderCode), type);
				else
					return createShader(combine(deferredLightFragmentShaderCode, shaderCode), type);
			}
		}
		return -2;
	}
	
	/* The static method to create a shader */
	public static int createShader(List<String> shaderCode, int shaderType) {
		//Load the default shaders
		loadDefaultShaders();
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
					Logger.log("Andor - ShaderInformation", getShaderLogInformation(shader), Log.ERROR);
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
				//Check for errors
	            int[] compiled = new int[1];
	            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
	            if (compiled[0] == 0) {
					//Log an error
					Logger.log("Andor - ShaderUtils createShader()" , "Error when creating shader with the type: " + shaderType + " could not compile the shader" , Log.ERROR);
					Logger.log("Andor - ShaderInformation", GLES20.glGetShaderInfoLog(shader), Log.ERROR);
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
	
	/* The static method used to combine two pieces of shader code */
	public static List<String> combine(List<String> source1, List<String> source2) {
		//Create the list
		List<String> list = new ArrayList<String>();
		list.addAll(source1);
		list.addAll(source2);
		return list;
	}
	
	/* The static method used to load the default shaders */
	public static void loadDefaultShaders() {
		//Check to see whether they need loading
		if (forwardVertexShaderCode == null)
			//Load the code
			forwardVertexShaderCode = FileUtils.read(Settings.Resources.Shaders.FORWARD_DEFAULT + ".vs", false);
		if (forwardFragmentShaderCode == null)
			forwardFragmentShaderCode = FileUtils.read(Settings.Resources.Shaders.FORWARD_DEFAULT + ".fs", false);
		if (deferredGeometryVertexShaderCode == null)
			deferredGeometryVertexShaderCode = FileUtils.read(Settings.Resources.Shaders.DEFERRED_GEOMETRY_PASS + ".vs", false);
		if (deferredGeometryFragmentShaderCode == null)
			deferredGeometryFragmentShaderCode = FileUtils.read(Settings.Resources.Shaders.DEFERRED_GEOMETRY_PASS + ".fs", false);
		if (deferredDefaultVertexShaderCode == null)
			deferredDefaultVertexShaderCode = FileUtils.read(Settings.Resources.Shaders.DEFERRED_DEFAULT_PASS + ".vs", false);
		if (deferredDefaultFragmentShaderCode == null)
			deferredDefaultFragmentShaderCode = FileUtils.read(Settings.Resources.Shaders.DEFERRED_DEFAULT_PASS + ".fs", false);
		if (deferredLightVertexShaderCode == null)
			deferredLightVertexShaderCode = FileUtils.read(Settings.Resources.Shaders.DEFERRED_LIGHT_PASS + ".vs", false);
		if (deferredLightFragmentShaderCode == null)
			deferredLightFragmentShaderCode = FileUtils.read(Settings.Resources.Shaders.DEFERRED_LIGHT_PASS + ".fs", false);
	}
	
}
