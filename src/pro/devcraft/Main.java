package pro.devcraft;

import java.util.Scanner;

public class Main {

    private static TicTacToeBoard board = new TicTacToeBoard();

    public static void main(String[] args) {
        runGame();
    }

    private static void runGame() {
        print("Welcome to TicTacToe");
        print(TicTacToeBoardFormatter.format(board.getValues()));
        TicTacToeValues player = TicTacToeValues.X;
        try {
            do {
                getPosition(player);
                print(TicTacToeBoardFormatter.format(board.getValues()));
                if (player == TicTacToeValues.X) player = TicTacToeValues.O;
                else player = TicTacToeValues.X;
            } while (board.Winner() == null);

            print("Game over! Winner is " + board.Winner());
        } catch (TicTacToeBoard.GameOverException e) {
            print("Game over! No Winner");
        }
    }

    private static void getPosition(TicTacToeValues player) {
        do {
            int[] position = readPosition(player);
            try {
                board.set(position[0], position[1], player);
                break;
            } catch (TicTacToeBoard.PositionFullException e) {
            }
        } while (true);
    }

    private static int[] readPosition(TicTacToeValues player) {
        print("Please enter " + player + " position");
        String position = new Scanner(System.in).nextLine();
        String[] positions = position.split("\\s*,\\s*");
        int row = Integer.parseInt(positions[0]);
        int column = Integer.parseInt(positions[1]);
        return new int[]{row, column};
    }

    private static void print(String output) {
        System.out.println(output);
    }
}
