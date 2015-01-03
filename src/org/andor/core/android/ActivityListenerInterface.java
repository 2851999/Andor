/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

public interface ActivityListenerInterface {
	
	/* The Android activity methods */
	public void onActivityCreated();
	public void onActivityPaused();
	public void onActivityResumed();
	public void onActivityStopped();
	public void onActivityRestarted();
	public void onActivityDestroy();
	
}