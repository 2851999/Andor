/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public interface AndroidSensorListenerInterface {
	
	/* A sensor method */
	public void onAccuracyChanged(Sensor sensor , int accuracy);
	
	/* A sensor method */
	public void onSensorChanged(SensorEvent e);
	
}