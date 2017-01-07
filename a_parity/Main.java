package a_parity;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1939984 for the problem 4167 - Parity has succeeded with verdict Accepted.
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
			int ones = 0;
			boolean even = false;
			for(char c : current.toCharArray()){
				if(c == '1'){
					ones++;
				}
				if(c == 'e'){
					even = true;	
				}
			}
			char toReplace = 'x', newChar = 'y';
			if(even){
				if(ones%2 == 0){
					toReplace = 'e';
					newChar = '0';
				}
				else
				{
					toReplace = 'e';
					newChar = '1';
				}
			}
			else
			{
				if(ones%2 == 0){
					toReplace = 'o';
					newChar = '1';
				}
				else
				{
					toReplace = 'o';
					newChar = '0';
				}
			}
			current = current.replace(toReplace, newChar);
			System.out.println(current);
			
			current = input.nextLine();
		}

	}

}
