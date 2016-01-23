
public class PopManager {
	
	private static final PopManager sharedInstance = new PopManager();
	
	public boolean poppedMe = false;
	public boolean poppedThem = false;
	
	public int totalBoards = 0;
	
	private PopManager() {
		if(sharedInstance != null) {
			throw new IllegalStateException("Derp");
		}
	}
	
	public static PopManager getSharedInstance() {
		
		return sharedInstance;
		
	}

}
