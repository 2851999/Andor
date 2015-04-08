#include "ForwardLightingData.glsl"
#include "GlobalEngineData.vs"

/* The main method */
void main() {
	andor_general_setup();
	//Might need to be other way round
	frag_andor_shadowMapCoords = (andor_lightMatrix * vec4(andor_vertex, 1.0));
}