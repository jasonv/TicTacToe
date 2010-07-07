package com.jasonv.tictactoe;

public class AiBestSquares extends Ai {
	public int makeMove(char player, char[] boardArray)
	{
		int bestSquares[] = {5,1,3,7,9,2,6,8,4};
		for(int square:bestSquares)
		{
			if(boardArray[square-1]==' ')
			{
				return square;
			}
		}
		return -1;
	}
}
