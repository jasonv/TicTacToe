package com.jasonv.tictactoe;

import junit.framework.TestCase;

public class AiMiniMaxTest extends TestCase 
{
	public void test_01()
	{
		AiMiniMax ai = new AiMiniMax();
		int move = ai.makeMove('O', "X   O   X".toCharArray());
		assertEquals(0,move);
	}
	
	public void test_02()
	{
		AiMiniMax1 ai = new AiMiniMax1();
		int list[] = {20,30,10};
		int value = ai.min(list);
		assertEquals(3,value);
	}
	
	public void test_03()
	{
		AiMiniMax1 ai = new AiMiniMax1();
		int list[] = {20,30,10,40};
		int value = ai.max(list);
		assertEquals(4,value);
	}
}
