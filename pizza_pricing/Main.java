package pizza_pricing;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1953983 for the problem 5930 - Pizza Pricing has succeeded with verdict Accepted.
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
		int menuCount = 1;
		while(!current.trim().equals("0")){
			int menuSize = Integer.parseInt(current);
			int bestDiam = -1;
			double bestValue = -1;
			for(int i = 0; i < menuSize; i++){
				current = input.nextLine();
				String[] data = current.split(" ");
				double diameter = Double.parseDouble(data[0]);
				int price = Integer.parseInt(data[1]);
				double value = value(diameter/2, price);
				if(value > bestValue){
					bestValue = value;
					bestDiam = (int)diameter;
				}
			}
			System.out.println("Menu " + menuCount + ": " + bestDiam);
			menuCount++;
			current = input.nextLine();
		}
	}
	
	private static double value(double r, int price){
		//πr2
		return (3.14*r*r)/price;
	}

}
