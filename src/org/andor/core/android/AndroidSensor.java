/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AndroidSensor implements SensorEventListener {
	
	/* The input listeners */
	public List<AndroidSensorListenerInterface> listeners;
	
	/* The constructor */
	public AndroidSensor(int sensor) {
		//Create the sensor
		listeners = new ArrayList<AndroidSensorListenerInterface>();
		SensorManager manager = (SensorManager) BaseActivity.instance.getSystemService(Context.SENSOR_SERVICE);
		Sensor accelerometer = manager.getDefaultSensor(sensor);
		if (! manager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_GAME))
			//An error occurred so log a message
			Logger.log(new Log("Andor - AndroidSensor" , "Error registering a listener " + sensor , Log.ERROR));
	}
	
	/* A sensor method */
	public void onAccuracyChanged(Sensor sensor , int accuracy) {
		//Call the event
		callOnAccuracyChanged(sensor, accuracy);
	}
	
	/* Called when the sensor has changed a value */
	public void onSensorChanged(SensorEvent e) {
		//Call the event
		callOnSensorChanged(e);
	}
	
	/* The static method used to add an input listener */
	public void addListener(AndroidSensorListenerInterface listener) {
		//Add the listener to the list of listeners
		listeners.add(listener);
	}
	
	/* The static methods used to call certain events */
	public void callOnAccuracyChanged(Sensor sensor , int accuracy) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onAccuracyChanged(sensor, accuracy);
	}
	
	public void callOnSensorChanged(SensorEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onSensorChanged(e);
	}
		
}