#include "ForwardLightingData.fs"

uniform Andor_SpotLight andor_spotLight;

vec4 andor_calculateLightingEffect() {
	//Apply the lighting
	return andor_calculateSpotLight(andor_spotLight, normalize(frag_andor_normal));
}

#include "ForwardLightingMain.fs"