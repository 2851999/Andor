/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

uniform sampler2D andor_texture;
uniform float andor_hasTexture;

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
	gl_FragData[0] = vec4(vec3(andor_fvertex), 1.0);
	gl_FragData[1] = andor_fcolour;
	if (andor_material.diffuseColour.a > 0)
		gl_FragData[1] *= andor_material.diffuseColour;
	if (andor_material.hasDiffuseTexture > 0.5)
		gl_FragData[1] *= texture2D(andor_material.diffuseTexture, andor_ftextureCoord);
	if (andor_hasTexture > 0.5)
		gl_FragData[1] *= texture2D(andor_texture, andor_ftextureCoord);
	gl_FragData[2] = vec4(andor_fnormal, 1.0);
	andor_main();
}