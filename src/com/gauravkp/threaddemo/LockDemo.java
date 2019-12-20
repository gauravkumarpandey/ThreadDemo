package com.gauravkp.threaddemo;

/**
 * Lock object can be use to guard the critical section in place of synchronization
 * @author Gaurav Panday
 *
 */
public class LockDemo {

	
	public static void main(String[] args) {
		unsafeIncrement();
		safeSynchronizedIncrement();
	}
	
	

	private static void safeSynchronizedIncrement() {
		
		Counter counter = new Counter();
		Thread t1 = new Thread(() ->  {
			
			for (int i = 0; i < 100; i ++) {
			  counter.safeSyncincrement();
			}
			
		});
		
		Thread t2 = new Thread(() ->  {
			for (int i = 0; i < 100; i ++) {
			  counter.safeSyncincrement();
			}
		});
		
		t1.start();
		t2.start();
		
		ThreadHelper.sleep(5000l);
		System.out.println(counter.getCounter());
		
	}



	/**
	 * represent a race condition, the expected output is 20 after both 
	 * thread finished its job
	 */
	private static void unsafeIncrement() {
		Counter counter = new Counter();
		Thread t1 = new Thread(() ->  {
			
			for (int i = 0; i < 100; i ++) {
			  counter.increment();
			}
			
		});
		
		Thread t2 = new Thread(() ->  {
			for (int i = 0; i < 100; i ++) {
			  counter.increment();
			}
		});
		
		t1.start();
		t2.start();
		
		ThreadHelper.sleep(5000l);
		System.out.println(counter.getCounter());
	}

}

class Counter{
	
	private Long counter = 0L;
	
	
	public void increment() {
		++counter;
	}
	
	public  void safeSyncincrement() {
		MyLock myLock = new MyLock();
		myLock.lock();
		++counter;
		myLock.unlock();
	}
	
	public Long getCounter() {
		return counter;
	}
}

