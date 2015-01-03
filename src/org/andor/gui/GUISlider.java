/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Vector2D;
import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
import org.andor.core.input.ControllerPovEvent;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;

public class GUISlider extends GUIComponent implements InputListenerInterface {
	
	/* The horizontal slider direction */
	public static final int DIRECTION_HORIZONTAL = 1;

	/* The vertical slider direction */
	public static final int DIRECTION_VERTICAL = 2;

	/* The variable which states which direction the slider should slide */
	public int sliderDirection;

	/* The slider button */
	public GUIButton sliderButton;

	/* The current slider value */
	public float sliderValue;
	
	/* The constructor */
	public GUISlider(GUIButton sliderButton , int sliderDirection, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.sliderButton = sliderButton;
		this.sliderDirection = sliderDirection;
		this.sliderValue = 0;
		this.renderer.colours = new Colour[] { Colour.WHITE };
		this.link(this.sliderButton);
		Input.addListener(this);
	}
	
	/* The constructor */
	public GUISlider(Colour colour, GUIButton sliderButton , int sliderDirection, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.sliderButton = sliderButton;
		this.sliderDirection = sliderDirection;
		this.sliderValue = 0;
		this.renderer.colours = new Colour[] { colour };
		this.link(this.sliderButton);
		Input.addListener(this);
	}
	
	/* The constructor */
	public GUISlider(Image image, GUIButton sliderButton , int sliderDirection, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.sliderButton = sliderButton;
		this.sliderDirection = sliderDirection;
		this.sliderValue = 0;
		this.renderer.colours = new Colour[] { Colour.WHITE };
		this.renderer.images = new Image[] { image };
		this.link(this.sliderButton);
		Input.addListener(this);
	}
	
	/* The constructor */
	public GUISlider(Colour colour, Image image, GUIButton sliderButton , int sliderDirection, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.sliderButton = sliderButton;
		this.sliderDirection = sliderDirection;
		this.sliderValue = 0;
		this.renderer.colours = new Colour[] { colour };
		this.renderer.images = new Image[] { image };
		this.link(this.sliderButton);
		Input.addListener(this);
	}
	
	/* The method called to update this component */
	protected void updateComponent() {
		//Update the button
		this.sliderButton.update();
	}
	
	/* The method called to render this component */
	protected void renderComponent() {
		//Check the slider direction
		if (this.sliderDirection == DIRECTION_VERTICAL) {
			//Clamp the boundaries
			if (this.sliderButton.position.y < 0)
				this.sliderButton.position.y = 0;
			else if (this.sliderButton.position.y > this.height - this.sliderButton.height)
				this.sliderButton.position.y = this.height - this.sliderButton.height;
			//Make sure the button is in the middle
			this.sliderButton.position.x = -this.sliderButton.width / 2 + this.width / 2;
		} else if (this.sliderDirection == DIRECTION_HORIZONTAL) {
			//Clamp the boundaries
			if (this.sliderButton.position.x < 0)
				this.sliderButton.position.x = 0;
			else if (this.sliderButton.position.x > this.width - this.sliderButton.width)
				this.sliderButton.position.x = this.width - this.sliderButton.width;
			//Make sure the button is in the middle
			this.sliderButton.position.y = -this.sliderButton.height / 2 + this.height / 2;
		}
		//Render the button
		this.sliderButton.render();
	}
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) { }
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) { }
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) { }
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) { }
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) {
		//Make sure this is visible and active
		if (this.visible && this.active) {
			//Get this sider's position
			Vector2D p = this.getPosition();
			//Check the direction of this slider
			if (this.sliderDirection == DIRECTION_VERTICAL) {
				if (this.sliderButton.mouseHoveringInside) {
					if (Mouse.y > p.y && Mouse.y < p.y + this.height) {
						this.sliderButton.position.y += e.dy;
						//Set the slider value
						this.sliderValue = (this.sliderButton.position.y / (this.height - this.sliderButton.height)) * 100;
					}
				}
			} else if (this.sliderDirection == DIRECTION_HORIZONTAL) {
				if (this.sliderButton.mouseHoveringInside) {
					if (Mouse.x > p.x && Mouse.x < p.x + this.width) {
						this.sliderButton.position.x += e.dx;
						//Set the slider value
						this.sliderValue = (this.sliderButton.position.y / (this.height - this.sliderButton.height)) * 100;
					}
				}
			}
			//Clamp the slider value
			if (this.sliderValue < 0)
				this.sliderValue = 0;
			else if (this.sliderValue > 100)
				this.sliderValue = 100;
		}
	}
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) { }
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) { }
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) { }
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) { }
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) { }
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) { }
	
	/* The get methods */
	public int getSliderDirection() { return this.sliderDirection; }
	public GUIButton getSiderButton() { return this.sliderButton; }
	public float getSliderValue() { return this.sliderValue; }
}