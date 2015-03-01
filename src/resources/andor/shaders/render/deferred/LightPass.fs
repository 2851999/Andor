/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

uniform sampler2D andor_positionTexture;
uniform sampler2D andor_diffuseTexture;
uniform sampler2D andor_normalTexture;
uniform sampler2D andor_depthTexture;
uniform sampler2D andor_texture;
uniform float andor_hasTexture;

varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;

void andor_main();

void main() {
	gl_FragColor = texture2D(andor_diffuseTexture, andor_ftextureCoord);
	//if (andor_hasTexture > 0.5)
		//gl_FragColor *= texture2D(andor_texture, andor_ftextureCoord);
	andor_main();
}