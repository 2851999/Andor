/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Vector2D;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;
import org.andor.utils.MaskUtils;

public class GUITextBox extends GUIComponent implements InputListenerInterface {
	
	/* The text within this text box */
	public String text;
	
	/* The text that is rendered */
	public String renderText;
	
	/* The boolean that states whether this text box is currently selected */
	public boolean selected;
	
	/* The boolean that states whether this text box is masked */
	public boolean masked;
	
	/* The mask */
	public String mask;
	
	/* The default text */
	public String defaultText;
	
	/* The font of the default text */
	public Font defaultTextFont;
	
	/* The cursor's position within the text */
	public int cursorIndex;
	
	/* The cursor */
	public GUITextBoxCursor cursor;
	
	/* The constructor */
	public GUITextBox(Colour colour, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Set the values
		this.renderer.colours = new Colour[] { colour };
		//Setup all of the variables
		this.setup();
	}
	
	/* The constructor */
	public GUITextBox(Image image, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE), width, height);
		//Set the values
		this.renderer.images = new Image[] { image };
		//Setup all of the variables
		this.setup();
	}
	
	/* The constructor */
	public GUITextBox(Image image, Colour colour, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE), width, height);
		//Set the values
		this.renderer.images = new Image[] { image };
		this.renderer.colours = new Colour[] { colour };
		//Setup all of the variables
		this.setup();
	}
	
	/* The method used to setup all of the variables */
	public void setup() {
		//Add this to the list of input listeners
		Input.addListener(this);
		//Assign the variables
		this.text = "";
		this.renderText = "";
		this.selected = false;
		this.masked = false;
		this.mask = "*";
		this.defaultText = "";
		this.cursorIndex = 0;
		this.cursor = new GUITextBoxCursor(this, 1f);
	}
	
	/* The method used to update this component */
	protected void updateComponent() {
		
	}
	
	/* The method used to render this component */
	protected void renderComponent() {
		//The font that should be used
		Font f = this.renderer.font;
		//Check to see whether the default text should be used
		if (this.shouldUseDefaultText()) {
			//Set the render text
			this.renderText = this.defaultText;
			//Check to see whether the default text font should be used
			if (this.defaultTextFont != null)
				f = this.defaultTextFont;
		} else {
			//Update the text to render
			this.updateRenderText();
		}
		//Get the position of this component
		Vector2D p = this.getPosition();
		//Get the position to render the text at
		float textX = p.x + 1;
		float textY = (p.y + (this.height / 2)) - (f.getHeight(this.renderText) / 2);
		//Render the text
		f.render(this.renderText, textX, textY);
		//Make sure this is selected
		if (this.selected)
			//Render the cursor
			this.cursor.render();
	}
	
	/* The method used to update the render text */
	public void updateRenderText() {
		if (! this.shouldUseDefaultText()) {
			//Check to see whether the text should be masked
			if (this.masked)
				//Mask the text
				this.renderText = MaskUtils.mask(this.text, this.mask);
			else {
				//Set the text
				this.renderText = this.text;
			}
		}
	}
	
	/* The method used to move the cursor to a specific place given the x
	 * coordinate */
	public void moveCursor(double x) {
		//Update the textToRender for rendering since we need to look at what the user can see
		this.updateRenderText();
		//Get the text to render as an array
		char[] textValue = this.renderText.toCharArray();
		//The current string
		String currentString = "";
		//The new cursor place
		int newPlace = 0;
		//Go through the texts
		for (int a = 0; a < textValue.length; a++) {
			//Add onto the current string
			currentString += textValue[a];

			//The x position to look for
			double lookX = 0;

			//The width of the string
			double widthOfString = this.renderer.font.getWidth(currentString);
			//The width of the last character
			double widthOfLastCharacter = this.renderer.font.getWidth(currentString.substring(currentString.length() - 1));

			//Add onto lookX the position this text box starts rendering the text
			lookX += this.position.x + 1;
			//Add onto lookX the width of the string - (the width of the last character / 2)
			lookX += widthOfString - (widthOfLastCharacter / 2);

			//Check the width of the string against the x position
			if (lookX < x)
				//Add 1 to the new place
				newPlace++;
			else
				//Exit the loop
				break;
		}
		//Assign the cursor's position relative to all of the text in this text box
		this.cursorIndex = newPlace;
		//Show the cursor
		this.cursor.showCursor();
	}
	
	/* The method called when the mouse enter's this component */
	protected void componentOnMouseEnter() { }
	
	/* The method called when the mouse leave's this component */
	protected void componentOnMouseLeave() { }
	
	/* The method called when the component is clicked */
	protected void componentOnClicked() {
		//Set selected to true
		this.selected = true;
		//Show the cursor
		this.cursor.showCursor();
	}
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) {
		//Check to see whether the mouse wasn't clicked within this component
		if (! this.mouseHoveringInside)
			//Set selected to false
			this.selected = false;
		else if (this.selected)
			this.moveCursor(e.x);
	}
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) { }
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) { }
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) { }
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) { }
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) {
		//Check to see whether this component is selected
		if (this.selected) {
			//Check the key code
			if (e.keyCode == Keyboard.KEY_BACKSPACE_CODE) {
				//Make sure there is text to be removed and the cursor index isn't at the beginning
				if (this.text.length() > 0 && this.cursorIndex > 0) {
					//Split up the text using the cursor index
					String front = this.text.substring(0, this.cursorIndex);
					String back = this.text.substring(this.cursorIndex);
					//Remove the last letter
					this.text = front.substring(0, front.length() - 1) + back;
					//Decrease the cursor index
					this.cursorIndex--;
				}
			} else if (e.keyCode == Keyboard.KEY_LEFT_CODE) {
				//Make sure the cursor's current index is more than 0
				if (this.cursorIndex > 0)
					//Decrement the cursor index
					this.cursorIndex--;
			} else if (e.keyCode == Keyboard.KEY_RIGHT_CODE) {
				//Make sure the cursor's current index is less than the length of the text
				if (this.cursorIndex < this.text.length())
					//Increment the cursor index
					this.cursorIndex++;
			} else {
				//Make sure the key that was pressed's character should be added to the text
				if (this.isDefined(e.keyCharacter)) {
					//Split up the text using the cursor index
					String front = this.text.substring(0, this.cursorIndex);
					String back = this.text.substring(this.cursorIndex);
					//Add the character onto the text
					this.text = front + e.keyCharacter + back;
					//Increase the cursor index
					this.cursorIndex++;
				}
			}
			//Show the cursor
			this.cursor.showCursor();
		}
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) { }
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) { }
	
	/* The method used to check to see whether a character is defined */
	private boolean isDefined(char character) {
		return ((character != '\u0000') && (Character.isDefined(character)) && (! Character.isISOControl(character)));
	}
	
	/* The method used to check whether the default text should be used */
	public boolean shouldUseDefaultText() {
		return ! this.selected && this.text.length() == 0 && this.defaultText.length() > 0;
	}
	
}