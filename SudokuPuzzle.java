import java.util.Scanner;
import java.io.File;


/**
 * A class that represents a sudoku puzzle. Contains instance variable boardState which
 * is a 2D-array representation of a Sudoku board. Includes a variety of methods used to
 * get information about the board (if a move is valid, where empty spaces are, format the board
 * as a string) and methods to update the board (undoing and executing moves).
 *
 * @author Charlie Apolinsky
 * @version 1.1.0 Build 2023.10.13
 */
public class SudokuPuzzle {
    private int[][] boardState = new int[9][9];

    /**
     * Construct a sudoku board by reading in a file.
     *
     * @param inputFilename the file that contains the puzzle data.
     */
    public SudokuPuzzle(String inputFilename) {
        Scanner scan;
        try {
            scan = new Scanner(new File(inputFilename));
        } catch (Exception e) {
            System.out.println("Failed to read file.");
            return;
        }
        int row = 0;
        int column = 0;
        while (row < 9) {
            while (column < 9) {
                boardState[row][column] = scan.nextInt();
                column++;
            }
            column = 0;
            row++;
        }
        scan.close();
    }

    /**
     * Converts the puzzle to a printable string.
     */
    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tileVal = boardState[i][j];
                if (tileVal == 0) {
                    returnString += "_ ";
                } else {
                    returnString += tileVal + " ";
                }
            }
            returnString += "\n";
            }
        return returnString;
    }

    /**
     * Checks whether obj is equivalent to this SudokuPuzzle.
     *
     * @param obj the object being compared to the SudokuPuzzle instance.
     * @return true if they are the same. False if they are not the same object type or
     * if the puzzles are not equivalent.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SudokuPuzzle) {
            SudokuPuzzle puzzle2 = (SudokuPuzzle) obj;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (puzzle2.boardState[i][j] != this.boardState[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Gets the coordinates of the next unfilled space in the puzzle.
     * @return an integer array representing the box's position {rowNum, colNum}.
     */
    public int[] getEmptySpace() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardState[i][j] == 0) {
                    return new int[] {i , j};
                }
            }
        }
        return null;
    }


    /**
     * Checks to see if an intended move is valid.
     *
     * @param rowNum the row of the box to be updated
     * @param columnNum the column of the box to be updated
     * @param num the number to be placed in the box
     *
     * @return true if the move is valid, false if not.
     */
    public boolean validMove(int num, int rowNum, int columnNum) {
        //Check if the intended number is already in the column or row.
        for (int i = 0; i < 9; i++) {
            if (boardState[rowNum][i] == num || boardState[i][columnNum] == num) {
                return false;
            }
        }
        //Check if the intended number is already in the local 3x3 grid.
        int localBoxRow = rowNum / 3; //equals 0 for top row, 1 for middle, 2 for bottom
        int localBoxCol = columnNum / 3; //equals 0 for left col, 1 for middle, 2 for right
        for (int i = 0; i < 3; i++) {
            int rowIndex = localBoxRow * 3 + i;
                for (int j = 0; j < 3; j++) {
                    int colIndex = localBoxCol * 3 + j;
                    if (boardState[rowIndex][colIndex] == num) {
                        return false;
                    }
                }
        }
        return true;
    }

    /**
     * Executes a move and updates the board.
     *
     * @param move the validated move to be executed.
     */
    public void executeMove(SudokuMove move) {
        int rowNum = move.getRow();
        int columnNum = move.getColumn();
        int num = move.getNumber();
        boardState[rowNum][columnNum] = num;
    }

    /**
     * Takes a past move and reverts the box to 0.
     *
     * @param move the move to be undone.
     */
    public void undoMove(SudokuMove move) {
        int rowNum = move.getRow();
        int colNum = move.getColumn();
        boardState[rowNum][colNum] = 0;
    }
}


