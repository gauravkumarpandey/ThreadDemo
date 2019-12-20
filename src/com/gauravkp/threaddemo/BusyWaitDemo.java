package com.gauravkp.threaddemo;

/**
 *  1. Two thread can only communicate with a common signal
 *  2. If a thread is waiting for a signal from other thread without doing anything this is called busy waiting
 *  3. This can be ignorable in case of short lived thread but if waiting time is large then this causes severe 
 *  performance implication
 * 
 * @author Gaurav Panday
 *
 */
public class BusyWaitDemo {
  
	private static MySignal mySignal = new MySignal();
	public static void main(String args[]) {
		
		//Thread T1 set the signal
		
		Thread t1 = new Thread(() ->  {
			// just to simulate busy wait
			ThreadHelper.sleep(3000);
			mySignal.setFlag(true);
		   ThreadHelper.printit("set the flag and out of run...");	
		});
		
		Thread t2 = new Thread(() ->  {
			while(!mySignal.isFlag()) {
				//keep waiting without doing anything
				System.out.println("waiting...");
			}
		   ThreadHelper.printit("Got signal executing now...");	
		});
		
		t1.start();
		t2.start();
	}
}
