import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import referee.Board;

/**
 * Created by prestonmueller on 1/17/16.
 */
public class TheresaPrestonPlayer {
	
	static final String logFile = "AIout.txt";
	
	boolean poppedMe = false;
	boolean poppedThem = false;
	
	int ourPlayer = -1;
	
	private Board currentBoard;

	public void processInput(BufferedReader input) throws IOException{
		log("Waiting for input");
		
    	String s = input.readLine();
    	log("Processing input: " + s);
    	
		List<String> ls = Arrays.asList(s.split(" "));

		//A move
		if(ls.size() == 2) {
			
			Move.Type type = (Integer.parseInt(ls.get(1)) == 1 ? Move.Type.DROP : Move.Type.POPOUT);
			
			Move incoming = new Move(Integer.parseInt(ls.get(0)), 2, type);
			currentBoard = incoming.applyMoveToExistingBoard(currentBoard);
			
			//Make a move back
			MinimaxBoard wrapper = new MinimaxBoard(currentBoard, incoming);
			MinimaxReturn outgoing = wrapper.minimax(4, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			Move outgoingMove = outgoing.board.parentMove;
			currentBoard = outgoingMove.applyMoveToExistingBoard(currentBoard);
			
			System.out.println(outgoingMove.column + " " + (outgoingMove.moveType == Move.Type.DROP ? 1 : 0));
			
		}
		else if(ls.size() == 1){
			System.out.println("game over!!!");
			System.exit(0);
		}
		else if(ls.size() == 5){
			//Game info
			int height = Integer.parseInt(ls.get(0));
			int width = Integer.parseInt(ls.get(1));
			int n = Integer.parseInt(ls.get(2));
			int whichPlayerFirst = Integer.parseInt(ls.get(3));
			int timeLimit = Integer.parseInt(ls.get(4));
			
			currentBoard = new Board(height, width, n);
			
			log("Created board");
			
			if(whichPlayerFirst == ourPlayer) {
				//We need to send the first move
				Random r = new Random();
				
				System.out.println(r.nextInt(5) + " 1");
			}
		}
		else if(ls.size() == 4){		//player1: aa player2: bb

			if(ls.get(2).equals("TheresaN'Preston")) {
				ourPlayer = 1;
			}
			else ourPlayer = 2;
		}
		else
			System.out.println("not what I want");
	}

    public static void main(String[] args) {

    	log("Starting AI");
    	System.out.println("TheresaN'Preston");
    	
    	TheresaPrestonPlayer player = new TheresaPrestonPlayer();
    	
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	while (true) {
    		
    		try {
    			player.processInput(input);
    		}
    		catch(Exception e) {
    			log("EXCEPTION CAUGHT!");
    			e.printStackTrace();
    		}
    		
		}
    	
    	
    }
    
    private static void log(String s) {
    	s += "\n";
    	
    	try {
			Files.write(Paths.get(logFile), s.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
