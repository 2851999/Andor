/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.parser.ml.MLParser;
import org.andor.core.parser.utils.ParserUtils;
import org.andor.gui.GUIPanel;
import org.andor.utils.ArrayUtils;
import org.andor.utils.FileUtils;
import org.andor.utils.OpenGLUtils;

public class MLTest extends BaseGame {
	
	GUIPanel panel;
	
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
		
	}
	
	public void create() {
		panel = GUIInterpreter.interpret("hello", true, MLParser.parse(ParserUtils.cleanUp(ArrayUtils.toStringArray(FileUtils.read("H:/Andor/GUITest.ml", true)))), "H:/Andor", true);
	}
	
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.setupOrtho(-1, 1);
		panel.render();
	}
	
	public static void main(String[] args) {
		new MLTest();
	}
	
}