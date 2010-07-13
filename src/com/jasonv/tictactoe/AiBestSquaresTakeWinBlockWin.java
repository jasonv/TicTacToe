package com.jasonv.tictactoe;

public class AiBestSquaresTakeWinBlockWin extends Ai {
	public int makeMove(char player, char[] boardArray)
	{
		int winLines[][] = {
				{1,2,3},{4,5,6},{7,8,9},
				{1,4,7},{2,5,8},{3,6,9},
				{1,5,9},{3,5,7}
		};		

		// Get opposite player
		char opponentMarker = ' ';
		if(player=='X')
		{
			opponentMarker='O';
		}
		else
		{
			opponentMarker='X';			
		}
	
		
		// Take a win
		for(int winLine[]:winLines)
		{
			int playerMarkerCount = 0;
			int openSquare = -1;
			for(int square:winLine)
			{
				if(boardArray[square-1]==player)
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
				System.out.println(String.copyValueOf(boardArray) + "" + openSquare);
				return openSquare;
			}
		}
		
		// Block you opponents win on the next move
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
				System.out.println(String.copyValueOf(boardArray) + "" + openSquare);
				return openSquare;
			}
		}
		
		// Find The Best Square
		int bestSquares[] = {5,1,3,7,9,2,6,8,4};
		for(int square:bestSquares)
		{
			if(boardArray[square-1]==' ')
			{
				
				System.out.println(String.copyValueOf(boardArray) + "" + square);
				return square;
			}
		}
		return -2;
	}
}
