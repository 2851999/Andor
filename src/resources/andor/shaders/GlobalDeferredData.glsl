/* DEFINE ALL OF THE DATA THAT IS NEEDED IN BOTH THE VERTEX AND FRAGMENT SHADERS FOR DEFERRED RENDERING HERE */

/*----------------------- UNIFORMS -----------------------*/

/* The textures */
uniform sampler2D andor_positionTexture;
uniform sampler2D andor_diffuseTexture;
uniform sampler2D andor_normalTexture;
uniform sampler2D andor_depthTexture;