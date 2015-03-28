/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import java.util.List;

import org.andor.core.parser.utils.ParserUtils;
import org.andor.utils.FileUtils;

public class IncludeDirective extends ShaderPreprocessorDirective {
	
	/* The identifier of this directive */
	public static final String IDENTIFIER = "include";
	
	/* The constructor */
	public IncludeDirective() {
		//Call the super constructor
		super(IDENTIFIER);
	}
	
	/* The execute this directive */
	public void execute(List<String> shaderCode, String[] arguments, int line, String path, boolean external) {
		/*
		 * #include "hello.txt"			This would include the code in hello.txt from the same folder location
		 */
		//Check the number of arguments
		if (arguments.length == 1) {
			//Remove the parenthesis to get the file path
			String filePath = ParserUtils.getString(arguments[0]);
			//Get the code from the other file
			List<String> code2 = FileUtils.read(path + "/" + filePath, external);
			//Do the same to the new code
			ShaderPreprocessor.execute(code2, path, external);
			//Remove the include directive
			shaderCode.remove(line);
			//Join the code together
			this.join(shaderCode, code2, line);
		}
	}
	
	/* The method used to add two pieces of code together at a certain location */
	private void join(List<String> code1, List<String> code2, int start) {
		//Go through the code
		for (int a = 0; a < code2.size(); a++)
			//Add the current shader code to the correct location
			code1.add(start + a, code2.get(a));
	}
	
}