import org.junit.Assert;
import org.junit.Test;
import pro.devcraft.TicTacToeBoard;
import pro.devcraft.TicTacToeBoardFormatter;
import pro.devcraft.TicTacToeValues;

public class TicTacToeTextUITest {
    @Test
    public void PrintBoard(){
        TicTacToeBoard board = new TicTacToeBoard();
        board.set(1,1, TicTacToeValues.O);
        board.set(0,1,TicTacToeValues.X);
        String expected = " |X| \n" +
                          "-+-+-\n" +
                          " |O| \n" +
                          "-+-+-\n" +
                          " | | ";
        String actual = TicTacToeBoardFormatter.format(board.getValues());
        Assert.assertEquals(expected, actual);
    }
}
