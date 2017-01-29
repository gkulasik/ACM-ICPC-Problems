package h_steganography;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1939983 for the problem 4174 - Steganography has succeeded with verdict Accepted.
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
			String holder = "";
			int counter = 0;
			while(!current.trim().equals("*")){
				for(char c : current.toCharArray()){
					if(c == ' '){
						counter++;
					}
					else{
						if(counter > 0){
							if(counter%2 == 0){
								holder += "1";
							}
							else{
								holder += "0";
							}
						}
						counter = 0;
					}
				}
				current = input.nextLine();
			}
			String toDecode = "";
			for(int i = 0; i < holder.length(); i++){
				if(toDecode.length() < 5)
					toDecode += ""+holder.charAt(i);
				else{
					System.out.print(convertCode(toDecode));
					toDecode = ""+holder.charAt(i);
				}
			}
			if(!toDecode.isEmpty()){
				if(toDecode.length() < 5){
					int toAdd = toDecode.length();
					for(int i = 0; i < (5 - toAdd); i++){
						toDecode += "0";
					}
				}
				System.out.print(convertCode(toDecode));
			}
			current = input.nextLine();
			System.out.println();
		}
	}
	
	private static String convertCode(String code){
		int value = Integer.parseInt(code,  2);
		switch(value){
		case 0: return " ";
		case 27: return "'";
		case 28: return ",";
		case 29: return "-";
		case 30: return ".";
		case 31: return "?";
		default: 
			if(value > 0 && value < 27){
				char letter = 'A';
				return  ((char) (letter + value - 1)) + "";
			}
			return ""; // return nothing otherwise
		}
	}

}
