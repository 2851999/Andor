/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.utils.ShaderCode;

public class GeometryPass extends RenderPass {
	
	/* The name of this pass */
	public static final String PASS_NAME = "Geometry";
	
	/* The constructor */
	public GeometryPass() {
		//Call the super constructor
		super(PASS_NAME, new ShaderCode(Settings.Resources.Shaders.DEFERRED_GEOMETRY_PASS));
	}
	
	/* The method called to do this render pass */
	public void renderPass(Renderer renderer) {
		//Set the shader
		this.setShader();
		//Get the forward pass
		RenderPass forward = RenderPasses.getPass(ForwardPass.PASS_NAME);
		//Get the current custom shader
		Shader previous = forward.customShader;
		//Assign the shader
		forward.customShader = this.currentShader;
		//Render
		forward.render(renderer);
		//Reset the shader
		forward.customShader = previous;
	}
	
}