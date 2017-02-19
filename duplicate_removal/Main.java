package duplicate_removal;

import java.util.ArrayList;
import java.util.Scanner;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1942059 for the problem 4574 - Duplicate Removal has succeeded with verdict Accepted.
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
			String[] nGuesses = current.split(" ");
			int n = Integer.parseInt(nGuesses[0]);
			ArrayList<Integer> noDups = new ArrayList<>();
			Integer prev = null;
			for(int i = 1; i <= n; i++){
				Integer toAdd = Integer.parseInt(nGuesses[i]);
				if(i != 1){
					if(prev != toAdd){
						noDups.add(toAdd);
					}
					prev = toAdd;
				}
				else{
					prev = toAdd;
					noDups.add(toAdd);
				}
			}
			for(Integer guess : noDups){
				System.out.print(guess + " ");
			}
			System.out.print("$");
			System.out.println();
			current = input.nextLine();
		}
	}
}
