package com.jasonv.tictactoe;

import java.util.ArrayList;
import junit.framework.TestCase;

public class TestForWinOrDraw extends TestCase 
{

	public void test01_aiDumb()
	{
		Ai ai = new AiDumb();
		assertEquals(0,6561-aiStarts(ai) + 59049-aiFolows(ai));
	}

	public void test02_aiBestSquares()
	{
		Ai ai = new AiBestSquares();
		assertEquals(0,6561-aiStarts(ai) + 59049-aiFolows(ai));
	}

	public void test03_aiBestSquaresBlockWin()
	{
		Ai ai = new AiBestSquaresBlockWin();
		assertEquals(0,6561-aiStarts(ai) + 59049-aiFolows(ai));
	}

	public void test04_aiBestSquaresTakeWinBlockWin()
	{
		Ai ai = new AiBestSquaresTakeWinBlockWin();
		assertEquals(0,6561-aiStarts(ai) + 59049-aiFolows(ai));
	}
	
	public void test05_aiWikipediaSlow()
	{
		Ai ai = new AiWikipediaSlow();
		int a = aiStarts(ai);
		int b = aiFolows(ai);
		assertEquals(0,6561-a + 59049-b);
	}

	public void test06_aiMiniMax()
	{
		Ai ai = new AiMiniMax1();
		int a = aiStarts(ai);
		int b = aiFolows(ai);
		assertEquals(0,6561-a + 59049-b);
	}
	
	public void test07_aiWikipedia()
	{
		Ai ai = new AiWikipedia();
		int a = aiStarts(ai);
		int b = aiFolows(ai);
		assertEquals(0,6561-a + 59049-b);
	}
	
	public int aiStarts(Ai ai)
	{
		int totalCombinations = 0;
		int goodCombinations = 0;
		int m[] = {0,0,0,0};
		for(m[0]=1;m[0]<=9;m[0]++)
		{
			System.out.println("aiStarts: " + m[0]);
			for(m[1]=1;m[1]<=9;m[1]++)
			{
				for(m[2]=1;m[2]<=9;m[2]++)
				{
					for(m[3]=1;m[3]<=9;m[3]++)
					{
						totalCombinations++;
						String outcome = play(m,ai);
						if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
						{
							goodCombinations++;
						}
						else
						{
							System.out.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + " " + outcome);
						}
						//System.out.println(">" + m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + outcome);
					}	
				}		
			}
		}
		return goodCombinations;
	}
	
	public int aiFolows(Ai ai)
	{
		int totalCombinations = 0;
		int goodCombinations = 0;
		int m[] = {0,0,0,0,0};
		for(m[0]=1;m[0]<=9;m[0]++)
		{
			System.out.println("aiFolows: " + m[0]);
			for(m[1]=1;m[1]<=9;m[1]++)
			{
				for(m[2]=1;m[2]<=9;m[2]++)
				{
					for(m[3]=1;m[3]<=9;m[3]++)
					{
						for(m[4]=1;m[4]<=9;m[4]++)
						{
							totalCombinations++;
							String outcome = play(m,ai);
							if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
							{
								goodCombinations++;
							}
							else
							{
								System.out.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + m[4] + " " + outcome);
							}
						}
					}	
				}		
			}
		}
		return goodCombinations;
	}
	
	private String play(int m[],Ai ai)
	{
		ai.clearStepHistory();
	    final String ok = "O has won! or It's a Tie! or Invalid Sequence.";
	    String moveSequence = "";
		Board b = new Board();
		String outcome = "(unset)";
	    try {
	    	if(m.length==4)
	    	{
	    		int move = ai.makeMove('O',b.getBoardArray());
	    		moveSequence += move + " ";
	        	b.place('O',move);
	    	}
	    	for(int i=0;i<m.length;i++)
	    	{	    		
	        	if(!b.getGameStatus().equals("")) break;
	        	b.place('X',m[i]);
	    		moveSequence += m[i] + " ";
	        	if(!b.getGameStatus().equals("")) break;
	        	int move = ai.makeMove('O',b.getBoardArray());
	        	b.place('O',move);
	    		moveSequence += move + " ";
	        	if(!b.getGameStatus().equals("")) break;
	        }
	        outcome = b.getGameStatus();
	    } catch (BoardException e) {
	    	outcome = b.getGameStatus() + e.getMessage();
	    }
	    if(outcome.endsWith("That space is taken."))
	    {
	    	return ok;
	    }
	    if(outcome.endsWith("O has won!"))
	    {
	    	return ok;
	    }
	    if(outcome.endsWith("It's a tie!"))
	    {
	    	return ok;
	    }	    
	    if(outcome.endsWith("X has won!"))
	    {
	    	return "X has won! ->" + moveSequence + "Steps: " + ai.getStepHistory();
	    }
	    ArrayList<String> mList = new ArrayList<String>();
	    for(int mInt:m)
	    {
	    	mList.add(""+mInt);
	    }
	    throw new RuntimeException("Invalide outcome: " + outcome + mList );
	}
	
}
