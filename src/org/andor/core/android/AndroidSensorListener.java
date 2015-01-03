/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class AndroidSensorListener implements AndroidSensorListenerInterface {
	
	/* The constructor */
	public AndroidSensorListener(AndroidSensor sensor) {
		//Add this listener
		sensor.addListener(this);
	}
	
	/* A sensor method */
	public void onAccuracyChanged(Sensor sensor , int accuracy) {}
	
	/* A sensor method */
	public void onSensorChanged(SensorEvent e) {}
	
}