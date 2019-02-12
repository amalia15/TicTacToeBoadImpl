package tictactoe;

public class TicTacToeBoardImpl_Alcantar implements TicTacToeBoard {
	
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;
	
	protected int[] movesArray;
	
	/* EXAMPLE:
	 * new TicTacToeBoardImpl_Skeleton() is
	 *  | | 
	 * _____
	 *  | | 
	 * -----
	 *  | | 
	 * and results in movesArray = [-1,-1,-1,-1,-1,-1,-1,-1,-1]
	 */
	public TicTacToeBoardImpl_Alcantar()
	{
		final int CELL_COUNT = ROW_COUNT* COLUMN_COUNT;
		movesArray = new int[CELL_COUNT];
		for(int i = 0; i < CELL_COUNT; i++)
		{
			movesArray[i] = NO_MOVE;
		}
		
	}
	
	/*  EXAMPLE 1:
	 * [-1,-1,-1,-1,-1,-1,-1,-1,-1].getMark(0, 1) == null
	 * [NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE].getMark(0,1) == null
	 * EXAMPLE 2:
	 * [ 3, 5, 7,-1,-1,-1,-1,-1,-1].getMark(1,0) == Mark.X
	 * EXAMPLE 3:
	 * [ 4, 3, 7, 5,-1,-1,-1,-1,-1].getMark(1,1) == Mark.X
	 */
	public Mark getMark(int row, int column) 
	{
		assert row >= 0;
		assert row < ROW_COUNT;
		
		assert column >= 0;
		assert column < COLUMN_COUNT;
		
		int gridPosition = getGridPosition(row, column); 
		int indexOfGridPosition = getSmallestIndexOfTarget(movesArray, gridPosition);
		if(indexOfGridPosition != -1){
			if(indexOfGridPosition%2 ==0)
			{
				return Mark.X;
			}
			return Mark.O;
		}
		return null;
	}

	/* EXAMPLES:
	 * EXAMPLE 1:
	 * [-1,-1,-1,-1,-1,-1,-1,-1,-1].setMark(2,2) --> [8,-1,-1,-1,-1,-1,-1,-1,-1]
	 * EXAMPLE 2:
	 * [[0,3,6,-1,-1,-1,-1,-1,-1].setMark(1,1) -->[0,3,6,4,-1,-1,-1,-1,-1]
	 * EXAMPLE 3:
	 * [3,6,2,4,1,7,-1.-1,-1].setMark(0,0) -->[6,2,4,1,7,0.-1,-1]
	 */
	public void setMark(int row, int column)
	{
		assert (column <=2): "Number has to be lower to or equal to 2." ;
		assert (column >=0): "Number has to be higher than or equal to 0" ;
		assert (row >=0): "Number has to be higher than or equal to 2." ;
		assert (row <=2): "Number has to be lower than or equal to 2." ;
		assert !isGameOver();
		assert(getMark(row, column) == null): "Grid position has already been marked.";;
		int nextIndex = getSmallestIndexOfTarget(movesArray, -1);
		int gridPosition = getGridPosition(row, column);
		movesArray[nextIndex] = gridPosition;
		System.out.println(toString());
		if(isGameOver())
			System.out.println(getWinner());
		

		
	}

	/* EXAMPLE:
	 * EXAMPLE 1:
	 * [-1,-1,-1,-1,-1,-1,-1,-1,-1].getTurn() == Mark.X
	 * [NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE,NO_MOVE].getTurn() == Mark.X
	 * EXAMPLE 2:
	 * [ 1, 3, 8,-1,-1,-1,-1,-1,-1].getTurn() == Mark.O
	 * EXAMPLE 3:
	 * [4,6,2,8,3,5,0,1,7]getTurn() ==null
	 * EXAMPLE 4:
	 * [6,1,7,0,8,-1,-1,-1,-1].getTurn() == null
	 * 
	 */
	public Mark getTurn() 
	{
		if(isGameOver())
		{
			return null;
		}
		int numberOfMarks = getSmallestIndexOfTarget(movesArray, -1); //finds number of marks in movesArray
		if(numberOfMarks%2 == 0)
		{
			return Mark.X;
		}
		else
		{
			return Mark.O;
		}
		
	}

