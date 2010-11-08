package com.jasonv.tictactoe.ai;


/**
   An implementation from the wikipedia rules.
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
public class AiWikipedia extends Ai {


	public int makeMove(char playersMarker, char[] boardArray)
	{
		char opponentsMarker = getOpponentMarker(playersMarker);

		//-------------------------------------------------------------------------
		// Sets of Squares
		//-------------------------------------------------------------------------
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
	

		int winLines[][] = {
				{1,2,3},{4,5,6},{7,8,9},
				{1,4,7},{2,5,8},{3,6,9},
				{1,5,9},{3,5,7}
		};	
		
		//-------------------------------------------------------------------------
		// Step 1 - Make a winning move.
		//-------------------------------------------------------------------------

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
				//throw new AiMove("Win",boardArray,openSquare);
				stepHistory+=("> 1");
				return openSquare;
			}
		}
		//-------------------------------------------------------------------------
		// Step 2 - Block your opponents wining move.
		//-------------------------------------------------------------------------
		for(int winLine[]:winLines)
		{
			int opponentMarkerCount = 0;
			int openSquare = -1;
			for(int square:winLine)
			{
				if(boardArray[square-1]==opponentsMarker)
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
				//throw new AiMove("Block",boardArray,openSquare);
				stepHistory+=("> 2");
				return openSquare;
			}
		}
		//-------------------------------------------------------------------------
		// Step 3 - Create a fork if possible.
		//-------------------------------------------------------------------------
		for(int[] square:forkSquares)
		{
			if(
					boardArray[square[0]-1]==playersMarker &&
					boardArray[square[1]-1]==' ' &&
					boardArray[square[2]-1]==' ' &&
					boardArray[square[3]-1]==' ' &&
					boardArray[square[4]-1]==playersMarker) 
			{
				//throw new AiMove("Fork",boardArray,square[2]);
				stepHistory+=("> 3");
				return square[2];
			}
		}
		//-------------------------------------------------------------------------
		// Step 4a - Block the double fork
		//-------------------------------------------------------------------------
		if
		(
			boardArray[1-1]==opponentsMarker &&
			boardArray[5-1]==playersMarker &&
			boardArray[9-1]==opponentsMarker
		)
		{
			stepHistory+=("> 4a");
			return 2;			
		}

		if
		(
			boardArray[3-1]==opponentsMarker &&
			boardArray[5-1]==playersMarker &&
			boardArray[7-1]==opponentsMarker
		)
		{
			stepHistory+=("> 4a");
			return 2;			
		}

		
		//-------------------------------------------------------------------------
		// Step 4b - Block a fork
		//-------------------------------------------------------------------------
		for(int[] square:forkSquares)
		{
			if(boardArray[square[2]-1]==' ')
			{
				int opponentMarkerCountALine = 0;
				int opponentMarkerCountBLine = 0;
				int blankCountALine = 0;
				int blankCountBLine = 0;
				
				// Line A
				if(boardArray[square[0]-1]==opponentsMarker)
				{
					opponentMarkerCountALine++;
				}
				if(boardArray[square[0]-1]==' ')
				{
					blankCountALine++;
				}
				if(boardArray[square[1]-1]==opponentsMarker)
				{
					opponentMarkerCountALine++;
				}
				if(boardArray[square[1]-1]==' ')
				{
					blankCountALine++;
				}

				// Line B
				if(boardArray[square[3]-1]==opponentsMarker)
				{
					opponentMarkerCountBLine++;
				}
				if(boardArray[square[3]-1]==' ')
				{
					blankCountBLine++;
				}
				if(boardArray[square[4]-1]==opponentsMarker)
				{
					opponentMarkerCountBLine++;
				}
				if(boardArray[square[4]-1]==' ')
				{
					blankCountBLine++;
				}
				if(
						opponentMarkerCountALine == 1 &&
						opponentMarkerCountBLine == 1 &&
						blankCountALine == 1 &&
						blankCountBLine == 1
				)
				{
					stepHistory+=("> 4b");
					//throw new AiMove("Fork",boardArray,square[2]);
					return square[2];								
				}	
			}			
		}		
		
		//-------------------------------------------------------------------------
		// Step 5 - Move in the center square.
		//-------------------------------------------------------------------------
		if(boardArray[5-1]==' ')
		{
			//throw new AiMove("Center",boardArray,5);
			stepHistory+=("> 5");
			return 5;
		}

		//-------------------------------------------------------------------------
		// Step 6 - Create a two in the row from the corner.
		//-------------------------------------------------------------------------
		int lSquares[][] = {{1,2,4},{3,2,6},{7,4,8},{9,6,8}};
		for(int[] square:lSquares)
		{
			if(boardArray[square[0]-1]==playersMarker)
			{
				if(boardArray[square[1]-1]==' ')
				{
					//throw new AiMove("Middle",boardArray,square[1]);
					stepHistory+=("> 6");
					return square[1];
				}
				if(boardArray[square[2]-1]==' ')
				{
					//throw new AiMove("Middle",boardArray,square[2]);
					stepHistory+=("> 6");
					return square[2];
				}
			}
		}

		//-------------------------------------------------------------------------
		// Step 7 - Move into an empty corner.
		//-------------------------------------------------------------------------
		int bestSquares[] = {1,3,7,9};
		for(int square:bestSquares)
		{
			if(boardArray[square-1]==' ')
			{
				//throw new AiMove("Empty corner",boardArray,square);
				stepHistory+=("> 7");
				return square;
			}
		}
		//-------------------------------------------------------------------------
		// Step 8 - Move into an empty side.
		//-------------------------------------------------------------------------
		int bestSquares2[] = {2,6,8,4};
		for(int square:bestSquares2)
		{
			if(boardArray[square-1]==' ')
			{
				//throw new AiMove("Empty Side",boardArray,square);
				stepHistory+=("> 8");
				return square;
			}
		}		
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

	


}
