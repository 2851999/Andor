/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

public class Lights {
	
	/* The static method to setup the shaders for each light */
	public static void setupShaders() {
		LitScene.setupShaders();
		DirectionalLight.setupShaders();
		PointLight.setupShaders();
		SpotLight.setupShaders();
	}
	
}