#include "ForwardLightingData.fs"
#include "Sampling.fs"

/* The main method */
void main() {
	gl_FragColor = andor_calculateColour(calculateTextureCoordinate()) * andor_ambientLight;
}