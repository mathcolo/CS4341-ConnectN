import java.util.ArrayList;

import referee.Board;

public class MinimaxBoard {
	
	public Board board;
	
	public ArrayList<MinimaxBoard> children = new ArrayList<MinimaxBoard>();

	public MinimaxBoard(Board board) {
		this.board = board;
	}
	
	public int generateChildren() {
		
		if(board == null)
			System.err.println("Cannot generate children, board was null!");
		
		//Iterate through each column of the board
		for(int i = 0; i < board.getBoard().length; i++) {
			int[] column = board.getBoard()[i];
			
		}
		
		return 0;
	}
	

}
