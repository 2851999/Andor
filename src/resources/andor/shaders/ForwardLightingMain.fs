#include "Sampling.fs"

float calculateShadowAmount() {
	vec3 shadowMapCoords = (frag_andor_shadowMapCoords.xyz / frag_andor_shadowMapCoords.w) * vec3(0.5) + vec3(0.5); //OpenGL usually does this between vertex and fragment shaders
	return sampleShadowMapPCF(shadowMapCoords.xy, shadowMapCoords.z - andor_shadowBias, andor_shadowMapTexelSize.xy);
}

/* The main method */
void main() {
	vec2 textureCoord = calculateTextureCoordinate();
	vec3 normal;
	if (andor_useNormalMap > 0.5)
		normal = normalize(frag_andor_tbnMatrix * (255.0/128.0 * (2 * texture2D(andor_material.normalMap, textureCoord.xy).xyz - 1))).xyz;
	else
		normal = normalize(frag_andor_normal);
	
	gl_FragColor = andor_calculateColour(textureCoord) * (andor_calculateLightingEffect(normal)) * calculateShadowAmount();
}