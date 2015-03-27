/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import java.util.List;

public abstract class ShaderPreprocessorDirective {
	
	/* The identifier of this directive */
	private String identifier;
	
	/* The constructor */
	public ShaderPreprocessorDirective(String identifier) {
		//Assign the variables
		this.identifier = identifier;
	}
	
	/* The execute this directive */
	public abstract void execute(List<String> shaderCode, String[] arguments, int line, String path, boolean external);
	
	/* The getters */
	public String getIdentifier() { return this.identifier; }
	
}