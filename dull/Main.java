package dull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1942660 for the problem 4572 - DuLL has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("0")){
			String[] inputs = current.split(" ");
			int N = Integer.parseInt(inputs[0]);
			int P = Integer.parseInt(inputs[1]);
			int S = Integer.parseInt(inputs[2]);
			HashMap<String, Integer> dlls = new HashMap<>();
			current = input.nextLine();
			String[] dll = current.split(" ");
			for(int i = 0; i < N; i++){
				dlls.put((char)('A'+i)+"", Integer.parseInt(dll[i]));
			}
			ArrayList<Program> programs = new ArrayList<>();
			for(int i = 0; i < P; i++){
				current = input.nextLine();
				String[] program = current.split(" ");
				programs.add(new Program(i+1, program[1], Integer.parseInt(program[0])));
			}
			current = input.nextLine(); // S
			String[] transitions = current.split(" ");
			ArrayList<Program> activePrograms = new ArrayList<>();
			HashMap<String, Integer> activeDlls = new HashMap<>();
			for(String d : dlls.keySet()){
				activeDlls.put(d, 0);
			}
			int maxMemory = 0;
			for(int i = 0; i < S; i++){
				int p = Integer.parseInt(transitions[i]);
				if(p > 0){ // add
					Program prog = programs.get(p-1);
					activePrograms.add(prog);
					for(String d : prog.dlls){
						activeDlls.put(d, activeDlls.get(d)+1);
					}
				}
				else{ // remove
					Program prog = programs.get(Math.abs(p)-1);
					activePrograms.remove(prog);
					for(String d : prog.dlls){
						activeDlls.put(d, activeDlls.get(d)-1);
					}
				}
				int totalMemoryUsed = totalProgramMemory(activePrograms) + totalDllMemory(activeDlls, dlls);
				if(totalMemoryUsed > maxMemory) maxMemory = totalMemoryUsed;
			}
			System.out.println(maxMemory);
			current = input.nextLine();
		}
	}
	
	private static int totalProgramMemory(ArrayList<Program> programs){
		int total = 0;
		for(Program p : programs){
			total += p.size;
		}
		return total;
	}
	
	private static int totalDllMemory(HashMap<String, Integer> instances, HashMap<String, Integer> memory){
		int totalMemoryUsed = 0;
		for(String s : memory.keySet()){
			if(instances.get(s) > 0) totalMemoryUsed += memory.get(s);
		}
		return totalMemoryUsed;
	}

}

class Program{
	public int number;
	public ArrayList<String> dlls;
	public int size;
	
	Program(int number, String dlls, int size){
		this.number = number;
		this.size = size;
		this.dlls = new ArrayList<>();
		for(char c : dlls.toCharArray()){
			this.dlls.add(c+"");
		}
	}
}
