import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by prestonmueller on 1/17/16.
 */
public class TheresaPrestonPlayer {
	
	boolean poppedMe = false;
	boolean poppedThem = false;
	
	public static void processInput(BufferedReader input) throws IOException{	
		
    	String s = input.readLine();	
		List<String> ls=Arrays.asList(s.split(" "));
		
		//A move
		if(ls.size() == 2){
			System.out.println(ls.get(0)+" "+ls.get(1));
		}
		else if(ls.size() == 1){
			System.out.println("game over!!!");
			System.exit(0);
		}
		else if(ls.size() == 5){          //ls contains game info
			System.out.println("0 1");  //first move
		}
		else if(ls.size() == 4){		//player1: aa player2: bb
			//TODO combine this information with game information to decide who is the first player
		}
		else
			System.out.println("not what I want");
	}

    public static void main(String[] args) {

    	System.out.println("TheresaN'Preston");
    	
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	while (true) {
    		
    		try {
    			processInput(input);
    		}
    		catch(Exception e) {
    			System.err.println("EXCEPTION CAUGHT!");
    			e.printStackTrace();
    		}
    		
		}
    	
    	
    }

}
