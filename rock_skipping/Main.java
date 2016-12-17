package rock_skipping;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1936287 for the problem 3853 - Rock Skipping has succeeded with verdict Accepted.
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
		String lake = input.nextLine();
		int[] results = {0,1,0,0,0,0}; // start 
		while(!lake.trim().equals("END")){
			int lakeLength = lake.length();
			for(int i = 0; i < lakeLength; i++){
				for(int d = 1; d < lakeLength; d++){
					int count = 0, length = i,  init_position = i, dist_between = d;
					sim: for(int j = i; true; j += d){
						if(j >= lakeLength || lake.charAt(j) != '.')
						{
							// fails or over the lake so record and check who is winner so far
							int[] contender = {i, d, count, length, init_position, dist_between};
							results = winner(results, contender);
							break sim;
						}
						else{
							count++;
							if(length+d < lakeLength){
								length += d;
							}
						}
					}
				}
			}
			System.out.println(results[0] + " " + results[1]);
			results = new int[6];
			lake = input.nextLine();
		}

	}
	
	private static int[] winner(int[] current, int[] contender){
		if(current[2] < contender[2]) return contender;
		if(current[2] > contender[2]) return current;
		if(current[3] < contender[3]) return contender;
		if(current[3] > contender[3]) return current;
		if(current[4] < contender[4]) return contender;
		if(current[4] > contender[4]) return current;
		if(current[5] < contender[5]) return current;
		if(current[5] > contender[5]) return contender;
		return current;
	}

}
