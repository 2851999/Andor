/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import org.andor.core.Colour;
import org.andor.core.Settings;
import org.andor.core.model.Material;
import org.andor.core.render.RenderPass;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class RenderUtils {
	
	/* ------------------------------- CAN MOVE INTO RENDERER ------------------------------- */
	
	/* The names of the variables */
	public static final String UNIFORM_MODEL_VIEW_PROJECTION_MATRIX = "andor_modelViewProjectionMatrix";
	public static final String UNIFORM_MODEL_MATRIX = "andor_modelMatrix";
	public static final String UNIFORM_NORMAL_MATRIX = "andor_normalMatrix";
	public static final String UNIFORM_TEXTURE = "andor_texture";
	public static final String UNIFORM_HAS_TEXTURE = "andor_hasTexture";
	public static final String UNIFORM_MATERIAL = "andor_material";
	public static final String ATTRIBUTE_VERTEX = "andor_vertex";
	public static final String ATTRIBUTE_NORMAL = "andor_normal";
	public static final String ATTRIBUTE_COLOUR = "andor_colour";
	public static final String ATTRIBUTE_TEXTURE_COORD = "andor_textureCoord";
	public static final String ATTRIBUTE_TANGENT = "andor_tangent";
	
	/* The names of the variables for materials */
	public class UniformMaterial {
		public static final String AMBIENT_COLOUR = UNIFORM_MATERIAL + ".ambientColour";
		public static final String DIFFUSE_COLOUR = UNIFORM_MATERIAL + ".diffuseColour";
		public static final String SPECULAR_COLOUR = UNIFORM_MATERIAL + ".specularColour";
		public static final String AMBIENT_TEXTURE = UNIFORM_MATERIAL + ".ambientTexture";
		public static final String DIFFUSE_TEXTURE = UNIFORM_MATERIAL + ".diffuseTexture";
		public static final String SPECULAR_TEXTURE = UNIFORM_MATERIAL + ".specularTexture";
		public static final String NORMAL_MAP = UNIFORM_MATERIAL + ".normalMap";
		public static final String DISPLACEMENT_MAP = UNIFORM_MATERIAL + ".displacementMap";
		public static final String DISPLACEMENT_MAP_SCALE = UNIFORM_MATERIAL + ".displacementMapScale";
		public static final String DISPLACEMENT_MAP_BIAS = UNIFORM_MATERIAL + ".displacementMapBias";
		public static final String SHININESS = UNIFORM_MATERIAL + ".shininess";
	}
	
	/* The static method used to prepare a vertex array */
	public static void prepareVertexArray(int index, int handle, int count) {
		//Enable the array
		GLUtils.enableVertexAttribArray(index);
		//Bind the buffer
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, handle);
		//Assign the pointer
		GLUtils.vertexAttribPointer(index, count, GL11.GL_FLOAT, false, 0, 0);
	}
	
	/* The static method used to assign the values in the shader for a material */
	public static void assignMaterial(RenderPass pass, Material material) {
		//The values
		Colour ambientColour = Colour.NONE;
		Colour diffuseColour = Colour.NONE;
		Colour specularColour = Colour.NONE;
		//Check the colours and assign them if necessary
		if (material.ambientColour != null)
			ambientColour = new Colour(material.ambientColour, material.alphaColourValue);
		if (material.diffuseColour != null)
			diffuseColour = new Colour(material.diffuseColour, material.alphaColourValue);
		if (material.specularColour != null)
			specularColour = new Colour(material.specularColour, material.alphaColourValue);
		//Check the textures and assign them if necessary
		if (material.ambientTextureMap != null)
			//Assign the texture
			pass.currentShader.setUniformi(UniformMaterial.AMBIENT_TEXTURE, pass.bindTexture(material.ambientTextureMap.getPointer()));
		if (material.diffuseTextureMap != null)
			//Assign the texture
			pass.currentShader.setUniformi(UniformMaterial.DIFFUSE_TEXTURE, pass.bindTexture(material.diffuseTextureMap.getPointer()));
		else
			//Bind the default texture
			pass.currentShader.setUniformi(UniformMaterial.DIFFUSE_TEXTURE, pass.bindTexture(Settings.Resources.DEFAULT_TEXTURE.getPointer()));
		if (material.specularTextureMap != null)
			//Assign the texture
			pass.currentShader.setUniformi(UniformMaterial.SPECULAR_TEXTURE, pass.bindTexture(material.specularTextureMap.getPointer()));
		if (material.normalTextureMap != null)
			//Assign the texture
			pass.currentShader.setUniformi(UniformMaterial.NORMAL_MAP, pass.bindTexture(material.normalTextureMap.getPointer()));
		if (material.displacementTextureMap != null) {
			//Assign the texture
			pass.currentShader.setUniformi(UniformMaterial.DISPLACEMENT_MAP, pass.bindTexture(material.displacementTextureMap.getPointer()));
			pass.currentShader.setUniformf(UniformMaterial.DISPLACEMENT_MAP_SCALE, material.displacementMapScale);
			pass.currentShader.setUniformf(UniformMaterial.DISPLACEMENT_MAP_BIAS, material.displacementMapBias);
		}
		//Assign the uniforms
		pass.currentShader.setUniformf(UniformMaterial.AMBIENT_COLOUR, ambientColour);
		pass.currentShader.setUniformf(UniformMaterial.DIFFUSE_COLOUR, diffuseColour);
		pass.currentShader.setUniformf(UniformMaterial.SPECULAR_COLOUR, specularColour);
		pass.currentShader.setUniformf(UniformMaterial.SHININESS, material.shininess);
	}

}
