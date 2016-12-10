package persistent_bits;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1936263 for the problem 3849 - Persistent Bits has succeeded with verdict Accepted.
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
		int and = Integer.MAX_VALUE;
		int or = 0;
		while(!current.trim().equals("0")){
			String[] ints = current.split(" ");
			int A =  Integer.parseInt(ints[0]);
			int B =  Integer.parseInt(ints[1]);
			int C =  Integer.parseInt(ints[2]);
			int S =  Integer.parseInt(ints[3]);
			int X = S;
			and = S;
			or = S;
			for(int i = 0; i < C; i++){
				X = (((A*X)+B)%C);
				and = and & X;
				or = or | X;
			}
		String ADD = Integer.toBinaryString(and);
		String OR = Integer.toBinaryString(or);
		int diffAnd = Math.abs(16 - ADD.length());
		int diffOr = Math.abs(16 - OR.length());
		StringBuffer andToLength = new StringBuffer(diffAnd);
		StringBuffer orToLength  = new StringBuffer(diffOr);
		for (int i = 0; i < diffAnd; i++){
		   andToLength.append("0");
		}
		for (int i = 0; i < diffOr; i++){
			   orToLength.append("0");
			}
		ADD = andToLength.toString() + ADD;
		OR = orToLength.toString() + OR;
		String result = "";
		for(int i = 0; i < 16; i++){
			if(ADD.charAt(i) == OR.charAt(i)){
				result = result + ADD.charAt(i);
			}
			else
			{
				result = result + "?";
			}
		}
		System.out.println(result);
		current = input.nextLine();
		}
	}

}
