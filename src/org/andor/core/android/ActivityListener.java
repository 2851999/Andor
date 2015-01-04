/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

public class ActivityListener implements ActivityListenerInterface {
	
	/* The constructor */
	public ActivityListener() {
		//Add this listener
		BaseActivity.addListener(this);
	}
	
	/* The Android activity methods */
	public void onActivityCreated() {}
	public void onActivityPaused() {}
	public void onActivityResumed() {}
	public void onActivityStopped() {}
	public void onActivityRestarted() {}
	public void onActivityDestroy() {}
	
}