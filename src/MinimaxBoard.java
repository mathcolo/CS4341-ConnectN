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
	
	public int minimax(int depth, int player, ArrayList<Move> moves) {
		
		if(board == null)
			System.err.println("Cannot generate children, board was null!");
		
		if(depth == 0) {
			//Insert real heuristic evaluation function here
			int heuristicValue = HeuristicEval.HeuristicEvalFn(this.board);
			System.out.println(heuristicValue);
			return heuristicValue;
		}
		
		//Max
		if(player == 1) {
			
			int maximumValue = Integer.MIN_VALUE;
			
			//Iterate through each column of the board
			for(int i = 0; i < board.getWidth(); i++) {
				if(board.canDropADiscFromTop(i, parentMove.player)) {
					//Move can be done to this column

					Move move = new Move(i, player, Move.Type.DROP);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					newBoard.board.dropADiscFromTop(i, move.player);
					this.children.add(newBoard);

					int value = newBoard.minimax(depth-1, Move.oppositePlayer(player), moves);
					if(value > maximumValue) {
						maximumValue = value;
					}
				}
				
			}
			
			return maximumValue;
			
		}
		//Min
		else if(player == 2) {
			
			int minimumValue = Integer.MAX_VALUE;
			
			//Iterate through each column of the board
			for(int i = 0; i < board.getWidth(); i++) {
				if(board.canDropADiscFromTop(i, parentMove.player)) {
					//Move can be done to this column

					Move move = new Move(i, player, Move.Type.DROP);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					newBoard.board.dropADiscFromTop(i, move.player);
					this.children.add(newBoard);

					int value = newBoard.minimax(depth-1, Move.oppositePlayer(player), moves);
					if(value < minimumValue) {
						minimumValue = value;
					}
						
				}
				
			}
			
			return minimumValue;
			
		}

		return -1;
	}
	
	
	public static void main(String[] args) {
		
		Board test = new Board(7, 7, 4);
							//col, player
		test.dropADiscFromTop(2, 2);
		test.printBoard();
		
		//1 = max
		MinimaxBoard wrapper = new MinimaxBoard(test, new Move(2,2,Move.Type.DROP));
		ArrayList<Move> moves = new ArrayList<Move>();
		
		int value = wrapper.minimax(4, 2, moves);
		
		System.out.println("Minimax value: " + value);

		test.printBoard();
		
		
	}
	
	private class MinimaxReturn {
		
		int value;
		MinimaxBoard board;
		
	}
	
	
}
