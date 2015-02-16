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

uniform sampler2D andor_positionTexture;
uniform sampler2D andor_diffuseTexture;
uniform sampler2D andor_normalTexture;
uniform sampler2D andor_depthTexture;
uniform sampler2D andor_texture;
uniform float andor_hasTexture;

attribute vec4 andor_vertex;
attribute vec3 andor_normal;
attribute vec4 andor_colour;
attribute vec2 andor_textureCoord;

varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;

void andor_main();

void main() {
	andor_ftextureCoord = andor_textureCoord;
	andor_fcolour = andor_colour;
	gl_Position = andor_modelViewProjectionMatrix * andor_vertex;
	andor_main();
}