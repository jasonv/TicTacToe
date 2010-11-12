package com.jasonv.tictactoe.ai;

import junit.framework.TestCase;

public class AiMiniMaxTest extends TestCase 
{
	public void setupBoardAndTest(int expected, String boardString, String playerString)
	{
	
		char player = playerString.toCharArray()[0];
		char[] board = boardString.toCharArray();
		int result = AiMiniMax.minimax(player,board);
		assertEquals(expected,result);
	}
	
	public void  testEmptyBoardX()
	{
		String board = "X  " +
		               "   " +
		               "   ";
		setupBoardAndTest(5,board,"O");
	}
	public void  testEmptyBoardO()
	{
		int board[][] = 
		{
				{-1,-1,-1},
				{-1,-1,-1},
				{-1,-1,-1}
		};
		int piece = 0;
		assertEquals(1,AiMiniMax.minimax(board, piece));
	}
	public void  testMiddleO()
	{
		int board[][] = 
		{
				{-1,-1,-1},
				{-1,0 ,-1},
				{-1,-1,-1}
		};
		int piece = 1;
		assertEquals(1,AiMiniMax.minimax(board, piece));
	}
	public void  testTopLeftO()
	{
		int board[][] = 
		{
				{1 ,-1,-1},
				{-1,-1,-1},
				{-1,-1,-1}
		};
		int ai = 0;
		
		System.out.println("-------------------------------");
		AiMiniMax.printBoard(board);
		int pos = AiMiniMax.minimax(board,ai);  
		System.out.println("" + pos + "<=minimax(board," + ai + ")");
		AiMiniMax.printBoard(board);
		System.out.println("-------------------------------");
		assertEquals(5,pos);
	}
	public void  testTopLeftAndMiddleO()
	{
		int board[][] = 
		{
				{0 ,1, -1},
				{-1,-1,-1},
				{-1,-1,-1}
		};
		int piece = 0;
		assertEquals(4,AiMiniMax.minimax(board, piece));
	}
	public void  testLeafO()
	{
		int board[][] = 
		{
				{1 , 1, 0},
				{-1, 0,-1},
				{-1,-1,-1}
		};
		int piece = 0;
		assertEquals(7,AiMiniMax.minimax(board, piece));
	}

}
