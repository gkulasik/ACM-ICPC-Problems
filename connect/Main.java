package connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1962111 for the problem 3381 - Connect has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

public class Main {
	public static int bmComponent = 1;
	public static int wmComponent = -1;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("0 0")){
			String[] specs = current.split(" ");
			int N = Integer.parseInt(specs[0]);
			int M = Integer.parseInt(specs[1]);
			ArrayList<Cord> cords = new ArrayList<>();
			ArrayList<Cord> bCords = new ArrayList<>();
			ArrayList<Cord> wCords = new ArrayList<>();
			for(int i = 1; i < M*2; i+=2){
				int x = input.nextInt();
				int y = input.nextInt();
				cords.add(new Cord(x, y));
			}
			input.nextLine();
			boolean black = true;
			// creates a list of all the black points and the white points
			for(Cord c : cords){
				if(black) bCords.add(c);
				else wCords.add(c);
				black = black ? false : true;
			}
			ArrayList<Seg> allSeg = new ArrayList<>();
			HashMap<Integer, ArrayList<Cord>> bComponents = new HashMap<>();
			HashMap<Integer, ArrayList<Cord>> wComponents = new HashMap<>();
			for(int T = 0; T < bCords.size(); T++){
				for(int i = 0; i <= T; i++){
					for(int j = 0; j <= i; j++){
						Cord one = bCords.get(i);
						Cord two = bCords.get(j);
						if(one != two && nearby(one, two) && !blocked(one, two, allSeg)){
							connect(true, one, two, allSeg, bComponents);
						}
						if(T != wCords.size()){
							one = wCords.get(i);
							two = wCords.get(j);
							if(one != two && nearby(one, two) && !blocked(one, two, allSeg)){
								connect(false, one, two, allSeg, wComponents);
							}
						}
					}
				}
			}
			System.out.println(complete(bComponents, 0, N) ? "yes" : "no");
			current = input.nextLine();
		}
	}

	private static boolean complete(HashMap<Integer, ArrayList<Cord>> cords, int beginX, int endX){
		for(ArrayList<Cord> cs : cords.values()){
			// Check if x == 0 and x == N are present
			boolean hasBegin = false;
			boolean hasEnd = false;
			for(Cord c : cs){
				if(c.x == beginX) hasBegin = true;
				if(c.x == endX) hasEnd = true;
			}
			if(hasBegin && hasEnd) return true;
			hasBegin = false;
			hasEnd = false;
		}
		return false;
		
	}
	// check if cord next is a knights move from cord prev
	private static boolean nearby(Cord prev, Cord next){
		int dX = Math.abs(prev.x - next.x);
		int dY = Math.abs(prev.y - next.y);
		if(dX == 1 && dY == 2) return true;
		if(dX == 2 && dY == 1) return true;
		return false;
	}
	
	private static boolean blocked(Cord one, Cord two, ArrayList<Seg> segs){
		Seg n = new Seg(one, two);
		for(Seg s : segs){
			if(intersects(n, s)){
				return true;
			}
		}
		return false;
	}
	// Compute cross product to determine if segments intersect
	 private static boolean intersects(Seg one, Seg two){
		 // one.A = A, one.B = B
		 // two.A = C, two.B = D
		 Cord A = one.A;
		 Cord B = one.B;
		 Cord C = two.A;
		 Cord D = two.B;
		 if((cross(A, C, D) * cross(B, C, D)) < 0 &&  (cross(C, A, B) * cross(D, A, B)) < 0)
			return true;
		 else
			return false;
	 }
	 
	 private static int cross(Cord one, Cord two, Cord three){
		 return ((one.x - two.x)*(two.y - three.y)) - ((one.y - two.y)*(two.x - three.x));
	 }
	// Connect two cords - factor in if a cord is already part of a connected component
	private static void connect(boolean blacks, Cord one, Cord two, ArrayList<Seg> segs, HashMap<Integer, ArrayList<Cord>> components){
		int mComponent = blacks? bmComponent : wmComponent;
		if((one.component == 1 && two.component == 2) || (one.component == 2 && two.component == 1 )){
			@SuppressWarnings("unused")
			int test = 100;
		}
		if(one.component == two.component && one.component == 0){
			one.component = mComponent;
			two.component = mComponent;
			Seg s = new Seg(one, two);
			segs.add(s);
			components.put(mComponent, new ArrayList<Cord>());
			components.get(mComponent).add(one);
			components.get(mComponent).add(two);
			if(blacks) bmComponent = ++mComponent;
			else wmComponent = --mComponent;
			return;
		}
		if(one.component == two.component && one.component != 0) return;
		if(one.component != 0 && two.component == 0){
			two.component = one.component;
			Seg s = new Seg(one, two);
			segs.add(s);
			components.get(one.component).add(two);
			return;
		}
		if(one.component == 0 && two.component != 0){
			one.component = two.component;
			Seg s = new Seg(one, two);
			segs.add(s);
			components.get(two.component).add(one);
			return;
		}
		if(one.component != 0 && two.component != 0){
			int changed = Integer.MAX_VALUE;
			int winner = Integer.MAX_VALUE;
			if(one.component <= two.component){
				changed = two.component;
				winner = one.component;
				two.component = one.component;
			}
			else{
				changed = one.component;
				winner = two.component;
				one.component = two.component;
			}
			// Propagate (join connected components)
			if(winner != changed){
				Seg s = new Seg(one, two);
				segs.add(s);
				ArrayList<Cord> w = components.get(winner);
				ArrayList<Cord> l = components.get(changed);
				for(Cord c : l){
					c.component = winner;
				}
				w.addAll(l);
				components.remove(changed);
			}
			else{
				System.out.println("Error - no winner/loser p1 and 2: " + one + " " + two);
			}
			return;
		}
	}

}

// objects to hold data
class Seg{
	public Cord A, B;
	
	Seg(Cord A, Cord B){
		this.A = A;
		this.B = B;
	}
}

class Cord{
	public int component;
	public int x, y;
	Cord(int x, int y){
		this.x = x;
		this.y = y;
		this.component = 0; // 0 = not assigned, positive = black component, negative = white component
	}
	@Override
	public String toString(){
		return "("+x+", "+y+")"; //  for nicer debugging print outs
	}
}
