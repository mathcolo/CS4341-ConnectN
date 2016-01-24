import referee.Board;

/**
 * 
 * @author Theresa Inzerillo, Preston Mueller
 *
 */
public class Move {
	
	public enum Type {
		DROP, POPOUT, INVALID
	}
	
	public Type moveType;
	
	public int column;
	public int player;
	
	public Move(int column, int player, Type moveType) {
		this.column = column;
		this.player = player;
		this.moveType = moveType;
	}
	
	public static int oppositePlayer(int player) {
		if(player == Board.PLAYER1) return Board.PLAYER2;
		else return Board.PLAYER1;
	}
	
	public Board applyMoveToExistingBoard(Board b) {
		
		Board newBoard = new Board(b);
		
		if(this.moveType == Move.Type.DROP) {
			newBoard.dropADiscFromTop(this.column, this.player);
		}
		else if(this.moveType == Move.Type.POPOUT) {
			newBoard.removeADiscFromBottom(this.column);
		}
		
		return newBoard;
	}
	
	public String toString() {
		return "Move(column, player): (" + column + ", " + player + ")";
	}

}
