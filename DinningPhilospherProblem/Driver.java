package DinningPhilospherProblem;

public class Driver {
	
	public static void main(String args[]) {

	Philospher[] philosphers = new Philospher[5];
    Fork[] forks = new Fork[philosphers.length];

    for (int i = 0; i < forks.length; i++) {
        forks[i] = new Fork();
    }

    for (int i = 0; i < philosphers.length; i++) {
        Fork leftFork = forks[i];
        Fork rightFork = forks[(i + 1) % forks.length];

        // preventing the dead lock 
        if (i == philosphers.length - 1) {
            philosphers[i] = new Philospher(rightFork, leftFork); 
        } else {
            philosphers[i] = new Philospher(leftFork, rightFork);
        }
        
        Thread thread = new Thread(philosphers[i], "Philosopher --> " + (i + 1));
        thread.start();
    }
	
	}
}
