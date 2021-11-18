package OperatingSystem.ReaderWriterProblem;
import java.util.concurrent.Semaphore;

public class Driver {

	public static void main(String args[]) throws InterruptedException 
    {
     
		// creating the object of semaphore initialized to 1
        Semaphore semphore = new Semaphore(1);
      
        // creating the reader and writer thread
        SemaphoreThread thread1 = new SemaphoreThread(semphore, "Reader");
        SemaphoreThread thread2 = new SemaphoreThread(semphore, "Writer");
      
        // start the reader and writer thread 
        thread1.start();
        thread2.start();
      
        // execute thread till it die
        thread1.join();
        thread2.join();
         
        System.out.println("Final resource count --> " + SharedResource.count);
    }
}
