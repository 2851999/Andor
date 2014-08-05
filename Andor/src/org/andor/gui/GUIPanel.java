/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import org.andor.core.GameLoop;
import org.andor.core.GameLoopInterface;
import org.andor.core.Object2D;

public class GUIPanel extends Object2D implements GameLoopInterface {
	
	/* The group */
	public GUIGroup group;
	
	/* The constructor */
	public GUIPanel(String name, boolean autoUpdate) {
		//Assign the variables
		this.group = new GUIGroup(name);
		if (autoUpdate)
			GameLoop.add(this);
	}
	
	/* The method called when the loop has been created */
	public void create() {}
	
	/* The method called when the game loop has started */
	public void start() {}
	
	/* The method called when the game loop is updated */
	public void update() {
		//Update the group
		this.group.update();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Render the group
		this.group.render();
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {}
	
	/* The method called when the game loop is closing */
	public void close() {}
	
	/* The method used to add a component to this panel */
	public void add(GUIComponent component) {
		this.group.add(component);
	}
	
}