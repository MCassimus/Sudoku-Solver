/*
 * File : PuzzleSolver.java
 * Date : 9/10/2019
 * Authors : Michael Cassimus + Tim Peters
 * Project URL : https://github.com/MCassimus/Sudoku-Solver/
 * Description : This class holds the main logic of the application and will hold methods
 * 				 such as printing, importing and exporting, and solving the puzzle.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PuzzleSolver{
	//Class members
	private static int SIZE = 9;
	private int[][] puzzle;
	public boolean isLoaded;
	
	
	//Constructor
	public PuzzleSolver(String fileName)
	{
		isLoaded = loadPuzzle(fileName);
	}
	
	
	//Load the puzzle
	private boolean loadPuzzle(String fName)
	{
		try 
		{
			File inFile = new File(fName);
			Scanner fileReader;
			fileReader = new Scanner(inFile);
			//clear the puzzle (there shouldn't be anything in there at this point)
			puzzle = new int[SIZE][SIZE];
			
			//start reading the input file and load to var
			for(int y = 0; y < SIZE; y++)
			{
				//get the next line and make it a character array (easier to convert to int)
				char [] line = fileReader.nextLine().toCharArray(); 
				
				for(int x = 0; x < SIZE; x++)
				{
					//convert from char to int, then subtract the character conversion offset
					int currentNum = Integer.parseInt(String.valueOf(line[x]));
					puzzle[x][y] =  currentNum;
				}
			}
			
			fileReader.close();
			
			//finished loading without issues. 
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			//e.printStackTrace();
			return false;//file is not found, print stacktrace + inform program
		}
	}
	
	
	//Prints the puzzle stored in the puzzle array variable
	public void printPuzzle()
	{
		for(int y = 0; y < SIZE; y++)
		{
			//we are at the top of a cell
			if(y % 3 == 0)
			{
				//print a border
				System.out.println("*---*---*---*");	
			}
			
			for(int x = 0; x < SIZE; x++)
			{
				//we are at the edge of a cell
				if(x % 3 == 0)
				{
					//print a border
					System.out.print("|");	
				}
				
				//show the actual numbers of the puzzle at the current location
				System.out.print(puzzle[x][y] == 0 ? " " : puzzle[x][y]);
			}
			
			//add line break and final character
			System.out.println("|");
		}
		
		//cap off the bottom of the puzzle
		System.out.println("*---*---*---*\n");	
	}
	
	
	//We will solve this via recursive backtracking so we don't redo all the work
	//just undo the stuff that didn't work. returns returns true when we are moving on
	//and into the next cell. also return true when completed (to fill exit condition)
	// ** Warning ** the stack is about to get REALLY large.
	public boolean solve(boolean showOutput, boolean waitKeyInput)
	{
		for(int y = 0; y < SIZE; y++)
		{
			for(int x = 0; x < SIZE; x++)
			{
				if(puzzle[x][y] == 0)//no solution in current coordinates
				{
					for(int guessedNum = 1; guessedNum <= SIZE; guessedNum++)
					{
						if(isValidMove(x, y, guessedNum))
						{
							puzzle[x][y] = guessedNum;
							
							if(showOutput)
							{
								printPuzzle();
							}
							if(waitKeyInput)
							{
								new java.util.Scanner(System.in).nextLine();
							}
							
							if(solve(showOutput, waitKeyInput))
							{
								return true;	
							}
							else
							{
								puzzle[x][y] = 0;	
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	//Checking Methods
	//check if a specified number is in a row
	private boolean numInRow(int row, int num) 
	{
		for(int i = 0; i < SIZE; i++)
			if(puzzle[i][row] == num) //we found a match in row
				return true;//exit loop with success
		
		//no match found, exit with failure
		return false;
	}

	
	//check if a specified number is in a column
	private boolean numInCol(int col, int num)
	{
		for(int i = 0; i < SIZE; i++)
			if(puzzle[col][i] == num) //we found a match in row
				return true;//exit loop with success
		
		//no match found, exit with failure
		return false;
	}
	
	
	//check if a specified number is in a segment of the puzzle
	private boolean numInChunk(int row, int col, int num)
	{
		//find the chunk of the puzzle where the row + col is locate
		//calculating it this way also leaves it at the top / left most index of that chunk
		int cnkY =  row - row % 3;
		int cnkX =  col - col % 3;
		
		for(int y = cnkY; y < cnkY + 3; y++)
			for(int x = cnkX; x < cnkX + 3; x++)
				if(puzzle[x][y] == num) //we found a match in the chunk
					return true;//exit loop with success
		
		//no match found, exit with failure
		return false;
	}
	
	
	//check if a move violates sudoku rules or not
	private boolean isValidMove(int x, int y, int num)
	{
		boolean colChk = numInCol(x, num);
		boolean rowChk = numInRow(y, num);
		boolean cnkChk = numInChunk(x, y, num);
		
		//all conditions need to be true for valid move
		return !(colChk || rowChk || cnkChk);
	}
}