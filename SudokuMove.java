/**
 * A simple class that represents a sudoku move. Contains various
 * getter methods to access the specific information about the move.
 *
 * @author Charlie Apolinsky
 * @version 1.1.0 Build 2023.10.13
 */
public class SudokuMove {
    private int row;
    private int column;
    private int number;

    /**
     * Constructs a new Sudoku move instance
     * @param row the row of the box to be updated
     * @param column the column of the box to be updated
     * @param number the number to be placed in the box
     */
    public SudokuMove(int number, int row, int column) {
        this.row = row;
        this.column = column;
        this.number = number;
    }

    /**
     * Gets the integer value of the row.
     * @return the row (y-cord) of the move.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the integer value of the column.
     * @return the row (x-cord) of the move.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Gets the integer value of the number.
     * @return the number being placed in the box.
     */
    public int getNumber() {
        return this.number;
    }
}
