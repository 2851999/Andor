/* DEFINE ALL OF THE DATA THAT IS NEEDED IN THE FRAGMENT SHADERS HERE */

#include "GlobalDeferredData.glsl"
#include "GlobalEngineData.glsl"

/*---------------------- METHODS ----------------------*/

/* The methods used to calculate some data and return it */
vec4 andor_calculatePosition() {
	return vec4(vec3(frag_andor_vertex), 1.0);
}

vec4 andor_calculateColour() {
	//Return the colour
	return texture2D(andor_diffuseTexture, frag_andor_textureCoord);
}

vec4 andor_calculateNormal() {
	return vec4(frag_andor_normal, 1.0);
}