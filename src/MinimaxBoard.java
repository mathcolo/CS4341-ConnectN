import referee.Board;

/**
 * This class runs a Minimax search on a board
 * with a given depth. It utilizes alpha-beta 
 * pruning to reduce search time.
 * 
 * @author Theresa Inzerillo, Preston Mueller
 *
 */
public class MinimaxBoard {

	public Board board; // root board for Minimax search
	Move parentMove;

	public MinimaxBoard(Board board, Move parentMove) {
		this.board = board;
		this.parentMove = parentMove;
	}
	
	/**
	 * This function runs a minimax search of a given depth using the board state 
	 * for the current player and decides the best move for that player to make.
	 * To reduce search time, it utilizes alpha-beta pruning.
	 * 
	 * NOTE: Incoming moves are going to be emulated as always 2s and outgoing as 1s.
	 * 
	 * @param depth - depth to run minimax search with
	 * @param player - current player
	 * @param alpha - current alpha value
	 * @param beta - current beta value
	 * 
	 * @return the minimax value of the best move and the corresponding board as a MinimaxReturn object
	 */
	public MinimaxReturn minimax(int depth, int player, int alpha, int beta) {
		if(board == null)
			System.err.println("Cannot generate children, board is null.");
		
		if(depth == 0) {
			int heuristicValue = HeuristicEval.HeuristicEvalFn(this.board);
			
			return new MinimaxReturn(heuristicValue, this);
		}
		
		// MAX SEARCH
		if(player == Board.PLAYER1) {
			
			int maximumValue = Integer.MIN_VALUE;
			MinimaxBoard maximumBoard = null;
			
			// DROP MOVE FOR MAX
			for(int i = 0; i < board.getWidth(); i++) {
				// check whether move is legal
				if(board.canDropADiscFromTop(i, parentMove.player)) {
					Move move = new Move(i, player, Move.DROP);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					newBoard.board.dropADiscFromTop(i, move.player);

					MinimaxReturn value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta);
					if(value.value > maximumValue) {
						maximumValue = value.value;
						maximumBoard = value.board;
					}
					
					if(value.value > alpha) {
						alpha = value.value;
					}
					
					if(beta <= alpha) {
						break;
					}
					
				}
				
			}
			
			// POPOUT MOVE FOR MAX
			for(int i = 0; i < board.getWidth(); i++) {
				// check whether move is legal
				if(board.canRemoveADiscFromBottom(i, player) && PopManager.getSharedInstance().poppedMe == false) {
					Move move = new Move(i, player, Move.POPOUT);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					newBoard.board.removeADiscFromBottom(i);

					MinimaxReturn value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta);
					if(value.value > maximumValue) {
						maximumValue = value.value;
						maximumBoard = value.board;
					}
					
					if(value.value > alpha) {
						alpha = value.value;
					}
					
					if(beta <= alpha) {
						break;
					}
					
				}
				
			}
			
			return new MinimaxReturn(maximumValue, maximumBoard);
			
		}
		
		// MIN SEARCH
		else if(player == Board.PLAYER2) {
			
			int minimumValue = Integer.MAX_VALUE;
			MinimaxBoard minimumBoard = null;
			
			// DROP CASE FOR MIN
			for(int i = 0; i < board.getWidth(); i++) {
				// check if move is legal
				if(board.canDropADiscFromTop(i, parentMove.player)) {
					Move move = new Move(i, player, Move.DROP);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					newBoard.board.dropADiscFromTop(i, move.player);

					MinimaxReturn value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta);
					if(value.value < minimumValue) {
						minimumValue = value.value;
						minimumBoard = value.board;
					}
					
					if(value.value < beta) {
						beta = value.value;
					}
					
					if(beta <= alpha) {
						break;
					}
					
						
				}
				
			}
			
			// POPOUT MOVE FOR MIN
			for(int i = 0; i < board.getWidth(); i++) {
				// check if move is legal
				if(board.canRemoveADiscFromBottom(i, player)  && PopManager.getSharedInstance().poppedThem == false) {
					Move move = new Move(i, player, Move.POPOUT);
					MinimaxBoard newBoard = new MinimaxBoard(new Board(this.board), move);
					newBoard.board.removeADiscFromBottom(i);

					MinimaxReturn value = newBoard.minimax(depth-1, Move.oppositePlayer(player), alpha, beta);
					if(value.value < minimumValue) {
						minimumValue = value.value;
						minimumBoard = value.board;
					}
					
					if(value.value < beta) {
						beta = value.value;
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
		
}
