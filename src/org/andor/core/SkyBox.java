/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class SkyBox {
	
	/* The image set used for this skybox */
	public ImageSet imageSet;
	
	/* The texture */
	public Image texture;
	
	/* The box */
	public RenderableObject3D box;
	
	/* The constructor */
	public SkyBox(String filePath, String[] fileNames, boolean external) {
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
		this.box = Object3DBuilder.createCube(images, 100, 100, 100, Colour.WHITE);
	}
	
	/* The method used to render this skybox */
	public void renderSkybox() {
		//Bind the texture
		this.texture.bind();
		//Render this
		this.box.render();
		//Unbind the texture
		this.texture.unbind();
	}
	
}