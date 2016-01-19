
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

}
