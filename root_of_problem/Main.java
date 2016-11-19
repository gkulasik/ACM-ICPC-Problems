package root_of_problem;

// 3600 - passed
//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1934039 for the problem 3600 - Root of the Problem has succeeded with verdict Accepted.
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
		String current = input.nextLine().trim();
		while(!current.equals("0 0")){
			String[] pair = current.split(" ");
			int B = Integer.parseInt(pair[0]);
			int N = Integer.parseInt(pair[1]);
			int A = 0;
			for(; (Math.pow(A, N)) < B; A++){}
			double dif1 = Math.abs(Math.pow(A, N) - B);
			double dif2 = Math.abs(Math.pow((A-1),N) - B);
			if(dif1 < dif2) System.out.println(A);
			else System.out.println(A-1);
			current = input.nextLine().trim();
		}

	}

}
