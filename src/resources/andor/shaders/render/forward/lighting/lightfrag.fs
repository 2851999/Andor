#define TYPE_VERTEX_DIRECTIONAL 1
#define TYPE_VERTEX_POINT 2
#define TYPE_VERTEX_SPOT 3
#define TYPE_FRAGMENT_DIRECTIONAL 4
#define TYPE_FRAGMENT_POINT 5
#define TYPE_FRAGMENT_SPOT 6
#define MAX_LIGHTS 6

/* The light structure */
struct AndorLight {
	vec3 position;
	vec3 ambient;
	vec3 diffuse;
	vec3 specular;
	float constantAttenuation;
	float linearAttenuation;
	float quadraticAttenuation;
	vec3 spotDirection;
	float spotCutOff;
	float spotExponent;
	int type;
	bool active;
};

/* The material structure */
struct AndorMaterial {
	vec3 ambient;
	vec3 diffuse;
	vec3 specular;
	float shininess;
};

uniform AndorLight andor_lights[MAX_LIGHTS];

/* The colour from the vertex shader */
varying vec3 andor_lightcolour;

varying vec3 andor_light_normals[MAX_LIGHTS];
varying vec3 andor_light_positions[MAX_LIGHTS];
varying vec4 andor_light_poss[MAX_LIGHTS];
varying vec3 andor_light_halfVectors[MAX_LIGHTS];

vec3 andor_applyLighting(AndorMaterial material);
vec3 andor_computeLight(AndorLight light, AndorMaterial material, int n);

/* The main method */
void andor_main() {
	//Setup the light and material
	AndorMaterial material = AndorMaterial(vec3(0.1, 0.1, 0.1), vec3(0.8, 0.8, 0.8), vec3(1.0, 1.0, 1.0), 1.0);
	vec3 colour = andor_applyLighting(material);
	//Calculate the final colour
	gl_FragColor *= vec4(andor_lightcolour + colour, 1.0);
}

/* The method used to apply lighting */
vec3 andor_applyLighting(AndorMaterial material) {
	vec3 colour = vec3(0, 0, 0);
	//Compute each light
	for (int a = 0; a < MAX_LIGHTS; a++) {
		colour += andor_computeLight(andor_lights[a], material, a);
	}
	return colour;
}

/* The method used to compute a directional light */
vec3 andor_computeLight(AndorLight light, AndorMaterial material, int n) {
	vec3 colour = vec3(0, 0, 0);
	//Make sure the light is active
	if (light.active) {
		//Check the light type
		if (light.type == TYPE_FRAGMENT_DIRECTIONAL) {
			//Transform the normal into eye space
			vec3 normalVector = normalize(andor_light_normals[n]);
			//Calculate the light direction
			vec3 lightDirectionVector = normalize(andor_lights[n].position).xyz;
			
			//Calculate the angle between the normal and the light direction
			float NdotL = max(dot(normalVector, lightDirectionVector), 0.0);
			//Add the ambient colour
			vec3 finalColor = material.ambient * light.ambient;
			
			//Check to see whether the surface normal is facing the light
			if (NdotL > 0.0) {
				//Add the diffuse light
				finalColor += material.diffuse * light.diffuse * NdotL;
				//Calculate the half vector and normalise it
				vec3 HV = normalize(andor_light_halfVectors[n]);
				//Find the angle between the normal and the half vector
				float NdotHV = max(dot(normalVector, HV), 0.0);
				//Add on the specular light
				finalColor += material.specular * light.specular * pow(NdotHV, material.shininess);
			}
			//Set the colour
			colour += finalColor;
		} else if (light.type == TYPE_FRAGMENT_POINT) {
			//Transform the normal into eye space
			vec3 normalVector = normalize(andor_light_normals[n]);
			//Calculate the position
			vec4 pos = andor_light_poss[n];
			
			//Calculate the light position (Subtract model coordinates to make sure it is not in model space)
			vec3 lightPositionVector = andor_light_positions[n];
			//Get the light direction
			vec3 lightDirectionVector = (lightPositionVector - vec3(pos));
			//Calculate the angle between the normal and the light direction
			float NdotL = max(dot(normalVector, lightDirectionVector), 0.0);
			//The distance between the vertex and the light
			float dist = length(lightDirectionVector);
			//Calculate the eye vector
			vec3 eyeVector = -pos.xyz;
			//Add the ambient colour
			vec3 finalColor = material.ambient * light.ambient;
			
			//Check to see whether the surface normal is facing the light
			if (NdotL > 0.0) {
				//Add the diffuse light
				finalColor += material.diffuse * light.diffuse * NdotL;
				//Calculate the half vector and normalise it
				vec3 HV = normalize(andor_light_halfVectors[n]);
				//Find the angle between the normal and the half vector
				float NdotHV = max(dot(normalVector, HV), 0.0);
				//Add on the specular light
				finalColor += material.specular * light.specular * pow(NdotHV, material.shininess);
			}
			
			//Calculate the attenuation factor
			float attenuation = 1.0 / (light.constantAttenuation + light.linearAttenuation * dist + light.quadraticAttenuation * dist * dist);
			//Set the colour
			colour += finalColor;
		} else if (light.type == TYPE_FRAGMENT_SPOT) {
			//Transform the normal into eye space
			vec3 normalVector = normalize(andor_light_normals[n]);
			//Calculate the position
			vec4 pos = andor_light_poss[n];
			
			//Calculate the light position (Subtract model coordinates to make sure it is not in model space)
			vec3 lightPositionVector = andor_light_positions[n];
			//Get the light direction
			vec3 lightDirectionVector = (lightPositionVector - vec3(pos));
			//Calculate the angle between the normal and the light direction
			float NdotL = max(dot(normalVector, lightDirectionVector), 0.0);
			//The distance between the vertex and the light
			float dist = length(lightDirectionVector);
			//Calculate the eye vector
			vec3 eyeVector = -pos.xyz;
			//Add the ambient colour
			vec3 finalColor = material.ambient * light.ambient;
			
			float attenuation = 1.0;
			
			//Check to see whether the surface normal is facing the light
			if (NdotL > 0.0) {
				//Find the angle between the light direction and the spotlight direction
				float spotEffect = dot(normalize(light.spotDirection), normalize(-lightDirectionVector));
				//Make sure it should be illuminated
				if (spotEffect > cos(light.spotCutOff)) {
					//Add the diffuse light
					finalColor += material.diffuse * light.diffuse * NdotL;
					//Calculate the half vector and normalise it
					vec3 HV = normalize(andor_light_halfVectors[n]);
					//Find the angle between the normal and the half vector
					float NdotHV = max(dot(normalVector, HV), 0.0);
					//Calculate the attenuation factor
					spotEffect = pow(spotEffect, light.spotExponent);
					attenuation = spotEffect / (light.constantAttenuation + light.linearAttenuation * dist + light.quadraticAttenuation * dist * dist);
					//Add on the specular light
					finalColor += material.specular * light.specular * pow(NdotHV, material.shininess);
				}
			}
			//Set the colour
			colour += finalColor * attenuation;
		}
	}
	return colour;
}