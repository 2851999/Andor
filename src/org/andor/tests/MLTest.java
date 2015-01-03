/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.parser.ml.MLParser;
import org.andor.core.parser.utils.ParserUtils;
import org.andor.utils.ArrayUtils;
import org.andor.utils.FileUtils;

public class MLTest {
	
	public static String text[] = {
		"<MLVersion \"V0.0.1\">",
		"<-- Hello this is a test",
		" this is the second line -->",
		"<SomeObjectType:objectName parameter=\"Hello World\">",
		"	<SomeObjectType:objectName2 parameter=\"Hello World Two\">",
			"</>",
		"</>"
	};
	
	public MLTest() {
		GUIInterpreter.interpret("hello", false, MLParser.parse(ParserUtils.cleanUp(ArrayUtils.toStringArray(FileUtils.read("H:/Storage/Users/Joel/Desktop/GUITest.ml", true)))));
	}
	
	public static void main(String[] args) {
		new MLTest();
	}
	
}