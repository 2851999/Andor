uniform sampler2D andor_texture;
uniform float andor_hasTexture;

varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;

void andor_main();

void main() {
	gl_FragColor = andor_fcolour;
	if (andor_hasTexture > 0.5)
		gl_FragColor *= texture2D(andor_texture, andor_ftextureCoord);
	andor_main();
}