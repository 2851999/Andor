uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_modelMatrix;
uniform mat4 andor_normalMatrix;
uniform sampler2D andor_texture;
uniform float andor_hasTexture;

attribute vec3 andor_vertex;
attribute vec3 andor_normal;
attribute vec4 andor_colour;
attribute vec2 andor_textureCoord;

varying vec4 andor_fcolour;
varying vec3 andor_fnormal;
varying vec2 andor_ftextureCoord;
varying vec3 worldPosition;

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
	andor_fnormal = (vec4(andor_normal, 0.0) * andor_normalMatrix).xyz;
	gl_Position = andor_modelViewProjectionMatrix * vec4(andor_vertex, 1.0);
	worldPosition = (vec4(andor_vertex, 1.0) * andor_normalMatrix).xyz;
	andor_main();
}