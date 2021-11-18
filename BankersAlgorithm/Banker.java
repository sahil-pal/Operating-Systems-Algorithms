package BankersAlgorithm;

public class Banker {

	// n is the number of processes and m is the number of resources types
	// Available array will tell the number of instances of each resource
	// Max array will give the requirement of particular instances of a type, so m*n size
	// Allocation array will give particular instances assigned of a resource type
	// Need array will tell the need of particular instances of a type.
	
	static int n = 5;
	static int m = 3;
	  
	static void calculateNeed(int need[][], int max[][],int alloc[][])
	{
	  
	    for (int i = 0 ; i < n ; i++) {
	        for (int j = 0 ; j < m ; j++) {
	        	need[i][j] = max[i][j] - alloc[i][j];
	        }
	    }
	}
	  
	
	static void isSafe(int processes[], int available[], int max[][],int alloc[][],int need[][]){
	
		boolean[] isCompleted = new boolean[n];
	    int[] safeSequence = new int[n];
	    int[] resourceCopy = new int[m];
	    
	    
	    for (int i = 0; i < m ; i++){
	    	resourceCopy[i] = available[i];
	    }
	  
	    
	    int count = 0;
	    while (count < n){
	        
	    	boolean found = false;
	        for (int processNumber = 0; processNumber < n; processNumber++){
	    
	            if (isCompleted[processNumber] == false){
	                
	                int j;
	                for (j = 0; j < m; j++) {
	                    if (need[processNumber][j] > resourceCopy[j]) {
	                        break;
	                    }
	                }
	  
	                // All need satisfied
	                if (j == m){
	                	for (int k = 0 ; k < m ; k++)
	                		resourceCopy[k] += alloc[processNumber][k];
	                		safeSequence[count++] = processNumber;
	                		isCompleted[processNumber] = true;
	                		found = true;
	                }
	            }
	        }
	
	        if (found == false)
	        {
	            System.out.print("System not in safe state.\n");
	            break;
	        }
	    }
	    
	    System.out.print("Complete/Partail safe sequence --> ");
	    for (int i = 0; i < n ; i++)
	    	System.out.print((safeSequence[i]+1) + " ");
		}
	  
	
}
