package com.gauravkp.threaddemo;

/**
 * simple lock implementation
 * synchronized block are reenterent i.e if a thread acquire lock on a monitor then it can get lock of any other synchronized block
 * @author Gaurav Panday
 *
 */
public class MyLock {
   
	private boolean isLocked = false;
	private Thread callingThread = null;
	private int lockCount = 0;
	
	public synchronized void lock() {
		while(isLocked && !(callingThread==Thread.currentThread())) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			isLocked = true;
			callingThread = Thread.currentThread();
			lockCount++;
		}
	}
		
	 public synchronized void unlock() {
		 if(callingThread == Thread.currentThread()) {
			 lockCount--;
		 }
		 if(lockCount == 0) {
			 isLocked = false;
			 notify();
			 
		 }
	 }
}
