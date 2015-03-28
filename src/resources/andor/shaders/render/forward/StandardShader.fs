/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 ********************************************* */

/* Define all of the matrices needed in this shader */
uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_normalMatrix;

/* The data that is supposed to go to the fragment shader */
varying vec4 frag_andor_colour;
varying vec3 frag_andor_normal;
varying vec2 frag_andor_textureCoord;
varying vec3 frag_andor_worldPosition;

/* The material structure */
struct Andor_Material {
	/* The various colours */
	vec4 ambientColour;
	vec4 diffuseColour;
	vec4 specularColour;

	/* The various textures */
	sampler2D ambientTexture;
	sampler2D diffuseTexture;
	sampler2D specularTexture;
	
	/* The shininess of this material */
	float shininess;
};

/* The current material */
uniform Andor_Material andor_material;

/************************* LIGHTING STRUCTURES *************************/
struct Andor_BaseLight {
	vec3 colour;
	float intensity;
};

struct Andor_Attenuation {
	float constant;
	float linear;
	float exponent;
};

struct Andor_DirectionalLight {
	Andor_BaseLight base;
	vec3 direction;
};

struct Andor_PointLight {
	Andor_BaseLight base;
	Andor_Attenuation attenuation;
	vec3 position;
	float range;
};

struct Andor_SpotLight {
	Andor_PointLight pointLight;
	vec3 direction;
	float cutoff;
};

/************************* LIGHTING VALUES *************************/
uniform vec4 andor_ambientLight;
uniform float andor_specularIntensity;
uniform float andor_specularPower;
uniform vec3 andor_eyePosition;
uniform Andor_DirectionalLight andor_directionalLight;
uniform Andor_PointLight andor_pointLight;
uniform Andor_SpotLight andor_spotLight;
uniform float andor_lighting;

/* The method used to calculate the effect of a base light */
vec4 andor_calculateLight(Andor_BaseLight base, vec3 direction, vec3 normal) {
	//Calculate the diffuse factor, then assign the colours
	float diffuseFactor = dot(normal, -direction);
	vec4 diffuseColour = vec4(0.0, 0.0, 0.0, 0.0);
	vec4 specularColour = vec4(0.0, 0.0, 0.0, 0.0);
	//Check to see whether the light takes any effect
	if (diffuseFactor > 0.0) {
		//Calculate the diffuse colour (BRDF * intensity * attenuation)
		diffuseColour = vec4(base.colour, 1.0) * base.intensity * diffuseFactor;
		//Calculate the direction from the current pixel to the eye, and then find the direction
		//the light should reflect
		vec3 directionToEye = normalize(frag_andor_worldPosition - andor_eyePosition);
		vec3 reflectDirection = normalize(reflect(direction, normal));
		//Calculate the specular factor
		float specularFactor = dot(directionToEye, reflectDirection);
		specularFactor = pow(specularFactor, andor_specularPower);
		//Check to see whether the specular colour takes any effect
		if (specularFactor > 0.0) {
			//Apply the colour
			specularColour = vec4(base.colour, 1.0) * andor_specularIntensity * specularFactor;
		}
		//Return the colour
		return diffuseColour + specularColour;
	}
}

/* The method used to calculate a directional light */
vec4 andor_calculateDirectionalLight(Andor_DirectionalLight directionalLight, vec3 normal) {
	return andor_calculateLight(directionalLight.base, -directionalLight.direction, normal);
}

/* The method used to calculate a point light */
vec4 andor_calculatePointLight(Andor_PointLight pointLight, vec3 normal) {
	//Calculate the light direction and then find the distance to the light
	vec3 lightDirection = frag_andor_worldPosition - pointLight.position;
	float distanceToLight = length(lightDirection);
	
	//Check to see whether the point is within range
	if (distanceToLight > pointLight.range)
		return vec4(0.0, 0.0, 0.0, 0.0);
	
	//Normalize the direction
	lightDirection = normalize(lightDirection);
	
	//Calculate the normal light colour
	vec4 colour = andor_calculateLight(pointLight.base, lightDirection, normal);
	
	//Gets bigger as the distance gets larger so divide the final colour
	float attenuation = pointLight.attenuation.constant +
						pointLight.attenuation.linear * distanceToLight +
						pointLight.attenuation.exponent * distanceToLight * distanceToLight + 0.00001;
						//Make sure /0 never occurs, as glsl sometimes will execute both code paths of an if statement at the same time
	//Return the final colour
	return colour / attenuation;
}

/* The method used to calculate a spot light */
vec4 andor_calculateSpotLight(Andor_SpotLight spotLight, vec3 normal) {
	//Calculate the light direction
	vec3 lightDirection = normalize(frag_andor_worldPosition - spotLight.pointLight.position);
	//Calculate the factor
	float spotFactor = dot(lightDirection, spotLight.direction);
	//The final colour
	vec4 colour = vec4(0.0, 0.0, 0.0, 0.0);
	
	//Make sure the the value is within the range of the spot light
	if (spotFactor > spotLight.cutoff) {
		//Calculate the colour
		colour = andor_calculatePointLight(spotLight.pointLight, normal) *
				(1.0 - (1.0 - spotFactor) / (1.0 - spotLight.cutoff)); //Give it a softer edge
	}
	//Return the colour
	return colour;
}

/* The andor main method */
void andor_main();

/* The main method */
void main() {
	vec3 totalLight = andor_ambientLight;
	vec4 colour = frag_andor_colour;
	
	colour *= texture2D(andor_material.diffuseTexture, frag_andor_textureCoord);
	if (andor_material.diffuseColour.a > 0.0)
		colour *= andor_material.diffuseColour;
	
	vec3 normal = normalize(frag_andor_normal);
	if (andor_directionalLight.base.intensity > 0.0)
		totalLight += andor_calculateDirectionalLight(andor_directionalLight, normal);
	if (andor_pointLight.base.intensity > 0.0)
		totalLight += andor_calculatePointLight(andor_pointLight, normal);
	if (andor_spotLight.pointLight.base.intensity > 0.0)
		totalLight += andor_calculateSpotLight(andor_spotLight, normal);
	
	gl_FragColor = colour;
	if (andor_lighting > 0.5)
		gl_FragColor *= vec4(totalLight, 1.0);
	andor_main();
}