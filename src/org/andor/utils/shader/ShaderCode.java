/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import java.util.List;

import org.andor.core.Shader;
import org.andor.utils.ArrayUtils;
import org.andor.utils.FileUtils;

public class ShaderCode {
	
	/* The default file extensions */
	public static final String DEFAULT_VERTEX_EXTENSION = ".vs";
	public static final String DEFAULT_FRAGMENT_EXTENSION = ".fs";
	
	/* The default addition */
	public static final String[] ANDOR_MAIN = new String[] {
		"void andor_main() {","}"
	};
	
	/* The vertex and fragment shader code */
	public List<String> vertexCode;
	public List<String> fragmentCode;
	
	/* The path to the shader code */
	public String path;
	
	/* The external property */
	public boolean external;
	
	/* The extensions */
	public String vertexExtension;
	public String fragmentExtension;
	
	/* The default constructor */
	public ShaderCode() {
		//Assign the default extensions
		this.vertexExtension = DEFAULT_VERTEX_EXTENSION;
		this.fragmentExtension = DEFAULT_FRAGMENT_EXTENSION;
		//Assign the default external value
		this.external = false;
	}
	
	/* The constructor with the path given */
	public ShaderCode(String path) {
		this();
		//Assign the variables
		this.path = path;
	}
	
	/* The method used to assign the locations of the shader code */
	public void setPath(String path) { this.path = path; }
	
	/* The method used to assign the extension properties */
	public void setExtensions(String vertexExtension, String fragmentExtension) {
		this.vertexExtension = vertexExtension;
		this.fragmentExtension = fragmentExtension;
	}
	
	/* The method used to assign the external property */
	public void setExternal(boolean external) { this.external = external; }
	
	/* The method used to load the shader code */
	public void load() {
		//Assign the shader code
		this.vertexCode = FileUtils.read(this.path + this.vertexExtension, this.external);
		this.fragmentCode = FileUtils.read(this.path + this.fragmentExtension, this.external);
		//Apply any custom preprocessor directives
		ShaderPreprocessor.execute(this.vertexCode, path + this.vertexExtension, this.external);
		ShaderPreprocessor.execute(this.fragmentCode, path + this.fragmentExtension, this.external);
	}
	
	/* The method used to load a shader given the path */
	public void load(String path) {
		//Assign the shader code
		this.vertexCode = FileUtils.read(path + this.vertexExtension, this.external);
		this.fragmentCode = FileUtils.read(path + this.fragmentExtension, this.external);
		//Apply any custom preprocessor directives
		ShaderPreprocessor.execute(this.vertexCode, path + this.vertexExtension, this.external);
		ShaderPreprocessor.execute(this.fragmentCode, path + this.fragmentExtension, this.external);
	}
	
	/* The method used to load a shader given the path and external values */
	public void load(String path, boolean external) {
		//Assign the shader code
		this.vertexCode = FileUtils.read(path + this.vertexExtension, external);
		this.fragmentCode = FileUtils.read(path + this.fragmentExtension, external);
		//Apply any custom preprocessor directives
		ShaderPreprocessor.execute(this.vertexCode, path + this.vertexExtension, external);
		ShaderPreprocessor.execute(this.fragmentCode, path + this.fragmentExtension, external);
	}
	
	/* The method used to create a default shader (No external code - use the default) */
	public Shader createDefault() {
		//Create the default shader and return it
		return ShaderUtils.createShader(this.vertexCode, ArrayUtils.toStringList(ANDOR_MAIN), this.fragmentCode, ArrayUtils.toStringList(ANDOR_MAIN));
	}
	
	/* The method used to create a shader given other shader code */
	public Shader createShader(List<String> vertex, List<String> fragment) {
		//Create the shader and return it
		return ShaderUtils.createShader(this.vertexCode, vertex, this.fragmentCode, fragment);
	}
	
	/* The method used to create a shader given other shader code */
	public Shader createShader(String path, boolean external) {
		//Create the shader and return it
		return ShaderUtils.createShader(this.vertexCode, FileUtils.read(path + this.vertexExtension, external), this.fragmentCode, FileUtils.read(path + this.fragmentExtension, external));
	}
	
}