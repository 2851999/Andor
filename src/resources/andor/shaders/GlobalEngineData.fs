/* DEFINE ALL OF THE DATA THAT IS NEEDED IN THE FRAGMENT SHADERS HERE */

#include "GlobalEngineData.glsl"

/*---------------------- METHODS ----------------------*/

/* The methods used to calculate some data and return it */
vec4 andor_calculatePosition() {
	return vec4(vec3(frag_andor_vertex), 1.0);
}

vec4 andor_calculateColour() {
	//Assign the colour
	vec4 colour = frag_andor_colour;
	//Apply the diffuse texture
	colour *= texture2D(andor_material.diffuseTexture, frag_andor_textureCoord);
	//Check the diffuse colour
	if (andor_material.diffuseColour.a > 0.0)
		//Apply the diffuse colour
		colour *= andor_material.diffuseColour;
	//Return the colour
	return colour;
}

vec4 andor_calculateNormal() {
	return vec4(frag_andor_normal, 1.0);
}