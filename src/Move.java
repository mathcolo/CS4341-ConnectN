import referee.Board;

/**
 * Contains the information associated with
 * a move in Connect-N.
 * 
 * @author Theresa Inzerillo, Preston Mueller
 *
 */
public class Move {

	public static final int DROP = 1;
	public static final int POPOUT = 0;
	public static final int INVALID = 3;
	
	public int moveType;
	public int column;
	public int player;
	
	/**
	 * Creates a new move.
	 * 
	 * @param column - the column the move was made in
	 * @param player - the player that made the move
	 * @param moveType - the type of move to make
	 */
	public Move(int column, int player, int moveType) {
		this.column = column;
		this.player = player;
		this.moveType = moveType;
	}
	
	public static int oppositePlayer(int player) {
		if(player == Board.PLAYER1) return Board.PLAYER2;
		else return Board.PLAYER1;
	}

	public String toString() {
		return "Move(column, player, type): (" + column + ", " + player + ", " + moveType + " )";
	}

}
