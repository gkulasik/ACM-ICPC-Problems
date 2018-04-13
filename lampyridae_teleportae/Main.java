package lampyridae_teleportae;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1964567 for the problem 4168 - Lampyridae Teleportae has succeeded with verdict Accepted.
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
		int counter = 1;
		while(!current.trim().equals("0 0 0")){
			String[] data = current.split(" ");
			double R = Double.parseDouble(data[0]);
			double X = Double.parseDouble(data[1]);
			double Y = Double.parseDouble(data[2]);
			current = input.nextLine();
			boolean caught = false;
			while(!current.trim().equals("-1 -1")){
				data = current.split(" ");
				double fX = Double.parseDouble(data[0]);
				double fY = Double.parseDouble(data[1]);
				double distance = calcDistance(X, Y, fX, fY);
				if(distance <= R+1) {
					caught = true;
					System.out.println("Firefly " + counter + " caught at " + "(" + (int)fX + "," + (int)fY + ")");
					while(!current.trim().equals("-1 -1")){
						current = input.nextLine();
					}
					break;
				}
				else{
					X = newX(R, distance, X, fX);
					Y = newY(R, distance, Y, fY);
				}
				current = input.nextLine();
			}	
			if(!caught) System.out.println("Firefly " + counter + " not caught");
			counter++;
			current = input.nextLine();
		}
	}
	
	private static double calcDistance(double x1, double y1, double x2, double y2){
		double xS = Math.pow(x2 - x1, 2);
		double yS = Math.pow(y2 - y1, 2);
		return Math.sqrt(xS+yS);
	}
	
	private static double newX(double r, double d, double x1, double x2){
		double rd = r/d;
		double x = x2-x1;
		double left = (rd*x) + x1;
		return left;
	}
	private static double newY(double r, double d, double y1, double y2){
		double rd = r/d;
		double y = y2-y1;
		double left = (rd*y) + y1;
		return left;
	}

}
