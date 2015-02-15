/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Colour;
import org.andor.core.Object3D;
import org.andor.core.Renderer;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.Vector3D;
import org.andor.utils.ShaderUtils;

public class Light3D extends Object3D {
	
	/* The types of light */
	public static final int TYPE_VERTEX_DIRECTIONAL = 1;
	public static final int TYPE_VERTEX_POINT = 2;
	public static final int TYPE_VERTEX_SPOT = 3;
	public static final int TYPE_FRAGMENT_DIRECTIONAL = 4;
	public static final int TYPE_FRAGMENT_POINT = 5;
	public static final int TYPE_FRAGMENT_SPOT = 6;
	
	/* The ambient, diffuse and specular values of this light */
	public Colour ambient;
	public Colour diffuse;
	public Colour specular;
	
	/* The constant, linear and quadratic attenuation values */
	public float constantAttenuation;
	public float linearAttenuation;
	public float quadraticAttenuation;
	
	/* The values used for a spot light*/
	public Vector3D spotDirection;
	public float spotCutOff;
	public float spotExponent;
	
	/* The type of this light */
	public int type;
	
	/* The light number */
	public int number;
	
	/* The boolean that states whether this light is currently active */
	public boolean active;
	
	/* The shader */
	public static Shader shader;
	
	/* The constructor */
	public Light3D(int type, int number) {
		//Reset the values
		this.reset();
		//Assign the variables
		this.type = type;
		this.number = number;
		//Load the shader
		this.loadShader();
	}
	
	/* The constructor with the position given */
	public Light3D(int type, int number, Vector3D position) {
		//Reset the values
		this.reset();
		//Assign the variables
		this.type = type;
		this.number = number;
		this.position = position;
		//Load the shader
		this.loadShader();
	}
	
	/* The constructor with the light values given */
	public Light3D(int type, int number, Colour ambient, Colour diffuse, Colour specular) {
		//Reset the values
		this.reset();
		//Assign the variable
		this.type = type;
		this.number = number;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		//Load the shader
		this.loadShader();
	}
	
	/* The constructor with the light values and position */
	public Light3D(int type, int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular) {
		//Reset the values
		this.reset();
		//Assign the variable
		this.type = type;
		this.number = number;
		this.position = position;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		//Load the shader
		this.loadShader();
	}
	
	/* The method used to reset all of the values */
	public void reset() {
		//Reset the variables to their defaults
		this.ambient = new Colour();
		this.diffuse = new Colour();
		this.specular = new Colour();
		this.spotDirection = new Vector3D();
		this.spotExponent = 1;
		this.spotCutOff = 60;
		this.constantAttenuation = 0.1f;
		this.linearAttenuation = 0.2f;
		this.quadraticAttenuation = 0.3f;
		this.active = false;
	}
	
	/* The method used to load the shader */
	public void loadShader() {
		//Load the shader
		if (shader == null)
			shader = ShaderUtils.createShader(Settings.Resources.Shaders.FORWARD_LIGHT, false);
	}
	
