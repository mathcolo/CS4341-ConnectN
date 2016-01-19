import junit.framework.TestCase;
import referee.Board;

public class HeuristicEvalTest extends TestCase{

	public void testNVertical() {
		Board b = new Board(5, 5, 3);
		b.setBoard(4, 0, 1);
		b.setBoard(4, 1, 1);
		b.setBoard(4, 2, 1);
		b.setBoard(3, 0, 2);
		b.setBoard(3, 1, 2);
		
		assertEquals(1, HeuristicEval.nVerticals(1, b, 3));
		assertEquals(1, HeuristicEval.nVerticals(2, b, 2));
		
		b.setBoard(4, 3, 2);
		b.setBoard(4, 4, 2);

		assertEquals(1, HeuristicEval.nVerticals(2, b, 2));
	}
	
}
