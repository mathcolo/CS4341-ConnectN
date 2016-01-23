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
	
	public MinimaxReturn minimax(int depth, int player, int alpha, int beta) {
		
		//Incoming moves are going to be emulated as always 2s and ours are 1s
		
		if(board == null)
			System.err.println("Cannot generate children, board was null!");
		
		if(depth == 0) {
			//Insert real heuristic evaluation function here
			int heuristicValue = HeuristicEval.HeuristicEvalFn(this.board);
			return new MinimaxReturn(heuristicValue, null);
		}
		
		//Max
		if(player == Board.PLAYER1) {
			
			int maximumValue = Integer.MIN_VALUE;
			MinimaxBoard maximumBoard = null;
			
			//DROP
			for(int i = 0; i < board.getWidth(); i++) {
				if(board.canDropADiscFromTop(i, parentMove.player)) {
					//Move can be done to this column

					Move move = new Move(i, player, Move.Type.DROP);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					
					PopManager.getSharedInstance().totalBoards++;
					
					newBoard.board.dropADiscFromTop(i, move.player);
					this.children.add(newBoard);

					int value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta).value;
					if(value > maximumValue) {
						maximumValue = value;
						maximumBoard = newBoard;
					}
					
					if(value > alpha) {
						alpha = value;
					}
					
					if(beta <= alpha) {
						break;
					}
				}
				
			}
			
			//POPOUT
			for(int i = 0; i < board.getWidth(); i++) {
				if(board.canRemoveADiscFromBottom(i, parentMove.player)) {
					//Move can be done to this column

					Move move = new Move(i, player, Move.Type.POPOUT);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					PopManager.getSharedInstance().totalBoards++;
					newBoard.board.removeADiscFromBottom(i);
					this.children.add(newBoard);

					int value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta).value;
					if(value > maximumValue) {
						maximumValue = value;
						maximumBoard = newBoard;
					}
					
					if(value > alpha) {
						alpha = value;
					}
					
					if(beta <= alpha) {
						break;
					}
				}
				
			}
			
			return new MinimaxReturn(maximumValue, maximumBoard);
			
		}
		//Min
		else if(player == Board.PLAYER2) {
			
			int minimumValue = Integer.MAX_VALUE;
			MinimaxBoard minimumBoard = null;
			
			//DROP
			for(int i = 0; i < board.getWidth(); i++) {
				if(board.canDropADiscFromTop(i, parentMove.player)) {
					//Move can be done to this column

					Move move = new Move(i, player, Move.Type.DROP);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					PopManager.getSharedInstance().totalBoards++;
					newBoard.board.dropADiscFromTop(i, move.player);
					this.children.add(newBoard);

					int value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta).value;
					if(value < minimumValue) {
						minimumValue = value;
						minimumBoard = newBoard;
					}
					
					if(value < beta) {
						beta = value;
					}
					
					if(beta <= alpha) {
						break;
					}
						
				}
				
			}
			
			//POPOUT
			for(int i = 0; i < board.getWidth(); i++) {
				if(board.canRemoveADiscFromBottom(i, parentMove.player)) {
					//Move can be done to this column

					Move move = new Move(i, player, Move.Type.POPOUT);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					PopManager.getSharedInstance().totalBoards++;
					newBoard.board.removeADiscFromBottom(i);
					this.children.add(newBoard);

					int value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta).value;
					if(value < minimumValue) {
						minimumValue = value;
						minimumBoard = newBoard;
					}
					
					if(value < beta) {
						beta = value;
					}
					
					if(beta <= alpha) {
						break;
					}
				}
				
			}
			
			return new MinimaxReturn(minimumValue, minimumBoard);
			
		}

		return null;
	}
	
	
	public static void main(String[] args) {
		
		Board test = new Board(7, 7, 4);
							//col, player
		test.dropADiscFromTop(4, 2);
		test.dropADiscFromTop(4, 1);
		test.dropADiscFromTop(4, 2);
		test.dropADiscFromTop(4, 1);
		test.dropADiscFromTop(4, 2);
		test.dropADiscFromTop(3, 1);
		test.dropADiscFromTop(3, 2);
		test.dropADiscFromTop(3, 2);
		test.dropADiscFromTop(3, 1);
		
		test.dropADiscFromTop(3, 2);
		test.dropADiscFromTop(3, 1);
		
		test.dropADiscFromTop(5, 1);
		test.dropADiscFromTop(5, 2);
		test.dropADiscFromTop(5, 1);
		
		test.dropADiscFromTop(6, 1);
		test.dropADiscFromTop(6, 1);
		
		test.printBoard();

			
//		System.exit(0);
		

		MinimaxBoard wrapper = new MinimaxBoard(test, new Move(6,1,Move.Type.DROP));
		MinimaxReturn stuff = wrapper.minimax(3, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		if(stuff.board.parentMove.moveType == Move.Type.DROP) {
			System.out.println("Playing drop move");
			test.dropADiscFromTop(stuff.board.parentMove.column, stuff.board.parentMove.player);
		}
		else if(stuff.board.parentMove.moveType == Move.Type.POPOUT) {
			System.out.println("Playing popout move");
			test.removeADiscFromBottom(stuff.board.parentMove.column);
		}
		else System.out.println("WTF");
		
		test.printBoard();

		
	}
	
	private class MinimaxReturn {
		
		int value;
		MinimaxBoard board;
		
		public MinimaxReturn(int value, MinimaxBoard board) {
			this.value = value;
			this.board = board;
		}
		
	}
	
	
}
