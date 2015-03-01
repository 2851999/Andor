/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Font;
import org.andor.core.Vector2D;

public class GUITextDisplayArea extends GUIComponent {
	
	/* The text */
	private List<String> text;
	
	/* The constructor */
	public GUITextDisplayArea(String text, float width) {
		//Call the super constructor
		super(null);
		//Assign the variables
		this.width = width;
		//Calculate the text
		calculateText(text);
	}
	
	/* The constructor */
	public GUITextDisplayArea(String text, Font font, float width) {
		//Call the super constructor
		super(null);
		//Assign the variables
		this.renderer.font = font;
		this.width = width;
		//Calculate the text
		calculateText(text);
	}
	
	/* The method used to calculate the text for this component */
	public void calculateText(String text) {
		//Split up the text
		String[] split = text.split(" ");
		//Initialise the list
		this.text = new ArrayList<String>();
		//Go through the text
		for (int a = 0; a < split.length; a++) {
			//Get the current position in the array
			int position = this.text.size() - 1;
			//Check to see whether any words have been added
			if (this.text.size() == 0)
				//Add the first word
				this.text.add(split[a]);
			else {
				//Check to see whether the next word can be added
				if (this.renderer.font.getWidth(this.text.get(position) + " " + split[a]) <= this.width)
					//Add the current word to the last line of text
					this.text.set(position, this.text.get(position) + " " + split[a]);
				else
					//Add the current word on a new line
					this.text.add(split[a]);
			}
		}
		//Assign the height
		this.height = this.renderer.font.getHeight("") * this.text.size();
	}
	
	/* The method used to render this component */
	protected void renderComponent() {
		//The current offset
		Vector2D offset = new Vector2D();
		//Go through each line of text
		for (int a = 0; a < this.text.size(); a++) {
			//Render the current line of text
			this.renderText(this.text.get(a), offset);
			//Add onto the offset
			offset.y += this.renderer.font.getHeight("");
		}
	}
	
	/* The methods used to set/return values */
	public void setText(String text) {
		//Calculate the text
		calculateText(text);
	}
	public String getText() {
		//The text
		String text = "";
		//Go through each line of text
		for (int a = 0; a < this.text.size(); a++)
			//Add the current line to the text
			text += this.text.get(a);
		//Return the text
		return text;
	}
	
}