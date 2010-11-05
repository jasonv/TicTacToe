package com.jasonv.tictactoe.ai;


/**
   A partial implementation from the wikipedia rules.
   http://en.wikipedia.org/wiki/Tic-tac-toe#Strategy
   1) Win: If you have two in a row, play the third to get three in a row.
   2) Block: If the opponent has two in a row, play the third to block them.
   3) Fork: Create an opportunity where you can win in two ways.
   4) Block Opponent's Fork:
   Option 1: Create two in a row to force the opponent into defending,
   as long as it doesn't result in them creating a fork or winning. 
   For example, if "X" has a corner, "O" has the center, and "X" has the 
   opposite corner as well, "O" must not play a corner in order to win. 
   (Playing a corner in this scenario creates a fork for "X" to win.)
   TODO: Implement
   Option 2: If there is a configuration where the opponent can fork, block that fork.
   TODO: Implement
   5) Center: Play the center.
   6) Middle: If the opponent is in the corner, play a center of that 
 	  row or column.
   7) Empty Corner: Play in a corner square.
   8) Empty Side: Play in a middle square on any of the 4 sides.
 */
public class AiWikipediaSlow extends Ai {

	public int makeMove(char playersMarker, char[] boardArray)
	{
		char opponentMarker = getOpponentMarker(playersMarker);
		try {
			step01_win(playersMarker,opponentMarker,boardArray);
			step02_blockWin(playersMarker,opponentMarker,boardArray);
			step03_fork(playersMarker,opponentMarker,boardArray);
			step05_playCenter(playersMarker, opponentMarker, boardArray);
			step06_twoInARow(playersMarker, opponentMarker, boardArray);
			step07_playEmptyCorner(playersMarker, opponentMarker, boardArray);
			step08_playEmptySide(playersMarker, opponentMarker, boardArray);
		} catch (AiMove e) {
			moveHistory += e.getMoveDisplay();
			return e.getSquare();
		}
		System.exit(0);
		return -1;
	}
	private char getOpponentMarker(char playersMarker) {
		char opponentMarker = ' ';
		if(playersMarker=='X')
		{
			opponentMarker='O';
		}
		else
		{
			opponentMarker='X';			
		}
		return opponentMarker;
	}
	public void step01_win(char playersMarker, char opponentMarker, char boardArray[]) throws AiMove
	{
		int winLines[][] = {
				{1,2,3},{4,5,6},{7,8,9},
				{1,4,7},{2,5,8},{3,6,9},
				{1,5,9},{3,5,7}
		};	
		for(int winLine[]:winLines)
		{
			
			int playerMarkerCount = 0;
			int openSquare = -1;
			for(int square:winLine)
			{
				if(boardArray[square-1]==playersMarker)
				{
					playerMarkerCount++;
				}
				if(boardArray[square-1]==' ')
				{
					openSquare = square;
				}
			}
			if(playerMarkerCount==2 && openSquare > 0)
			{
				throw new AiMove("Win",boardArray,openSquare);
			}
		}
	}

	public void step02_blockWin(char playersMarker, char opponentMarker, char boardArray[]) throws AiMove
	{
		int winLines[][] = {
				{1,2,3},{4,5,6},{7,8,9},
				{1,4,7},{2,5,8},{3,6,9},
				{1,5,9},{3,5,7}
		};
		for(int winLine[]:winLines)
		{
			int opponentMarkerCount = 0;
			int openSquare = -1;
			for(int square:winLine)
			{
				if(boardArray[square-1]==opponentMarker)
				{
					opponentMarkerCount++;
				}
				if(boardArray[square-1]==' ')
				{
					openSquare = square;
				}
			}
			if(opponentMarkerCount==2 && openSquare > 0)
			{
				throw new AiMove("Block",boardArray,openSquare);
			}
		}
	}
	
	public void step03_fork(char playersMarker, char opponentMarker, char boardArray[]) throws AiMove
	{
		int forkSquares[][] = {
				{7,4,1,2,3}, // Corner L's
				{1,2,3,6,9},
				{3,6,9,8,7},
				{9,8,7,4,1},
				
				{1,2,3,5,7}, // 45 Angle with row
				{3,2,1,5,9},
				{3,5,7,8,9},
				{1,5,9,8,7},
				
				{1,4,7,5,3}, // 45 angle with col
				{7,4,1,5,9},
				{3,6,9,5,1}, 
				{9,6,3,5,7}
		};
		for(int[] square:forkSquares)
		{
			if(
					boardArray[square[0]-1]==playersMarker &&
					boardArray[square[1]-1]==' ' &&
					boardArray[square[2]-1]==' ' &&
					boardArray[square[3]-1]==' ' &&
					boardArray[square[4]-1]==playersMarker) 
			{
				throw new AiMove("Fork",boardArray,square[2]);
			}
		}
	}
	public void step05_playCenter(char playersMarker,char opponentMarker, char boardArray[]) throws AiMove
	{
		if(boardArray[5-1]==' ')
		{
			throw new AiMove("Center",boardArray,5);
		}
	}
	
	public void step06_twoInARow(char playersMarker,char opponentMarker, char boardArray[]) throws AiMove
	{
		int lSquares[][] = {{1,2,4},{3,2,6},{7,4,8},{9,6,8}};
		for(int[] square:lSquares)
		{
			if(boardArray[square[0]-1]==opponentMarker)
			{
				if(boardArray[square[1]-1]==' ')
				{
					throw new AiMove("Middle",boardArray,square[1]);
				}
				if(boardArray[square[2]-1]==' ')
				{
					throw new AiMove("Middle",boardArray,square[2]);
				}
			}
		}
	}
	
	public void step07_playEmptyCorner(char playersMarker,char opponentMarker, char boardArray[]) throws AiMove
	{
		int bestSquares[] = {1,3,7,9};
		for(int square:bestSquares)
		{
			if(boardArray[square-1]==' ')
			{
				throw new AiMove("Empty corner",boardArray,square);
			}
		}
	}
	
	public void step08_playEmptySide(char playersMarker,char opponentMarker, char boardArray[]) throws AiMove
	{
		int bestSquares[] = {2,6,8,4};
		for(int square:bestSquares)
		{
			if(boardArray[square-1]==' ')
			{
				throw new AiMove("Empty Side",boardArray,square);
			}
		}
	}


}
