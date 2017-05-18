package voting;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1946210 for the problem 4921 - Voting has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("#")){
			char[] votes = current.toCharArray();
			int yes = 0;
			int no = 0;
			int pass = 0;
			int absent = 0;
			for(char c : votes){
				switch(c){
				case 'Y': yes++; break;
				case 'N': no++; break;
				case 'P': pass++; break;
				case 'A': absent++; break;
				}
			}
			if(absent >= yes+no+pass) System.out.println("need quorum");
			else{
				if(yes > no) System.out.println("yes");
				if(no > yes) System.out.println("no");
				if(yes == no) System.out.println("tie");
			}
			current = input.nextLine();
		}
	}

}
