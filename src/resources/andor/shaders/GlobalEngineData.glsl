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
	
	/* The shininess of this material */
	float shininess;
};

/*----------------------- UNIFORMS -----------------------*/

/* The needed matrices */
uniform mat4 andor_modelViewProjectionMatrix;
uniform mat4 andor_modelMatrix;
uniform mat4 andor_normalMatrix;

/* The current material */
uniform Andor_Material andor_material;

/*----------------------- VARYINGS -----------------------*/

/* The data that is passed from the vertex to the fragment
   shaders */
varying vec3 frag_andor_vertex;
varying vec4 frag_andor_colour;
varying vec3 frag_andor_normal;
varying vec2 frag_andor_textureCoord;
varying vec3 frag_andor_worldPosition;

/*----------------------- METHODS -----------------------*/

/* The andor main method */
void andor_main();