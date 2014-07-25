/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Object2D;
import org.andor.core.RenderableObject2D;
import org.andor.core.input.Mouse;

public class GUIComponent extends Object2D {
	
	/* The GUIRenderer instance */
	public GUIRenderer renderer;
	
	/* The name of this component */
	public String name;
	
	/* The boolean that states whether this component is visible */
	public boolean visible;
	
	/* The boolean that states whether this component is active */
	public boolean active;
	
	/* The boolean that states whether the mouse is hovering inside this component */
	public boolean mouseHoveringInside;
	
	/* The boolean that states whether the mouse has been clicked inside this component */
	public boolean clicked;
	
	/* The boolean that states whether clicked events should be repeated */
	public boolean repeatClickedEvents;
	
	/* The boolean that states whether a clicked event has already been sent */
	private boolean hasBeenClickedEvent;
	
	/* The component listeners */
	private List<GUIComponentListener> componentListeners;
	
	/* The constructor */
	public GUIComponent(RenderableObject2D object) {
		//Assign the variables
		this.renderer = new GUIRenderer(object);
		this.name = "";
		this.visible = true;
		this.active = true;
		this.mouseHoveringInside = false;
		this.clicked = false;
		this.repeatClickedEvents = false;
		this.hasBeenClickedEvent = false;
		this.componentListeners = new ArrayList<GUIComponentListener>();
	}
	
	/* The method used to update this component */
	public void update() {
		//Make sure this component is active
		if (this.active) {
			//Check to see whether the mouse is inside this component
			if (this.getBounds().contains(Mouse.x, Mouse.y)) {
				//The mouse is hovering inside of this component
				this.mouseHoveringInside = true;
				//Check to see whether the left mouse button is down
				if (Mouse.leftButton) {
					//Check to see whether repeat click events are enabled
					if (this.repeatClickedEvents || (! this.repeatClickedEvents && ! this.hasBeenClickedEvent)) {
						//Set clicked to true
						this.clicked = true;
						this.hasBeenClickedEvent = true;
						//Call a clicked event
						this.callClickedEvent();
					}
				} else {
					//Not clicked since the left mouse button is not down
					this.clicked = false;
					this.hasBeenClickedEvent = false;
				}
			} else {
				//The mouse is not hovering inside of this component
				this.mouseHoveringInside = false;
				//Not clicked, because the mouse isn't even within the bounds of this component
				this.clicked = false;
				this.hasBeenClickedEvent = false;
			}
			//Update this component
			this.updateComponent();
		}
	}
	
	/* The method used to update this component */
	public void updateComponent() {
		
	}
	
	/* The method used to render this component */
	public void render() {
		//Make sure this component is visible
		if (this.visible) {
			//Render this component
			this.renderer.render(this);
			this.renderComponent();
		}
	}
	
	/* The method used to render this component */
	public void renderComponent() {
		
	}
	
	/* The method used to render some text */
	public void renderText(String text, float x, float y) { this.renderer.font.render(text, x, y); }
	public void renderTextAtCenter(String text) {
		//Get the width and height of the text
		float textWidth = this.renderer.font.getWidth(text);
		float textHeight = this.renderer.font.getHeight(text);
		//Calculate the position to render the text
		float textX = (this.position.x + (this.width / 2)) - (textWidth / 2);
		float textY = (this.position.y + (this.height / 2)) - (textHeight / 2);
		//Render the text
		this.renderText(text, textX, textY);
	}
	
	/* The method used to add a GUIComponent listener */
	public void addListener(GUIComponentListener listener) {
		this.componentListeners.add(listener);
	}
	
	/* The method used to call a clicked event from this component */
	public void callClickedEvent() {
		//Go through the listeners
		for (int a = 0; a < this.componentListeners.size(); a++)
			//Call the event in the current listener
			this.componentListeners.get(a).clicked(this);
	}
	
	/* The methods used to set/toggle/return some values */
	public void setName(String name) { this.name = name; }
	public void setVisible(boolean visible) { this.visible = visible; }
	public void setActive(boolean active) { this.active = active; }
	public void setRepeatClickedEvents(boolean repeatClickedEvents) { this.repeatClickedEvents = repeatClickedEvents; }
	public void toggleVisible() { this.visible = ! this.visible; }
	public void toggleActive() { this.active = ! this.active; }
	public String getName() { return this.name; }
	public boolean isVisible() { return this.visible; }
	public boolean isActive() { return this.active; }
	public boolean isRepeatClickedEventsEnabled() { return this.repeatClickedEvents; }
	
}