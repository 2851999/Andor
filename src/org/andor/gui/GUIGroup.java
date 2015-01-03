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

public class GUIGroup extends GUIComponent {
	
	/* The list of components within this group */
	private List<GUIComponent> groupComponents;
	
	/* The constructor */
	public GUIGroup(String name) {
		//Call the super constructor
		super(null);
		//Assign the variables
		this.name = name;
		this.groupComponents = new ArrayList<GUIComponent>();
	}
	
	/* The method used to update this component */
	public void updateComponent() {
		//Go through all of the components within this group
		for (int a = 0; a < this.groupComponents.size(); a++)
			//Update the current component
			this.groupComponents.get(a).update();
	}
	
	/* The method used to render this component */
	public void renderComponent() {
		//Go through all of the components within this group
		for (int a = 0; a < this.groupComponents.size(); a++)
			//Render the current component
			this.groupComponents.get(a).render();
	}
	
	/* The method used to add a component to this group */
	public void add(GUIComponent component) {
		//Set the components group to this
		component.setGroup(this);
		//Link the component to this
		this.link(component);
		//Add the component to the list of components
		this.groupComponents.add(component);
	}
	
	/* The set/get methods */
	public void setGroupComponents(List<GUIComponent> groupComponents) { this.groupComponents = groupComponents; }
	public List<GUIComponent> getGroupComponents() { return this.groupComponents; }
	
}