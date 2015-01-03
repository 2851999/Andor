/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.ml;

public class MLSyntax {
	
	/* Here is an object definition in ML as an example
	 * 
	 * <MLVersion "V0.0.1">
	 * <SomeObjectType:objectName parameter="value">
	 *  	<-- Some Other Objects Here (Comment) -->
	 * </>
	 * 
	 */
	
	/* Here are some parts of the syntax for ML */
	public static final String TAG_START = "<";
	public static final String TAG_END = ">";
	public static final String OBJECT_END = "/>";
	public static final String PARAMETER_ASSIGNMENT = "=";
	public static final String NAME_ASSIGNMENT = ":";
	public static final String STRING_DEFINITION = "\"";
	public static final String COMMENT_START = "<--";
	public static final String COMMENT_END = "-->";
	public static final String STRING_ESCAPE_CHARACTER = "/";
	
}