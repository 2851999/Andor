/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.Object2DBuilder;
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.RenderableObject3D;
import org.andor.core.Renderer;
import org.andor.core.Settings;
import org.andor.core.deferredrendering.DeferredScene;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.utils.OpenGLUtils;

public class DeferredTest extends BaseGame {
	
	public Camera3D camera;
	public DeferredScene scene;
	public RenderableObject3D cube;
	public Model model;
	
	public DeferredTest() {
		
	}
	
	public void create() {
		scene = new DeferredScene();
		RenderableObject2D quad = Object2DBuilder.createQuad(200, 200, Colour.ARRAY_RGB);
		quad.position.add(100);
		scene.add(quad);
		cube = Object3DBuilder.createCube(0.5f, 0.5f, 0.5f, Colour.ARRAY_SUNSET);
		scene.add(cube);
		camera = new Camera3D();
		camera.position.z = -3.2f;
		
		//Load the model
		this.model = OBJLoader.loadModel("H:/Andor/dragon.obj", true);
		this.model.prepare();
		this.model.position.z = -1;
		this.model.scale.multiply(4f);
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.disableTexture2D();
		
		OpenGLUtils.setupPerspective(70, 1f, 1000f);
		Renderer.geometryBuffer.bindWriting();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		camera.useView();
		cube.rotation.y += 0.1f * this.getDelta();
		scene.render3DObjects();
		model.render();
		
		OpenGLUtils.setupOrtho(-1, 1);
		scene.render2DObjects();
		OpenGLUtils.enableTexture2D();
		this.renderInformation();
		Renderer.geometryBuffer.unbind();
		scene.renderToScreen();
	}
	
	public static void main(String[] args) {
		Settings.Video.DeferredRendering = true;
		new DeferredTest();
	}
	
}