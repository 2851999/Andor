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

import org.andor.utils.ArrayUtils;
import org.lwjgl.opengl.GL11;

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
	
	/* The random generator */
	private Random randomGenerator;
	
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
		this.randomGenerator = new Random();
		this.renderer = new Renderer(GL11.GL_POINTS, Renderer.VERTEX_VALUES_COUNT_3D);
		this.renderer.setValues(new float[] { 0, 0, 0 }, new Colour(0.0f, 0.0f, 0.0f, 0.0f).getValuesRGBA());
		this.renderer.setupBuffers();
	}
	
	/* The method called to update this particle emitter */
	public void update() {
		//Add as many particles as necessary
		for (int a = 0; a < this.particlesPerUpdate; a++)
			this.particles.add(generateParticle());
		//The vertices list
		List<Float> vertices = new ArrayList<Float>();
		//The colours list
		List<Float> colours = new ArrayList<Float>();
		//Go through each particle
		for (int a = 0; a < this.particles.size(); a++) {
			//Make sure the current particle is not dead
			if (! this.particles.get(a).isDead()) {
				//Update the current particle
				this.particles.get(a).update();
				//Get the current vertices and add it to the list
				float[] cpv = this.particles.get(a).getVertices();
				for (int b = 0; b < cpv.length; b++)
					vertices.add(cpv[b]);
				//Get the current colours and add it to the list
				float[] cpc = this.particles.get(a).getColours();
				for (int b = 0; b < cpc.length; b++)
					colours.add(cpc[b]);
			} else
				//Remove the current particle
				this.particles.remove(a);
		}
		//Update this renderer
		this.renderer.updateVertices(ArrayUtils.toFloatArray(vertices));
		this.renderer.updateColours(ArrayUtils.toFloatArray(colours));
	}
	
	/* The method used to generate a particle */
	public Particle generateParticle() {
		//The particle
		Particle particle = null;
		//The particle values
		Vector3D particlePosition = this.position.clone();
		Vector3D particleVelocity = null;
		//The random values
		float randomX = this.randomGenerator.nextFloat() - 0.5f;
		float randomY = this.randomGenerator.nextFloat() - 0.5f;
		float randomZ = this.randomGenerator.nextFloat() - 0.5f;
		particleVelocity = new Vector3D(
				(randomX + this.particleInitialVelocity.x) / uniformity,
				(randomY + this.particleInitialVelocity.y) / uniformity,
				(randomZ + this.particleInitialVelocity.z) / uniformity);
		//Create the particle
		particle = new Particle(particlePosition, particleVelocity, this.particleLifeTime, this.particleColour, this.particleEffect);
		//Return the particle
		return particle;
	}
	
}