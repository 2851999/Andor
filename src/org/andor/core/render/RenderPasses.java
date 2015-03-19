/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import java.util.ArrayList;
import java.util.List;

public class RenderPasses {
	
	/* The list of render passes */
	public static List<RenderPass> renderPasses;
	
	/* The static method used to add all of the render passes to the list */
	public static void setupPasses() {
		//Create the list
		renderPasses = new ArrayList<RenderPass>();
		//Add the render passes
		renderPasses.add(new ForwardPass());
		renderPasses.add(new GeometryPass());
		renderPasses.add(new FinalPass());
	}
	
	/* The static method used to 'set' a specific pass given its name */
	public static void setPass(String name, RenderPass pass) {
		//Set the pass
		renderPasses.set(getPassLocation(name), pass);
	}
	
	/* The static method used to 'get' a specific pass given its name */
	public static RenderPass getPass(String name) {
		//Return the pass
		return renderPasses.get(getPassLocation(name));
	}
	
	/* The static method used to 'get' a specific pass's location given its name */
	public static int getPassLocation(String name) {
		//Go through the passes
		for (int a = 0; a < renderPasses.size(); a++) {
			//Check the name of the current pass
			if (renderPasses.get(a).getName().equals(name))
				return a;
		}
		//Return -1
		return 01;
	}
	
}