import junit.framework.TestCase;
import referee.Board;

public class HeuristicEvalTest extends TestCase{

	public void testNVertical() {
		Board b = new Board(5, 5, 3);
		b.setBoard(4, 0, 1);
		b.setBoard(3, 0, 1);
		b.setBoard(2, 0, 1);
		
		b.printBoard();
		
		b.setBoard(4, 1, 2);
		b.setBoard(3, 1, 2);
		
		b.printBoard();
		
		assertEquals(1, HeuristicEval.nVerticals(1, b, 3));
		assertEquals(1, HeuristicEval.nVerticals(2, b, 2));
		
		b.setBoard(1, 0, 2);
		b.setBoard(0, 0, 2);
		
		b.printBoard();

		assertEquals(1, HeuristicEval.nVerticals(2, b, 2));
	}
	
	public void testNHorizontal() {
		Board b = new Board(5, 10, 3);
		
		b.setBoard(4, 0, 1);
		b.setBoard(4, 1, 1);
		b.setBoard(4, 2, 1);
		b.setBoard(4, 5, 1);
		b.setBoard(4, 6, 1);
		b.setBoard(4, 7, 1);
		b.setBoard(3, 0, 2);
		b.setBoard(3, 1, 2);
		
		b.printBoard();
		
		assertEquals(0, HeuristicEval.nHorizontals(2, b, 3));
	}
	
	public void testNDiagonal() {
		Board b = new Board(4, 4, 3);
		b.setBoard(3, 0, 1);
		b.setBoard(2, 0, 1);
		b.setBoard(3, 1, 2);
		b.setBoard(2, 1, 1);
		
		b.printBoard();
		
		assertEquals(1, HeuristicEval.nDiagonals(1, b, 2));
	}
	
	public void testHeuristicFn() {
		Board b  = new Board(5, 5, 4);
		
		b.setBoard(4, 0, 1);
		b.setBoard(3, 0, 1);
		b.setBoard(2, 0, 1);
		
		b.setBoard(4, 4, 2);
		b.setBoard(3, 4, 2);
		b.printBoard();
		
		assertEquals(5, HeuristicEval.HeuristicEvalFn(b));
	}
	
	public void testBoards() {
		Board test = new Board(7,7,4);
		
		test.dropADiscFromTop(0, 1);
		test.dropADiscFromTop(1, 1);
		test.dropADiscFromTop(2, 1);
		//test.dropADiscFromTop(3, 2);
		
		System.out.println(HeuristicEval.HeuristicEvalFn(test));
	}
 	
}
