/* DEFINE ALL OF THE DATA THAT IS NEEDED IN BOTH THE VERTEX AND FRAGMENT SHADERS FOR DEFERRED RENDERING HERE */

#include "GlobalDeferredData.glsl"
#include "GlobalEngineData.vs"

/*---------------------- METHODS ----------------------*/

/* The general method used to setup the data for
   rendering in the fragment shader when using
    deferred rendering */
void andor_deferred_setup() {
	//TODO: CHANGE BELOW TO USE TEXTURES FOR DEFERRED SHADERS
	//Assign the values to go to the fragment shader
	frag_andor_vertex = vec3(andor_modelViewProjectionMatrix * vec4(andor_vertex, 1.0));
	frag_andor_textureCoord = andor_textureCoord;
	frag_andor_colour = andor_colour;
	frag_andor_normal = (vec4(andor_normal, 0.0) * andor_normalMatrix).xyz;
	frag_andor_worldPosition = (vec4(andor_vertex, 1.0) * andor_normalMatrix).xyz;
	//Assign the position
	gl_Position = andor_modelViewProjectionMatrix * vec4(andor_vertex, 1.0);
	//Call the andor_main method
	andor_main();
}