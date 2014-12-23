/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.ml;

import org.andor.utils.ArrayUtils;
import org.andor.utils.FileUtils;

public class MLConfig {
	
	/* The MLTree instance */
	public MLTree tree;
	
	/* The constructor */
	public MLConfig() {
		
	}
	
	/* The constructor with the file path and inFolder variables given */
	public MLConfig(String path, boolean inFolder) {
		//Read the file
		read(path, inFolder);
	}
	
	/* The method used to read an ML file */
	public void read(String path, boolean inFolder) {
		//Get the text from the file
		String[] text = ArrayUtils.toStringArray(FileUtils.read(path, inFolder));
		//Parse the text
		this.tree = MLParser.parse(text);
	}
	
}