	/* EXAMPLES:
	 * EXAMPLE 1:
	 * [ 4, 3, 0, 5, 8,-1,-1,-1,-1].isGameOver() == true
	 * EXAMPLE 2:
	 * [ 0, 1, 2, 6, 7, 8, 3, 4 ,5].isGamerOver() == true
	 * EXAMPLE 3: 
	 * [0, 2, -1,-1,-1,-1, -1, -1].isGameOver() = false
	 */
	public boolean isGameOver() {
		int marksOnBoard = getSmallestIndexOfTarget(movesArray, -1);
	
			
			if((getMark(0,0)== getMark(0,1) && getMark(0,1)==getMark(0,2)) && getMark(0,0) != null)
			{
				return true;
			}
			else if((getMark(1,0)== getMark(1,1) && getMark(1,1)==getMark(1,2)) && getMark(1,0) != null)
			{
				return true;
			}
			else if((getMark(2,0)== getMark(2,1) && getMark(2,1)==getMark(2,2)) && getMark(2,0) != null)
			{
				return true;
			}
			else if((getMark(0,0)== getMark(1,0) && getMark(1,0)==getMark(2,0)) && getMark(0,0) != null)
			{
				return true;
			}
			else if((getMark(0,1)== getMark(1,1) && getMark(1,1)==getMark(2,1)) && getMark(0,1) != null)
			{
				return true;
			}
			else if((getMark(0,2)== getMark(1,2) && getMark(1,2)==getMark(2,2)) && getMark(0,2) != null)
			{
				return true;
			}
			else if((getMark(2,0)== getMark(1,1) && getMark(1,1)==getMark(0,2)) && getMark(2,0) != null)
			{
				return true;
			}
			else if((getMark(0,0)== getMark(1,1) && getMark(1,1)==getMark(2,2)) && getMark(0,0) != null)
			{
				return true;
			}
			else if(marksOnBoard == -1)
			{
				return true;
			}
			else
				return false;

	}

	//part of post: rv == null <==> game ended in a tie
		/* EXAMPLES:
		 * EXAMPLE 1:
		 * [6,4,2,8,1,5,0,-1].getWinner() == X
		 * EXAMPLE 2:
		 * [4,6,8,0,1,3,-1,-1].getWinner() == 0
		 * EXAMPLE 3:
		 * [[4,6,2,8,3,5,0,1,7].getWinner()==null
		 */
	public Mark getWinner() {
		
		assert isGameOver(): "Game is not over!";
		
			if((getMark(0,0)== getMark(0,1) && getMark(0,1)==getMark(0,2)) && getMark(0,0) != null)
			{
				return getMark(0,0);
			}
			else if((getMark(1,0)== getMark(1,1) && getMark(1,1)==getMark(1,2)) && getMark(1,0) != null)
			{
				return getMark(1,0);
			}
			else if((getMark(2,0)== getMark(2,1) && getMark(2,1)==getMark(2,2)) && getMark(2,0) != null)
			{
				return getMark(2,0);
			}
			else if((getMark(0,0)== getMark(1,0) && getMark(1,0)==getMark(2,0)) && getMark(0,0) != null)
			{
				return getMark(0,0);
			}
			else if((getMark(0,1)== getMark(1,1) && getMark(1,1)==getMark(2,1)) && getMark(0,1) != null)
			{
				return getMark(0,1);
			}
			else if((getMark(0,2)== getMark(1,2) && getMark(1,2)==getMark(2,2)) && getMark(0,2) != null)
			{
				return getMark(0,2);
			}
			else if((getMark(2,0)== getMark(1,1) && getMark(1,1)==getMark(0,2)) && getMark(2,0) != null)
			{
				return getMark(2,0);
			}
			else if((getMark(0,0)== getMark(1,1) && getMark(1,1)==getMark(2,2)) && getMark(0,0) != null)
			{
				return getMark(0,0);
			}
		
		return null;
		
		
		

	}
	
	public String toString()
	{
		
		String board = "";
		for(int row = 0; row<ROW_COUNT; row++)
		{
			for(int column = 0; column<COLUMN_COUNT;column++)
			{
			
					if(getMark(row,column)==null)	
					{
						if(column==2)
						{
							board+=" \n";
							board += "------\n";
						}
						else
						{
							board+=" | ";
						}
					}
					else
					{
						if( row!=2 && column==2)
						{
							board+=getMark(row,column)+"\n";
							board += "------\n";
						}
						else
						{
							board+=getMark(row,column)+" | ";
						}
					
					}
				
				
			}
			
			
		}

		return board;
		
	}

	//post: returns first occurrence of target
	//post: returns -1 if not found
	private static int getSmallestIndexOfTarget(int[] intArray, int target){
		for(int index = 0; index < intArray.length; index++){
			if(intArray[index] == target){
				return index;
			}
		}
		return NO_MATCH;
	}

	//converts (row, col) to grid position
	private static int getGridPosition(int row, int col)
	{
		int gridPosition = -1;
		gridPosition = ROW_COUNT*row+col;
		return gridPosition;
	}
	


	
	 		
	
	
	
	

	
}