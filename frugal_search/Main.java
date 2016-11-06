package frugal_search;

//3598 - passed
//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1934035 for the problem 3598 - Frugal Search has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Main {
	static ArrayList<String> dictionary = new ArrayList<>();
	static ArrayList<String> queries = new ArrayList<>();
	static String current = "";
	static boolean dictionary_stage = true;
	static boolean query_stage = false;
	static boolean process = true;
	static boolean done = false;
	static Scanner input = new Scanner(System.in);

	
	
	public static void main(String[] args) {
		while(!done){
			populateTerms();
			sortDictionary();
			for(String query : queries){
				System.out.println(findTerm(query));
			}
			if(!done)
			System.out.println("$");
			dictionary.clear();
			queries.clear();
		}
	}
	
	private static String findTerm(String query){
		for(String word : dictionary){
			Query q = new Query(query);
			if(q.matches(word))
				return word;
		}
		return "NONE";
	}
	
	private static void sortDictionary(){
		Collections.sort(dictionary);	
	}
	
	private static void populateTerms(){
		dictionary_stage = true;
		query_stage = false;
		current = input.nextLine().trim();
		while(!current.equals("**")){
			switch(current){
			case "*": query_stage = true; dictionary_stage = false; process = false; break;
			case "**": dictionary_stage = true; query_stage = false; process = false; break;
			case "#": process = false; done = true; return;
			default:
				process = true;
			}
			if(dictionary_stage && process){
				dictionary.add(current);
			}
			if(query_stage && process){
				queries.add(current);
			}
			current = input.nextLine().trim();
		}
	}

}
class Query{
	public ArrayList<String> components;
	Query(String query){
		components = new ArrayList<String>(Arrays.asList(query.split("\\|")));
	}
	public boolean matches(String term){
		boolean match = false;
		top: for(String component : components){
			int length = component.length();
			counter: for(int i = 0; i < length; i++){
				switch(component.charAt(i)){
				case '+':
					i++;
					if(!term.contains(""+component.charAt(i))){
						match = false;
						continue top;
					}
					break;
				case '-':
					i++;
					if(term.contains(""+component.charAt(i))){
						match = false;
						continue top;
					}
					break;
				default:
					if(term.contains(""+component.charAt(i))){
						match = true;
					}
				}
			}
		}
	return match;
	}
}
