import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A class that solves the puzzle using trial-and-error and backtracking. Contains 1 method (solve)
 * that executes the solving of the puzzle, and a helper function used to generate the next move.
 *
 * @author Charlie Apolinsky
 * @version 1.1.0 Build 2023.10.13
 */
public class SudokuSolver {
    private SudokuPuzzle puzzle;
    private Deque<SudokuMove> moveDeque = new ArrayDeque<>();


    /**
     * Constructs an instance of the SudokuSolver class.
     * Simply takes an input SudokuPuzzle and stores it locally.
     *
     * @param puzzle the input puzzle.
     */
    SudokuSolver(SudokuPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Solves the sudoku puzzle.
     */
    public SudokuPuzzle solve() {
        int lastGuess = 0;
        while (puzzle.getEmptySpace() != null) { //Finishes when there are no empty spaces left
            SudokuMove newMove = generateMove(lastGuess); 
            if (newMove == null) {
                SudokuMove lastMove = moveDeque.pop();
                lastGuess = lastMove.getNumber();
                puzzle.undoMove(lastMove);
            } else {
                puzzle.executeMove(newMove);
                moveDeque.push(newMove);
                lastGuess = 0;
            }
        }
        return puzzle;
    }

    /**
     * Helper function to generate and return a new valid sudoku move.
     * @param lastGuess the previous number that the solver tried.
     * @return the valid SudokuMove
     */
    public SudokuMove generateMove(int lastGuess) {
        int[] tilePosition = puzzle.getEmptySpace();
        int rowNum = tilePosition[0];
        int colNum = tilePosition[1];
        for (int i = lastGuess + 1; i < 10; i++) {
            if (puzzle.validMove(i, rowNum, colNum)) {
                return new SudokuMove(i, rowNum, colNum);
            }
        }
        return null;
    }
}
