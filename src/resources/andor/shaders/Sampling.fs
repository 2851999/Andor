vec2 calculateTextureCoordinate() {
	vec2 textureCoord;
	if (andor_useDisplacementMap > 0.5) {
		vec3 directionToEye = normalize(andor_eyePosition - frag_andor_worldPosition).xyz;
		textureCoord = frag_andor_textureCoord.xy + (directionToEye * frag_andor_tbnMatrix).xy * (texture2D(andor_material.displacementMap, frag_andor_textureCoord).r * andor_material.displacementMapScale + andor_material.displacementMapBias);
	} else
		textureCoord = frag_andor_textureCoord;
	return textureCoord;
}