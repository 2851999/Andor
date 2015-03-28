#include "GlobalEngineData.fs"

/* The main method */
void main() {
	gl_FragData[0] = andor_calculatePosition();
	gl_FragData[1] = andor_calculateColour();
	gl_FragData[2] = andor_calculateNormal();
	andor_main();
}