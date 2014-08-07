/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/


package org.andor.core.input;

import org.andor.utils.Timer;

public class ControllerRumbler {
	
	/* The controller this rumbler is part of */
	public InputController controller;
	
	/* The index of this rumbler */
	public int index;
	
	/* The name of this rumbler */
	public String name;
	
	/* The timer */
	public Timer timer;
	
	/* The current time this rumbler has been set to run for */
	public long time;
	
	/* The boolean that states whether this rumbler is currently running */
	public boolean running;
	
	/* The constructor */
	public ControllerRumbler(InputController controller, int index) {
		//Assign the variables
		this.controller = controller;
		this.index = index;
		this.name = this.controller.getController().getButtonName(this.index);
		this.timer = new Timer();
		this.time = -1;
		this.running = false;
	}
	
	/* The method used to check this rumbler */
	public void check() {
		//Check the timer if needed
		if (this.time != -1 && this.running) {
			if (this.timer.hasTimePassed(this.time)) {
				//Stop running
				this.stop();
				this.timer.reset();
			}
		}
	}
	
	/* The method used to make this rumbler stop rumbling */
	public void stop() {
		this.rumble(0);
	}
	
	/* The method used to make this rumbler rumble given the strength */
	public void rumble(float strength) {
		this.controller.controller.setRumblerStrength(this.index, strength);
		this.time = -1;
		if (strength == 0)
			this.running = false;
		else
			this.running = true;
	}
	
	/* The method used to make this rumbler rumble for a certain amount of time */
	public void rumble(long time, float strength) {
		//Assign the variables
		this.time = time;
		//Rumble
		this.controller.controller.setRumblerStrength(this.index, strength);
		this.running = true;
		this.timer.start();
	}
	
	/* The get methods */
	public InputController getController() { return this.controller; }
	public int getIndex() { return this.index; }
	public String getName() { return this.name; }
	public boolean isRunning() { return this.running; }
	
}