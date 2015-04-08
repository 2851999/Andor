/* DEFINE ALL OF THE DATA THAT IS NEEDED IN BOTH THE VERTEX AND FRAGMENT SHADERS HERE */

/*---------------------- STRUCTURES ----------------------*/

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
	sampler2D normalMap;
	sampler2D displacementMap;
	
	float displacementMapScale;
	float displacementMapBias;
	
	/* The shininess of this material */
	float shininess;
};

/*----------------------- UNIFORMS -----------------------*/

/* The needed matrices */
uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_modelMatrix;
uniform mat4 andor_normalMatrix;
uniform mat4 andor_viewMatrix;
uniform mat4 andor_lightMatrix;

/* The current material */
uniform Andor_Material andor_material;
uniform float andor_useNormalMap;
uniform float andor_useDisplacementMap;

/*----------------------- VARYINGS -----------------------*/

/* The data that is passed from the vertex to the fragment
   shaders */
varying vec3 frag_andor_vertex;
varying vec4 frag_andor_colour;
varying vec3 frag_andor_normal;
varying vec2 frag_andor_textureCoord;
varying vec3 frag_andor_tangent;
varying vec3 frag_andor_worldPosition;
varying mat3 frag_andor_tbnMatrix;

/*----------------------- METHODS -----------------------*/

/* The andor main method */
void andor_main();