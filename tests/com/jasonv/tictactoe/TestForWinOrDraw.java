package com.jasonv.tictactoe;

import java.util.ArrayList;
import junit.framework.TestCase;

public class TestForWinOrDraw extends TestCase 
{

	public void test01_aiDumb()
	{
		Ai ai = new AiDumb();
		checkForZeroLosses(ai);
	}

	public void test02_aiBestSquares()
	{
		Ai ai = new AiBestSquares();
		checkForZeroLosses(ai);
	}

	public void test03_aiBestSquaresBlockWin()
	{
		Ai ai = new AiBestSquaresBlockWin();
		checkForZeroLosses(ai);
	}

	public void test04_aiBestSquaresTakeWinBlockWin()
	{
		Ai ai = new AiBestSquaresTakeWinBlockWin();
		checkForZeroLosses(ai);
	}
	
	public void test05_aiWikipediaSlow()
	{
		Ai ai = new AiWikipediaSlow();
		checkForZeroLosses(ai);
	}

	public void test06_aiMiniMax()
	{
		Ai ai = new AiMiniMax1();
		checkForZeroLosses(ai);
	}
	
	public void test07_aiWikipedia()
	{
		Ai ai = new AiWikipedia();
		checkForZeroLosses(ai);
	}
	
	public void checkForZeroLosses(Ai ai)
	{
		int aiMakes1stMoveLosses = 6561 - aiMakes1stMove(ai);
		int aiMakes2ndMoveLosses = 59049 - aiMakes2ndMove(ai);
		int totalLosses = aiMakes1stMoveLosses + aiMakes2ndMoveLosses; 
		assertEquals(0,totalLosses);		
	}
	
	public int aiMakes1stMove(Ai ai)
	{
		System.out.println("A.I. Makes the 1st move.");
		System.out.println("()     = 2nd move location.");
		System.out.println("[]     = 4th move location. ");
		System.out.println("||     = 6th move.");
		System.out.println(". or 1 = 8th move.");

		int totalCombinations = 0;
		int goodCombinations = 0;
		int move[] = {0,0,0,0};
		for(move[0]=1;move[0]<=9;move[0]++)
		{
			System.out.print("\n\n(" + move[0] + ")");
			for(move[1]=1;move[1]<=9;move[1]++)
			{
				System.out.print("\n[" + move[1] + "]");
				for(move[2]=1;move[2]<=9;move[2]++)
				{
					System.out.print("|");
					for(move[3]=1;move[3]<=9;move[3]++)
					{
						totalCombinations++;
						String outcome = play(move,ai);
						if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
						{
							System.out.print(".");
							goodCombinations++;
						}
						else
						{
							System.out.print("X");
							//System.out.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + m[4] + " " + outcome);
						}
					}	
				}		
			}
		}
		System.out.println();
		return goodCombinations;
	}
	
	public int aiMakes2ndMove(Ai ai)
	{
		System.out.println("A.I. Makes the 2nd move.");
		System.out.println("()            = 1nd move location.");
		System.out.println("[]            = 3rd move location. ");
		System.out.println("||            = 5th move.");
		System.out.println("~=0,1,2,...,9 = 7th and 9th move. (Number is number of losses)");

		int totalCombinations = 0;
		int goodCombinations = 0;
		int move[] = {0,0,0,0,0};
		for(move[0]=1;move[0]<=9;move[0]++)
		{
			System.out.print("\n\n(" + move[0] + ")");
			for(move[1]=1;move[1]<=9;move[1]++)
			{
				System.out.print("\n[" + move[1] + "]");
				for(move[2]=1;move[2]<=9;move[2]++)
				{
					System.out.print("|");
					for(move[3]=1;move[3]<=9;move[3]++)
					{
						int losses=0;
						for(move[4]=1;move[4]<=9;move[4]++)
						{
							totalCombinations++;
							String outcome = play(move,ai);
							if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
							{
								goodCombinations++;
							}
							else
							{
								losses++;
								//System.out.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + outcome);
							}
						}
						if(losses==0)
						{
							System.out.print("~");
						}
						else
						{
							System.out.print(losses);							
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
