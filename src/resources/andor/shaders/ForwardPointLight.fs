#include "ForwardLightingData.fs"

uniform Andor_PointLight andor_pointLight;

vec4 andor_calculateLightingEffect(vec3 normal) {
	//Apply the lighting
	return andor_calculatePointLight(andor_pointLight, normal);
}

#include "ForwardLightingMain.fs"