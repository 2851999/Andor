/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Shader;
import org.andor.utils.GLUtils;
import org.andor.utils.RenderUtils;
import org.andor.utils.shader.ShaderCode;
import org.lwjgl.opengl.GL11;

public abstract class RenderPass {
	
	/* The name of this render pass */
	public String name;
	
	/* The default shader */
	public Shader defaultShader;
	
	/* The custom shader for this render pass */
	public Shader customShader;
	
	/* The current texture number */
	public int currentTexture;
	
	/* The default shader code */
	public ShaderCode shaderCode;
	
	/* The current shader being used */
	public Shader currentShader;
	
	/* The enabled vertex arrays */
	public List<Integer> enabledArrays;
	
	/* The constructor */
	public RenderPass(String name, ShaderCode shaderCode) {
		//Assign the values
		this.name = name;
		this.shaderCode = shaderCode;
		this.enabledArrays = new ArrayList<Integer>();
		//Load the shader code
		this.shaderCode.load();
		//Create the default shader
		this.defaultShader = this.shaderCode.createDefault();
	}
	
	/* The method used to render this */
	public void render(Renderer renderer) {
		//Reset the current texture
		this.currentTexture = 0;
		//Clear the arrays
		this.enabledArrays.clear();
		//Do the render pass
		this.renderPass(renderer);
	}
	
	/* The method used to do this render pass */
	public abstract void renderPass(Renderer renderer);
	
	/* The method used to bind a texture */
	public int bindTexture(int texture) {
		//Set the active texture
		GLUtils.activeTexture(currentTexture);
		//Bind the texture
		GLUtils.bindTexture(GL11.GL_TEXTURE_2D, texture);
		//Increment the current texture
		this.currentTexture++;
		//Return the current texture
		return this.currentTexture - 1;
	}
	
	/* The method used to unbind the textures */
	public void unbindTextures() {
		//Go through the textures
		while (this.currentTexture >= 0) {
			//Assign the active texture
			GLUtils.activeTexture(this.currentTexture);
			//Unbind the image
			GLUtils.bindTexture(GL11.GL_TEXTURE_2D, 0);
			//Decrement the current texture
			this.currentTexture--;
		}
	}
	
	/* The method used to set and use the current shader */
	public void useShader() {
		//Set the shader
		this.setShader();
		//Use the shader
		this.currentShader.use();
	}
	
	/* The method used to assign the current shader */
	public void setShader() {
		//Check to see whether the custom shader has been assigned
		if (this.customShader != null)
			//Assign the current shader
			this.currentShader = this.customShader;
		else
			//Assign the current shader
			this.currentShader = this.defaultShader;
	}
	
	/* The method used to prepare a vertex array */
	public void prepareVertexArray(int index, int handle, int count) {
		//Add the index to the list of enabled arrays
		this.enabledArrays.add(index);
		//Prepare the array
		RenderUtils.prepareVertexArray(index, handle, count);
	}
	
	/* The method used to disable any arrays */
	public void disableArrays() {
		//Go through the arrays
		for (int a = 0; a < this.enabledArrays.size(); a++)
			//Disable the current array
			GLUtils.disableVertexArrtibArray(enabledArrays.get(a));
	}
	
	/* The method used to stop using any shaders */
	public void stopUsingShader() {
		//Stop using any shaders
		GLUtils.useProgram(0);
	}
	
	/* The 'get' and 'set' methods */
	public String getName() { return this.name; }
	
}