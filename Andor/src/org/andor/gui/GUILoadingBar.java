/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Vector2D;

public class GUILoadingBar extends GUIComponent {

	/* The number of stages in the loading bar */
	public int loadingStages;

	/* The current stage in the loading bar */
	public int currentLoadingStage;
	
	/* The fill */
	public GUIFill barFill;

	/* The constructor */
	public GUILoadingBar(int loadingStages, int width, int height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.loadingStages = loadingStages;
		this.currentLoadingStage = 0;
		this.barFill = new GUIFill(this, Colour.BLACK);
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
		this.barFill = new GUIFill(this, Colour.BLACK);
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
		this.barFill = new GUIFill(this, Colour.BLACK);
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
		this.barFill = new GUIFill(this, Colour.BLACK);
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
		this.barFill.position = new Vector2D(this.getPosition());
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

}