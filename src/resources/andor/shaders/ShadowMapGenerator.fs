#include "ForwardLightingData.fs"
#include "Sampling.fs"

/* The main method */
void main() {
	gl_FragColor = vec4(gl_FragCoord.z);
}