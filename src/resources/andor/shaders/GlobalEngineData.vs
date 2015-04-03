/* DEFINE ALL OF THE DATA THAT IS NEEDED IN THE VERTEX SHADERS HERE */

/*---------------------- ATTRIBUTES ----------------------*/

/* The data from the renderer */
attribute vec3 andor_vertex;
attribute vec3 andor_normal;
attribute vec4 andor_colour;
attribute vec2 andor_textureCoord;
attribute vec3 andor_tangent;

#include "GlobalEngineData.glsl"

/*---------------------- METHODS ----------------------*/

/* The general method used to setup the data for
   rendering in the fragment shader */
void andor_general_setup() {
	//Assign the values to go to the fragment shader
	frag_andor_vertex = vec3(andor_modelViewProjectionMatrix * vec4(andor_vertex, 1.0));
	frag_andor_textureCoord = andor_textureCoord;
	frag_andor_colour = andor_colour;
	frag_andor_normal = normalize(vec4(andor_normal, 0.0) * andor_modelMatrix).xyz;
	frag_andor_tangent = normalize(vec4(andor_tangent, 0.0) * andor_modelMatrix).xyz;
	
	frag_andor_tangent = normalize(frag_andor_tangent - dot(frag_andor_tangent, frag_andor_normal) * frag_andor_normal);
	
	vec3 biTangent = cross(frag_andor_tangent, frag_andor_normal);
	frag_andor_tbnMatrix = mat3(frag_andor_tangent, biTangent, frag_andor_normal);
	
	frag_andor_worldPosition = (vec4(andor_vertex, 1.0) * andor_modelMatrix).xyz;
	//Assign the position
	gl_Position = andor_modelViewProjectionMatrix * vec4(andor_vertex, 1.0);
	//Call the andor_main method
	andor_main();
}