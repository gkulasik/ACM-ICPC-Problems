package black_vienna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1942646 for the problem 4573 - Black Vienna has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("0")){
			int turns = Integer.parseInt(current.trim());
			ArrayList<Player> players = new ArrayList<>();
			current = input.nextLine();
			String[] deal = current.split(" ");
			players.add(new Player(1, deal[0].toCharArray()));
			players.add(new Player(2, deal[1].toCharArray()));
			players.add(new Player(3, deal[2].toCharArray()));
			String gang = deal[3];
			boolean knowsGang = false;
			int turnKnown = -1;
			current = input.nextLine();
			 turns: for(int i = 0; i < turns; i++){
				String[] draw = current.split(" ");
				for(Player p : players){
					p.considerData(Integer.parseInt(draw[0]), draw[1].toCharArray(), Integer.parseInt(draw[2]));
					if(p.knowsWinner()){
						turnKnown = i+1;
						knowsGang = true;
						for(int j = i; j < turns; j++)
							current = input.nextLine(); // finish reading lines
						break turns;
					}
				}
				current = input.nextLine();
			}
			if(knowsGang) System.out.println(turnKnown);
			else System.out.println("?");
		}

	}

}

class Player{
	public HashMap<Character, Confidence> guess;
	public ArrayList<HashMap<Character, Confidence>> players;
	public int number;
	public ArrayList<Entry> revisit;
	
	Player(int number, char[] cards){
		this.number = number;
		guess = new HashMap<>();
		players = new ArrayList<>();
		revisit = new ArrayList<>();
		players.add(new HashMap<Character, Confidence>()); // p1
		players.add(new HashMap<Character, Confidence>()); // p2
		for(int i = 0; i < players.size()+1; i++){ // populates data
			if(i == players.size()){
				populate(guess);
			}
			else{
				populate(players.get(i));
			}
		}
		for(char ch : cards){ //  mark the cards the player has
			for(int i = 0; i < players.size()+1; i++){
				if(i == players.size()){
					guess.put(ch, Confidence.NotPossible);
				}
				else{
					players.get(i).put(ch, Confidence.NotPossible);
				}
			}
		}
	}
	
	private void populate(HashMap<Character, Confidence> toPop){
		for(int i = 0; i < 18; i++){
			toPop.put((char) ('A' + i), Confidence.Possible); // A-R
		}
	}
	
	public void considerData(int playerNumber, char[] interrogate, int result){ // playerNumber = converts based on player number.  
		if(playerNumber == number) return;
		HashMap<Character, Confidence> player = getPlayer(playerNumber);
		// easy cases
		if(result == 0){
			for(char ch : interrogate){
				player.put(ch, Confidence.NotPossible);
			}
			return;
		}
		if(result == 3){
			for(char ch : interrogate){
				player.put(ch, Confidence.Definite);
			}
			return;
		}
		boolean metCase = false;
		// tricky cases
		Character[] unknown = removeKnown(interrogate);
		Character[] definite = definiteKnown(playerNumber, interrogate);
		if(result == 1 && unknown.length == 1){
			player.put(unknown[0], Confidence.Definite);
			metCase = true;
		}
		if(result == 1 &&  definite.length == 1){
			for(char ch : interrogate){
				if(definite[0] != ch) player.put(ch, Confidence.NotPossible);
			}
			metCase = true;
		}
		if(result == 2 && unknown.length == 2){
			player.put(unknown[0], Confidence.Definite);
			player.put(unknown[1], Confidence.Definite);
			metCase = true;
		}
		if(result == 2 && definite.length == 2){
			for(char ch : interrogate){
				if(definite[0] != ch && definite[1] != ch) player.put(ch, Confidence.NotPossible);
			}
			metCase = true;
		}
		if(!metCase)
			// if no criteria met - revisit later
			revisit.add(new Entry(playerNumber, interrogate, result));
	}
	
	public boolean knowsWinner(){
		Entry[] toRevisit = revisit.toArray(new Entry[revisit.size()]);
		filterRevisits();
		for(Entry e : toRevisit){ // revisit unknown data first
			revisit.remove(e);
			considerData(e.playerNumber, e.interrogation, e.result);
		}
		mergeGuesses();
		ArrayList<Character> gang = new ArrayList<>();
		int notPossible = 0;
		for(Character ch : guess.keySet()){
			if(guess.get(ch) == Confidence.NotPossible) notPossible++;
			if(guess.get(ch) == Confidence.Definite) gang.add(ch);
		}
		if(gang.size() == 3) return true;
		if(notPossible == 15) return true;
		else return false;
	}
	
