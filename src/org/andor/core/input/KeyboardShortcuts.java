/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

public class KeyboardShortcuts extends InputListener {
	
	/* The listeners */
	public List<KeyboardShortcutListener> listeners;
	
	/* The shortcuts */
	public List<KeyboardShortcut> shortcuts;
	
	/* The constructor */
	public KeyboardShortcuts() {
		//Assign the variables
		this.listeners = new ArrayList<KeyboardShortcutListener>();
		this.shortcuts = new ArrayList<KeyboardShortcut>();
	}
	
	/* The method used to add a shortcut */
	public void add(KeyboardShortcut shortcut) {
		//Make this the shortcut's instance
		shortcut.instance = this;
		//Add it to the list
		this.shortcuts.add(shortcut);
	}
	
	/* The method used to add a listener */
	public void addListener(KeyboardShortcutListener listener) {
		//Add it to the list
		this.listeners.add(listener);
	}
	
	/* The method used to call an onShortcut() event */
	public void callOnShortcut(KeyboardShortcut e) {
		//Go through each listener
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the method in the current listener
			this.listeners.get(a).onShortcut(e);
	}
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) {
		//Call the method in all of the shortcuts
		for (int a = 0; a < this.shortcuts.size(); a++)
			this.shortcuts.get(a).onKeyPressed(e);
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {
		//Call the method in all of the shortcuts
		for (int a = 0; a < this.shortcuts.size(); a++)
			this.shortcuts.get(a).onKeyReleased(e);
	}
	
}