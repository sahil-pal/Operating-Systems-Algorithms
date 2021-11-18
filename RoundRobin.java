package OperatingSystem;

import java.util.*;

public class RoundRobin {
	
	public static String printStatement(Process p ) {
		return "Pid="+p.getPid()+"  arrivalTime="+p.getArrivalTime()+"  completionTime="+p.getCompletionTime()+"  waitingTime="+p.getWaitingTime()+"  turnAroundTime="+p.getTurnAroundTime()+"  BurstTime="+p.getBurstTime();
	}

	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of processes : ");
		int n = sc.nextInt();
		
		ArrayList<Process> processes = new ArrayList<Process>();
		ArrayList<Process> copy = new ArrayList<Process>();
 		
		float avgTAT = 0;
		float avgWT = 0;
		int[] arrivaltime = {0,2,3,5};
		int[] bursttime = {2,1,3,4};
		int timeslice = 2;
		
		// storing the input
		for(int i = 0; i < n; i++) {
			Process temp1 = new Process((i+1),arrivaltime[i],bursttime[i]);
			Process temp2 = new Process((i+1),arrivaltime[i],bursttime[i]);
			processes.add(temp1);
			copy.add(temp2);
		}

		// sorting on the base of arrival time
		processArrivalSort asort = new processArrivalSort();
		Collections.sort(processes,asort);
		System.out.println("\nSorted Process accoding to Arrival Time ==> \n");
		for(Process p : processes) {
			System.out.println(p);
		}
		
		ArrayList<Process> executionQueue = new ArrayList<Process>();
		int time = 0;
		while(!processes.isEmpty()) {
			for(int index = 0; index < processes.size(); index++) {
				if(processes.get(index).getBurstTime() < timeslice) {
					processes.get(index).setArrivalTime(time);
					time = time + processes.get(index).getBurstTime();
					processes.get(index).setCompletionTime(time);
					executionQueue.add(processes.get(index));
					processes.remove(index);
					index--;	
				}
				else if(processes.get(index).getArrivalTime() <= time) {
					Process temp = new Process(processes.get(index).Pid,0,0);
					temp.setArrivalTime(time);
					
					time = time + timeslice;
					temp.setCompletionTime(time);
					int newBurstTime = processes.get(index).getBurstTime() - timeslice;
					processes.get(index).setBurstTime(newBurstTime);
					temp.setBurstTime(newBurstTime);
					executionQueue.add(temp);
					if(newBurstTime == 0) {
						processes.remove(index);
						index--;
					}
					
				}
				else {
					time++;
				}
			}
		}
		
		System.out.println("\nExecution sequence ==> \n");
		for(Process p : executionQueue) {
			System.out.println(p);
		}

		processes.removeAll(processes);
		
		for(int i = 0; i < executionQueue.size() - 1; i++) {
			Process temp = new Process(executionQueue.get(i).getPid(),executionQueue.get(i).getArrivalTime(),executionQueue.get(i).getBurstTime());
			temp.setCompletionTime(executionQueue.get(i).getCompletionTime());
			for(int j = i+1; j < executionQueue.size(); j++) {
				if(executionQueue.get(j).Pid == temp.getPid()) {
					temp.completionTime = Math.max(temp.completionTime,executionQueue.get(j).completionTime);
					executionQueue.remove(j);
				}
			}
			processes.add(temp);
		}
		
		for(int i = 0; i < processes.size(); i++) {
			for(int j = 0; j < copy.size(); j++) {
				if(processes.get(i).getPid() == copy.get(j).getPid()) {
					processes.get(i).setBurstTime(copy.get(j).getBurstTime());
				}
			}
		}
		
		for(int i = 0; i < processes.size(); i++) {
			processes.get(i).turnAroundTime = processes.get(i).getCompletionTime() - processes.get(i).getArrivalTime();
			processes.get(i).waitingTime = processes.get(i).getTurnAroundTime() - processes.get(i).getBurstTime();
			avgTAT += processes.get(i).turnAroundTime;
			avgWT += processes.get(i).waitingTime;
			
		}
		
		avgTAT = avgTAT / processes.size();
		avgWT = avgWT / processes.size();
		
		System.out.println("\nResult ==> \n");
		for(Process p : processes) {
			System.out.println(printStatement(p));
		}
		
		System.out.println("\n");
		System.out.println("Avergae Waiting Time = "+avgWT+" ms \n"+"Average Turn Around time = "+avgTAT+" ms");
	}
}
