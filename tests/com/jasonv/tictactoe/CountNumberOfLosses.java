package com.jasonv.tictactoe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import junit.framework.TestCase;

public class CountNumberOfLosses extends TestCase 
{

	public void test01_aiDumb()
	{
		Ai ai = new AiDumb();
		checkForExpectedNumberOfLosses(ai,"01AiDumb",4631);
	}

	public void test02_aiBestSquares()
	{
		Ai ai = new AiBestSquares();
		checkForExpectedNumberOfLosses(ai,"02AiBestSquares",3486);
	}

	public void test03_aiBestSquaresBlockWin()
	{
		Ai ai = new AiBestSquaresBlockWin();
		checkForExpectedNumberOfLosses(ai,"03AiBestSquaresBlockWin",162);
	}

	public void test04_aiBestSquaresTakeWinBlockWin()
	{
		Ai ai = new AiBestSquaresTakeWinBlockWin();
		checkForExpectedNumberOfLosses(ai,"04AiBestSquaresTakeWinBlockWin",108);
	}
	
	public void test05_aiWikipedia()
	{
		Ai ai = new AiWikipedia();
		checkForExpectedNumberOfLosses(ai,"05AiWikipedia",0);
	}
	
//	public void test06_aiWikipediaSlow()
//	{
//		Ai ai = new AiWikipediaSlow();
//		checkForExpectedNumberOfLosses(ai,"06AiWikipediaSlow",0);
//	}
//
//	public void test07_aiMiniMax()
//	{
//		Ai ai = new AiMiniMax1();
//		checkForExpectedNumberOfLosses(ai,"07AiMiniMax1",0);
//	}
	

	
	public void checkForExpectedNumberOfLosses(Ai ai,String aiName,int expectedNumberOfLosses)
	{
		FileWriter fstream;
		BufferedWriter out;
		int totalLosses = 0;
		try {
			fstream = new FileWriter("testOutput/"+aiName+".txt");
	        out = new BufferedWriter(fstream);
			int aiMakes1stMoveLosses = 6561 - aiMakes1stMove(ai,out);
			int aiMakes2ndMoveLosses = 59049 - aiMakes2ndMove(ai,out);
			totalLosses = aiMakes1stMoveLosses + aiMakes2ndMoveLosses; 
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expectedNumberOfLosses,totalLosses);		
	}
	
	public int aiMakes1stMove(Ai ai,BufferedWriter out) throws IOException
	{
		out.write("A.I. Makes the 1st move.\n");
		out.write("()     = 2nd move location.\n");
		out.write("[]     = 4th move location. \n");
		out.write("||     = 6th move.\n");
		out.write(". or 1 = 8th move.\n");

		int totalCombinations = 0;
		int goodCombinations = 0;
		int move[] = {0,0,0,0};
		for(move[0]=1;move[0]<=9;move[0]++)
		{
			out.write("\n\n(" + move[0] + ")");
			for(move[1]=1;move[1]<=9;move[1]++)
			{
				out.write("\n[" + move[1] + "]");
				for(move[2]=1;move[2]<=9;move[2]++)
				{
					out.write("|");
					for(move[3]=1;move[3]<=9;move[3]++)
					{
						totalCombinations++;
						String outcome = play(move,ai);
						if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
						{
							out.write(".");
							goodCombinations++;
						}
						else
						{
							out.write("X");
							//System.out.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3] + " " + m[4] + " " + outcome);
						}
					}	
				}		
			}
		}
		out.write("\n");
		return goodCombinations;
	}
	
	public int aiMakes2ndMove(Ai ai,BufferedWriter out) throws IOException
	{
		out.write("A.I. Makes the 2nd move.\n");
		out.write("()            = 1nd move location.\n");
		out.write("[]            = 3rd move location.\n");
		out.write("||            = 5th move.\n");
		out.write("~=0,1,2,...,9 = 7th and 9th move. (Number is number of losses)\n");

		int totalCombinations = 0;
		int goodCombinations = 0;
		int move[] = {0,0,0,0,0};
		for(move[0]=1;move[0]<=9;move[0]++)
		{
			out.write("\n\n(" + move[0] + ")");
			for(move[1]=1;move[1]<=9;move[1]++)
			{
				out.write("\n[" + move[1] + "]");
				for(move[2]=1;move[2]<=9;move[2]++)
				{
					out.write("|");
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
							out.write("~");
						}
						else
						{
							out.write(""+losses);							
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
