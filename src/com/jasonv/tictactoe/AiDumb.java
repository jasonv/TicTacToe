package com.jasonv.tictactoe;

public class AiDumb extends Ai {
	public int makeMove(char player, char[] boardArray)
	{
		int i=1;
		for(char space:boardArray)
		{
			if(space==' ')
			{
				return i;
			}
			i++;
		}
		return -1;
	}
}
