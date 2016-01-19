import referee.Board;

public class HeuristicEval {

	public int HeuristicEvalFn(Board state) {
		//for (int i = state.getN(); i < Math.floorDiv(state.getN(), 2)) {

		//}
		return 0;
	}

	public int nInARow(int player, Board state, int n) {
		return nDiagonals(player, state, n) + nHorizontals(player, state, n) + nVerticals(player, state, n);
	}

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
						if (col.length()== n || col.charAt(n) != player)
							numV++;
					}
				}
			}
		}
		return numV;
	}

	static int nHorizontals(int player, Board state, int n) {
		int numH = 0;
		int otherplayer = (player == 1) ? 2 : 1;
		String match = "";
		for (int m = 0; m <n; m++) {
			match+=player;
		}
		
		for (int i = 0; i< state.getHeight(); i++) {
			String row = "";
			for (int j = 0; j < state.getWidth(); j++) {
				row += state.getBoard()[i][j];
			}
			System.out.println(row);
			
			while (row.indexOf(player) != -1) {
				int p = row.indexOf(""+player);
				if (p == 0 && row.length() > n && row.substring(0, n) == match && row.charAt(p+1) == 9) {
					numH++;
					row = row.substring(n);
				}
				else if (row.length() - p == n && row.substring(p, n+p) == match 
						&& row.charAt(p-1) == 9) {
					numH++;
					row = "";
				}
				else if (row.length() - p > n && row.substring(p, n+p) == match 
						&& (row.charAt(p-1) == 9 || row.charAt(p+1) == 9)) {
					numH++;
					row = row.substring(n);
				}
			}
			
		}
		return numH;
	}

	private int nDiagonals(int player, Board state, int n) {
		// TODO Auto-generated method stub
		return 0;
	}




}
