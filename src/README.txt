CS4341 AI - Connect-N Project 1
Theresa Inzerillo and Preston Mueller
January 24, 2016

*** TESTING OUR PLAYER ***

In our Connect-N project, we have submitted an executable jar file to use with the Referee called TheresaPreston.jar. An example execution would be:


	$ java -jar Referee.jar "java -jar testPlayer.jar" "java -jar TheresaPreston.jar" 7 7 4 5 9


Our source code is structured as follows:

	HeuristicEval.java: class that does the heuristic evaluation of a board
	MinimaxBoard.java: class that represented as wrapped Board, as well as commits minimax
	MinimaxReturn.java: class that represents a return up the Minimax tree (needed for double return)
	Move.java: class that encapsulates a Move
	PopManager.java: a class, done using the singleton pattern, that keeps track of who has done a POPOUT move
	TheresaPrestonPlayer.java: the main class of our AI

	Board.java: The modified Board class for our AI (just some additional methods)
	Referee.java: The standard Referee class


Please note, we have implemented all required components of this project, including

	- MINIMAX
	- ALPHA-BETA PRUNING
	- HEURISTIC BOARD EVALUATION
	- EXPERIMENTATION AND WRITEUP
	- DOCUMENTATION IN CODE (AND THIS FILE)