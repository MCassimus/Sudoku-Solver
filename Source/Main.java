import java.util.Scanner; //input

public class Main{
	public static String fileName = "../Puzzles/";
	
	public static void main(String [] args){
		// create Scanner to obtain input from command window
      	Scanner input = new Scanner(System.in);
      	
      	System.out.print("Enter Sudoku Puzzle File Name : ");
      	fileName += input.nextLine();
      	
      	PuzzleSolver solver = new PuzzleSolver("");
      	solver.printPuzzle();
      	
      	
      	System.out.print("Press any key to continue ");
      	input.next();
	}
}