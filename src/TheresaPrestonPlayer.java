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
	
	//Keep track of who has popped out or not
	boolean poppedMe = false;
	boolean poppedThem = false;

	int ourPlayer = -1;
	
	//Our keeping track of the current game state
	private Board currentBoard;

	public void processInput(BufferedReader input) throws IOException{
		log("Waiting for input");
		
    	String s = input.readLine();
    	log("Processing input: " + s);
    	
		List<String> ls = Arrays.asList(s.split(" "));

		//A move incoming from the Ref
		if(ls.size() == 2) {
			
			int type = Integer.parseInt(ls.get(1));
			
			Move incoming = new Move(Integer.parseInt(ls.get(0)), 2, type);
			log("Recvd " + incoming);
			
			if(type == Move.DROP) {
				currentBoard.dropADiscFromTop(incoming.column, 2);
			}
			else if(type == Move.POPOUT) {
				currentBoard.removeADiscFromBottom(incoming.column);
				PopManager.getSharedInstance().poppedThem = true;
			}
			
			//Make a move back
			MinimaxBoard wrapper = new MinimaxBoard(currentBoard, incoming);
			MinimaxReturn outgoing = wrapper.minimax(7, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			Move outgoingMove = outgoing.board.parentMove;
			if(outgoingMove.moveType == Move.DROP) {
				currentBoard.dropADiscFromTop(outgoingMove.column, 2);
			}
			else if(outgoingMove.moveType == Move.POPOUT) {
				currentBoard.removeADiscFromBottom(outgoingMove.column);
				PopManager.getSharedInstance().poppedMe = true;
			}
			
			log("Sending " + outgoingMove);
			
			//Send it back!
			System.out.println(outgoingMove.column + " " + outgoingMove.moveType);
			
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
				log("Sending first move");
				Random r = new Random();
				
				System.out.println(r.nextInt(5) + " 1");
			}
		}
		else if(ls.size() == 4){		//player1: aa player2: bb

			if(ls.get(2).equals("TheresaPreston")) {
				log("We're player 1");
				ourPlayer = 1;
			}
			else {
				log("We're player 2");
				ourPlayer = 2;
			}
		}
		else
			System.out.println("not what I want");
	}

    public static void main(String[] args) {

    	log("\n\n\n\n\nStarting AI");
    	System.out.println("TheresaPreston");
    	
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
    
    /**
     * Log a value to AIout.txt (the file name specified at the top of this file) for logging outside of stdout and stderr
     * @param s - the string to log
     * @author Theresa Inzerillo, Preston Mueller
     */
    private static void log(String s) {
    	s += "\n";
    	
    	try {
			Files.write(Paths.get(logFile), s.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
