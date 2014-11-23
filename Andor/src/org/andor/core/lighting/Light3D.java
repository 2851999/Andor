/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Object3D;
import org.andor.core.Renderer;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.Vector3D;
import org.andor.utils.ShaderUtils;

public class Light3D extends Object3D {
	
	/* The types of light */
	public static final int TYPE_DIRECTIONAL = 1;
	
	/* The ambient, diffuse and specular values of this light */
	public Vector3D ambient;
	public Vector3D diffuse;
	public Vector3D specular;
	
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
		//Assign the variables
		this.type = type;
		this.number = number;
		this.ambient = new Vector3D();
		this.diffuse = new Vector3D();
		this.specular = new Vector3D();
		this.active = false;
		//Load the shader
		this.loadShader();
	}
	
	/* The constructor with the position given */
	public Light3D(int type, int number, Vector3D position) {
		//Assign the variables
		this.type = type;
		this.number = number;
		this.position = position;
		this.ambient = new Vector3D();
		this.diffuse = new Vector3D();
		this.specular = new Vector3D();
		this.active = false;
		//Load the shader
		this.loadShader();
	}
	
	/* The constructor with the light values given */
	public Light3D(int type, int number, Vector3D ambient, Vector3D diffuse, Vector3D specular) {
		//Assign the variable
		this.type = type;
		this.number = number;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.active = false;
		//Load the shader
		this.loadShader();
	}
	
	/* The constructor with the light values and position */
	public Light3D(int type, int number, Vector3D position, Vector3D ambient, Vector3D diffuse, Vector3D specular) {
		//Assign the variable
		this.type = type;
		this.number = number;
		this.position = position;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.active = false;
		//Load the shader
		this.loadShader();
	}
	
	/* The method used to load the shader */
	public void loadShader() {
		//Load the shader
		if (shader == null)
			shader = ShaderUtils.createShader(Settings.Resources.SHADER_FORWARD_LIGHT, false);
	}
	
	/* The method used to start using this light */
	public void use() {
		//Assign the active variable
		this.active = true;
		//Get the position
		Vector3D p = this.getPosition();
		//Assign the shader
		Renderer.currentShader = shader;
		//Use the current shader
		Renderer.currentShader.use();
		//Assign the variables
		Renderer.currentShader.setUniformi("andor_lights[" + this.number + "].active", 1);
		Renderer.currentShader.setUniformf("andor_lights[" + this.number + "].position", p.x, p.y, p.z);
		Renderer.currentShader.setUniformf("andor_lights[" + this.number + "].ambient", this.ambient.x, this.ambient.y, this.ambient.z);
		Renderer.currentShader.setUniformf("andor_lights[" + this.number + "].diffuse", this.diffuse.x, this.diffuse.y, this.diffuse.z);
		Renderer.currentShader.setUniformf("andor_lights[" + this.number + "].specular", this.specular.x, this.specular.y, this.specular.z);
		Renderer.currentShader.setUniformi("andor_lights[" + this.number + "].type", this.type);
		//Stop using the current shader
		Renderer.currentShader.stopUsing();
	}
	
	/* The method used to stop using this light */
	public void stopUsing() {
		//Assign the active variable
		this.active = false;
		//Assign the shader
		Renderer.currentShader = shader;
		//Use the current shader
		Renderer.currentShader.use();
		//Assign the variables
		Renderer.currentShader.setUniformi("andor_light" + this.number + ".active", 0);
		//Stop using the current shader
		Renderer.currentShader.stopUsing();
		//Reset the shader
		Renderer.currentShader = null;
	}
	
	/* The 'set' and 'get' methods */
	public void setType(int type) { this.type = type; }
	public void setNumber(int number) { this.number = number; }
	public void setAmbient(Vector3D ambient) { this.ambient = ambient; }
	public void setDiffuse(Vector3D diffuse) { this.diffuse = diffuse; }
	public void setSpecular(Vector3D specular) { this.specular = specular; }
	public void setActive(boolean active) { this.active = active; }
	public int getType() { return this.type; }
	public int getNumber() { return this.number; }
	public Vector3D getAmbient() { return this.ambient; }
	public Vector3D getDiffuse() { return this.diffuse; }
	public Vector3D getSpecular() { return this.specular; }
	public boolean isActive() { return this.active; }
	
}