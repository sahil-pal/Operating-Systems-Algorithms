package OperatingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PriorityScheduling_NP {

	public static void main(String args[]) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of processes : ");
		int n = sc.nextInt();
		
		ArrayList<Process> processes = new ArrayList<Process>();
		
		float avgTAT = 0;
		float avgWT = 0;
		int[] arrivaltime = {0,5,12,2,9};
		int[] bursttime = {11,28,2,10,16};
		int[] priority = {2,0,3,1,4};
		
		// storing the input
		//System.out.println("Enter arrival time and burst time for the processes : \n");
		for(int i = 0; i < n; i++) {
			Process temp = new Process((i+1),arrivaltime[i],bursttime[i],priority[i]);
			processes.add(temp);		
		}
		
		// sorting on the base of arrival time
		processArrivalSort asort = new processArrivalSort();
		Collections.sort(processes,asort);
		
		// selecting the first process
		Process first = processes.get(0);
		for(int i = 1; i < n; i++) {
			int currArrivalTime = processes.get(i).arrivalTime;
			int currPriority = processes.get(i).priority;
			if(first.getArrivalTime() == currArrivalTime && currPriority < first.getPriority()) {
				first = processes.get(i);
			}
		}
		
		// creating Q
		HashSet<Process> executionQueue = new LinkedHashSet<Process>();
		first.setSelectionStatus(true);
		executionQueue.add(first);
		int time = first.burstTime;
		processPrioritySort psort = new processPrioritySort();
		
		while(executionQueue.size() != n) {
			ArrayList<Process> temp = new ArrayList<Process>();
			for(int i = 1; i < processes.size(); i++) {
				if(processes.get(i).arrivalTime <= time  && !processes.get(i).isSelectionStatus()) {
					temp.add(processes.get(i));
//					System.out.println("added to temp"+processes.get(i));
				}
			}
			Collections.sort(temp, psort);
//			System.out.println("temp -- >");
//			for(Process p : temp) {
//				System.out.println(p);
//			}
//			System.out.println("---");
			executionQueue.add(temp.get(0));
//			System.out.println("==> "+temp.get(0)+"\n");
			time = time + temp.get(0).getBurstTime();
			temp.get(0).setSelectionStatus(true);
		}
		
		// storing processes
		processes.removeAll(processes);
		for(Process p : executionQueue) {
			processes.add(p);
		}

		processes.get(0).waitingTime = 0;
		time = processes.get(0).burstTime;
		processes.get(0).setCompletionTime(time);
		processes.get(0).setTurnAroundTime(processes.get(0).burstTime);
		for(int i = 1; i < n; i++) {
			processes.get(i).waitingTime = time - processes.get(i).arrivalTime;
			processes.get(i).turnAroundTime = processes.get(i).waitingTime + processes.get(i).getBurstTime();
			time = time + processes.get(i).burstTime;
			processes.get(i).setCompletionTime(time);
		}
						
		for(int i = 0; i < n; i++) {
			avgTAT = avgTAT + processes.get(i).turnAroundTime;
			avgWT = avgWT + processes.get(i).waitingTime;
		}
						
		avgTAT = avgTAT/n;
		avgWT = avgWT/n;
						
		System.out.println("\nfinal result ==>");
		for(Process p : processes) {
			System.out.println(p);
		}
		System.out.println("\n Average waiting time : "+avgWT+" secs\n Average turn around time : "+avgTAT+" secs");
		
		
		
		
		
	}
}
