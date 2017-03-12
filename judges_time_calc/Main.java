package judges_time_calc;




//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1946204 for the problem 4919 - Judges' Time Calculation has succeeded with verdict Accepted.
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
		int cases = Integer.parseInt(current);
		for(int i = 0; i < cases; i++){
			int offset = 60;
			int hours = 0;
			current = input.nextLine();
			String[] data =  current.split(" ");
			int startHour = Integer.parseInt(data[0]);
			int startMinute = Integer.parseInt(data[1]);
			offset -= startMinute;
			int durationHour = Integer.parseInt(data[2]);
			int durationMinute = Integer.parseInt(data[3]);
			hours = durationHour;
			System.out.println("------+---------");
			System.out.println(" time | elapsed");
			System.out.println("------+---------");
			String sHour = startHour < 10 ? " "+startHour : startHour+"";
			System.out.print(sHour + ":XX | XX");
			if(startMinute > 0) System.out.print(" - " + startMinute);
			System.out.println();
			if(startMinute + durationMinute > 59) hours++; 
			int hourCounter = startHour+1;
			for(int j = 0; j < hours; j++){
				if(hourCounter >= 13) hourCounter = 1;
				String hour = hourCounter < 10 ? " " + hourCounter : hourCounter+"";
				System.out.println(hour + ":XX | XX + " + (offset+(j*60)));
				hourCounter++;
			}
		}
	}
}
