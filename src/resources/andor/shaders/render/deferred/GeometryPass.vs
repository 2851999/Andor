/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_modelMatrix;
uniform mat4 andor_normalMatrix;
uniform sampler2D andor_texture;

attribute vec4 andor_vertex;
attribute vec3 andor_normal;
attribute vec4 andor_colour;
attribute vec2 andor_textureCoord;
attribute float andor_hasTexture;

varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;
varying vec3 andor_fnormal;
varying vec4 andor_fvertex;

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

void andor_main();

void main() {
	andor_ftextureCoord = andor_textureCoord;
	andor_fcolour = andor_colour;
	andor_fnormal = andor_normal;
	andor_fvertex = andor_modelViewProjectionMatrix * andor_vertex;
	gl_Position = andor_fvertex;
	andor_main();
}