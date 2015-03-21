/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.core.render.Renderer;

public abstract class Effect {
	
	/* The name of the effect */
	public String name;
	
	/* The renderer instance used to apply the effect to */
	public Renderer renderer;
	
	/* The constructor */
	public Effect(String name, Renderer renderer) {
		//Assign the variables
		this.name = name;
		this.renderer = renderer;
	}
	
	/* The method used to apply this effect given the current delta */
	public abstract void update(long delta);
	
}