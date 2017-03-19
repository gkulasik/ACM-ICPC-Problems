package mad_scientist;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1946218 for the problem 4920 - Mad Scientist has succeeded with verdict Accepted.
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
		while(!current.trim().equals("0")){
			String output = "";
			String[] data = current.split(" ");
			int nums = Integer.parseInt(data[0]);
			int currentNum = 1;
			int lastCount = 0;
			int newCount = 0;
			for(int i = 0; i < nums; i++){
				newCount = Integer.parseInt(data[i+1]);
				if(newCount > lastCount){
					for(int j = 0; j < newCount - lastCount; j++){
						output += currentNum + " ";
					}
				}
				lastCount = newCount;
				currentNum++;
			}
			System.out.println(output.substring(0, output.length()-1));
			current = input.nextLine();
		}

	}
}
