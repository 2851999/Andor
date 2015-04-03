/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental;

import org.andor.core.Colour;
import org.andor.core.Object2D;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Vector2D;
import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;

public class OnScreenJoystickAxis extends Object2D implements InputListenerInterface {
	
	/* The circles */
	public RenderableObject2D circleA;
	public RenderableObject2D circleB;
	
	/* The information about the circles */
	public float radiusA;
	public float radiusB;
	
	/* The information for the axes */
	public float minX;
	public float minY;
	public float maxX;
	public float maxY;
	
	/* The current values */
	public float x;
	public float y;
	public boolean pressed;
	
	/* The constructor */
	public OnScreenJoystickAxis(float radiusA, int segmentsA, Colour colourA, float radiusB, int segmentsB, Colour colourB) {
		//Assign the values
		this.radiusA = radiusA;
		this.radiusB = radiusB;
		this.x = 0;
		this.y = 0;
		pressed = false;
		//Create the circles
		this.circleA = Object2DBuilder.createCircle(this.radiusA, segmentsA, colourA);
		this.circleB = Object2DBuilder.createCircle(this.radiusB, segmentsB, colourB);
		//Link circle B to A
		this.circleA.link(this.circleB);
		//Attach circle A to this
		this.link(this.circleA);
		//Calculate the necessary values
		this.calculateValues();
		//Add this listener
		Input.addListener(this);
	}
	
	/* The method used to calculate the needed values */
	public void calculateValues() {
		this.maxX = this.radiusA - this.radiusB;
		this.maxY = this.radiusA - this.radiusB;
		this.minX = this.maxX * -1;
		this.minY = this.maxY * -1;
	}
	
	/* The method used to update this */
	public void update() {
		//Check to see whether the mouse is contained within circle B
		if (Mouse.x > (this.circleB.getPosition().x - this.radiusB) &&
			Mouse.x < (this.circleB.getPosition().x + this.radiusB) &&
			Mouse.y > (this.circleB.getPosition().y - this.radiusB) &&
			Mouse.y < (this.circleB.getPosition().y + this.radiusB)) {
			//Check to see whether the mouse is pressed
			if (pressed) {
				//Move the circle
				this.circleB.position.add(new Vector2D(Mouse.x, Mouse.y).subtractNew(this.circleB.getPosition()));
				//Check the position
				if (this.circleB.position.x > this.maxX)
					this.circleB.position.x = this.maxX;
				else if (this.circleB.position.x < this.minX)
					this.circleB.position.x = this.minX;
				
				if (this.circleB.position.y > this.maxY)
					this.circleB.position.y = this.maxY;
				else if (this.circleB.position.y < this.minY)
					this.circleB.position.y = this.minY;
				//Assign the values
				this.x = this.circleB.position.x / this.maxX;
				this.y = this.circleB.position.y / this.maxY;
			} else {
				//Reset the position
				this.circleB.position = new Vector2D(0, 0);
				this.x = 0;
				this.y = 0;
			}
		} else {
			//Reset the position
			this.circleB.position = new Vector2D(0, 0);
			this.x = 0;
			this.y = 0;
		}
	}
	
	/* The method used to render this */
	public void render() {
		//Render the circles
		this.circleA.render();
		this.circleB.render();
	}
	
	/* MOUSE STUFF */
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) {
		//Check to see whether the mouse is contained within circle B
		if (Mouse.x > (this.circleB.getPosition().x - this.radiusB) &&
			Mouse.x < (this.circleB.getPosition().x + this.radiusB) &&
			Mouse.y > (this.circleB.getPosition().y - this.radiusB) &&
			Mouse.y < (this.circleB.getPosition().y + this.radiusB)) {
			if (e.leftButton)
				pressed = true;
		}
	}
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) {
		if (pressed)
			pressed = false;
	}
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) {}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) {}
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) {}
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) {}
	
	/* KEYBOARD STUFF */
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) {}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {}
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) {}
	
	/* CONTROLLER STUFF */
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) {}
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) {}
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) {}
	
}