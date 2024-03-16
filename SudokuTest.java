import java.util.Scanner;


/**
 * Prompts the user for file information, reads in the puzzle (and solution if provided),
 * solves the puzzle using SudokuSolver and checks it against the solution (if applicable).
 *
 * THIS IS THE MAIN ClASS FOR TESTING THE PROGRAM
 
 *
 * @author Charlie Apolinsky
 * @version 1.1.0 Build 2023.10.13
 */
public class SudokuTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the filename of the puzzle: ");
        SudokuPuzzle puzzle = new SudokuPuzzle(input.nextLine());
        System.out.println("Enter the filename of the solution (optional): ");
        String solutionFileName = input.nextLine();
        System.out.println("Starting puzzle: \n" + puzzle); //Print the puzzle
        SudokuSolver solver = new SudokuSolver(puzzle);
        SudokuPuzzle solvedPuzzle = solver.solve(); //Solve the puzzle
        System.out.println("Solved puzzle: \n" + solvedPuzzle);
        if (!solutionFileName.isEmpty()) { //Executes if a solution file was provided
            SudokuPuzzle solution = new SudokuPuzzle(solutionFileName);
            if (solvedPuzzle.equals(solution)) {
                System.out.println("Solution is correct!");
            } else {
                System.out.println("Solution is NOT correct");
            }
        }
    }
}
