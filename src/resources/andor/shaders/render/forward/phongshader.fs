uniform sampler2D andor_texture;
uniform float andor_hasTexture;
uniform mat4 andor_viewMatrix;
uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_modelMatrix;
uniform mat4 andor_normalMatrix;

varying vec4 andor_fcolour;
varying vec2 andor_ftextureCoord;
varying vec3 andor_fnormal;
varying vec3 worldPosition;

struct Andor_Material {
	vec4 ambientColour;
	vec4 diffuseColour;
	vec4 specularColour;
	sampler2D ambientTexture;
	float hasAmbientTexture;
	sampler2D diffuseTexture;
	float hasDiffuseTexture;
	sampler2D specularTexture;
	float hasSpecularTexture;
	float shininess;
};

uniform Andor_Material andor_material;
uniform vec4 ambientLight;

struct BaseLight {
	vec3 colour;
	float intensity;
};

struct DirectionalLight {
	BaseLight base;
	vec3 direction;
};

struct Attenuation {
	//Physically accurate = 0, 0, 1
	float constant;
	float linear;
	float exponent;
};

struct PointLight {
	BaseLight base;
	Attenuation attenuation;
	vec3 position;
	float range; //Maximum distance (Stop it effecting whole scene)
};

struct SpotLight {
	PointLight pointLight;
	vec3 direction;
	float cutoff;
};

uniform DirectionalLight directionalLight;
uniform PointLight pointLight;
uniform SpotLight spotLight;

//Spec intensity = 2
//Spec exponent = 32
uniform float specularIntensity;
uniform float specularPower;
uniform vec3 eyePos;

uniform float lighting;

vec4 calculateLight(BaseLight base, vec3 direction, vec3 normal) {
	//Attenuation
	//- because it is the reflected light
	float diffuseFactor = dot(normal, -direction);
	vec4 diffuseColour = vec4(0.0, 0.0, 0.0, 0.0);
	vec4 specularColour = vec4(0.0, 0.0, 0.0, 0.0);
	if (diffuseFactor > 0.0) {
		//BRDF * intensity * attenuation
		diffuseColour = vec4(base.colour, 1.0) * base.intensity * diffuseFactor;
		
		vec3 directionToEye = normalize(worldPosition - eyePos);
		vec3 reflectDirection = normalize(reflect(direction, normal));
		
		float specularFactor = dot(directionToEye, reflectDirection); //1 if same, less if they diverge (cosine)
		specularFactor = pow(specularFactor, specularPower);
		
		if (specularFactor > 0.0) {
			specularColour = vec4(base.colour, 1.0) * specularIntensity * specularFactor;
		}
	}
	return diffuseColour + specularColour;
}

vec4 calculateDirectionalLight(DirectionalLight directionalLight, vec3 normal) {
	return calculateLight(directionalLight.base, -directionalLight.direction, normal);
}

vec4 calculatePointLight(PointLight pointLight, vec3 normal) {
	//May need to flip this around
	vec3 lightDirection = worldPosition - pointLight.position;
	float distanceToLight = length(lightDirection);
	
	if (distanceToLight > pointLight.range)
		return vec4(0.0, 0.0, 0.0, 0.0);
	
	lightDirection = normalize(lightDirection);
	
	vec4 colour = calculateLight(pointLight.base, lightDirection, normal);
	
	//Gets bigger as the distance gets larger so divide the final colour
	float attenuation = pointLight.attenuation.constant +
						pointLight.attenuation.linear * distanceToLight +
						pointLight.attenuation.exponent * distanceToLight * distanceToLight + 0.00001;
						//Make sure /0 never occurs, as glsl sometimes will execute both code paths of an if statement at the same time
						
	return colour / attenuation;
}

vec4 calculateSpotLight(SpotLight spotLight, vec3 normal) {
	vec3 lightDirection = normalize(spotLight.pointLight.position - worldPosition);
	float spotFactor = dot(lightDirection, spotLight.direction);
	
	vec4 colour = vec4(0.0, 0.0, 0.0, 0.0);
	
	if (spotFactor > spotLight.cutoff) {
		colour = calculatePointLight(spotLight.pointLight, normal) *
				(1.0 - (1.0 - spotFactor) / (1.0 - spotLight.cutoff)); //Give it a softer edge
	}
	
	return colour;
}

void andor_main();

void main() {
	vec4 totalLight = ambientLight;
	vec4 colour = andor_fcolour;
	vec4 textureColour = texture2D(andor_texture, andor_ftextureCoord);
	
	if (textureColour != vec4(0.0, 0.0, 0.0, 1.0))
		colour *= textureColour;
	if (andor_material.diffuseColour.a > 0.0)
		colour *= andor_material.diffuseColour;
	
	vec3 normal = normalize(andor_fnormal);
	if (directionalLight.base.intensity > 0.0)
		totalLight += calculateDirectionalLight(directionalLight, normal);
	if (pointLight.base.intensity > 0.0)
		totalLight += calculatePointLight(pointLight, normal);
	if (spotLight.pointLight.base.intensity > 0.0)
		totalLight += calculateSpotLight(spotLight, normal);
	
	gl_FragColor = colour;
	if (lighting > 0.5)
		gl_FragColor *= vec4(vec3(totalLight), 1.0);
	andor_main();
}