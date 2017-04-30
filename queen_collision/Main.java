package queen_collision;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1947197 for the problem 4922 - Queen Collision has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


import java.util.HashMap;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("0")){
			String[] setup = current.split(" ");
			int g = Integer.parseInt(setup[1]);
			HashMap<Integer, Integer> X = new HashMap<>();
			HashMap<Integer, Integer> Y = new HashMap<>();
			HashMap<String, Integer> M1 = new HashMap<>(); //  /
			HashMap<String, Integer> Mn1 = new HashMap<>(); // \
			for(int i = 0; i < g; i++){
				current = input.nextLine();
				String[] data = current.split(" ");
				int k = Integer.parseInt(data[0]);
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int s = Integer.parseInt(data[3]);
				int t = Integer.parseInt(data[4]);
				for(int j = 0; j < k; j++){
					int xCord = x+(j*s);
					int yCord = y+(j*t);
					String pFormula = formulaGenerator(xCord, yCord, 1);
					String nFormula = formulaGenerator(xCord, yCord, -1);
					if(!X.containsKey(xCord)) X.put(xCord, 1);
					else X.put(xCord, X.get(xCord)+1);
					if(!Y.containsKey(yCord)) Y.put(yCord, 1);
					else Y.put(yCord, Y.get(yCord)+1);
					if(!M1.containsKey(pFormula)) M1.put(pFormula, 1);
					else M1.put(pFormula, M1.get(pFormula)+1);
					if(!Mn1.containsKey(nFormula)) Mn1.put(nFormula, 1);
					else Mn1.put(nFormula, Mn1.get(nFormula)+1);
				}
			}
			int collisions = 0;
			collisions += countXYCollisions(X);
			collisions += countXYCollisions(Y);
			collisions += countDiagCollisions(M1);
			collisions += countDiagCollisions(Mn1);
			System.out.println(collisions);
			current = input.nextLine();
		}
	}
	
	private static String formulaGenerator(int x, int y, int m){
		// y1 - y = m(x1-x)
		String result = m+"x";
		x = (-1*x)*m;
		x += y;
		result += x > 0 ? "+"+x : x+"";
		return result;
	}
	
	private static int countXYCollisions(HashMap<Integer, Integer> map){
		int collisions = 0;
		for(Integer cord : map.keySet()){
			int val = map.get(cord);
			if(val > 1) collisions += val-1;
		}
		return collisions;
	}
	private static int countDiagCollisions(HashMap<String, Integer> map){
		int collisions = 0;
		for(String cord : map.keySet()){
			int val = map.get(cord);
			if(val > 1) collisions += val-1;
		}
		return collisions;
	}
}
