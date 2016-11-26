package suprising_strings;

// 3596 - passed
//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1934049 for the problem 3596 - Surprising Strings has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
public static ArrayList<String> entries = new ArrayList<>();
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String currentRaw = input.nextLine();
		String current = currentRaw.trim();
		while(!current.equals("*")){
			boolean is = true;
			int n = current.length();
			top: for(int i = 0; i < n; i++){
				int start = 0;
				int end = start + i + 1;
				for(int j = i; j < n; j++){
					try{
					entries.add(""+current.charAt(start)+current.charAt(end));
					start++;
					end++;
					}
					catch(Exception e){
						continue;
					}
					
				}
				if(!isUnique()){
					System.out.println(currentRaw + " is NOT surprising.");
					is = false;
					break top;
				}
				entries.clear();
			}
			if(is)
				System.out.println(currentRaw + " is surprising.");
			entries.clear();
			
			currentRaw = input.nextLine();
			current = currentRaw.trim();
		}
		System.exit(0);
	}
	
	private static boolean isUnique()
	{ 
	  HashSet<String> checkSet = new HashSet<>(); 

	  for (String entry : entries)
	  {
	   if (!checkSet.add(entry))
	   {
	    return false;
	   }
	  }
	  return true;
	}

}
