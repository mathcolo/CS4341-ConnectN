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
			match+=(char)player;
		}

		for (int i = 0; i< state.getWidth(); i++) {
			String col = "";
			for (int j = 0; j < state.getHeight(); j++) {
				col += (char) state.getBoard()[i][j];
			}

			if (col.charAt(0) == '9') {
				while (col.charAt(0)=='9') {
					col = col.substring(1);
				}

				if (col.substring(0, n).equals(match) && col.charAt(n) != (char) player) {
					numV++;
				}
			}
		}
		return numV;
	}

	private int nHorizontals(int player, Board state, int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int nDiagonals(int player, Board state, int n) {
		// TODO Auto-generated method stub
		return 0;
	}



}
