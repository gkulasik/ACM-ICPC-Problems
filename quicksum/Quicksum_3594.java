package quicksum;

// 3594 - passed
//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1933966 for the problem 3594 - Quicksum has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

import java.util.Scanner;

class Main{
	
    public static void main (String args[]) throws Exception
    {
    	work();
    }
    
	static void work() {
		int quicksum = 0;
		int index = 1;
		boolean run = true;
		char A = 'A';
		Scanner input = new Scanner(System.in);
		while(run && input.hasNextLine()){
		String line = input.nextLine();
		for(char current : line.toCharArray()){
			if(current == '#'){
				run = false;
				break;
			}
			if(current != ' ')
				quicksum += (index * ((current - A)+1));
			index++;
		}
		if(run)
			System.out.println(quicksum);
		quicksum = 0; index = 1; // reset
		}
	}
}
