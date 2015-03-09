/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

/* UNIFORMS - Given by renderer, available in both vertex and fragment shaders */

/* Define the needed matrices */
uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_modelMatrix;
uniform mat4 andor_normalMatrix;

/* Define the texture values */
uniform sampler2D andor_texture;
uniform float andor_hasTexture;

/* ATTRIBUTES - Given by renderer, only available in vertex shader (Here) */

/* Define the geometry information */
attribute vec4 andor_vertex;
attribute vec3 andor_normal;
attribute vec4 andor_colour;
attribute vec2 andor_textureCoord;

/* VARYINGS - Assigned in vertex shader, passed to fragment shader */

/* Define the geometry information to be passed to the fragment shader */
varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;

struct Andor_Material {
	vec4 ambientColour;
	vec4 diffuseColour;
	vec4 specularColour;
	sampler2D ambientTexture;
	float hasAmbientTexture;
	sampler2D diffuseTexture;
	float hasDiffuseTexture;
	sampler2D specularTexture;
	float hasSpecularTexture;
	float shininess;
};

uniform Andor_Material andor_material;

/* Define the follow up method used by custom shaders */
void andor_main();

/* The main method */
void main() {
	andor_ftextureCoord = andor_textureCoord;
	andor_fcolour = andor_colour;
	gl_Position = andor_modelViewProjectionMatrix * andor_vertex;
	//Call the follow up method
	andor_main();
}