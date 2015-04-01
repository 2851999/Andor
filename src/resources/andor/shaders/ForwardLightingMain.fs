#include "Sampling.fs"

/* The main method */
void main() {
	vec2 textureCoord = calculateTextureCoordinate();
	vec3 normal;
	if (andor_useNormalMap > 0.5)
		normal = normalize(frag_andor_tbnMatrix * (255.0/128.0 * (2 * texture2D(andor_material.normalMap, textureCoord.xy).xyz - 1))).xyz;
	else
		normal = normalize(frag_andor_normal);
	
	gl_FragColor = andor_calculateColour(textureCoord) * (andor_calculateLightingEffect(normal));
}