package OperatingSystem;

import java.util.*;

public class FCFS {
	public static void main(String args[]) {
	
		Scanner sc = new Scanner(System.in);
		
		// initialising variables
		System.out.println("Enter the number of processes : ");
		int n= sc.nextInt();
		int[] burstTime = new int[n];
		int[] waitingTime = new int[n];
		int[] turnAroundTime = new int[n];
		float avgTAT = 0;
		float avgWT = 0;
		
		System.out.println("Enter the burst time for "+n+" processes : ");
		for(int i = 0; i < n; i++) {
			burstTime[i] = sc.nextInt();
		}
		
		// waiting time of first process is zero
		waitingTime[0] = 0;
		// setting waiting time of processes
		for(int i = 1; i < n; i++) {
			waitingTime[i] = waitingTime[i-1] + burstTime[i-1];
		}
		
		//setting turn around time of processes
		System.out.println("Process   BurstTime   WaitingTime   TurnArountTime");
		for(int i =0; i < n; i++) {
			turnAroundTime[i] = waitingTime[i] + burstTime[i];
			avgTAT = avgTAT + turnAroundTime[i];
			avgWT = avgWT + waitingTime[i];
			System.out.println((i+1)+"         "+burstTime[i]+"            "+waitingTime[i]+"            "+turnAroundTime[i]);
		}
		
		avgTAT = avgTAT/n;
		avgWT = avgWT/n;
		
		// print results
		
		System.out.println("\n Avg Waiting time : "+avgWT+" secs \n Avg Turn Around time : "+avgTAT+" secs");
	}
}





