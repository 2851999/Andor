#include "GlobalEngineData.fs"

uniform vec2 blurScale;

/* The main method */
void main() {
	vec4 colour = vec4(0.0);
	
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(-3.0) * blurScale.xy)) * (1.0 / 64.0);
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(-2.0) * blurScale.xy)) * (6.0 / 64.0);
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(-1.0) * blurScale.xy)) * (15.0 / 64.0);
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(0.0) * blurScale.xy)) * (20.0 / 64.0);
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(1.0) * blurScale.xy)) * (15.0 / 64.0);
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(2.0) * blurScale.xy)) * (6.0 / 64.0);
	colour += texture2D(andor_material.diffuseTexture, frag_andor_textureCoord + (vec2(3.0) * blurScale.xy)) * (1.0 / 64.0);
	
	gl_FragColor = colour;
	//gl_FragColor = andor_calculateColour();
}