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
		isLoaded = LoadPuzzle(fileName);
	}
	
	
	//load the puzzle
	private boolean LoadPuzzle(String fName)
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
			e.printStackTrace();
			return false;//file is not found, print stacktrace + inform program
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