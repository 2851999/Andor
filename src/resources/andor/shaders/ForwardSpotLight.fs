#include "ForwardLightingData.fs"

uniform Andor_SpotLight andor_spotLight;

vec4 andor_calculateLightingEffect(vec3 normal) {
	//Apply the lighting
	return andor_calculateSpotLight(andor_spotLight, normal);
}

#include "ForwardLightingMain.fs"