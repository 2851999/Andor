/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.andor.utils.Timer;

public class ParticleEmitter extends RenderableObject3D {
	
	/* The amount of particles to produce each update */
	public int particlesPerUpdate;
	
	/* The initial velocity */
	public Vector3D particleInitialVelocity;
	
	/* The life time of each particle */
	public long particleLifeTime;
	
	/* The colour of each particle */
	public Colour particleColour;
	
	/* The particle effect */
	public ParticleEffect particleEffect;
	
	/* The list of particles */
	public List<Particle> particles;
	
	/* The value used to slow down the particles when they are generated
	 * (This also makes the particles more uniformed)
	 * This should never be 0, and 1 looks a bit like rain (Spread out)
	 * 120 makes it look like the particles come from a single point */
	public float uniformity;
	
	/* The boolean that states whether this emitter is emitting */
	public boolean emitting;
	
	/* The random generator */
	private Random randomGenerator;
	
	/* The boolean that represents whether this should be run in 2D mode (Ignore the z coordinate) */
	public boolean ignoreZ;
	
	/* The timer */
	public Timer timer;
	
	/* The life time of this emitter */
	public long emittingLifeTime;
	
	/* The constructor */
	public ParticleEmitter() {
		this(new Vector3D(), 20, new Vector3D(), 500, Colour.WHITE.clone(), null, 120);
	}
	
	/* The constructor */
	public ParticleEmitter(Vector3D position, int particlesPerUpdate, Vector3D particleInitialVelocity, long particleLifeTime, Colour particleColour, ParticleEffect particleEffect, float uniformity) {
		//Assign the variables
		this.position = position;
		this.particlesPerUpdate = particlesPerUpdate;
		this.particleInitialVelocity = particleInitialVelocity;
		this.particleLifeTime = particleLifeTime;
		this.particleColour = particleColour;
		this.particleEffect = particleEffect;
		this.uniformity = uniformity;
		this.particles = new ArrayList<Particle>();
		this.emitting = true;
		this.ignoreZ = false;
		this.randomGenerator = new Random();
		this.renderer = Renderer.create(Renderer.POINTS, Renderer.VERTEX_VALUES_COUNT_3D);
		this.renderer.setValues(new float[] { 0, 0, 0 }, new Colour(0.0f, 0.0f, 0.0f, 0.0f).getValuesRGBA());
		this.renderer.setupBuffers();
		this.emittingLifeTime = 0;
		this.timer = new Timer();
		this.timer.start();
	}
	
	/* The method called to update this particle emitter */
	public void update() {
		//Make sure this doesn't go on for ever
		if (this.emittingLifeTime != 0) {
			//Check the timer
			if (this.timer.hasTimePassed(emittingLifeTime))
				//Stop emitting
				this.emitting = false;
		}
		//Make sure this emitter is emitting
		if (this.emitting) {
			//Add as many particles as necessary
			for (int a = 0; a < this.particlesPerUpdate; a++)
				this.particles.add(generateParticle());
		}
		//The current number
		int count = 0;
		//Go through each particle
		while (count < this.particles.size()) {
			//Make sure the current particle is not dead
			if (! this.particles.get(count).isDead())
				//Increment count
				count++;
			else
				//Remove the current particle
				this.particles.remove(count);
		}
		//The vertices array
		float[] vertices = new float[this.particles.size() * 3];
		//The colours array
		float[] colours = new float[this.particles.size() * 4];
		//Go through each particle
		for (int a = 0; a < this.particles.size(); a++) {
			//Update the current particle
			this.particles.get(a).update();
			//Get the current vertices and add it to the array
			float[] cpv = this.particles.get(a).getVertices();
			int startV = a * 3;
			vertices[startV] = cpv[0];
			vertices[startV + 1] = cpv[1];
			vertices[startV + 2] = cpv[2];
			//Get the current colours and add it to the array
			float[] cpc = this.particles.get(a).getColours();
			int startC = a * 4;
			colours[startC] = cpc[0];
			colours[startC+ 1] = cpc[1];
			colours[startC + 2] = cpc[2];
			colours[startC + 3] = cpc[3];
		}
		//Update this renderer
		this.renderer.updateVertices(vertices);
		this.renderer.updateColours(colours);
	}
	
	/* The method used to generate a particle */
	public Particle generateParticle() {
		//The particle
		Particle particle = null;
		//The particle values
		Vector3D particlePosition = new Vector3D();
		Vector3D particleVelocity = null;
		//The random values
		float randomX = this.randomGenerator.nextFloat() - 0.5f;
		float randomY = this.randomGenerator.nextFloat() - 0.5f;
		float randomZ = 0;
		//Make sure z shouldn't be ignored
		if (! this.ignoreZ)
			randomZ = this.randomGenerator.nextFloat() - 0.5f;
		particleVelocity = new Vector3D(
				(randomX + this.particleInitialVelocity.x) / uniformity,
				(randomY + this.particleInitialVelocity.y) / uniformity,
				(randomZ + this.particleInitialVelocity.z) / uniformity);
		//Create the particle
		particle = new Particle(particlePosition, particleVelocity, this.particleLifeTime, this.particleColour, this.particleEffect);
		//Return the particle
		return particle;
	}
	
	/* The set/toggle/get methods */
	public void setParticlesPerUpdate(int particlesPerUpdate) { this.particlesPerUpdate = particlesPerUpdate; }
	public void setParticleInitialVelocity(Vector3D particleInitialVelocity) { this.particleInitialVelocity = particleInitialVelocity; }
	public void setParticleLifeTime(long particleLifeTime) { this.particleLifeTime = particleLifeTime; }
	public void setParticleColour(Colour particleColour) { this.particleColour = particleColour; }
	public void setParticleEffect(ParticleEffect particleEffect) { this.particleEffect = particleEffect; }
	public void setUniformity(float uniformity) { this.uniformity = uniformity; }
	public void setEmitting(boolean emitting) { this.emitting = emitting; }
	public void setIgnoreZ(boolean ignoreZ) { this.ignoreZ = ignoreZ; }
	public void toggleEmitting() { this.emitting = ! this.emitting; }
	public void toggleIgnoreZ() { this.ignoreZ = ! this.ignoreZ; }
	public int getParticlesPerUpdate() { return this.particlesPerUpdate;}
	public Vector3D getParticlesInitialVelocity() { return this.particleInitialVelocity; }
	public long getParticleLifeTime() { return this.particleLifeTime; }
	public Colour getParticleColour() { return this.particleColour; }
	public ParticleEffect getParticleEffect() { return this.particleEffect; }
	public float getUniformity() { return this.uniformity; }
	public boolean isEmitting() { return this.emitting; }
	public boolean doesIgnoreZ() { return this.ignoreZ; }
	
}