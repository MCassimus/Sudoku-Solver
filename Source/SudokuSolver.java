/*
 * File : Main.java
 * Date : 9/10/2019
 * Authors : Michael Cassimus + Tim Peters
 * Project URL : https://github.com/MCassimus/Sudoku-Solver/
 * Description : This is the entry point of the application, and is the "controller" of the 
 * 				 program's flow as it calls methods from other classes to complete its task
 */

import java.util.Scanner; //input

public class SudokuSolver {
	// Class members
	public static String fileBase = "../Puzzles/";

	// Entry point of the application
	public static void main(String[] args) {
		// Method variables are declared here
		Scanner input = new Scanner(System.in);
		String selectedPuzzleFile = "";

		// Gather input from the user on which puzzle they want to select
		System.out.print("Enter Sudoku Puzzle File Name : ");
		selectedPuzzleFile += input.nextLine();

		// Initialize the puzzle solver and start the solving algorithm
		PuzzleSolver solver = new PuzzleSolver(fileBase + selectedPuzzleFile);

		if (solver.isLoaded)
			solver.printPuzzle();
		else
			System.out.println("File not found. Please try again.");

		// Closing the application
		System.out.print("Press any key to continue ");
		input.nextLine();

		input.close();
	}
}