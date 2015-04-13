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

public class GUILoadingBar extends GUIComponent {

	/* The number of stages in the loading bar */
	public int loadingStages;

	/* The current stage in the loading bar */
	public int currentLoadingStage;
	
	/* The fill */
	public GUIFill barFill;

	/* The constructor */
	public GUILoadingBar(int loadingStages, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.loadingStages = loadingStages;
		this.currentLoadingStage = 0;
		this.renderer.colours = new Colour[] { Colour.WHITE };
		this.barFill = new GUIFill(this, Colour.WHITE);
	}
	
	/* The constructor */
	public GUILoadingBar(int loadingStages, Colour colour, float width, float height) {
		//Call the super constructor
		//If using a colour, the colour must be used to colour VBO setup properly (Same for images)
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.loadingStages = loadingStages;
		this.currentLoadingStage = 0;
		this.renderer.colours = new Colour[] { colour };
		this.barFill = new GUIFill(this, Colour.WHITE);
	}
	
	/* The constructor */
	public GUILoadingBar(int loadingStages, Image image, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.loadingStages = loadingStages;
		this.currentLoadingStage = 0;
		this.renderer.colours = new Colour[] { Colour.WHITE };
		this.renderer.images = new Image[] { image };
		this.barFill = new GUIFill(this, Colour.WHITE);
	}
	
	/* The constructor */
	public GUILoadingBar(int loadingStages, Image image, Colour colour, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.loadingStages = loadingStages;
		this.currentLoadingStage = 0;
		this.renderer.images = new Image[] { image };
		this.renderer.colours = new Colour[] { colour };
		this.barFill = new GUIFill(this, Colour.WHITE);
	}

	/* The overrideable method called when the current stage has changed */
	public void stageChanged() {

	}

	/* The method to update the loading bar */
	protected void updateComponent() {
		//Update the bar
		this.barFill.update();
	}

	/* The method used render the loading bar */
	protected void renderComponent() {
		//Get the width of the bar
		float barWidth = (this.width / this.loadingStages) * this.currentLoadingStage;
		//Setup the bar fill
		this.barFill.width = barWidth;
		this.barFill.height = this.height;
		//Render the loading bar
		this.barFill.render();
	}

	/* The method to increase the current stage */
	public void completedStage() {
		//Check the current stage
		if (this.currentLoadingStage != this.loadingStages) {
			//Increase the current loading stage
			this.currentLoadingStage ++;
			//Call the stage changed method
			this.stageChanged();
		}
	}

	/* Returns a boolean value which states whether the
	   loading bar has finished */
	public boolean hasCompleted() {
		//Return the correct value
		return this.currentLoadingStage == this.loadingStages;
	}

	/* Returns the percentage of the process that is completed */
	public float getPercentageComplete() {
		//Return the percentage
		return (float)((float) this.currentLoadingStage / (float) this.loadingStages) * 100;
	}
	
	/* The methods used to set/get the colour/images for the loading bar */
	public void setBackgroundColour(Colour backgroundColour) { this.renderer.colours = new Colour[] { backgroundColour }; }
	public Colour getBackgroundColour() { return this.renderer.colours[0]; }
	public void setFillColour(Colour fillColour) { this.barFill.colour = fillColour; }
	public Colour getFillColour() { return this.barFill.colour; }
	public void setBackgroundImage(Image backgroundImage) { this.renderer.images = new Image[] { backgroundImage }; }
	public Image getBackgroundImage() { return this.renderer.images[0]; }
	public void setFillImage(Image fillImage) { this.barFill.setImage(fillImage);; }
	public Image getFillImage() { return this.barFill.image; }
	public void setCurrentStage(int currentLoadingStage) { this.currentLoadingStage = currentLoadingStage; }
	public int getCurrentStage() { return this.currentLoadingStage; }
	
	public boolean hasBackgroundColour() { return this.renderer.colours != null; }
	public boolean hasBackgroundImage() { return this.renderer.images != null; }
	public boolean hasFillColour() { return this.barFill.colour != null; }
	public boolean hasFillImage() { return this.barFill.image != null; }
	
}