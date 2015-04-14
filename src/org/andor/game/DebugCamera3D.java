/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.game;

import org.andor.core.Camera3D;
import org.andor.core.Vector3D;
import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;
import org.andor.utils.ClampUtils;

public class DebugCamera3D extends Camera3D implements InputListenerInterface {
	
	/* The button for each key */
	public int keyForward = Keyboard.KEY_W_CODE;
	public int keyBackward = Keyboard.KEY_S_CODE;
	public int keyLeft = Keyboard.KEY_A_CODE;
	public int keyRight =  Keyboard.KEY_D_CODE;
	public int keyFaster = Keyboard.KEY_LSHIFT_CODE;
	
	/* The speeds */
	public float speed;
	public float fasterSpeed;
	public float mouseSensitivity;
	
	/* Maximum and minium x rotation values */
	public float minXRotation;
	public float maxXRotation;
	
	/* The boolean that states whether the mouse should be locked */
	public boolean lockMouse;
	
	/* The constructor */
	public DebugCamera3D() {
		//Add this input listener
		Input.addListener(this);
		//Setup the speeds
		this.speed = 0.01f;
		this.fasterSpeed = 0.05f;
		this.mouseSensitivity = 0.5f;
		this.minXRotation = -80;
		this.maxXRotation = 80;
		this.lockMouse = true;
		
		//Lock the mouse
		Mouse.lock();
	}
	
	/* The update method */
	public void update(long delta) {
		//Clamp the rotation
		this.rotation.x = ClampUtils.clamp(this.rotation.x, this.minXRotation, this.maxXRotation);
		//The speed
		float s = this.speed;
		//Check the input
		if (Keyboard.isKeyDown(this.keyFaster))
			s = this.fasterSpeed;
		if (Keyboard.isKeyDown(this.keyForward))
			this.moveForward(s * delta);
		if (Keyboard.isKeyDown(this.keyBackward))
			this.moveBackward(s * delta);
		if (Keyboard.isKeyDown(this.keyLeft))
			this.moveLeft(s * delta);
		if (Keyboard.isKeyDown(this.keyRight))
			this.moveRight(s * delta);
	}
	
	public void onMousePressed(MouseEvent e) { }
	public void onMouseReleased(MouseEvent e) { }
	public void onMouseClicked(MouseEvent e) { }
	public void onMouseMoved(MouseMotionEvent e) {
		if (Mouse.isLocked() || ! this.lockMouse)
			this.rotation.add(new Vector3D(e.dy * this.mouseSensitivity, e.dx * this.mouseSensitivity, 0));
	}
	public void onMouseDragged(MouseMotionEvent e) { }
	public void onScroll(ScrollEvent e) { }
	public void onKeyPressed(KeyboardEvent e) { }
	public void onKeyReleased(KeyboardEvent e) { }
	public void onKeyTyped(KeyboardEvent e) { }
	public void onAxisChange(ControllerAxisEvent e) { }
	public void onButtonPressed(ControllerButtonEvent e) { }
	public void onButtonReleased(ControllerButtonEvent e) { }
	
}