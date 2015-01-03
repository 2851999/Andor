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

public class GUIRadioCheckBox extends GUICheckBox {
	
	/* The constructor */
	public GUIRadioCheckBox(Colour[] colours, float width, float height) {
		//Call the super constructor
		super(colours, width, height);
	}
	
	/* The constructor */
	public GUIRadioCheckBox(Image[] images, float width, float height) {
		//Call the super constructor
		super(images, width, height);
	}
	
	/* The constructor */
	public GUIRadioCheckBox(Image[] images, Colour[] colours, float width, float height) {
		//Call the super constructor
		super(images, colours, width, height);
	}
	
	/* The method called when the component is clicked */
	protected void componentOnClicked() {
		//Toggle checked
		this.checked = ! this.checked;
		//Check to make sure this component is part of a GUIGroup
		if (this.belongsToGroup()) {
			//Go through the other components in the same group
			for (int a = 0; a < this.group.getGroupComponents().size(); a++) {
				//Get an instance of the current component
				GUIComponent component = this.group.getGroupComponents().get(a);
				//Check to make sure the component is also an instance of GUIRadioCheckBox, but not the same as this
				if (component instanceof GUIRadioCheckBox && component != this)
					//Make sure the component is unchecked
					((GUIRadioCheckBox) component).checked = false;
			}
		}
	}
	
}