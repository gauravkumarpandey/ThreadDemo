
package com.gauravkp.threaddemo;

/**
 * @author Gaurav Panday
 * 
 * contains helper method to improve understanding for thread execution
 *
 */
public class ThreadHelper {

	/**
	 * Sleep the calling thread by specified time
	 * @param ms
	 * @return
	 */
	public static boolean sleep(long ms) {
		try {
			Thread.sleep(ms);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * print the give msg with thread
	 * @param msg
	 */
	public static void printit(String msg) {
		System.out.println(msg + " " + Thread.currentThread());
	}
}
