/**
	 * Class to encapsulate the board
	 * and its minimax value.
	 * 
	 * @author Theresa Inzerillo, Preston Mueller
	 *
	 */
	public class MinimaxReturn {
		
		int value;
		MinimaxBoard board;
		
		/**
		 * Creates an object to store a value and a board
		 * as computed by {@link MinimaxBoard#minimax(int, int, int, int)}.
		 * 
		 * @param value - heuristic value of the board selected by minimax
		 * @param board - board selected by minimax
		 */
		public MinimaxReturn(int value, MinimaxBoard board) {
			this.value = value;
			this.board = board;
		}
		
	}