package grade_school_multiplication;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1954054 for the problem 5928 - Grade School Multiplication has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


import java.util.ArrayList;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		int problemNumber = 1;
		while(!current.trim().equals("0 0")){
			String[] data = current.split(" ");
			long top = Integer.parseInt(data[0]);
			long bottom = Integer.parseInt(data[1]);
			char[] topCA = data[0].toCharArray();
			char[] bottomCA = data[1].toCharArray();
			ArrayList<String> results = new ArrayList<>();
			results.add(top+"");
			results.add(bottom+"");
			int spaceCount = 0;
			for(int i = bottomCA.length-1; i >= 0; i--){
				spaceCount++;
				long temp;
				if(bottomCA[i] == '0'){
					temp = -1;
				}
				else{
					long number = Long.parseLong(bottomCA[i]+"");
					temp = top*number;
				}
				results.add(temp+"");
			}
			long fullResult = top*bottom;
			results.add(fullResult+"");
			printResults(problemNumber, results, spaceCount);
			current = input.nextLine();
			problemNumber++;
		}
	}
	
	private static void printResults(int number, ArrayList<String> results, int spaceCount){
		int totalSpace = results.get(results.size()-1).length();
		System.out.println("Problem " + number);
		System.out.println(spaces(results.get(0).length(), totalSpace) + results.get(0));
		System.out.println(spaces(results.get(1).length(), totalSpace) + results.get(1));
		System.out.println(dashes(totalSpace));
		int workLength = results.size()-2; // two at end
		int zeros = 0;
		StringBuilder steps = new StringBuilder();
		int stepCount = 0;
		int prevLength = 0;
		for(int i = 0, j = 2; i < spaceCount && j <= workLength; i++, j++){
			if(!results.get(j).equals("-1")) {
				int used = -1;
				if(i - zeros == 0){
					used = 0;
				}
				else if(prevLength > results.get(j).length()){
					used = i-1;
				}
				else{
					used = i;
				}
				steps.append(spaces(used, spaceCount-1) + results.get(j) + zeros(zeros) + "\n");
				stepCount++;
				zeros = 0;
				prevLength = results.get(j).length();
			}
			else zeros++;
		}
		if(stepCount > 1){
			System.out.print(steps.toString());
			System.out.println(dashes(totalSpace));
		}	
		System.out.println(results.get(results.size()-1)); // last
	}
	
	private static String spaces(int used, int total){
		int needed = total - used;
		String spaces = "";
		for(int i = 0; i < needed; i++){
			spaces += " ";
		}
		return spaces;
	}
	private static String dashes(int total){
		String spaces = "";
		for(int i = 0; i < total; i++){
			spaces += "-";
		}
		return spaces;
	}
	
	private static String zeros(int total){
		String spaces = "";
		for(int i = 0; i < total; i++){
			spaces += "0";
		}
		return spaces;
	}

}
