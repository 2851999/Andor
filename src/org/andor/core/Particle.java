/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.Timer;

public class Particle {
	
	/* The values used with this particle */
	public Vector3D position;
	public Vector3D velocity;
	public long lifeTime;
	
	/* The timer for this particle */
	public Timer timer;
	
	/* The colour */
	public Colour colour;
	
	/* The effect */
	public ParticleEffect effect;
	
	/* The constructor */
	public Particle(Vector3D position, Vector3D velocity, long lifeTime, Colour colour, ParticleEffect effect) {
		//Assign the variables
		this.position = position;
		this.velocity = velocity;
		this.lifeTime = lifeTime;
		this.colour = colour;
		this.effect = effect;
		this.timer = new Timer();
		this.timer.start();
	}
	
	/* The method used to update this particle */
	public void update() {
		//Update this particles position
		this.position.add(this.velocity);
		//Make sure the effect has been set
		if (this.effect != null)
			this.effect.update(this);
	}
	
	/* The set/get methods */
	public void setPosition(Vector3D position) { this.position = position; }
	public void setVelocity(Vector3D velocity) { this.velocity = velocity; }
	public void setLifeTime(long lifeTime) { this.lifeTime = lifeTime; }
	public void setEffect(ParticleEffect effect) { this.effect = effect; }
	public Vector3D getPosition() { return this.position; }
	public Vector3D getVelocity() { return this.velocity; }
	public long getLifeTime() { return this.lifeTime; }
	public float[] getVertices() { return this.position.getValues(); }
	public float[] getColours() { return this.colour.getValuesRGBA(); }
	public ParticleEffect getEffect() { return this.effect; }
	public boolean isDead() { return this.timer.hasTimePassed(this.lifeTime); }
	public float getPercentageOfLife() { return ((float) this.timer.getTime() / (float) this.lifeTime) * 100f; }
	
}