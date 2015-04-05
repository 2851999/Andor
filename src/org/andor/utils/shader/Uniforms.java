/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Shader;
import org.andor.core.Vector2D;
import org.andor.core.Vector3D;
import org.andor.core.logger.Logger;

public class Uniforms {
	
	/* The uniforms */
	public List<UniformFloat> floats;
	public List<UniformVector2D> vector2Ds;
	public List<UniformVector3D> vector3Ds;
	
	/* The constructor */
	public Uniforms() {
		//Assign the variables
		this.floats = new ArrayList<UniformFloat>();
		this.vector2Ds = new ArrayList<UniformVector2D>();
		this.vector3Ds = new ArrayList<UniformVector3D>();
	}
	
	
	/* The methods used to add uniforms */
	public void addUniform(UniformFloat u) { this.floats.add(u); }
	public void addUniform(UniformVector2D u) { this.vector2Ds.add(u); }
	public void addUniform(UniformVector3D u) { this.vector3Ds.add(u); }
	
	/* The methods used to return a uniform given its name */
	public UniformFloat getUniformFloat(String name) {
		for (int a = 0; a < this.floats.size(); a++) {
			if (this.floats.get(a).getName().equals(name))
				return this.floats.get(a);
		}
		//Log an error
		Logger.error("Shader getUniformFloat()", "Uniform with the name " + name + " could not be found");
		return null;
	}
	
	public UniformVector2D getUniformVector2D(String name) {
		for (int a = 0; a < this.vector2Ds.size(); a++) {
			if (this.vector2Ds.get(a).getName().equals(name))
				return this.vector2Ds.get(a);
		}
		//Log an error
		Logger.error("Shader getUniformVector2D()", "Uniform with the name " + name + " could not be found");
		return null;
	}
	
	public UniformVector3D getUniformVector3D(String name) {
		for (int a = 0; a < this.vector3Ds.size(); a++) {
			if (this.vector3Ds.get(a).getName().equals(name))
				return this.vector3Ds.get(a);
		}
		//Log an error
		Logger.error("Shader getUniformVector3D()", "Uniform with the name " + name + " could not be found");
		return null;
	}
	
	/* The methods used to assign a uniform */
	public void setUniform(String name, float value) { this.getUniformFloat(name).setValue(value); }
	public void setUniform(String name, Vector2D value) { this.getUniformVector2D(name).setValue(value); }
	public void setUniform(String name, Vector3D value) { this.getUniformVector3D(name).setValue(value); }
	
	/* The method used to assign the uniforms given a shader */
	public void assign(Shader shader) {
		//Go through the uniforms and assign them
		for (int a = 0; a < this.floats.size(); a++)
			shader.setUniformf(this.floats.get(a).getName(), this.floats.get(a).getValue());
		for (int a = 0; a < this.vector2Ds.size(); a++)
			shader.setUniformf(this.vector2Ds.get(a).getName(), this.vector2Ds.get(a).getValue());
		for (int a = 0; a < this.vector3Ds.size(); a++)
			shader.setUniformf(this.vector3Ds.get(a).getName(), this.vector3Ds.get(a).getValue());
	}
	
}