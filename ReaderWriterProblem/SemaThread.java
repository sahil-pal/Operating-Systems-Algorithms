package OperatingSystem.ReaderWriterProblem;

import java.util.concurrent.Semaphore;

class SharedResource 
{
    static int count = 0;
}
  
class SemaphoreThread extends Thread
{
    Semaphore semaphore;
    public SemaphoreThread(Semaphore semaphore, String threadName) 
    {
        super(threadName);
        this.semaphore=semaphore;
    }
  
    @Override
    public void run() {
         
    	// if reader thread 
        if(this.getName().equals("Reader"))
        {
            System.out.println("Starting thread -->  " + this.getName());
            try 
            {
                System.out.println(this.getName() + " --> Starting acquire.");
                semaphore.acquire();
                System.out.println(this.getName() + " --> has successfully acquired.");
          
                for(int i=0; i<5; i++)
                {
                    SharedResource.count++;
                    System.out.println(this.getName() + " --> Shared Resource count = " + SharedResource.count);
                    Thread.sleep(10);
                }
            } 
            catch (InterruptedException err) 
            {
                System.out.println(err);
            }
          
                System.out.println(this.getName() + " --> releasing the resource.");
                semaphore.release();
                System.out.println(this.getName() + " --> successfully released the resource.");
        }
          
        // if writer thread
        else
        {
        	System.out.println("Starting thread -->  " + this.getName());
            try 
            {
                System.out.println(this.getName() + " --> Starting acquire.");
                semaphore.acquire();
                System.out.println(this.getName() + " --> has successfully acquired.");
          
                for(int i=0; i<5; i++)
                {
                    SharedResource.count--;
                    System.out.println(this.getName() + " --> Shared Resource count = " + SharedResource.count);
                    Thread.sleep(10);
                }
            } 
            catch (InterruptedException err) 
            {
                 System.out.println(err);
            }
   
            System.out.println(this.getName() + " --> releasing the resource.");
            semaphore.release();
            System.out.println(this.getName() + " --> successfully released the resource.");
        }
    }
}
  
