package pascals_travels;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1956464 for the problem 3390 - Pascal's Travels has succeeded with verdict Accepted.
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
		while(!current.trim().equals("-1")){
			int n = Integer.parseInt(current.trim());
			int[][] board = new int[n][n];
			long[][] cost = new long[n][n];
			cost[0][0] = 1;
			for(int i = 0; i < n; i++){
				current = input.nextLine();
				char[] row = current.toCharArray();
				for(int j = 0; j < n; j++){
					board[i][j] = (int) (row[j]-48);
				}
			}
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(i+j != 0)
						cost[i][j] = sum(board, cost, i, j);
				}
			}
			System.out.println(cost[n-1][n-1]);
			current = input.nextLine();
		}
	}
	
	private static long sum(int[][] board, long[][] cost, int i, int j){
		long sum = 0;
		for(int x = i-1; x >= 0; x--){ // up
			if(board[x][j]+x == i) sum += cost[x][j];
			
		}
		for(int x = j-1; x >= 0; x--){ // left
			if(board[i][x]+x == j) sum += cost[i][x];
			
		}
		return sum;
	}

}
