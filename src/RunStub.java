import referee.Referee;
/**
 * Created by prestonmueller on 1/17/16.
 */
public class RunStub {

    public static void main(String[] args) throws Exception {

        String command_to_execute_player1="java TheresaPrestonPlayer";
        String command_to_execute_player2="java -jar testPlayer2.jar";
        int board_height=5;
        int board_width=5;
        int N=4;
        int announceNameTimeLimit = 5;
        int moveTimeLimit = 20;
        Referee ref=new Referee(command_to_execute_player1, command_to_execute_player2, board_height, board_width,  N,  announceNameTimeLimit,  moveTimeLimit);


        ref.start();
    }

}
