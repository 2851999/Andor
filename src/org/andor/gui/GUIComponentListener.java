/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/


package org.andor.gui;

public interface GUIComponentListener {
	
	/* The method called when the mouse enters a gui component */
	public void onMouseEnter(GUIComponent component);
	
	/* The method called when the mouse exits a gui component */
	public void onMouseLeave(GUIComponent component);
	
	/* The method called when a gui component is clicked */
	public void onClicked(GUIComponent component);
	
}