#define TYPE_VERTEX_DIRECTIONAL 1
#define TYPE_VERTEX_POINT 2
#define TYPE_VERTEX_SPOT 3
#define MAX_LIGHTS 16

/* The colour from the vertex shader */
varying vec3 andor_lightcolour;

/* The main method */
void andor_main() {
	//Calculate the final colour
	gl_FragColor *= vec4(andor_lightcolour, 1.0);
}