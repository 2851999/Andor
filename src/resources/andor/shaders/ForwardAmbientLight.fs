#include "ForwardLightingData.fs"

/* The main method */
void main() {
	gl_FragColor = andor_calculateColour() * andor_ambientLight;
}