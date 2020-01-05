package com.gauravkp.threaddemo;

import java.sql.SQLException;

/**
 * When an exception is thrown out of run method the thread terminates and 
 * following activities takes place:
 *   1. The Java virtual machine (JVM) looks for an instance of Thread.
UncaughtExceptionHandler installed via Thread’s void setUncau
ghtExceptionHandler(Thread.UncaughtExceptionHandler eh)
method. When this handler is found, it passes execution to the
instance’s void uncaughtException(Thread t, Throwable e)
method, where t identifies the Thread object of the thread that
threw the exception, and e identifies the thrown exception or
error

2. Assuming that setUncaughtExceptionHandler() was not called
to install a handler, the JVM passes control to the associated
ThreadGroup object’s uncaughtException(Thread t, Throwable e)
method. Assuming that ThreadGroup was not extended and that
its uncaughtException() method was not overridden to handle
the exception, uncaughtException() passes control to the
parent ThreadGroup object’s uncaughtException() method
when a parent ThreadGroup is present. Otherwise, it checks to
see if a default uncaught exception handler has been installed
(via Thread’s static void setDefaultUncaught
ExceptionHandler(Thread.UncaughtExceptionHandler
handler) method). If a default uncaught exception handler has
been installed, its uncaughtException() method is called with
the same two arguments. Otherwise, uncaughtException()
checks its Throwable argument to determine if it’s an instance
of java.lang.ThreadDeath. If so, nothing special is done.
Otherwise,  exception message shows, a message
containing the thread’s name, as returned from the thread’s
getName() method, and a stack backtrace, using the Throwable
argument’s printStackTrace() method, is printed to the
standard error stream.
 * @author Gaurav Panday
 *
 */
public class ThreadException {
 @SuppressWarnings("static-access")
public static void main(String []args) {
	   Thread thd = new Thread(() ->  {
		   System.out.println("In  thread "+ Thread.currentThread());
		   
		   //wrapped the checked exception in RuntimeException as we can't throw checked exception from run method 
		   throw new RuntimeException(new SQLException("explicit"));
	   });
	   
	   //Not the default handler is attached but not executed as the specific handler is set for the thread
	   //the default handle will only be executed if no uncaught exception handler attached
	   thd.setUncaughtExceptionHandler(
			   (t,  e) -> {
				   System.out.println("Caught throwable " + e +
						   " for thread " + t);
			   });
			 			
	   thd.setDefaultUncaughtExceptionHandler((t,e)-> {
		   System.out.println("Default uncaught exception handler");
		   System.out.println("Caught throwable " + e +
		   " for thread " + t);
	   });
	   
	   
	   thd.start();
	   
	  
	   
	   //when ever an exception is thrown from a child thread the main thread still complete its execution..
	   ThreadHelper.sleep(1000);
	   System.out.println("In main thread "+ Thread.currentThread());
   }
   

}
