/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Vector2D;
import org.andor.core.input.Mouse;
import org.andor.utils.Timer;

public class GUIToolTip implements GUIComponentListener {
	
	/* The label */
	public GUILabel label;
	
	/* The component this tool tip is attached to */
	public GUIComponent component;
	
	/* The timer */
	public Timer timer;
	
	/* The time it takes until this tool tip appears */
	public long time;
	
	/* The boolean that states whether this tool tip is showing */
	public boolean showing;
	
	/* The constructor */
	public GUIToolTip(GUIComponent component, String text) {
		//Assign the variables
		component.addListener(this);
		this.component = component;
		this.label = new GUILabel(text);
		this.label.border = new GUIBorder(this.label, 1f, new Colour[] { Colour.LIGHT_GREY });
		this.timer = new Timer();
		this.time = 1000;
		this.showing = false;
	}
	
	/* The method used to update this tool tip */
	protected void update() {
		//Check the timer
		if (this.timer.hasTimePassed(this.time)) {
			//Set this tool tips position
			this.label.position = new Vector2D(Mouse.x, Mouse.y);
			//Show this tool tip
			this.showing = true;
			//Reset the timer
			this.timer.reset();
		}
	}
	
	/* The method used to render this tool tip */
	protected void render() {
		//Check to see whether this tool tip is showing
		if (this.showing)
			//Set this tool tips position
			this.label.render();
	}
	
	/* The method called when the mouse enters a gui component */
	public void onMouseEnter(GUIComponent component) {
		//Check the component
		if (component == this.component)
			//Start the timer
			this.timer.start();
	}
	
	/* The method called when the mouse exits a gui component */
	public void onMouseLeave(GUIComponent component) {
		//Check the component
		if (component == this.component) {
			//Reset the timer
			this.timer.reset();
			//Hide this tool tip
			this.showing = false;
		}
	}
	
	/* The method called when a gui component is clicked */
	public void onClicked(GUIComponent component) {
		//Check the component
		if (component == this.component) {
			//Reset the timer
			this.timer.reset();
			//Hide this tool tip
			this.showing = false;
		}
	}
	
	/* The set/get methods */
	public void setBackgroundColour (Colour colour) {
		//Set the border
		this.label.border = new GUIBorder(this.label, 1f, new Colour[] { colour });
	}
	
}