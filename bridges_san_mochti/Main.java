package d_bridges_san_mochti;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1940574 for the problem 4170 - The Bridges of San Mochti has succeeded with verdict Accepted.
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
		int B = 0, P = 0;
		int time = 0;
		while(!current.trim().equals("0 0")){
			if(current.contains("-")){
				String[] dimensions = current.split(" ");
				B = Math.abs(Integer.parseInt(dimensions[0]));
				P = Integer.parseInt(dimensions[1]);
			}
			ArrayList<BridgePart> bridge = new ArrayList<>();
			bridge.add(new BridgePart(Integer.MAX_VALUE, 0));
			for(int i = 0; i < B; i++){ // create bridge
				current = input.nextLine();
				String[] bridgeInfo = current.split(" ");
				int capacity = Integer.parseInt(bridgeInfo[0]);
				int toCrossTime = Integer.parseInt(bridgeInfo[1]);
				bridge.add(new BridgePart(capacity, toCrossTime));
				bridge.add(new BridgePart(Integer.MAX_VALUE, 0));
			}
			boolean done = false;
			bridge.get(0).currentPeople = P;
			int length = bridge.size();
			while(!done){
				time++;
				for(int i = 0; i < length; i++){
					if(i != length-1){
						BridgePart now = bridge.get(i);
						BridgePart next = bridge.get(i+1);
						now.timeOnBridge++;
						if((now.timeOnBridge >= now.timeToCross) && (next.currentPeople == 0 || next.capacity == Integer.MAX_VALUE)){
							int move = toMove(now, next);
							now.currentPeople -= move;
							next.currentPeople += move;
							next.timeOnBridge = 0;
						}
					}
				}
				if(bridge.get(length-1).currentPeople == P){
					done = true;
				}
			}
			System.out.println(time+(B-1));
			current = input.nextLine();
			time = 0;
		}
	}
	
	private static int toMove(BridgePart now, BridgePart next){
		if(now.currentPeople >= next.capacity){
			return next.capacity;
		}
		else{
			return now.currentPeople;
		}
	}


}
class BridgePart{
	public int capacity;
	public int timeToCross;
	public int timeOnBridge;
	public int currentPeople;
	
	BridgePart(int capacity, int timeToCross){
		this.capacity = capacity;
		this.timeToCross = timeToCross;
		this.timeOnBridge = 0;
		this.currentPeople = 0;
	}
}

