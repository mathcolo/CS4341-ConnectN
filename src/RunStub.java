import referee.Referee;
/**
 * Created by prestonmueller on 1/17/16.
 */
public class RunStub {

    public static void main(String[] args) throws Exception {

        String command_to_execute_player1="java -jar testPlayer.jar";
        String command_to_execute_player2="java TheresaPrestonPlayer";
        int board_height=7;
        int board_width=7;
        int N=4;
        int announceNameTimeLimit = 5;
        int moveTimeLimit = 10;
        Referee ref=new Referee(command_to_execute_player1, command_to_execute_player2, board_height, board_width,  N,  announceNameTimeLimit,  moveTimeLimit);


        ref.start();
    }

}
