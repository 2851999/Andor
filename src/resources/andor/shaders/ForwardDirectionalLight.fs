#include "ForwardLightingData.fs"

uniform Andor_DirectionalLight andor_directionalLight;

vec4 andor_calculateLightingEffect() {
	//Apply the lighting
	return andor_calculateDirectionalLight(andor_directionalLight, normalize(frag_andor_normal));
}

#include "ForwardLightingMain.fs"