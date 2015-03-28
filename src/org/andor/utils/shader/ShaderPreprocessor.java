/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.core.parser.utils.ParserUtils;

public class ShaderPreprocessor {
	
	/* The prefix for preprocessor directives */
	public static final String PREPROCESSOR_DIRECTIVE_PREFIX = "#";
	
	/* The list of preprocessor directives */
	public static List<ShaderPreprocessorDirective> directives;
	
	/* The static method used to setup the preprocessor directives */
	public static void setupDirectives() {
		//Create the list
		directives = new ArrayList<ShaderPreprocessorDirective>();
		//Add the directives
		directives.add(new IncludeDirective());
	}
	
	/* The static method called to execute the preprocessor with some code */
	public static void execute(List<String> shaderCode, String filePath, boolean external) {
		//Check to see whether the directives have been set up
		if (directives == null)
			//They haven't so set them up
			setupDirectives();
		//The file path will currently have the shader files name in it so try and remove it
		//and find the actual location the current file is stored in
		String path = filePath;
		//Check the file path
		if (filePath.contains("."))
			//Remove the name
			path = filePath.substring(0, filePath.lastIndexOf("/"));
		//Go through the code
		for (int a = 0; a < shaderCode.size(); a++) {
			//Remove any whitespace on the current line
			String line = ParserUtils.removeWhitespace(shaderCode.get(a));
			//Check the line for a preprocessor directive
			if (line.startsWith(PREPROCESSOR_DIRECTIVE_PREFIX)) {
				//Get the identifier and arguments
				String identifier = line.split(" ")[0].replace(PREPROCESSOR_DIRECTIVE_PREFIX, "");
				String[] arguments = line.replace(PREPROCESSOR_DIRECTIVE_PREFIX + identifier + " ", "").split(" ");
				//Execute the directive
				getDirective(identifier).execute(shaderCode, arguments, a, path, external);
			}
		}
	}
	
	/* The method used to get a preprocessor directive given its identifier */
	public static ShaderPreprocessorDirective getDirective(String identifier) {
		//The directive
		ShaderPreprocessorDirective directive = null;
		//Go through the directives
		for (int a = 0; a < directives.size(); a++) {
			//Check the name
			if (directives.get(a).getIdentifier().equals(identifier)) {
				//Assign the directive
				directive = directives.get(a);
				//Exit the loop
				break;
			}
		}
		//Check to see whether the directive was found
		if (directive == null)
			//Log an error
			Logger.log("ShaderPreprocessor", "The preprocessor directive withn the identifier " + identifier + " does not exist", Log.ERROR);
		//Return the directive
		return directive;
	}
	
}