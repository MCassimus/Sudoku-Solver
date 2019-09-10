public class PuzzleSolver{
	public int[][] puzzle = new int[9][9];
	
	public PuzzleSolver(String fileName)
	{
		//initialize the puzzle
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				puzzle[x][y] = 0;
			}
		}
	}
	
	public void printPuzzle()
	{
		for(int y = 0; y < 9; y++)
		{
			if(y % 3 == 0)
			{
				System.out.println("*---*---*---*");	
			}
			
			for(int x = 0; x < 9; x++)
			{
				if(x % 3 == 0)
				{
					System.out.print("|");	
				}
				
				System.out.print(puzzle[x][y]);
			}
			
			System.out.println("|");//add line break and final character
		}
		System.out.println("*---*---*---*");//cap off the bottom of the puzzle	
	}
}