package gnome_sequencing;

import java.util.Scanner;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1942047 for the problem 4571 - Gnome Sequencing has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine().trim();
		int groups = Integer.parseInt(current);
		System.out.println("Gnomes:");
		for(int i = 0; i < groups; i++){
			current = input.nextLine();
			String[] gnomes = current.split(" ");
			int g1 = Integer.parseInt(gnomes[0]);
			int g2 = Integer.parseInt(gnomes[1]);
			int g3 = Integer.parseInt(gnomes[2]);
			boolean ordered = false;
			if(g1 >= g2 && g2 >= g3) ordered = true;
			if(g1 <= g2 && g2 <= g3) ordered = true;
			if(ordered) System.out.println("Ordered");
			else System.out.println("Unordered");
		}
	}

}
