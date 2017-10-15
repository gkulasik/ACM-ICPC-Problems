package go_go_gorelians;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1959785 for the problem 3599 - Go Go Gorelians has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("0")){
			int N = Integer.parseInt(current.trim());
			ArrayList<Planet> planets = new ArrayList<>();
			for(int i = 0; i < N; i++){
				current = input.nextLine();
				Planet newPlanet = createPlanet(current);
				findClosestAndConnect(planets, newPlanet);
				planets.add(newPlanet);
			}
			trimMap(planets);
			if(planets.size() == 2) {
				if(planets.get(0).id < planets.get(1).id)
					System.out.println(planets.get(0).id + " " + planets.get(1).id);
				else
					System.out.println(planets.get(1).id + " " + planets.get(0).id);
			}
			else System.out.println(planets.get(0).id);
			current = input.nextLine();
		}
	}
	
	private static void trimMap(ArrayList<Planet> planets){
		ArrayList<Planet> removed = new ArrayList<>();
		while(planets.size() > 2){
			ArrayList<Planet> toRemove = new ArrayList<>();
			for(Planet p : planets){
				if(p.connections.size() == 1){
					toRemove.add(p);
				}
			}
			planets.removeAll(toRemove);
			removed.addAll(toRemove);
			toRemove.clear();
			for(Planet p : planets){
				for(Planet r : removed){
					if(p.connections.contains(r)){
						p.connections.remove(r);
					}
				}

			}
		}
		
	}
	private static Planet createPlanet(String current){
		String[] data = current.split(" ");
		int id = Integer.parseInt(data[0]);
		int x = Integer.parseInt(data[1]);
		int y = Integer.parseInt(data[2]);
		int z = Integer.parseInt(data[3]);
		return new Planet(id, x, y, z);
	}
	private static void findClosestAndConnect(ArrayList<Planet> planets, Planet newPlanet){
		Planet closest = null;
		double min = Integer.MAX_VALUE;
		for(Planet p : planets){
			double dist = distance(p, newPlanet);
			if(dist < min){
				min = dist;
				closest = p;
			}
		}
		if(closest != null){
			closest.add(newPlanet);
			newPlanet.add(closest);
		}
			
		
	}
	private static double distance(Planet a, Planet b){
		double xS = Math.pow(b.x - a.x, 2);
		double yS = Math.pow(b.y-a.y, 2);
		double zS = Math.pow(b.z - a.z, 2);
		double tempS = xS+yS+zS;
		return Math.sqrt(tempS);
	}

}

class Planet{
	public int id, x, y, z;
	public ArrayList<Planet> connections;
	
	Planet(int id, int x, int y, int z){
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		connections = new ArrayList<>();
	}
	
	public void add(Planet newPlanet){
		connections.add(newPlanet);
	}
}
