package here_we_go_gorillians_again;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


//
//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1959777 for the problem 3850 - Here We Go(relians) Again has succeeded with verdict Accepted.
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
		while(!current.trim().equals("0 0")){
			String[] dimensions = current.split(" ");
			int vertical = Integer.parseInt(dimensions[0]), horizontal = Integer.parseInt(dimensions[1]);
			Point[][] points = new Point[vertical+1][horizontal+1];
			initPoints(points);
			Graph map = new Graph(points);
			int lines = (vertical*2)+1;
			Orientation orient = Orientation.Horizontal;
			for(int i = 0; i < lines; i++){
				current = input.nextLine();
				current = current.replaceAll("\\s","");
				if(orient == Orientation.Horizontal){
					for(int h = 0; h < (horizontal*2); h++){
						 int startI = i/2;
						 int endH = h/2;
						 int speed = Integer.parseInt(""+current.charAt(h));
						 h++;
						 String direction = ""+current.charAt(h);
						 switch(direction){
						 case "*": 
							 // add both ways
							 map.addEdge(points[startI][endH], points[startI][endH+1], speed);
							 map.addEdge(points[startI][endH+1], points[startI][endH], speed);
							 break;
						 case "<":
							 // add <-
							 map.addEdge(points[startI][endH+1], points[startI][endH], speed);
							 break;
						 case ">":
							 // add ->
							 map.addEdge(points[startI][endH], points[startI][endH+1], speed);
							 break;
						 default: // do nothing
						 }
						 
					 }
				}
				else{ // vertical
					 for(int v = 0; v < ((horizontal+1)*2); v++){
						 int startI = i/2;
						 int endV = v/2; 
						 int speed = Integer.parseInt(""+current.charAt(v));
						 v++;
						 String direction = ""+current.charAt(v);
						 switch(direction){
						 case "*": 
							 // add both ways
							 map.addEdge(points[startI][endV], points[startI+1][endV], speed); // down
							 map.addEdge(points[startI+1][endV], points[startI][endV], speed); // up
							 break;
						 case "^":
							 // add ^
							 //     |
							 map.addEdge(points[startI+1][endV], points[startI][endV], speed);
							 break;
						 case "v":
							 // add |
							 //     v
							 map.addEdge(points[startI][endV], points[startI+1][endV], speed);
							 break;
						 default: // do nothing
						 }
					 }
				}
				orient = orient == Orientation.Horizontal? Orientation.Vertical : Orientation.Horizontal;
			}
			map.shortestPath2(points[0][0]);
			Point end = points[vertical][horizontal];
			if(end.time == 0){
				System.out.println("Holiday");
			}
			else{
				System.out.println(map.timeForShortestPath2() + " blips");
			}
			current = input.nextLine();
		}
	}

	private static void initPoints(Point[][] points){
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[0].length; j++){
				points[i][j] = new Point();
			}
		}
	}
}

class Graph{
	
	public Point[][]  points;
	public int totalSize;
	public int time;
	
	public Graph(Point[][] points){
		this.points = points;
		totalSize = points.length * points[0].length;
		time = 0;
	}
	public void addEdge(Point start, Point end, int speed){
		start.points.add(new Edge(end, speed));
	}
	
	// OLD method - rewritten below
	public void shortestPath(Point start){
		PriorityQueue<Route> q = new PriorityQueue<>();
		resetAll();
		q.add(new Route(start, 0));
		int seen = 0;
		while(!q.isEmpty() && seen < totalSize){
			Route min = q.remove();
			Point current = min.point;
			if(current.visited)
				continue;
			current.visited = true;
			seen++;
			
			for(Edge e : current.points){
				if(e.speed != 0 && current.previous != e.other && !e.other.visited && e.other.speed < current.speed+e.speed){
					e.other.speed = current.speed+e.speed;
					e.other.time = (2520/e.speed);
					e.other.previous = current;
					q.add(new Route(e.other, e.other.speed));
				}
			}
		}
		
	}
	public void shortestPath2(Point start){
		PriorityQueue<Point> q = new PriorityQueue<>();
		resetAll();
		q.add(start);
		int seen1 = 0;
		while(!q.isEmpty()){
			Point current = q.remove();
			current.visited = true;
			seen1++;
			
			for(Edge e : current.points){
				if(e.speed != 0){
					if(e.other.time == 0){
						e.other.time = (2520/e.speed) + current.time;
						q.add(e.other);
					}
					else{
						if(e.other.time > ((2520/e.speed) + current.time)){
							e.other.time = (2520/e.speed) + current.time;
							q.add(e.other);
						}
					}
						
					
				}
			}
		}
		
	}
	
	public int timeForShortestPath2(){
		int vertical = points.length;
		int horizontal = points[0].length;
		Point end = points[vertical-1][horizontal-1];
		return end.time;
	}
	
	// OLD method - rewritten above - tried to backtrack and build the path
	public int timeForShortestPath(){
		int vertical = points.length;
		int horizontal = points[0].length;
		Point end = points[vertical-1][horizontal-1];
		Point marker = end;
		int totalTime = 0;
		while(marker != null){
			totalTime += marker.time;
			marker = marker.previous;
		}
		return totalTime;
	}
	
	public void resetAll(){
		for (int i = 0; i < points.length; i++){
		     for (int j = 0; j < points[0].length; j++){
		    	 points[i][j].reset();
		     }
	     }
	}
}

class Point implements Comparable{
	public ArrayList<Edge> points;
	public Point previous;
	public int speed;
	public int time;
	public boolean visited;
	
	Point(){
		points = new ArrayList<>();
		visited = false;
		speed = 0;
		time = 0;
	}
	
	public void reset(){
		previous = null;
		visited = false;
		speed = 0;
		time = 0;
	}
	public int compareTo(Object other){
		if(other == null) return 1;
		if(!(other instanceof Point)) return 1;
		Point that = (Point) other;
		if(that.time > this.time) return -1;
		if(that.time < this.time) return 1;
		return 0;
	}
}

class Edge{
	public int speed;
	public Point other;
	
	Edge(Point other, int speed){
		this.speed = speed;
		this.other = other;
	}
	
	public boolean equals(Object other){
		if(other == null) return false;
		if(!(other instanceof Edge)) return false;
		Edge that = (Edge) other;
		if(that.other != this.other) return false;
		if(that.speed != this.speed) return false;
		return true;
	}
	
}
// NOT USED IN NEW IMPLEMENTATION
class Route implements Comparable{
	public Point point;
	public int speed;
	
	public Route(Point point, int speed){
		this.point = point;
		this.speed = speed;
	}
	
	public int compareTo(Object other){
		if(other == null) return 1;
		if(!(other instanceof Edge)) return 1;
		Route that = (Route) other;
		if(that.speed > this.speed) return -1;
		if(that.speed < this.speed) return 1;
		return 0;
	}
}

enum Orientation {
	Vertical, Horizontal
}
