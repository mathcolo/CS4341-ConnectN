import java.util.ArrayList;

import referee.Board;

public class MinimaxBoard {
	
	public Board board;
	public ArrayList<MinimaxBoard> children = new ArrayList<MinimaxBoard>();
	
	Move parentMove;

	public MinimaxBoard(Board board, Move parentMove) {
		this.board = board;
		this.parentMove = parentMove;
	}
	
	public int generateChildren(int depth) {
		
		if(board == null)
			System.err.println("Cannot generate children, board was null!");
		
		if(depth == 0)
			return 0;
		
		int totalSub = 0;
		
		//Iterate through each column of the board
		for(int i = 0; i < board.getWidth(); i++) {
			if(board.canDropADiscFromTop(i, parentMove.player)) {
				//Move can be done to this column

				Move move = new Move(i, Move.oppositePlayer(parentMove.player), Move.Type.DROP);
				MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
				newBoard.board.dropADiscFromTop(i, move.player);
				this.children.add(newBoard);
				totalSub++;
				
				totalSub += newBoard.generateChildren(depth-1);
				
				
			}
			
		}
		
		return totalSub;
	}
	
	
	public static void main(String[] args) {
		
		Board test = new Board(7, 7, 4);
		test.dropADiscFromTop(2, 1);
		
		
		MinimaxBoard wrapper = new MinimaxBoard(test, new Move(2,1,Move.Type.DROP));
		wrapper.generateChildren(5);
		wrapper.children.get(2).board.printBoard();
		
		
	}
	

}