	/* The method used to start using this light */
	public void use() {
		//Assign the active variable
		this.active = true;
		//Get the position
		Vector3D p = this.getPosition();
		//Assign the shader
		Renderer.customShader = shader;
		//Use the current shader
		Renderer.customShader.use();
		//Assign the variables
		Renderer.customShader.setUniformi("andor_lights[" + this.number + "].active", 1);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].position", p.x, p.y, p.z);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].ambient", this.ambient.r, this.ambient.g, this.ambient.b);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].diffuse", this.diffuse.r, this.diffuse.g, this.diffuse.b);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].specular", this.specular.r, this.specular.g, this.specular.b);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].constantAttenuation", this.constantAttenuation);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].linearAttenuation", this.linearAttenuation);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].quadraticAttenuation", this.quadraticAttenuation);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].spotDirection", this.spotDirection.x, this.spotDirection.y, this.spotDirection.z);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].spotCutOff", this.spotCutOff);
		Renderer.customShader.setUniformf("andor_lights[" + this.number + "].spotExponent", this.spotExponent);
		Renderer.customShader.setUniformi("andor_lights[" + this.number + "].type", this.type);
		//Stop using the current shader
		Renderer.customShader.stopUsing();
	}
	
	/* The method used to stop using this light */
	public void stopUsing() {
		//Assign the active variable
		this.active = false;
		//Assign the shader
		Renderer.customShader = shader;
		//Use the current shader
		Renderer.customShader.use();
		//Assign the variables
		Renderer.customShader.setUniformi("andor_light" + this.number + ".active", 0);
		//Stop using the current shader
		Renderer.customShader.stopUsing();
		//Reset the shader
		Renderer.customShader = null;
	}
	
	/* The 'set' and 'get' methods */
	public void setType(int type) { this.type = type; }
	public void setNumber(int number) { this.number = number; }
	public void setAmbient(Colour ambient) { this.ambient = ambient; }
	public void setDiffuse(Colour diffuse) { this.diffuse = diffuse; }
	public void setSpecular(Colour specular) { this.specular = specular; }
	public void setConstantAttenuation(float constantAttenuation) { this.constantAttenuation = constantAttenuation; }
	public void setLinearAttenuation(float linearAttenuation) { this.linearAttenuation = linearAttenuation; }
	public void setQuadraticAttenuation(float quadraticAttenuation) { this.quadraticAttenuation = quadraticAttenuation; }
	public void setActive(boolean active) { this.active = active; }
	public int getType() { return this.type; }
	public int getNumber() { return this.number; }
	public Colour getAmbient() { return this.ambient; }
	public Colour getDiffuse() { return this.diffuse; }
	public Colour getSpecular() { return this.specular; }
	public float getConstantAttenuation() { return this.constantAttenuation; }
	public float getLinearAttenuation() { return this.linearAttenuation; }
	public float getQuadraticAttenuation() { return this.quadraticAttenuation; }
	public boolean isActive() { return this.active; }
	
	
	/* The utility methods used to create light instances */
	public static Light3D create(int type, int number) { return new Light3D(type, number); }
	public static Light3D create(int type, int number, Vector3D position) { return new Light3D(type, number, position); }
	public static Light3D create(int type, int number, Colour ambient, Colour diffuse, Colour specular) { return new Light3D(type, number, ambient, diffuse, specular); }
	public static Light3D create(int type, int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular) { return new Light3D(type, number, position, ambient, diffuse, specular); }
	
	public static Light3D createVertexDirectional(int number) { return create(TYPE_VERTEX_DIRECTIONAL, number); }
	public static Light3D createVertexDirectional(int number, Vector3D position) { return create(TYPE_VERTEX_DIRECTIONAL, number, position); }
	public static Light3D createVertexDirectional(int number, Colour ambient, Colour diffuse, Colour specular) { return create(TYPE_VERTEX_DIRECTIONAL, number, ambient, diffuse, specular); }
	public static Light3D createVertexDirectional(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular) { return create(TYPE_VERTEX_DIRECTIONAL, number, position, ambient, diffuse, specular); }
	
	public static Light3D createVertexPoint(int number) { return create(TYPE_VERTEX_POINT, number); }
	public static Light3D createVertexPoint(int number, Vector3D position) { return create(TYPE_VERTEX_POINT, number, position); }
	public static Light3D createVertexPoint(int number, Colour ambient, Colour diffuse, Colour specular) { return create(TYPE_VERTEX_POINT, number, ambient, diffuse, specular); }
	public static Light3D createVertexPoint(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular) { return create(TYPE_VERTEX_POINT, number, position, ambient, diffuse, specular); }
	public static Light3D createVertexPoint(int number, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_POINT, number);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexPoint(int number, Vector3D position, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_POINT, number, position);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexPoint(int number, Colour ambient, Colour diffuse, Colour specular, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_POINT, number, ambient, diffuse, specular);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexPoint(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_POINT, number, position, ambient, diffuse, specular);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	
	public static Light3D createVertexSpot(int number) { return create(TYPE_VERTEX_SPOT, number); }
	public static Light3D createVertexSpot(int number, Vector3D position) { return create(TYPE_VERTEX_SPOT, number, position); }
	public static Light3D createVertexSpot(int number, Colour ambient, Colour diffuse, Colour specular) { return create(TYPE_VERTEX_SPOT, number, ambient, diffuse, specular); }
	public static Light3D createVertexSpot(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular) { return create(TYPE_VERTEX_SPOT, number, position, ambient, diffuse, specular); }
	public static Light3D createVertexSpot(int number, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Vector3D position, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, position);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Colour ambient, Colour diffuse, Colour specular, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, ambient, diffuse, specular);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular, float constantAttenuation, float linearAttenuation, float quadraticAttenuation) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, position, ambient, diffuse, specular);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Vector3D spotDirection, float spotCutOff) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number);
		instance.spotDirection = spotDirection;
		instance.spotCutOff = spotCutOff;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Vector3D position, float constantAttenuation, float linearAttenuation, float quadraticAttenuation, Vector3D spotDirection, float spotCutOff) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, position);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		instance.spotDirection = spotDirection;
		instance.spotCutOff = spotCutOff;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Colour ambient, Colour diffuse, Colour specular, float constantAttenuation, float linearAttenuation, float quadraticAttenuation, Vector3D spotDirection, float spotCutOff) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, ambient, diffuse, specular);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		instance.spotDirection = spotDirection;
		instance.spotCutOff = spotCutOff;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular, float constantAttenuation, float linearAttenuation, float quadraticAttenuation, Vector3D spotDirection, float spotCutOff) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, position, ambient, diffuse, specular);
		instance.constantAttenuation = constantAttenuation;
		instance.linearAttenuation = linearAttenuation;
		instance.quadraticAttenuation = quadraticAttenuation;
		instance.spotDirection = spotDirection;
		instance.spotCutOff = spotCutOff;
		return instance;
	}
	public static Light3D createVertexSpot(int number, Vector3D position, Colour ambient, Colour diffuse, Colour specular, Vector3D spotDirection, float spotCutOff) {
		Light3D instance = create(TYPE_VERTEX_SPOT, number, position, ambient, diffuse, specular);
		instance.spotDirection = spotDirection;
		instance.spotCutOff = spotCutOff;
		return instance;
	}
	
}