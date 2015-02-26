/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
import org.andor.core.input.ControllerPovEvent;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.InputManager;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;
import org.andor.utils.FPSCalculator;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class GameLoop implements GameLoopInterface, InputListenerInterface {
	
	/* The boolean that states whether a close is requested */
	private boolean closeRequested;
	
	/* The FPS calculator */
	public FPSCalculator fpsCalculator;
	
	/* The other game loop interfaces */
	public static List<GameLoopInterface> interfaces;
	
	/* The default constructor */
	public GameLoop() {
		//Create the FPS calculator
		this.fpsCalculator = new FPSCalculator();
		//Setup the interfaces
		interfaces = new ArrayList<GameLoopInterface>();
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode) {
			//Create the window
			Window.create();
			//Set the icons
			Window.setIcon(new Image[] { ImageLoader.loadImage(Settings.Resources.Icons.ICON_16, false), ImageLoader.loadImage(Settings.Resources.Icons.ICON_32, false) });
			//Load the default font
			Settings.Engine.DefaultFont = FontUtils.loadBitmapFont("/resources/andor/defaultfont.png", 12);
			//Create the input
			InputManager.create();
			//Create the audio
			AudioPC.create();
		}
		//Add this input listener
		Input.addListener(this);
		//Set close requested to false
		this.closeRequested = false;
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Call the create method
			this.create();
		//Start the game loop
		this.startGameLoop();
	}
	
	/* The method used to start the game loop */
	private void startGameLoop() {
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode) {
			//Call the start method
			this.start();
			//Keep going until the window closes
			while (! Window.shouldClose() && ! this.closeRequested)
				//Run a cycle of the loop
				this.tick();
			//Shutdown the game engine
			this.shutdown();
		}
	}
	
	/* The method called to run a 'tick' of this game loop */
	public void tick() {
		//Update the FPS calculator
		this.fpsCalculator.update();
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Check the input
			InputManager.checkInput();
		//Call the update method
		this.update();
		this.interfaceUpdate();
		//Call the render method
		this.render();
		this.interfaceRender();
		//Check to see whether any information should be shown
		if (Settings.Debugging.ShowInformation)
			//Render the information
			this.renderInformation();
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Update the display
			Window.updateDisplay();
	}
	
	/* The method used to render some information */
	public void renderInformation() {
		//Setup
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.enableTexture2D();
		OpenGLUtils.setupOrtho(-1, 1);
		//Render the FPS
		Settings.Engine.DefaultFont.render("DEBUGGING", 10, 10);
		Settings.Engine.DefaultFont.render("Andor Version: " + Settings.Information.VERSION, 10, 24);
		Settings.Engine.DefaultFont.render("Andor Build: " + Settings.Information.BUILD, 10, 38);
		Settings.Engine.DefaultFont.render("Current FPS: " + this.getFPS(), 10, 52);
		Settings.Engine.DefaultFont.render("Window Size: " + Settings.Window.Width + "x" + Settings.Window.Height, 10, 66);
		Settings.Engine.DefaultFont.render("VSync: " + Settings.Video.VSync, 10, 80);
		OpenGLUtils.disableTexture2D();
	}
	
	/* The method used to shutdown the game loop */
	public void shutdown() {
		//Release all of the data
		this.releaseAll();
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode) {
			//Destroy the input
			InputManager.destroy();
			//Destroy the audio
			AudioPC.destroy();
			//Close the window
			Window.close();
		}
		//Call the stop method
		this.stop();
		this.interfaceStop();
		//Call the close method
		this.close();
		this.interfaceClose();
	}
	
	/* The method called to release all of the data */
	public void releaseAll() {
		//Release all of the renderer's
		Renderer.releaseAll();
		//Release all of the images's
		Image.releaseAll();
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode)
			//Release all of the audio
			Audio.releaseAll();
	}
	
	/* The method called to request the program to close  */
	public void requestClose() {
		//Set close requested to true
		this.closeRequested = true;
	}
	
	/* The method called when the loop has been created */
	public void create() {
		
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {
		
	}
	
	/* The method called when the game loop is closing */
	public void close() {
		
	}
	
	/* MOUSE STUFF */
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) { }
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) { }
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) { }
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) { }
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) { }
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) { }
	
	/* KEYBOARD STUFF */
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) { }
	
	/* CONTROLLER STUFF */
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) { }
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) { }
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) { }
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) { }
	
	/* The static method used to add an interface */
	public static void add(GameLoopInterface i) {
		interfaces.add(i);
	}
	
	/* The method used to update all of the game loop interfaces */
	public void interfaceUpdate() {
		for (int a = 0; a < interfaces.size(); a++)
			interfaces.get(a).update();
	}
	
	/* The method used to render all of the game loop interfaces */
	public void interfaceRender() {
		for (int a = 0; a < interfaces.size(); a++)
			interfaces.get(a).render();
	}
	
	/* The method used to render all of the game loop interfaces */
	public void interfaceStop() {
		for (int a = 0; a < interfaces.size(); a++)
			interfaces.get(a).stop();
	}
	
	/* The method used to render all of the game loop interfaces */
	public void interfaceClose() {
		for (int a = 0; a < interfaces.size(); a++)
			interfaces.get(a).close();
	}
	
	/* The method used to get the current delta */
	public long getDelta() { return this.fpsCalculator.currentDeltaTime; }
	
	/* The method used to get the current FPS */
	public float getFPS() { return this.fpsCalculator.currentFPS; }
	
}