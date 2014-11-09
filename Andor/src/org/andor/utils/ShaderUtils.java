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
	
	/* The Android shader code */
	public static final String[] androidVertexShaderCode = new String[] {
			"attribute vec4 andor_vertexPosition;",
	    	"attribute vec3 andor_normal;",
	    	"attribute vec2 andor_vtextureCoord;",
		 	"attribute vec4 andor_vcolour;",
		 	"uniform mat4 andor_modelviewprojectionmatrix;",
		 	"varying vec4 andor_colour;",
		    "varying vec2 andor_textureCoord;",
		    "void andor_main();",
		    "void main() {",
		    "  andor_colour = andor_vcolour;",
		    "  andor_textureCoord = andor_vtextureCoord;",
		    "  gl_Position = andor_matrix * andor_vertexPosition;",
		    "  andor_main();",
		    "}" };
	
	public static final String[] androidVertexAndorMain = new String[] {
		"void andor_main() {","}"
	};
	
	public static final String[] androidFragmentShaderCode = new String[] {
			"uniform sampler2D andor_texture;",
			"uniform float andor_hasTexture;",
		    "varying vec4 andor_colour;",
		    "varying vec2 andor_textureCoord;",
		    "void andor_main();",
		    "void main() {",
		    "  if (andor_hasTexture > 0.5) {",
		    "    gl_FragColor = andor_colour * texture2D(andor_texture, andor_textureCoord);",
		    "  } else {",
		    "    gl_FragColor = andor_colour;",
		    "  }",
		    "  andor_main();",
		    "}" };
	
	public static final String[] androidFragmentAndorMain = new String[] {
		"void andor_main() {","}"
	};
	
	/* The pc shader code */
	public static final String[] pcVertexShaderCode = new String[] {
		"attribute vec4 andor_vertexPosition;",
    	"attribute vec3 andor_normal;",
    	"attribute vec2 andor_vtextureCoord;",
	 	"attribute vec4 andor_vcolour;",
	 	"uniform mat4 andor_modelmatrix;",
	 	"uniform mat4 andor_viewmatrix;",
	 	"uniform mat4 andor_projectionmatrix;",
	 	"uniform mat4 andor_modelviewprojectionmatrix;",
	 	"varying vec4 andor_colour;",
	    "varying vec2 andor_textureCoord;",
	    "void andor_main();",
	    "void main() {",
	    "  andor_colour = andor_vcolour;",
	    "  andor_textureCoord = andor_vtextureCoord;",
	    "  gl_Position = transpose(andor_modelviewprojectionmatrix) * andor_vertexPosition;",
	    "  andor_main();",
	    "}" };
	
	public static final String[] pcFragmentShaderCode = new String[] {
		"uniform sampler2D andor_texture;",
		"uniform float andor_hasTexture;",
	    "varying vec4 andor_colour;",
	    "varying vec2 andor_textureCoord;",
	    "void andor_main();",
	    "void main() {",
	    "  if (andor_hasTexture > 0.5) {",
	    "    gl_FragColor = andor_colour * texture2D(andor_texture, andor_textureCoord);",
	    "  } else {",
	    "    gl_FragColor = andor_colour;",
	    "  }",
	    "  andor_main();",
	    "}" };
	
	public static final String[] pcVertexAndorMain = new String[] {
		"void andor_main() {","}"
	};
	
	public static final String[] pcFragmentAndorMain = new String[] {
		"void andor_main() {","}"
	};
	
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
					shaderSourceArray = combine(ShaderUtils.pcVertexShaderCode, source1);
				else if (shaderType == GL20.GL_FRAGMENT_SHADER)
					shaderSourceArray = combine(ShaderUtils.pcFragmentShaderCode, source1);
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
				//Join together both pieces of code
				String[] source1 = ArrayUtils.toStringArray(shaderCode);
				String[] shaderSourceArray = null;
				if (shaderType == GLES20.GL_VERTEX_SHADER)
					shaderSourceArray = combine(ShaderUtils.androidVertexShaderCode, source1);
				else if (shaderType == GLES20.GL_FRAGMENT_SHADER)
					shaderSourceArray = combine(ShaderUtils.androidFragmentShaderCode, source1);
				//The shader source
				StringBuilder shaderSource = new StringBuilder();
				//Look at all of the shader file text
				for (int a = 0; a < shaderSourceArray.length; a++)
					//Add onto the shader source
					shaderSource.append(shaderSourceArray[a]).append('\n');
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

	/* The static method used to get any log information */
	public static String getLogInformation(int shader) {
		//Return the information
		return GL20.glGetShaderInfoLog(shader, 1000);
	}
	
	/* The static method used to combine two pieces of shader code */
	public static String[] combine(String[] source1, String[] source2) {
		List<String> src1 = ArrayUtils.toStringList(source1);
		List<String> src2 = ArrayUtils.toStringList(source2);
		src1.addAll(src2);
		return ArrayUtils.toStringArray(src1);
	}
	
}
