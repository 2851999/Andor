/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.parser.ml.MLParser;
import org.andor.core.parser.utils.ParserUtils;

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
		MLParser.parse(ParserUtils.cleanUp(text));
	}
	
	public static void main(String[] args) {
		new MLTest();
	}
	
}