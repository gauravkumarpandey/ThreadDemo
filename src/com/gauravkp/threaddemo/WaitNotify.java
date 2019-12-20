/**
 * 
 */
package com.gauravkp.threaddemo;

/**
 * Represent a wait notify 
 * @author Gaurav Panday
 *
 */
public class WaitNotify {
	/** the monitor object **/
	private MonitorObject monitorObject = new MonitorObject();
	
	/** to prevent lost notification **/
	private boolean wasSignaled = false;
	
	public void doWait() {
		synchronized (monitorObject) {
			while(wasSignaled) {
				try {
					monitorObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		 }			
		}
		
		public void doNotify() {
			synchronized (monitorObject) {
				wasSignaled = true;
				monitorObject.notify();
			}
	     }
	

}
