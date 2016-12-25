package seven_percent_solution;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1936237 for the problem 3847 - The Seven Percent Solution has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Queue<Encode> encodings = new LinkedList<Encode>();
		encodings.add(new Encode("%", "%25"));
		encodings.add(new Encode(" ", "%20"));
		encodings.add(new Encode("!", "%21"));
		encodings.add(new Encode("$", "%24"));
		encodings.add(new Encode("(", "%28"));
		encodings.add(new Encode(")", "%29")); 
		encodings.add(new Encode("*",  "%2a"));
		String current = input.nextLine();
		while(!current.trim().equals("#")){
			String fixed = current;
			for(Encode encode : encodings){
				 fixed = fixed.replace(encode.character, encode.encoding);
			}
			System.out.println(fixed);
			current = input.nextLine();
		}

	}

}
class Encode {
	public String character;
	public String encoding;
	
	Encode(String character, String encoding){
		this.character = character;
		this.encoding = encoding;
	}
}