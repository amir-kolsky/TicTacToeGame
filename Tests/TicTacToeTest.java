import org.junit.Assert;
import org.junit.Test;
import pro.devcraft.TicTacToeBoard;
import pro.devcraft.TicTacToeBoard.PositionFullException;
import pro.devcraft.TicTacToeValues;

import java.util.Arrays;

public class TicTacToeTest {

    @Test
    public void BoardValues() {
        String[] expectedTicTacToeValues = new String[]{"O", "X" };
        TicTacToeValues[] values = TicTacToeValues.values();
        String[] actualTicTacToeValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            actualTicTacToeValues[i] = values[i].name();
        }

        Arrays.sort(expectedTicTacToeValues);
        Arrays.sort(actualTicTacToeValues);
        Assert.assertArrayEquals(expectedTicTacToeValues, actualTicTacToeValues);
    }

    @Test
    public void InitialBoard() {
        TicTacToeValues[][] emptyBoardValues = getEmptyBoardValues();
        TicTacToeBoard actualBoard = new TicTacToeBoard();
        Assert.assertArrayEquals(emptyBoardValues, actualBoard.getValues());
        Assert.assertNull(actualBoard.Winner());
    }

    @Test
    public void SetPositionIfEmpty() {
        final int row = 1;
        final int column = 2;
        final TicTacToeValues value = TicTacToeValues.O;
        TicTacToeValues[][] emptyBoardValues = getEmptyBoardValues();
        emptyBoardValues[row][column] = value;
        TicTacToeBoard actualBoard = new TicTacToeBoard();

        actualBoard.set(row, column, value);

        Assert.assertArrayEquals(emptyBoardValues, actualBoard.getValues());
    }

    @Test
    public void SetPositionIfAlreadyFull() {
        final int row = 1;
        final int column = 2;
        TicTacToeValues value = TicTacToeValues.X;

        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        ticTacToeBoard.set(row, column, value);

        try {
            ticTacToeBoard.set(row, column, TicTacToeValues.O);
            Assert.fail(TicTacToeBoard.PositionFullException.class.getName() + " Should have been thrown");
        } catch (PositionFullException e) {
            Assert.assertEquals(value, ticTacToeBoard.getValues()[row][column]);
        }
    }

    @Test
    public void DeclareWinnerIfThreeInaRow() {
        int[][] row1 = {{0, 0}, {0, 1}, {0, 2}};
        int[][] row2 = {{1, 0}, {1, 1}, {1, 2}};
        int[][] row3 = {{2, 0}, {2, 1}, {2, 2}};
        int[][] diag1 = {{0, 0}, {1, 1}, {2, 2}};
        int[][] diag2 = {{2, 0}, {1, 1}, {0, 2}};
        int[][] col1 = {{0, 0}, {1, 0}, {2, 0}};
        int[][] col2 = {{0, 1}, {1, 1}, {2, 1}};
        int[][] col3 = {{0, 2}, {1, 2}, {2, 2}};

        int[][][] winScenarios = new int[][][]{row1, row2, row3, diag1, diag2, col1, col2, col3};
        for (int[][] winScenario: winScenarios) {
            TicTacToeValues winner = TicTacToeValues.X;
            TicTacToeBoard ticTacToeBoard = InitializeBoardWithScenario(winScenario, winner);
            TicTacToeValues actualWinner = ticTacToeBoard.Winner();
            Assert.assertEquals(winner, actualWinner);
        }
    }

    @Test
    public void AddToBoardIfGameWon() {
        int[][] winScenario = {{0, 2}, {1, 2}, {2, 2}};
        TicTacToeBoard ticTacToeBoard = InitializeBoardWithScenario(winScenario, TicTacToeValues.O);
        int nextRow = 2;
        int nextColumn = 2;
        TicTacToeValues expectedValue = null;
        expectedValue = ticTacToeBoard.getValues()[nextRow][nextColumn];
        try {
            ticTacToeBoard.set(nextRow, nextColumn, TicTacToeValues.O);
            Assert.fail(TicTacToeBoard.GameOverException.class.getName() + " Should have been thrown");
        } catch (TicTacToeBoard.GameOverException e) {
            Assert.assertEquals(expectedValue, ticTacToeBoard.getValues()[nextRow][nextColumn]);
        }
    }

    @Test
    public void AddToBoardIfGameTied() {
        int[][] xPositions = {{0, 0},{0, 1},{1, 1},{1, 2},{2, 0}};
        int[][] oPositions = {{0, 2},{1, 0},{2, 1},{2, 2}};

        TicTacToeBoard ticTacToeBoard = InitializeBoardWithScenario(xPositions, TicTacToeValues.X);
        fillBoard(oPositions, TicTacToeValues.O, ticTacToeBoard);
        int nextRow = 2;
        int nextColumn = 2;
        TicTacToeValues expectedValue = ticTacToeBoard.getValues()[nextRow][nextColumn];
        try {
            ticTacToeBoard.set(nextRow, nextColumn, TicTacToeValues.O);
            Assert.fail(TicTacToeBoard.GameOverException.class.getName() + " Should have been thrown");
        } catch (TicTacToeBoard.GameOverException e) {
            Assert.assertEquals(expectedValue, ticTacToeBoard.getValues()[nextRow][nextColumn]);
        }
    }

    private TicTacToeBoard InitializeBoardWithScenario(int[][] scenario, TicTacToeValues player) {
        TicTacToeBoard actualBoard = new TicTacToeBoard();
        fillBoard(scenario, player, actualBoard);
        return actualBoard;
    }

    private void fillBoard(int[][] scenario, TicTacToeValues player, TicTacToeBoard board) {
        for (int[] position: scenario) {
            board.set(position[0], position[1], player);
        }
    }

    private TicTacToeValues[][] getEmptyBoardValues() {
        return new TicTacToeValues[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null}};
    }
}