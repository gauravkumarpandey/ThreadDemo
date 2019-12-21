package com.gauravkp.threaddemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch countDownLatch = new CountDownLatch(2);
		
		Thread t1 = new Thread(()-> {
			countDownLatch.countDown();
			countDownLatch.countDown();
			countDownLatch.countDown();
		
			ThreadHelper.printit("running ....");
		});
		
//		Thread t2 = new Thread(()-> {
//			countDownLatch.countDown();
//			ThreadHelper.printit("running ....");
//		});
		
		t1.start();
	    //t2.start();
		countDownLatch.await(5, TimeUnit.SECONDS);
		System.out.println("All threads are fishied...");

	} 

}
