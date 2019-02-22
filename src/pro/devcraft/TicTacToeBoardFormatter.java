package pro.devcraft;

public class TicTacToeBoardFormatter {
    public static String format(TicTacToeValues[][] values) {
        String boardPrint = "";
        for (int row = 0; row < 3; row++) {
            boardPrint += getRowString(values[row]);
            if (row < 2) boardPrint += "\n-+-+-\n";
        }
        return boardPrint;
    }

    private static String getRowString(TicTacToeValues[] row) {
        String rowString = "";
        for (int column = 0; column < 3; column++) {
            rowString += getCellString(row[column]);
            if (column < 2) rowString += "|";
        }
        return rowString;
    }

    private static String getCellString(TicTacToeValues cellValue) {
        String cellContent = " ";
        if (cellValue != null)  cellContent = cellValue.name();
        return cellContent;
    }
}
