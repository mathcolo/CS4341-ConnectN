import java.util.ArrayList;

import referee.Board;

/**
 * This class contains the heuristic evaluation function
 * for boards as well as helper methods.
 * 
 * @author Theresa Inzerillo, Preston Mueller
 */
public class HeuristicEval {

	/**
	 * Heuristic evaluation function for Connect-N board states.
	 * 
	 * @param state - board state to evaluate
	 * @return the result of the evaluation, 0 for a board in favor in
	 * neither player, > 0 if in favor of player 1 (max) and < 0 if in favor 
	 * of player 2 (min).
	 */
	public static int HeuristicEvalFn(Board state) {
		int result = 0;
		for (int i = state.getN()-1; i >= (int)Math.floorDiv(state.getN(), 2); i--) {
			result += (int)Math.pow(i, 2)*nInARow(Board.PLAYER1, state, i);
			result -= (int)Math.pow(i, 2)*nInARow(Board.PLAYER2, state, i);
		}

		int winner = state.isConnectN();

		if (winner == Board.PLAYER1) {
			result += (int)Math.pow(state.getN()+1, 2);
		}
		else if (winner == Board.PLAYER2) {
			result -= (int)Math.pow(state.getN()+1, 2);
		}
		return result;
	}

	/**
	 * Calculates the number of diagonals, horizontals and vertical 
	 * sequences of a certain length, n, that a player has in the current
	 * board.
	 * 
	 * @param player - player to check
	 * @param state - board state to check
	 * @param n - the length of sequences to check for
	 * 
	 * @return the number of sequences of n length in any direction for a player
	 */
	public static int nInARow(int player, Board state, int n) {
		return nDiagonals(player, state, n) + nHorizontals(player, state, n) + nVerticals(player, state, n);
	}

	/**
	 * Calculates the number of vertical sequences a player has on the board
	 * of n length. 
	 * 
	 * @param player - player to check for
	 * @param state - the board state
	 * @param n - the length of sequences to check for
	 * @return the number of vertical sequences of n length
	 */
	static int nVerticals(int player, Board state, int n) {
		int numV = 0;

		String match = "";
		for (int m = 0; m <n; m++) {
			match+=player;
		}

		for (int i = 0; i< state.getWidth(); i++) {
			String col = "";
			for (int j = 0; j < state.getHeight(); j++) {
				col += state.getBoard()[j][i];
			}
			if (col.charAt(0) == '9') {
				int p = col.indexOf(""+player);
				if (p != -1) {
					col = col.substring(p);
					if (col.length() >= n && col.substring(0, n).equals(match)) {
						if (col.length() == n || col.charAt(n) != (char)(player + '0')) {
							numV++;
						}
					}
				}
			}
		}
		return numV;
	}

	/**
	 * Calculates the number of horizontals sequences a player has on the board
	 * of n length. 
	 * 
	 * @param player - player to check for
	 * @param state - the board state
	 * @param n - the length of sequences to check for
	 * @return the number of horizontal sequences of n length
	 */
	static int nHorizontals(int player, Board state, int n) {
		int numH = 0;
		String match = "";
		for (int m = 0; m <n; m++) {
			match+=player;
		}

		for (int i = 0; i < state.getHeight(); i++) {
			String row = "";
			for (int j = 0; j < state.getWidth(); j++) {
				row += state.getBoard()[i][j];
			}
			while (row.indexOf(""+player) != -1) {
				int p = row.indexOf(""+player);
				//System.out.println(row.substring(0, n));
				if (p == 0 && row.length() > n && row.substring(0, n).equals(match) && row.charAt(n) == (char) Board.emptyCell + '0') {
					numH++;
					row = row.substring(n+p);
				}
				else if (p != 0 &&row.length() - p == n && row.substring(p, n+p).equals(match)
						&& row.charAt(p-1) == (char) Board.emptyCell + '0') {
					numH++;
					row = "";
				}  
				else if (p != 0 && row.length() - p > n && row.substring(p, n+p).equals(match) 
						&& (row.charAt(p-1) == (char)(Board.emptyCell + '0') || row.charAt(p+n) == (char)(Board.emptyCell + '0')) && 
						(row.charAt(p-1) != (char)(player + '0') && row.charAt(p+n) != (char)(player + '0'))) {
					numH++;
					row = row.substring(n+p);
				}
				else {
					row = "";
				}
			}

		}
		return numH;
	}

	/**
	 * Calculates the number of diagonal sequences a player has on the board
	 * of n length.
	 *  
	 * @param player - player to check for
	 * @param state - the board state
	 * @param n - the length of sequences to check for
	 * @return the number of diagonal sequences of n length
	 */
	static int nDiagonals(int player, Board state, int n) {
		ArrayList<String> diagStrs = new ArrayList<String>();
		int numD=0;

		String match = "";
		for (int m = 0; m <n; m++) {
			match+=player;
		}

		// top left
		for(int i = 0; i < state.getWidth(); i++){
			String diag = "";
			for(int j = 0, k = i; j < state.getHeight() && k >= 0; j++,k--){
				diag += state.getBoard()[j][k];
			}
			diagStrs.add(diag);
		}

		// bottom right
		for(int i = 1; i < state.getHeight(); i++){
			String diag = "";
			for(int j = i, k = state.getWidth() -1; j < state.getHeight() && k >= 0; j++,k--){
				diag += state.getBoard()[j][k];
			}
			diagStrs.add(diag);
		}

		// bottom left
		for(int i = state.getWidth()-1; i >=0; i--){
			String diag = "";
			for(int j = i, k = 0; k < state.getHeight() && j < state.getWidth(); k++,j++){
				diag += state.getBoard()[j][k];
			}
			diagStrs.add(diag);
		}

		// top right
		for(int i = state.getWidth()-1; i >=0; i--){
			String diag = "";
			for(int j = 0, k = i; k < state.getHeight() && j < state.getWidth(); k++,j++){
				diag += state.getBoard()[j][k];
			}
			diagStrs.add(diag);
		}

		for (String d : diagStrs) {
			while (d.indexOf(""+player) != -1) {
				int p = d.indexOf(""+player);
				if (p == 0 && d.length() > n && d.substring(0, n-1).equals(match) && d.charAt(p+n) == (char) Board.emptyCell + '0') {
					numD++;
					d = d.substring(n+p);
				}
				else if (p!= 0 && d.length() - p == n && d.substring(p, n+p-1).equals(match) 
						&& d.charAt(p-1) == (char) Board.emptyCell + '0') {
					numD++;
					d = "";
				}  
				else if (p != 0 && d.length() - p > n && d.substring(p, n+p-1).equals(match)
						&& d.charAt(p-1) != (char) (player + '0') && d.charAt(p+n) != (char) (player + '0') && 
						(d.charAt(p-1) == (char)(Board.emptyCell + '0') 
						|| d.charAt(p+n) == (char)(Board.emptyCell + '0'))) {
					numD++;
					d = d.substring(n+p);
				}
				else {
					d="";
				}
			}

		}
		return numD;
	}
}
