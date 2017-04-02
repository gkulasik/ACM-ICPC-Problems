package mirror_mirror_on_the_wall;




//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1946205 for the problem 4923 - Mirror, Mirror on the Wall has succeeded with verdict Accepted.
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
		currentWord: while(!current.trim().equals("#")){
			char[] word = current.toCharArray();
			boolean skip = false;
			exchange: for(int i = 0; i < word.length; i++){
				switch(word[i]){
				case 'b': word[i] = 'd'; break;
				case 'd': word[i] = 'b'; break;
				case 'p': word[i] = 'q'; break;
				case 'q': word[i] = 'p'; break;
				case 'i':
				case 'o':
				case 'v':
				case 'w':
				case 'x':
					break;
				default: 
						System.out.println("INVALID");
						skip = true;
						break exchange;
				}
			}
			if(!skip){
				char[] newWord = word.clone();
				int length = newWord.length;
				for(int i = 0; i < word.length; i++){
					newWord[length-1-i] = word[i];
				}
				System.out.println(String.copyValueOf(newWord));	
			}
			current = input.nextLine();
		}
	}
}
