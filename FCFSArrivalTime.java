package OperatingSystem;

import java.util.*;
	
public class FCFSArrivalTime{
	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of processes : ");
		int n = sc.nextInt();
		Process[] processes = new Process[n];
		float avgTAT = 0;
		float avgWT = 0;
		int[] arrivaltime = {3,1,2,5,6,4};
		int[] bursttime = {4,2,5,6,2,23};
		
		System.out.println("Enter arrival time and burst time for the processes : \n");
		for(int i = 0; i < n; i++) {
			Process temp = new Process((i+1),arrivaltime[i],bursttime[i]);
			processes[i] = temp;		
		}
		
		for(int i = 0; i < n; i++) {
			Process value = processes[i];
			int hole = i-1;
			while(hole >= 0 && value.arrivalTime < processes[hole].arrivalTime) {
				processes[hole+1] = processes[hole];
				hole--;
			}
			processes[hole+1]=value;
		}
		
		// waiting time of 1st process is zero
		processes[0].waitingTime = 0;
		for(int i = 1; i < n; i++) {
			processes[i].waitingTime = processes[i-1].waitingTime + processes[i-1].burstTime - processes[i].arrivalTime;
		}
		
		for(int i = 0; i < n; i++) {
			processes[i].turnAroundTime = processes[i].waitingTime + processes[i].burstTime;
			avgTAT = avgTAT + processes[i].turnAroundTime;
			avgWT = avgWT + processes[i].waitingTime;
		}
		
		avgTAT = avgTAT/n;
		avgWT = avgWT/n;
		
		for(Process p : processes) {
			System.out.println(p);
		}
		System.out.println("\n Average waiting time : "+avgWT+" secs\n Average turn arount time : "+avgTAT+" secs");
		
	}
}
