/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Object2D;
import org.andor.core.Object2DBuilder;
import org.andor.core.Vector2D;
import org.andor.utils.Timer;

public class GUITextBoxCursor extends Object2D {
	
	/* The text box instance */
	public GUITextBox textBox;
	
	/* The renderer */
	public GUIComponentRenderer renderer;
	
	/* The timer */
	public Timer timer;
	
	/* The time between blink */
	public long timeBetweenBlink;
	
	/* The boolean that states whether this cursor should be shown */
	public boolean cursorShown;
	
	/* The constructor */
	public GUITextBoxCursor(GUITextBox textBox, float thickness) {
		//Assign the variables
		this.textBox = textBox;
		this.width = thickness;
		this.height = this.textBox.renderer.font.getHeight("A");
		this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(this.width, this.height, Colour.WHITE));
		this.renderer.colours = new Colour[] { Colour.BLACK };
		this.timer = new Timer();
		this.timer.start();
		this.timeBetweenBlink = 600;
		this.cursorShown = false;
	}
	
	/* The method used to render this cursor */
	public void render() {
		//Check the timer
		if (this.timer.hasTimePassed(this.timeBetweenBlink)) {
			//Toggle cursor shown
			this.cursorShown = ! this.cursorShown;
			//Restart the timer
			this.timer.restart();
		}
		//Make sure the cursor should be shown
		if (this.cursorShown) {
			//Get the position of the text box
			Vector2D p = this.textBox.getPosition();
			//The position values for the cursor
			float x = 1 + p.x + (this.textBox.renderer.font.getWidth(this.textBox.renderText.substring(0, this.textBox.cursorIndex - this.textBox.viewIndexStart)));
			float y = (p.y + (this.textBox.height / 2)) - (this.height / 2);
			//Set the cursor's position
			this.position = new Vector2D(x, y);
			//Render the cursor
			this.renderer.render(this, this.textBox.active);
		}
	}
	
	/* The method used to show this cursor */
	public void showCursor() {
		//Restart the timer
		this.timer.restart();
		//Show the cursor
		this.cursorShown = true;
	}
	
	/* The set/get methods */
	public void setTimeBetweenBlink(long timeBetweenBlink) { this.timeBetweenBlink = timeBetweenBlink; }
	public long getTimeBetweenBlink() { return this.timeBetweenBlink; }
	public boolean isCursorShowing() { return this.cursorShown; }
	
}