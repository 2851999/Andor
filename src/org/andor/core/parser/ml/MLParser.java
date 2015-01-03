/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.parser.ml;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.parser.utils.ParserUtils;
import org.andor.utils.ArrayUtils;

public class MLParser {
	
	/* The static method used to parse an array of strings that represent
	 * each line in an ML file, and create an MLTree out of it that can
	 * then be interpreted. */
	public static MLTree parse(String[] text)  {
		//Create the tree
		MLTree tree = new MLTree();
		//The current line being parsed
		int currentLine = 0;
		//Check to see whether the version is defined on the first line
		if (text[0].startsWith(MLSyntax.TAG_START + "MLVersion")) {
			//The version is defined so get the version
			tree.setVersion(ParserUtils.getString(text[0].split(" ")[1].replace(MLSyntax.TAG_END, "")));
			//Log some verbose
			ML.log("ML Version defined, found version " + tree.getVersion());
			//Skip the first line
			currentLine = 1;
		}
		//The stack of objects currently being modified
		List<MLObject> objectStack = new ArrayList<MLObject>();
		//The boolean that states whether there is an open comment (Ignore current lines)
		boolean comment = false;
		//Go through each line
		while (currentLine < text.length) {
			//Check for a comment on the current line
			if (text[currentLine].startsWith(MLSyntax.COMMENT_START))
				//Set comment to true
				comment = true;
			//Make sure the current line isn't a comment
			if (! comment) {
				//Parse the current line (Assuming it is an object)
				
				//Check to see whether it starts as expected
				if (text[currentLine].startsWith(MLSyntax.TAG_START) && ! text[currentLine].startsWith(MLSyntax.TAG_START + MLSyntax.OBJECT_END)) {
					//Cut out any end tags (Just get first side of object declaration)
					String objectDeclaration = text[currentLine].substring(1, text[currentLine].indexOf(MLSyntax.TAG_END));
					//Log some verbose
					ML.log("Found object declaration " + objectDeclaration);
					//Get the objects type and name
					String objectType = objectDeclaration.substring(0, objectDeclaration.indexOf(MLSyntax.NAME_ASSIGNMENT));
					String objectName = objectDeclaration.substring(objectDeclaration.indexOf(MLSyntax.NAME_ASSIGNMENT) + 1, objectDeclaration.indexOf(" "));
					//Log some verbose
					ML.log("Found object type " + objectType + " and name " + objectName);
					//Get the parameters
					String objectParameters = objectDeclaration.substring(objectDeclaration.indexOf(" ") + 1);
					//Split up each parameter
					String[] parameters = splitWithStrings(objectParameters);
					//Create the object
					MLObject object = new MLObject(objectType, objectName);
					//Go through each parameter
					for (int a = 0; a < parameters.length; a++) {
						//Parse the parameter
						MLParameter parameter = new MLParameter(parameters[a].substring(0, parameters[a].indexOf(MLSyntax.PARAMETER_ASSIGNMENT)), ParserUtils.getString(parameters[a].substring(parameters[a].indexOf(MLSyntax.PARAMETER_ASSIGNMENT) + 1)));
						//Log some verbose
						ML.log("Parsed parameter with the name " + parameter.getName() + " and the value " + parameter.getValue());
						//Add the parameter to the object
						object.add(parameter);
					}
					//Add the object to the object stack
					objectStack.add(object);
				}
				//Check to see whether the object has ended its definition
				if (text[currentLine].endsWith(MLSyntax.OBJECT_END)) {
					//Make sure there is more than one object in the stack
					if (objectStack.size() > 1) {
						//Add the last object to the previous one
						objectStack.get(objectStack.size() - 2).add(objectStack.get(objectStack.size() - 1));
						//Remove the last object
						objectStack.remove(objectStack.size() - 1);
					} else {
						//Add the object to the tree
						tree.add(objectStack.get(objectStack.size() - 1));
						//Remove the last object
						objectStack.remove(objectStack.size() - 1);
					}
				}
			} else {
				//Log some verbose
				ML.log("Ignoring comment " + text[currentLine]);
				//Check to see whether the current line is the end of a comment
				if (text[currentLine].endsWith(MLSyntax.COMMENT_END))
					//Set comment to false
					comment = false;
			}
			//Go to the next line
			currentLine++;
		}
		//Return the tree
		return tree;
	}
	
	/* The static method used to split a string using a space while keeping string values in tact */
	public static String[] splitWithStrings(String line) {
		//Split the line
		String[] split = line.split(" ");
		//The new set of values
		List<String> newSplit = new ArrayList<String>();
		//The boolean that represents whether it is currently parsing a string
		boolean parsingString = false;
		//Go through each split
		for (int a = 0; a < split.length; a++) {
			//Check to see whether a string is currently being parsed
			if (parsingString) {
				//Check to see whether the string ends on the current line
				if (! split[a].endsWith(MLSyntax.STRING_ESCAPE_CHARACTER + MLSyntax.STRING_DEFINITION) && split[a].endsWith(MLSyntax.STRING_DEFINITION))
					//Set parsing string to true
					parsingString = false;
				//Add the current split to the current line
				newSplit.set(newSplit.size() - 1, newSplit.get(newSplit.size() - 1) + " " + split[a]);
			} else {
				//Check the beginning of the current split
				if (split[a].contains(MLSyntax.STRING_DEFINITION)) {
					//Make sure the current split doesn't also end the string
					if (split[a].endsWith(MLSyntax.STRING_ESCAPE_CHARACTER + MLSyntax.STRING_DEFINITION) || ! split[a].endsWith(MLSyntax.STRING_DEFINITION))
						//Set parsing string to true
						parsingString = true;
				}
				//Add the current value to the new split
				newSplit.add(split[a]);
			}
		}
		//Return the new split
		return ArrayUtils.toStringArray(newSplit);
	}
	
}