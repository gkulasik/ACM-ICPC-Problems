package ripoff;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1957243 for the problem 4578 - RIPOFF has succeeded with verdict Accepted.
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
		while(!current.trim().equals("0")){
			String[] data = current.split(" ");
			int N = Integer.parseInt(data[0]);
			int S = Integer.parseInt(data[1]);
			int T = Integer.parseInt(data[2]);
			int[][] memory = new int[T+1][N+2];
			for(int i = 0; i <= T; i++){ // make memory of past answers all very negative (zero wont work since answer could be negative
				for(int j = 1; j <= N+1; j++){
					memory[i][j] = -10000000; // using Integer.min_value doesn't work - loops to the positives
				}
			}
			int[] board = new int[N+2];
			for(int i = 1; i < N+1; i++){ // fill in board [100, -50, -20, ... etc.]
				current = input.next();
				board[i] = Integer.parseInt(current);
			}
			for(int i = 1; i < T+1; i++){
				for(int j = i; j < N+2; j++){
					int start = j > S && j-S > i-1 ? j-S : i-1; // if past S then should return the -1000000 values (by shifting start)
					memory[i][j] = board[j] + bestPrevious(memory[i-1], start, j); // look at all previous answers and find best
				}
			}
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < T+1; i++){
				if(memory[i][N+1] > max) max = memory[i][N+1];
			}
			System.out.println(max);
			input.nextLine();
			current = input.nextLine();
		}
	}
	
	private static int bestPrevious(int[] toSearch, int start, int end){
		int max = Integer.MIN_VALUE;
		for(int i = start; i < end; i++){
			if(max < toSearch[i]) max = toSearch[i];
		}
		return max;
	}

}

// FIRST ATTEMPT - using a tree structure to hold all previous answers - too inefficient
//
//
//LinkedList<Node> toWorkon = new LinkedList<>();
//Node root = new Node(0, 0, 0, 0, null, null);
//toWorkon.add(root);
//int max = board[0];
//
//for(int i = 1; i < T; i++){
//	Node now = toWorkon.remove();
//	for(int j = 1; j <= S; j++){
//		Node jump = new Node(i, now.spot+j, board[now.spot+j], now.sum+board[now.spot+j], now, null);
//		now.addChild(jump);
//		if(jump.spot < board.length) toWorkon.add(jump);
//		if(jump.sum > max && jump.spot >= board.length){
//			max = jump.sum;
//		}
//	}
//}
//LinkedList<Node> work = new LinkedList<>();
//Node r = new Node(0, -1, 0, 0, null, null);
//work.add(r);
//int m = Integer.MIN_VALUE;
//while(!work.isEmpty()) {
//	Node n = work.removeFirst();
//	for (int j = 1; j <= S; j++) {
//		if(n.spot < board.length && n.t <= T){
//			if(n.spot+j < board.length){
//				Node jump = new Node(n.t+1, n.spot+j, board[n.spot+j], n.sum+board[n.spot+j], n, null);
//				n.addChild(jump);
//				work.add(jump);
//			}
//			else
//			{
//				Node jump = new Node(n.t, n.spot, 0, n.sum, n, null);
//				n.addChild(jump);
//			}
//		}
//	}
//}
//ArrayList<Node> leafs = r.leafs(new ArrayList<Node>());
// int m = Integer.MIN_VALUE; // max of the leafs
//for(Node leaf : leafs){
//	if(leaf.sum > m && leaf.spot >= board.length && leaf.t <= T) m = leaf.sum;
//}
//class Node
//{
//	int t;
//	int spot;
//	int amount;
//	int sum;
//	ArrayList<Node> children;
//	boolean leaf;
//	Node parent;
//	
//	Node(int t, int spot, int amount, int sum, Node parent, ArrayList<Node> children){
//		this.t = t;
//		this.spot = spot;
//		this.amount = amount;
//		this.sum = sum;
//		this.leaf = true;
//		this.parent = parent;
//		this.children = children;
//		if(children == null) this.children = new ArrayList<>();
//	}
//	
//	public void addChild(Node child){
//		leaf = false;
//		children.add(child);
//	}
//	
//	public ArrayList<Node> leafs(ArrayList<Node> toFill){
//		for(Node child : children){
//			if(child.leaf){
//				toFill.add(child);
//			}
//			else{
//				child.leafs(toFill);
//			}	
//		}
//		return toFill;
//	}
//}
