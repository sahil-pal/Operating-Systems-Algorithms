package BankersAlgorithm;

public class Driver {

	public static void main(String args[]) {
	
		int processes[] = {1, 2, 3, 4, 5};
		  
	    int avail[] = {2, 3, 3};
	  
	    int maxm[][] = {{7, 5, 3},
	                    {3, 2, 2},
	                    {2, 0, 2},
	                    {4, 2, 2},
	                    {4, 5, 3}};
	  
	    int allot[][] = {{0, 1, 0},
	                    {2, 0, 0},
	                    {3, 0, 2},
	                    {2, 1, 1},
	                    {0, 0, 2}};
	  
	    
	    Banker b1 = new Banker();
	    int[][] need = new int[5][3];
	    
	    b1.calculateNeed(need, maxm, allot);
	    b1.isSafe(processes, avail, maxm, allot,need);
	
	}
}
