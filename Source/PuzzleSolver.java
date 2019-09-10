/*
 * File : PuzzleSolver.java
 * Date : 9/10/2019
 * Authors : Michael Cassimus + Tim Peters
 * Project URL : https://github.com/MCassimus/Sudoku-Solver/
 * Description : This class holds the main logic of the application and will hold methods
 * 				 such as printing, importing and exporting, and solving the puzzle.
 */


public class PuzzleSolver{
	//Class members
	private int[][] puzzle = new int[9][9];
	
	
	//Constructor
	public PuzzleSolver(String fileName)
	{
		//initialize the puzzle
		//this loop is temporary
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				puzzle[x][y] = 0;
			}
		}
	}
	
	
	//Prints the puzzle stored in the puzzle array variable
	public void printPuzzle()
	{
		for(int y = 0; y < 9; y++)
		{
			//we are at the top of a cell
			if(y % 3 == 0)
			{
				//print a border
				System.out.println("*---*---*---*");	
			}
			
			for(int x = 0; x < 9; x++)
			{
				//we are at the edge of a cell
				if(x % 3 == 0)
				{
					//print a border
					System.out.print("|");	
				}
				
				//show the actual numbers of the puzzle at the current location
				System.out.print(puzzle[x][y]);
			}
			
			//add line break and final character
			System.out.println("|");
		}
		
		//cap off the bottom of the puzzle
		System.out.println("*---*---*---*");	
	}
}