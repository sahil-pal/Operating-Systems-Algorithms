package DinningPhilospherProblem;

public class Philospher implements Runnable {

	private Fork leftFork;
	private Fork rightFork;
	
	public Philospher(Fork leftFork, Fork rightFork) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {
		
		try {
            while (true) {
                
                // thinking
                performAction("Thinking");
                synchronized (leftFork) {
                	performAction("Picked up left fork");
                    synchronized (rightFork) {
                        // eating
                    	performAction("Picked up right fork"); 
                    	System.out.println("Started eating");
                        performAction("Put down right fork");
                    }
                    // Going Back to thinking
                    performAction("Put down left fork");
                    System.out.println("Started Thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
		
	}
	
	// to perform action - think or eat
	private void performAction(String actionName) throws InterruptedException{
		
		Thread currThread = Thread.currentThread();
		System.out.println(currThread.getName()+" --> performing "+actionName);
		
		// suspending the thread for random amount of time
		currThread.sleep((int)(Math.random()*100));
	}
	
}
