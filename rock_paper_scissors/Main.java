package rock_paper_scissors;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1942135 for the problem 4575 - Rock, Paper, Scissors has succeeded with verdict Accepted.
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
		String current1 = input.nextLine();
		String current2 = input.nextLine();
		while(!current1.trim().equals("E") && !current2.trim().equals("E")){
			int p1Wins = 0;
			int p2Wins = 0;
			for(int i = 0; i < current1.length(); i++){
				int result = decideWinner(current1.charAt(i), current2.charAt(i));
				if(result > 0) p2Wins++;
				if(result < 0) p1Wins++;
			}
			System.out.println("P1: " + p1Wins);
			System.out.println("P2: " + p2Wins);
			current1 = input.nextLine();
			current2 = input.nextLine();
		}
	}

	private static int decideWinner(char p1, char p2){ // return -1 for p1 1 for p2 0 for tie
        if(p1 == p2) return 0;
        switch(p1){
		case 'R':
			if(p2 == 'S') return -1;
			if(p2 == 'P') return 1;
			break;
		case 'S':
			if(p2 == 'R') return 1;
			if(p2 == 'P') return -1;
			break;
		case 'P':
			if(p2 == 'R') return -1;
			if(p2 == 'S') return 1;
			break;
		default: 
			// should not go here
		}
		return 0;
	}
}
