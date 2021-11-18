package OperatingSystem;

import java.util.*;

public class SJF_NP {

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
		
		for(Process p : processes) {
			System.out.println(p);
		}
		
		// creating Q
		HashSet<Process> executionQueue = new LinkedHashSet<Process>();
		
		int time = 0;
		processBurstSort bsort = new processBurstSort();
		
		while(executionQueue.size() != n) {
			ArrayList<Process> temp = new ArrayList<Process>();
			for(Process p : processes) {
				if(p.getArrivalTime() <= time && !p.isSelectionStatus()) {
					temp.add(p);
				}
			}
			Collections.sort(temp, bsort);
			executionQueue.add(temp.get(0));
			time = time + temp.get(0).getBurstTime();
			temp.get(0).setSelectionStatus(true);

		}
		
		System.out.println("\nprinting execution Queue ==> ");
		for(Process p : executionQueue) {
			System.out.println(p);
		}
		
		// waiting time of 1st process is zero
		processes.removeAll(processes);
		time=0;
		for(Process p : executionQueue) {
			processes.add(p);
		}
		
		processes.get(0).waitingTime = 0;
		time = time + processes.get(0).burstTime;
		for(int i = 1; i < n; i++) {
			processes.get(i).waitingTime = time - processes.get(i).arrivalTime;
			processes.get(i).turnAroundTime = processes.get(i).waitingTime + processes.get(i).getBurstTime();
			time = time + processes.get(i).burstTime;
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
		System.out.println("\n Average waiting time : "+avgWT+" secs\n Average turn arount time : "+avgTAT+" secs");
				
		
	}
}
