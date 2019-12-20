/**
 * 
 */
package com.gauravkp.threaddemo;

/**
 * Busy wait can be avoided with wait notify 
 *  1. wait -notify calling thread must acquire lock on monitor i.e must be called within synchronized
 *  2. wait release lock on monitor and allow other thread to call wait or notify
 *  3. Wait should be always in while loop to prevent spurious wake-up
 *  4. notify /notify all never store its call so it could be chance that waiting thread miss the notification so notifyfing thread must save their signal
 * @author Gaurav Panday
 *
 */
public class WaitNotifyDemo {

	/**
	 * @param args
	 */
	private static WaitNotify waitNotify = new WaitNotify();
	public static void main(String[] args) {
		
       Thread t1 = new Thread(() -> {
    	   ThreadHelper.printit("waiting for....");
    	   waitNotify.doWait();
    	   ThreadHelper.printit("Notified....");
       });
       
       Thread t2 = new Thread(() -> {
    	   ThreadHelper.printit("Running ...");
    	   waitNotify.doNotify();
			
			/** whenever notify call awkened thred must wait for come out the notifying thread from synchronize block **/
			ThreadHelper.sleep(3000l);
			ThreadHelper.printit("Out of run...");
       });
       
       t1.start();
       t2.start();
	}

}

