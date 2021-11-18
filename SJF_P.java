package OperatingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class SJF_P {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of processes : ");
		int n = sc.nextInt();
		
		ArrayList<Process> processes = new ArrayList<Process>();
		
		float avgTAT = 0;
		float avgWT = 0;
		int[] arrivaltime = {2,5,1,0,4};
		int[] bursttime = {6,2,8,3,4};
		
		// taking input
		System.out.println("Enter arrival time and burst time for the processes : \n");
		for(int i = 0; i < n; i++) {
			Process temp = new Process((i+1),arrivaltime[i],bursttime[i]);
			processes.add(temp);		
		}
		
		// sorting on the base of arrival time
		processArrivalSort asort = new processArrivalSort();
		Collections.sort(processes,asort);
		
		// creating a variable having total time of execution of all programs
		int totalTime = 0;
		for(Process p : processes) {
			System.out.println(p);
			totalTime = totalTime + p.getBurstTime();
		}
		
		
		// creating Q
		LinkedList<Process> executionQueue = new LinkedList<Process>();
		
		int time = 0;
		processBurstSort bsort = new processBurstSort();
		
		while(time != totalTime) {
			ArrayList<Process> temp = new ArrayList<Process>();
			for(Process p : processes) {
				if(p.getArrivalTime() <= time && p.getBurstTime() > 0) {
					temp.add(p);
				}
			}
			Collections.sort(temp, bsort);
			executionQueue.add(temp.get(0));
			time++;
			temp.get(0).setBurstTime(temp.get(0).getBurstTime()-1);
		}
		
		System.out.println("\nprinting execution Queue ==> ");
		for(Process p : executionQueue) {
			System.out.println(p.getPid());
		}
		
		for(int i = 0; i < totalTime; i++) {
			executionQueue.get(i).setBurstTime(executionQueue.get(i).getBurstTime()+1);
			executionQueue.get(i).setCompletionTime((i+1));
		}
		
		processes.removeAll(processes);
		for(int i = executionQueue.size()-1 ; i >= 0; i--) {
			Process temp = executionQueue.get(i);
			if(!processes.contains(temp)) {
				processes.add(temp);
			}
		}
		
		for(int i = 0; i < processes.size(); i++) {
			processes.get(i).setTurnAroundTime(processes.get(i).getCompletionTime()-processes.get(i).getArrivalTime());
			processes.get(i).setWaitingTime(processes.get(i).getTurnAroundTime()-processes.get(i).getBurstTime());
			avgTAT = avgTAT + processes.get(i).turnAroundTime;
			avgWT = avgWT + processes.get(i).waitingTime;
		}
										
		avgTAT = avgTAT/n;
		avgWT = avgWT/n;
						
		System.out.println("\nfinal result ==>");
		for(Process p : processes) {
			System.out.println(p);
		}
		System.out.println("\n Average waiting time : "+avgWT+" secs\n Average turn arount time : "+avgTAT+" secs");
		
	}
}
