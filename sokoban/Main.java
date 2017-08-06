package sokoban;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1954128 for the problem 5934 - Sokoban has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


import java.util.ArrayList;
import java.util.Scanner;

class Main {
	public static char[][] map;
	public static ArrayList<Point> plusses;
	public static ArrayList<Point> boxes;
	public static Point w;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		int gameNumber = 1;
		while(!current.trim().equals("0 0")){
			String[] data = current.split(" ");
			int rows = Integer.parseInt(data[0]);
			int col = Integer.parseInt(data[1]);
			map = new char[rows][];
			plusses = new ArrayList<>();
			boxes = new ArrayList<>();
			for(int i = 0; i < rows; i++){
				current = input.nextLine();
				char[] row = current.toCharArray();
				map[i] = row;
			}
			w = find('w', 'W');
			plusses = findAll('+', 'B', 'W'); 
			boxes = findAll('b', 'B', 'N'); // third term is just a term that would never be found
			current = input.nextLine();
			char[] moves = current.toCharArray();
			for(char move : moves){
				performMove(move);
				 if(finished()) break;
			}
			boxes = findAll('b', 'B', 'N');
			boolean complete = true;
			for(int i = 0; i < plusses.size(); i++){
				if(!plusses.contains(boxes.get(i))) complete = false;
			}
			System.out.println("Game " + gameNumber +": " + (complete? "complete" : "incomplete"));
			printMap();
			current = input.nextLine();
			gameNumber++;
		}
	}
	
	private static boolean finished(){
		boxes = findAll('b', 'B', 'N');
		boolean finished = true;
		for(int i = 0; i < plusses.size(); i++){
			if(!plusses.contains(boxes.get(i))) finished = false;
		}
		return finished;
	}
	private static void printMap(){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				System.out.print(map[i][j]+"");
			}
			System.out.println();
		}
	}
	
	private static void performMove(char move){
		Point tempW = null;
		Point tempB = null;
		switch(move){
		case 'U': 
			tempW = new Point(w.x-1, w.y);
			tempB = new Point(w.x-2, w.y);
			break;
		case 'D':
			tempW = new Point(w.x+1, w.y);
			tempB = new Point(w.x+2, w.y);
			break;
		case 'L':
			tempW = new Point(w.x, w.y-1);
			tempB = new Point(w.x, w.y-2);
			break;
		case 'R':
			tempW = new Point(w.x, w.y+1);
			tempB = new Point(w.x, w.y+2);
			break;
			
		}
		if(obstacle(tempW)) return;
		if(box(tempW) && obstacle(tempB)) return;
		if(box(tempW) && !obstacle(tempB) && box(tempB)) return;
		if(box(tempW) && !obstacle(tempB) && !box(tempB)) updateMap(tempW, tempB);
		updateMap(w, tempW);
		w = tempW;
	}
	
	private static void updateMap(Point oldP, Point newP){
		char toMove = get(oldP.x, oldP.y);
		char newPos = get(newP.x, newP.y);
		map[oldP.x][oldP.y] = '.';
		map[newP.x][newP.y] = toMove;
		if(toMove == 'b' && plusses.contains(newP)){
			map[newP.x][newP.y] = 'B';
		}
		if(toMove == 'B' && !plusses.contains(newP)){
			map[newP.x][newP.y] = 'b';
		}
		if(toMove == 'w' && plusses.contains(newP)){
			map[newP.x][newP.y] = 'W';
		}
		if(toMove == 'W' && !plusses.contains(newP)){
			map[newP.x][newP.y] = 'w';
		}
		if(plusses.contains(oldP)) map[oldP.x][oldP.y] = '+';
	}
	private static boolean obstacle(Point p){
		switch(get(p))
		{
		case '#': return true;
		default: return false;
		}
	}
	private static boolean box(Point p){
		switch(get(p))
		{
		case 'b': return true;
		case 'B': return true;
		default: return false;
		}
	}
	
	private static char get(int x, int y){
		return map[x][y];
	}
	private static char get(Point p){
		return get(p.x, p.y);
	}
	
	private static Point find(char toFind1, char toFind2){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] == toFind1 || map[i][j] == toFind2) return new Point(i, j);
			}
		}
		return null;
	}
	
	private static ArrayList<Point> findAll(char toFind1, char toFind2, char toFind3){
		ArrayList<Point> found = new ArrayList<>();
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] == toFind1 || map[i][j] == toFind2 || map[i][j] == toFind3) found.add(new Point(i, j));
			}
		}
		return found;
	}

}
class Point{
	public int x;
	public int y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public String toString(){
		return "("+x+", "+y+")";
	}
	public boolean equals(Object o){
		if (this == o) return true;
	    if (!(o instanceof Point)) return false;
	    Point that = (Point) o;
	    return this.x == that.x && this.y == that.y;
	}
}
