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
import org.andor.core.Vector2D;
import org.andor.core.input.Mouse;

public class GUIComponent extends Object2D {
	
	/* The GUIRenderer instance */
	public GUIComponentRenderer renderer;
	
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
	
	/* The group */
	public GUIGroup group;
	
	/* The constructor */
	public GUIComponent(RenderableObject2D object) {
		//Assign the variables
		this.renderer = new GUIComponentRenderer(object);
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
		if (this.visible && this.active) {
			//Check to see whether the mouse is inside this component
			if (this.getBounds().contains(Mouse.x, Mouse.y)) {
				//Check to see whether the mouse is already hovering
				if (! this.mouseHoveringInside) {
					//The mouse is hovering inside of this component
					this.mouseHoveringInside = true;
					//Call an onMouseEnter event
					this.callOnMouseEnterEvent();
				}
				//Check to see whether the left mouse button is down
				if (Mouse.leftButton) {
					//Check to see whether repeat click events are enabled
					if (this.repeatClickedEvents || (! this.repeatClickedEvents && ! this.hasBeenClickedEvent)) {
						//Set clicked to true
						this.clicked = true;
						this.hasBeenClickedEvent = true;
						//Call an onClicked event
						this.callOnClickedEvent();
					}
				} else {
					//Not clicked since the left mouse button is not down
					this.clicked = false;
					this.hasBeenClickedEvent = false;
				}
			} else {
				//Check to see whether the mouse is already not hovering
				if (this.mouseHoveringInside) {
					//The mouse is not hovering inside of this component
					this.mouseHoveringInside = false;
					//Call an onMouseLeave event
					this.callOnMouseLeaveEvent();
				}
				//Not clicked, because the mouse isn't even within the bounds of this component
				this.clicked = false;
				this.hasBeenClickedEvent = false;
			}
			//Update this component
			this.updateComponent();
		}
	}
	
	/* The method used to render this component */
	public void render() {
		//Make sure this component is visible
		if (this.visible) {
			//Render this component
			this.renderer.render(this, this.active);
			this.renderComponent();
		}
	}
	
	/* The method used to update this component */
	protected void updateComponent() { }
	
	/* The method used to render this component */
	protected void renderComponent() { }
	
	/* The method called when the mouse enter's this component */
	protected void componentOnMouseEnter() { }
	
	/* The method called when the mouse leave's this component */
	protected void componentOnMouseLeave() { }
	
	/* The method called when the component is clicked */
	protected void componentOnClicked() { }
	
	/* The method used to render some text */
	public void renderText(String text) {
		//Get the position
		Vector2D p = this.getPosition();
		this.renderer.font.render(text, p.x, p.y);
	}
	public void renderText(String text, float x, float y) { this.renderer.font.render(text, x, y); }
	public void renderTextAtCenter(String text) {
		//Get the width and height of the text
		float textWidth = this.renderer.font.getWidth(text);
		float textHeight = this.renderer.font.getHeight(text);
		//Get the position
		Vector2D p = this.getPosition();
		//Calculate the position to render the text
		float textX = (p.x + (this.width / 2)) - (textWidth / 2);
		float textY = (p.y + (this.height / 2)) - (textHeight / 2);
		//Render the text
		this.renderText(text, textX, textY);
	}
	
	public void renderTextAtCenter(String text, Vector2D offset) {
		//Get the width and height of the text
		float textWidth = this.renderer.font.getWidth(text);
		float textHeight = this.renderer.font.getHeight(text);
		//Get the position
		Vector2D p = this.getPosition();
		//Calculate the position to render the text
		float textX = (p.x + (this.width / 2)) - (textWidth / 2);
		float textY = (p.y + (this.height / 2)) - (textHeight / 2);
		//Add the offset
		textX += offset.x;
		textY += offset.y;
		//Render the text
		this.renderText(text, textX, textY);
	}
	
	/* The method used to add a GUIComponent listener */
	public void addListener(GUIComponentListener listener) {
		this.componentListeners.add(listener);
	}
	
	/* The method used to call an onMouseEnter event from this component */
	public void callOnMouseEnterEvent() {
		//Call the method
		this.componentOnMouseEnter();
		//Go through the listeners
		for (int a = 0; a < this.componentListeners.size(); a++)
			//Call the event in the current listener
			this.componentListeners.get(a).onMouseEnter(this);
	}
	
	/* The method used to call an onMouseLeave event from this component */
	public void callOnMouseLeaveEvent() {
		//Call the method
		this.componentOnMouseLeave();
		//Go through the listeners
		for (int a = 0; a < this.componentListeners.size(); a++)
			//Call the event in the current listener
			this.componentListeners.get(a).onMouseLeave(this);
	}
	
	/* The method used to call an onClicked event from this component */
	public void callOnClickedEvent() {
		//Call the method
		this.componentOnClicked();
		//Go through the listeners
		for (int a = 0; a < this.componentListeners.size(); a++)
			//Call the event in the current listener
			this.componentListeners.get(a).onClicked(this);
	}
	
	/* The methods used to set/toggle/return some values */
	public void setName(String name) { this.name = name; }
	public void setVisible(boolean visible) { this.visible = visible; }
	public void setActive(boolean active) { this.active = active; }
	public void setRepeatClickedEvents(boolean repeatClickedEvents) { this.repeatClickedEvents = repeatClickedEvents; }
	public void setGroup(GUIGroup group) { this.group = group; }
	public void toggleVisible() { this.visible = ! this.visible; }
	public void toggleActive() { this.active = ! this.active; }
	public String getName() { return this.name; }
	public boolean isVisible() { return this.visible; }
	public boolean isActive() { return this.active; }
	public boolean isRepeatClickedEventsEnabled() { return this.repeatClickedEvents; }
	public GUIGroup getGroup() { return this.group; }
	public boolean belongsToGroup() { return this.group != null; }
	
}