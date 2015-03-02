uniform sampler2D andor_texture;
uniform float andor_hasTexture;

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

void andor_main();

void main() {
	gl_FragColor = andor_fcolour;
	if (andor_material.diffuseColour.a > 0.0)
		gl_FragColor *= andor_material.diffuseColour;
	
	if (andor_material.hasDiffuseTexture > 0.5)
		gl_FragColor *= texture2D(andor_material.diffuseTexture, andor_ftextureCoord);
	else if (andor_hasTexture > 0.5)
		gl_FragColor *= texture2D(andor_texture, andor_ftextureCoord);
	andor_main();
}