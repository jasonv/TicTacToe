package com.jasonv.tictactoe;

import junit.framework.TestCase;

public class BoardTest extends TestCase 
{
	public void testBoard01()
	{
		Board b = new Board();
		assertEquals("   |   |   ",b.getBoardLayout());
	}
	public void testBoard02()
	{
		Board b = new Board();
		b.place('X',2,2);
		assertEquals("   | X |   ",b.getBoardLayout());
	}
	
	public void testBoard03()
	{
		Board b = new Board();
		b.place('X',1,1);
		assertEquals("X  |   |   ",b.getBoardLayout());
	}

	public void testBoard04()
	{
		Board b = new Board();
		b.place('X',3,3);
		b.place('O',2,2);
		assertEquals("   | O |  X",b.getBoardLayout());
	}
	
	/**
	 * A player may not move on a space that is taken.
	 */
	public void testBoard05()
	{
		try
		{
			Board b = new Board();
			b.place('X',3,3);
			b.place('O',2,2);
			b.place('X',2,2);
			fail("Expected Error");
		}
		catch(BoardException e)
		{
			assertEquals("That space is taken.",e.getMessage());
		}
	}

	public void testBoard06()
	{
		try
		{
			Board b = new Board();
			b.place('O',3,3);
			b.place('X',2,2);
			b.place('O',2,2);
			fail("Expected Error");
		}
		catch(BoardException e)
		{
			assertEquals("That space is taken.",e.getMessage());
		}
	}

	public void testBoard07()
	{
		Board b = new Board();
		b.place('X',1,1);
		b.place('O',1,2);
		b.place('X',2,1);
		b.place('O',2,2);
		b.place('X',3,1);
		assertEquals("XXX|OO |   ",b.getBoardLayout());
		assertEquals("X has won!",b.getGameStatus());
	}

	public void testBoard08()
	{
		try
		{
			Board b = new Board();
			b.place('X',1,1);
			b.place('X',2,1);
			fail("Expected Error");
		}
		catch(BoardException e)
		{
			assertEquals("It is not X's turn.",e.getMessage());
		}
	}
	
	public void testBoard09()
	{
		try
		{
			Board b = new Board();
			b.place('O',1,1);
			b.place('O',1,2);
			fail("Expected Error");
		}
		catch(BoardException e)
		{
			assertEquals("It is not O's turn.",e.getMessage());
		}
	}

	public void testBoard10()
	{
		try
		{
			Board b = new Board();
			b.place('X',2,5);
			fail("Expected Error");
		}
		catch(BoardException e)
		{
			assertEquals("Column numbers must be between 1 and 3.",e.getMessage());
		}
	}
	
	public void testBoard11()
	{
		try
		{
			Board b = new Board();
			b.place('X',5,2);
			fail("Expected Error");
		}
		catch(BoardException e)
		{
			assertEquals("Row numbers must be between 1 and 3.",e.getMessage());
		}
	}
	

}
