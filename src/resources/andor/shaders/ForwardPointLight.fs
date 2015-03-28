#include "ForwardLightingData.fs"

uniform Andor_PointLight andor_pointLight;

vec4 andor_calculateLightingEffect() {
	//Apply the lighting
	return andor_calculatePointLight(andor_pointLight, normalize(frag_andor_normal));
}

#include "ForwardLightingMain.fs"