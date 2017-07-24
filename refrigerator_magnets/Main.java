package refrigerator_magnets;



//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1953984 for the problem 5932 - Refrigerator Magnets has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


import java.util.HashMap;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		HashMap<Character, Integer> letterCount = new HashMap<>();
		while(!current.trim().equals("END")){
			char[] phrase = current.toCharArray();
			boolean canSpell = true;
			for(char c : phrase){
				if(letterCount.containsKey(c)) canSpell = false;
				else if(c != ' ') letterCount.put(c,  1);
			}
			if(canSpell) System.out.println(current);
			letterCount.clear();
			current = input.nextLine();
		}
	}

}
