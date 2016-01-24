/**
 * Singleton to manage each player's use of their one "pop" of 
 * a Connect-N piece during the game.
 * 
 * @author Theresa Inzerillo, Preston Mueller
 *
 */
public class PopManager {
	
	private static final PopManager sharedInstance = new PopManager();
	
	public boolean poppedMe = false;
	public boolean poppedThem = false;
	
	public int totalBoards = 0;
	
	/**
	 * Creates a new PopManager if one does not exist. 
	 * 
	 * @throws IllegalStateException if a PopManager aready exists.
	 */
	private PopManager() {
		if(sharedInstance != null) {
			throw new IllegalStateException("Invalid operation. A PopManager has already been instantiated.");
		}
	}
	
	/**
	 * @return handle to the PopManager if existing, otherwise, null.
	 */
	public static PopManager getSharedInstance() {
		return sharedInstance;
	}

}
