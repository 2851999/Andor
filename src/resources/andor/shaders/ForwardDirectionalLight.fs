#include "ForwardLightingData.fs"

uniform Andor_DirectionalLight andor_directionalLight;

vec4 andor_calculateLightingEffect(vec3 normal) {
	//Apply the lighting
	return andor_calculateDirectionalLight(andor_directionalLight, normal);
}

#include "ForwardLightingMain.fs"