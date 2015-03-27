/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.core.render.Renderer;

public class EffectColourChange extends Effect {
	
	/* The name of this effect */
	public static final String NAME = "Colour Change";
	
	/* The initial colour */
	public Colour initial;
	
	/* The current colour value */
	public Colour current;
	
	/* The colour to fade to */
	public Colour end;
	
	/* The time the whole effect should take */
	public long time;
	
	/* The constructor */
	public EffectColourChange(Colour initial, Colour end, long time, Renderer renderer) {
		super(NAME, renderer);
		//Assign the variables
		this.initial = initial;
		this.current = initial.clone();
		this.time = time;
		this.end = end;
	}
	
	/* The method called to update this effect given the delta */
	public void update(long delta) {
		//Calculate the difference between the two colours
		Colour difference = this.end.subtractRGBA(this.initial);
		//Calculate the value
		float value = (float) delta / (float) time;
		//Multiply by the proportion of the time that has elapsed
		difference = difference.multiplyRGB(value);
		//Assign the current colour
		current = current.addRGBA(difference);
		//Update the colour
		this.getRenderer().updateColour(current);
	}
	
}