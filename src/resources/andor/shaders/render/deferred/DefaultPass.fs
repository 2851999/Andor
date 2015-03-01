/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

/* Define the texture values */
uniform sampler2D andor_texture;
uniform float andor_hasTexture;

/* Define the geometry information to be passed to the fragment shader */
varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;

/* Define the follow up method used by custom shaders */
void andor_main();

void main() {
	gl_FragColor = andor_fcolour;
	if (andor_hasTexture > 0.5)
		gl_FragColor *= texture2D(andor_texture, andor_ftextureCoord);
	//Call the follow up method
	andor_main();
}