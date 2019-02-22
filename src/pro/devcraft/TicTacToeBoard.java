package pro.devcraft;

public class TicTacToeBoard {
    private TicTacToeValues[][] boardValues = new TicTacToeValues[][]{
            {null,null,null},
            {null,null,null},
            {null,null,null}};
    private TicTacToeValues winner = null;
    private int moves = 0;

    public TicTacToeValues[][] getValues() {
        return boardValues;
    }

    public TicTacToeValues Winner() {
        return winner;
    }

    public void set(int row, int column, TicTacToeValues value) {
        if (moves == 9) throw new GameOverException();
        if (winner != null) throw new GameOverException();
        if (boardValues[row][column] != null) {
            throw new PositionFullException();
        }
        boardValues[row][column] = value;
        moves++;
        checkWinner();
    }

    private void checkWinner() {
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
            int [] position0 = winScenario[0];
            int [] position1 = winScenario[1];
            int [] position2 = winScenario[2];

            TicTacToeValues potentialWinner = boardValues[position0[0]][position0[1]];
            if (potentialWinner == null) continue;
            if (boardValues[position1[0]][position1[1]] != potentialWinner) continue;
            if (boardValues[position2[0]][position2[1]] == potentialWinner) winner = potentialWinner;
        }
    }

    public class PositionFullException extends RuntimeException {
    }

    public class GameOverException extends RuntimeException {
    }
}