	private void filterRevisits(){
		HashMap<Character, Integer> instanceCount = new HashMap<>();
		if(revisit.size() > 3){
			for(Entry e : revisit){
				for(Character ch : e.interrogation){
					if(instanceCount.containsKey(ch))
						instanceCount.put(ch, instanceCount.get(ch)+1);
					else
						instanceCount.put(ch, 1);
				}
			}
			for(Character ch : instanceCount.keySet()){
				if(instanceCount.get(ch) == revisit.size()){
					players.get(0).put(ch, Confidence.NotPossible);
					players.get(1).put(ch,  Confidence.NotPossible);
				}
			}
		}
	}
	
	private void mergeGuesses(){
		HashMap<Character, Confidence> p1 = players.get(0);
		HashMap<Character, Confidence> p2 = players.get(1);
		for(Character ch : p1.keySet()){ 
			if(p1.get(ch) == Confidence.Definite) p2.put(ch, Confidence.NotPossible);
			if(p2.get(ch) == Confidence.Definite) p1.put(ch,  Confidence.NotPossible);
		}
		fillInAssumptions(p1);
		fillInAssumptions(p2);
		for(Character ch : guess.keySet()){
			if(p1.get(ch) == Confidence.Definite || p2.get(ch) == Confidence.Definite) guess.put(ch, Confidence.NotPossible);
			if(p1.get(ch) == Confidence.NotPossible &&
					p2.get(ch) == Confidence.NotPossible &&
					guess.get(ch) == Confidence.Possible) guess.put(ch, Confidence.Definite);
			
		}
	}
	
	
	private void fillInAssumptions(HashMap<Character, Confidence> player){
		int possible = 0;
		ArrayList<Character> possibles = new ArrayList<>();
		int definite = 0;
		for(Character ch : player.keySet()){
			if(player.get(ch) == Confidence.Possible){
				possible++;
				possibles.add(ch);
			}
			if(player.get(ch) == Confidence.Definite) definite++;
		}
		if(definite + possible == 5){
			for(Character ch : possibles){
				player.put(ch, Confidence.Definite);
			}
		}
	}
	
	private Character[] definiteKnown(int playerNumber, char[] card) {
		HashMap<Character, Confidence> player = getPlayer(playerNumber);
		ArrayList<Character> definite = new ArrayList<>();
		for(char ch : card){
			if(player.get(ch) == Confidence.Definite) definite.add(ch);
		}
		return definite.toArray(new Character[definite.size()]);
	}

	private Character[] removeKnown(char[] card){
		ArrayList<Character> removedKnown = new ArrayList<>();
		HashMap<Character, Confidence> p1 = players.get(0);
		HashMap<Character, Confidence> p2 = players.get(1);
		for(char ch : card){
			boolean known = false;
			Confidence ourGuess = guess.get(ch);
			Confidence p1Guess= p1.get(ch);
			Confidence p2Guess = p2.get(ch);
			if(ourGuess == Confidence.NotPossible || ourGuess == Confidence.Definite) known = true;
			if(p1Guess == Confidence.Definite || p2Guess == Confidence.Definite) known = true;
			if(!known){
				removedKnown.add(ch);
			}
		}
		return removedKnown.toArray(new Character[removedKnown.size()]);
	}
	
	private HashMap<Character, Confidence> getPlayer(int playerNumber){
		if(number == 1){
			switch(playerNumber){
			case 2: return players.get(0);
			case 3: return players.get(1);
			default: return null;
			}
		}
		if(number == 2){
			switch(playerNumber){
			case 1: return players.get(0);
			case 3: return players.get(1);
			default: return null;
			}
		}
		if(number == 3){
			switch(playerNumber){
			case 1: return players.get(0);
			case 2: return players.get(1);
			default: return null;
			}
		}
		return null;
	}
}

enum Confidence{
	Possible, NotPossible, Definite;
}

class Entry{
	public int playerNumber;
	public char[] interrogation;
	public int result;
	
	Entry(int playerNumber, char[] interrogation, int result){
		this.playerNumber = playerNumber;
		this.interrogation = interrogation;
		this.result = result;
	}
}
