/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import java.util.List;

import org.andor.utils.ArrayUtils;

public class KeyboardShortcut {
	
	/* The name of this shortcut */
	public String name;
	
	/* The keys needed to be pressed for this shortcut */
	public List<Integer> keys;
	
	/* The current state of each key */
	public boolean[] states;
	
	/* The instance of KeyboardShortcuts */
	public KeyboardShortcuts instance;
	
	/* The constructor */
	public KeyboardShortcut(String name, int[] keys) {
		//Assign the variables
		this.name = name;
		this.keys = ArrayUtils.toIntegerList(keys);
		this.states = new boolean[keys.length];
	}
	
	/* The method used to check whether this event has been completed and call
	 * any events if necessary */
	public void check() {
		//Check to see whether this shortcut has been completed
		if (this.hasCompleted()) {
			//Make sure the instance has been assigned
			if (this.instance != null)
				//Call the event
				this.instance.callOnShortcut(this);
		}
	}
	
	/* The method used to check whether this keyboard shortcut has been completed */
	public boolean hasCompleted() {
		//The boolean that represents whether an event has occurred
		boolean completed = true;
		//Go through each state
		for (int a = 0; a < this.states.length; a++)
			completed = completed && this.states[a];
		//Return the value
		return completed;
	}
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) {
		//Check all of the keys against the key code
		for (int a = 0; a < this.keys.size(); a++) {
			if (e.keyCode == this.keys.get(a)) {
				//Set that state to true
				this.states[a] = true;
				//Check this shortcut
				this.check();
			}
		}
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {
		//Check all of the keys against the key code
		for (int a = 0; a < this.keys.size(); a++) {
			if (e.keyCode == this.keys.get(a))
				//Set that state to false
				this.states[a] = false;
		}
	}
	
	
}