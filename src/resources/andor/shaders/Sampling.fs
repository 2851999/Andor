vec2 calculateTextureCoordinate() {
	vec2 textureCoord;
	if (andor_useDisplacementMap > 0.5) {
		vec3 directionToEye = normalize(andor_eyePosition - frag_andor_worldPosition).xyz;
		textureCoord = frag_andor_textureCoord.xy + (directionToEye * frag_andor_tbnMatrix).xy * (texture2D(andor_material.displacementMap, frag_andor_textureCoord).r * andor_material.displacementMapScale + andor_material.displacementMapBias);
	} else
		textureCoord = frag_andor_textureCoord;
	return textureCoord;
}

float sampleShadowMap(vec2 coords, float compare) {
	return step(compare, texture2D(andor_shadowMap, coords.xy).r);
}

float sampleShadowMapLinear(vec2 coords, float compare, vec2 texelSize) {
	vec2 pixelPosition = coords / texelSize + vec2(0.5);
	vec2 fracPart = fract(pixelPosition);
	vec2 startTexel = (pixelPosition - fracPart) * texelSize;
	
	//Bi-linear
	//0,0 bottom left
	float blTexel = sampleShadowMap(startTexel, compare);
	float brTexel = sampleShadowMap(startTexel + vec2(texelSize.x, 0.0), compare);
	float tlTexel = sampleShadowMap(startTexel + vec2(0.0, texelSize.y), compare);
	float trTexel = sampleShadowMap(startTexel + texelSize, compare);
	
	float mixA = mix(blTexel, tlTexel, fracPart.y);
	float mixB = mix(brTexel, trTexel, fracPart.y);
	
	return mix(mixA, mixB, fracPart.x);
}

float sampleShadowMapPCF(vec2 coords, float compare, vec2 texelSize) {
	//Avoid use as uniform
	const float SAMPLES = 3.0f;
	const float SAMPLES_START = (SAMPLES - 1.0f) / 2.0f;
	const float SAMPLES_SQUARED = SAMPLES * SAMPLES;
	
	float result = 0.0;
	for (float y = -SAMPLES_START; y <= SAMPLES_START; y+= 1.0f) {
		for (float x = -SAMPLES_START; x <= SAMPLES_START; x+= 1.0f) {
			vec2 coordsOffset = vec2(x, y) * texelSize;
			result += sampleShadowMapLinear(coords + coordsOffset, compare, texelSize);
		}
	}
	return result / SAMPLES_SQUARED;
}

float linearStep(float low, float high, float v) {
	return clamp((v - low) / (high - low), 0.0, 1.0);
}

float sampleVarianceShadowMap(vec2 coords, float compare) {
	vec2 moments = texture2D(andor_shadowMap, coords.xy).xy;
	float p = step(compare, moments.x);
	float variance = max(moments.y - moments.x * moments.x, andor_shadowVarianceLightBleedReduction);
	
	float d = compare - moments.x;
	float maxPercentage = linearStep(andor_shadowVarianceMin, 1.0, variance / (variance + d * d));
	
	return min(max(p, maxPercentage), 1.0);
	
	//return step(compare, texture2D(andor_shadowMap, coords.xy).r);
}