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

uniform DirectionalLight directionalLight;

//Spec intensity = 2
//Spec exponent = 32
uniform float specularIntensity;
uniform float specularPower;
uniform vec3 eyePos;

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
		
		if (specularFactor > 0) {
			specularColour = vec4(base.colour, 1.0) * specularIntensity * specularFactor;
		}
	}
	return diffuseColour + specularColour;
}

vec4 calculateDirectionalLight(DirectionalLight directionalLight, vec3 normal) {
	return calculateLight(directionalLight.base, -directionalLight.direction, normal);
}

void andor_main();

void main() {
	vec4 totalLight = ambientLight;
	vec4 colour = andor_fcolour;
	vec4 textureColour = texture2D(andor_texture, andor_ftextureCoord);
	
	if (textureColour != vec4(0.0, 0.0, 0.0, 1.0))
		colour *= textureColour;
	
	vec3 normal = normalize(andor_fnormal);
	totalLight += calculateDirectionalLight(directionalLight, normal);
	
	gl_FragColor = vec4(vec3(colour * totalLight), 1.0);
	andor_main();
}