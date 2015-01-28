/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import org.andor.core.GameLoop;
import org.andor.core.GameLoopInterface;
import org.andor.core.Object2D;
import org.andor.core.Vector2D;

public class GUIPanel extends Object2D implements GameLoopInterface {
	
	/* The group */
	public GUIGroup group;
	
	/* The initial resolution of this panel */
	public Vector2D resolution;
	
	/* The constructor */
	public GUIPanel(String name, boolean autoUpdate) {
		//Assign the variables
		this.group = new GUIGroup(name);
		this.link(this.group);
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
	
	/* The method used to change the scale of this panel */
	public void scale(Vector2D amount) {
		//Scale all of the components
		this.group.scale(amount);
	}
	
	/* The method used to change the resolution of the panel */
	public void setResolution(Vector2D resolution) {
		//Check to see whether the resolution has already been set
		if (this.resolution == null) {
			//Assign the resolution
			this.resolution = resolution;
		} else {
			//Calculate the change in scale
			float scaleWidth = resolution.x / this.resolution.x;
			float scaleHeight = resolution.y / this.resolution.y;
			//Assign the new resolution
			this.resolution = resolution;
			//Apply the scale
			this.scale(new Vector2D(scaleWidth, scaleHeight));
		}
	}
	
	
}