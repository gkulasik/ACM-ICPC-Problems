package cell_towers;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1964630 for the problem 4577 - Cell Towers has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


import java.util.ArrayList;
import java.util.Scanner;

class Main {

	public static boolean switched = false;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("0")){
			String result = "";
			String[] data = current.split(" ");
			int T = Integer.parseInt(data[0]);
			int R = Integer.parseInt(data[1]);
			ArrayList<Tower> towers = new ArrayList<>();
			ArrayList<Point> road = new ArrayList<>();
			ArrayList<Point> miles = new ArrayList<>();
			for(int i = 0; i < T; i++){
				current = input.nextLine();
				towers.add(new Tower((char) ('A' + i), current));
			}
			int temp = 2*(R+1);
			for(int i = 0; i < temp; i+=2){
				road.add(new Point(input.nextInt(), input.nextInt()));
			}
			miles = findAllMiles(road);
			Tower bestTower = null;
			int startMile = 0;
			for(Point mile : miles){
				Tower best = null;
				int bestP = -1;
				for(Tower tower : towers){
					int p = towerPower(tower, mile);
					if(p > bestP){
						bestP = p;
						best = tower;
					}
				}
				if(bestTower != best){
					bestTower = best;
					result += "("+startMile+","+bestTower.label+") ";
				}
				startMile++;
			}
			System.out.println(result.substring(0, result.length()-1));
			input.nextLine();
			current = input.nextLine();
			
		}
	}
	
	private static int towerPower(Tower t, Point p){
		double d = distance(new Point(t.x, t.y), p);
		return (int)(t.power/(Math.pow(d, 2)));
	}
	
	private static ArrayList<Point> findAllMiles(ArrayList<Point> road){
		int size = road.size();
		ArrayList<Point> miles = new ArrayList<>();
		miles.add(road.get(0));
		boolean done = false;
		int i = 1, j = 2;
		while(!done){
			Point mile = findNextMile(miles.get(miles.size()-1), road.get(i), j >= size ? null : road.get(j));
			if(mile != null) miles.add(mile);
			if(useNextMarker(mile, road.get(i))){
				i++;
				j++;
			}
			if(i >= size){
				done = true;
			}
		}
		return miles;
	}
	
	private static boolean useNextMarker(Point newMile, Point roadMarker){
		if(newMile == null || roadMarker == null) return true;
		double d = distance(newMile, roadMarker);
		boolean result = false;
		if(switched) result = true;
		else result = false;
		switched = false;
		return result;
	}
	private static Point findNextMile(Point lastMile, Point roadMarker, Point nextRoadMarker){
		double d = distance(lastMile, roadMarker);
		Point newMile = null;
		if(d >= 1){
			newMile = new Point(newX(1, d, lastMile.x, roadMarker.x), newY(1, d, lastMile.y, roadMarker.y));
		}
		else{
			if(nextRoadMarker != null){
				double residual = 1 - d;
				double newDistance = distance(roadMarker, nextRoadMarker);
				newMile = new Point(newX(residual, newDistance, roadMarker.x, nextRoadMarker.x), newY(residual, newDistance, roadMarker.y, nextRoadMarker.y));
				switched = true;
			}
			else{
				if(d >= 0.50){
					newMile = roadMarker;
				}
			}
		}
		return newMile;
	}
	private static double distance(Point one, Point two){
		double x1 = one.x;
		double x2 = two.x;
		double y1 = one.y;
		double y2 = two.y;
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

class Tower{
	public int x, y, power;
	char label;
	
	Tower(char label, int x, int y, int power){
		this.label = label;
		this.x = x;
		this.y = y;
		this.power = power;
	}
	Tower(char label, String input){
		String[] data = input.split(" ");
		this.label = label;
		this.x = Integer.parseInt(data[0]);
		this.y = Integer.parseInt(data[1]);
		this.power = Integer.parseInt(data[2]);
	}
}

class Point{
	double x, y;
	Point(double x, double y){
		this.x = x;
		this.y = y;
	}
}

