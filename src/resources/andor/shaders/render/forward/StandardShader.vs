/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 ********************************************* */

/* Define all of the matrices needed in this shader */
uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_normalMatrix;

/* The values from the renderer */
attribute vec3 andor_vertex;
attribute vec3 andor_normal;
attribute vec4 andor_colour;
attribute vec2 andor_textureCoord;

/* The data that is supposed to go to the fragment shader */
varying vec4 frag_andor_colour;
varying vec3 frag_andor_normal;
varying vec2 frag_andor_textureCoord;
varying vec3 frag_andor_worldPosition;

/* The material structure */
struct Andor_Material {
	/* The various colours */
	vec4 ambientColour;
	vec4 diffuseColour;
	vec4 specularColour;

	/* The various textures */
	sampler2D ambientTexture;
	sampler2D diffuseTexture;
	sampler2D specularTexture;
	
	/* The shininess of this material */
	float shininess;
};

/* The current material */
uniform Andor_Material andor_material;

/* The andor main method */
void andor_main();

/* The main method */
void main() {
	//Assign the values to go to the fragment shader
	frag_andor_textureCoord = andor_textureCoord;
	frag_andor_colour = andor_colour;
	frag_andor_normal = (vec4(andor_normal, 0.0) * andor_normalMatrix).xyz;
	frag_andor_worldPosition = (vec4(andor_vertex, 1.0) * andor_normalMatrix).xyz;
	//Assign the position
	gl_Position = andor_modelViewProjectionMatrix * vec4(andor_vertex, 1.0);
	//Call the andor_main method
	andor_main();
}