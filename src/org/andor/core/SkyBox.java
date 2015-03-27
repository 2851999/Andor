/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class SkyBox {
	
	/* The texture coordinates */
	public static final float[] TEXTURE_COORDINATES = {
		//BACK
		3f / 4f, 1f / 3f,
		1, 1f / 3f,
		1, 2f / 3f,
		3f / 4f, 2f / 3f,
		
		//LEFT
		3f / 4f, 2f / 3f,
		2f / 4f, 2f / 3f,
		2f / 4f, 1f / 3f,
		3f / 4f, 1f / 3f,
		
		//FORWARD
		2f / 4f, 1f / 3f,
		1f / 4f, 1f / 3f,
		1f / 4f, 2f / 3f,
		2f / 4f, 2f / 3f,
		
		//BOTTOM
		2f / 4f, 1,
		2f / 4f, 2f / 3f,
		1f / 4f, 2f / 3f,
		1f / 4f, 1,
		
		//RIGHT
		1f / 4f, 2f / 3f,
		0f, 2f / 3f,
		0f, 1f / 3f,
		1f / 4f, 1f / 3f,
		
		//TOP
		2f / 4f, 1f / 3f,
		2f / 4f, 0,
		1f / 4f, 0,
		1f / 4f, 1f / 3f,
	};
	
	/* The image set used for this skybox */
	public ImageSet imageSet;
	
	/* The texture */
	public Image texture;
	
	/* The box */
	public RenderableObject3D box;
	
	/* The constructor */
	public SkyBox(String filePath, String[] fileNames, boolean external, float size) {
		//Create the image set
		this.imageSet = new ImageSet();
		//The images
		Image[] images = new Image[6];
		//Add all of the images
		images[0] = this.imageSet.loadImage(filePath + fileNames[0], external);
		images[1] = this.imageSet.loadImage(filePath + fileNames[1], external);
		images[2] = this.imageSet.loadImage(filePath + fileNames[2], external);
		images[3] = this.imageSet.loadImage(filePath + fileNames[3], external);
		images[4] = this.imageSet.loadImage(filePath + fileNames[4], external);
		images[5] = this.imageSet.loadImage(filePath + fileNames[5], external);
		//Load the texture
		this.texture = this.imageSet.joinImages();
		//Setup the cube
		this.box = Object3DBuilder.createCube(images, size, size, size, Colour.WHITE);
		//Assign the texture
		this.box.setTexture(this.texture);
	}
	
	/* The constructor */
	public SkyBox(Image image, float size) {
		//Setup the cube
		this.box = Object3DBuilder.createCube(image, size, size, size, Colour.WHITE);
		this.texture = image;
		//Assign the texture coordinates
		this.box.renderer.updateTextures(TEXTURE_COORDINATES);
		//Assign the texture
		this.box.setTexture(this.texture);
	}
	
	/* The method used to render this skybox */
	public void renderSkybox() {
		this.texture.bind();
		//Render this
		this.box.render();
		this.texture.unbind();
	}
	
}