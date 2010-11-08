package com.jasonv.tictactoe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.jasonv.tictactoe.ai.Ai;
import com.jasonv.tictactoe.ai.AiBestSquares;
import com.jasonv.tictactoe.ai.AiBestSquaresBlockWin;
import com.jasonv.tictactoe.ai.AiBestSquaresTakeWinBlockWin;
import com.jasonv.tictactoe.ai.AiDumb;
import com.jasonv.tictactoe.ai.AiWikipedia;

import junit.framework.TestCase;

public class CountNumberOfLossesRecursive extends TestCase 
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
	
	public void checkForExpectedNumberOfLosses(Ai ai,String aiName,int expectedNumberOfLosses)
	{
		FileWriter fstream;
		BufferedWriter out;
		int totalLosses = 0;
		try {
			fstream = new FileWriter("testOutput/Recursive"+aiName+".txt");
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


	
	// A.I. Makes First Move
	
	public int aiMakes1stMove(Ai ai,BufferedWriter out) throws IOException
	{
		out.write("A.I. Makes the 1st move.\n");
		out.write("()     = 2nd move location.\n");
		out.write("[]     = 4th move location. \n");
		out.write("||     = 6th move.\n");
		out.write(". or 1 = 8th move.\n");

		int move[] = {1,1,1,1};
		int totalGoodGames = humanMakes2ndMove(move.clone(),out,ai);
		return totalGoodGames;
	}
	
	private int humanMakes2ndMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		out.write("\n\n(" + move[0] + ")");
		int total = 0;
		total += humanMakes4thMove(move.clone(),out,ai);
		if(move[0]<9)
		{
			move[0]++;
			total += humanMakes2ndMove(move.clone(),out,ai);
		}
		return total;
	}

	private int humanMakes4thMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		out.write("\n[" + move[1] + "]");
		int total = 0;
		total += humanMakes6thMove(move.clone(),out,ai);
		if(move[1]<9)
		{
			move[1]++;
			total +=  humanMakes4thMove(move.clone(),out,ai);
		}
		return total;
	}
	
	private int humanMakes6thMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		out.write("|");
		int total = 0;
		total += humanMakes8thMove(move.clone(),out,ai);
		if(move[2]<9)
		{
			move[2]++;
			total += humanMakes6thMove(move.clone(),out,ai);
		}
		return total;
	}
	
	private int humanMakes8thMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		int total = 0; 
		System.out.println(move[0] + " " + move[1] + " " + move[2] + " " + move[3]);
		String outcome = play(move,ai);
		if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
		{
			out.write(".");
			total++;
		}
		else
		{
			out.write("X");
		}
		if(move[3]<9)
		{
			move[3]++;
			total += humanMakes8thMove(move.clone(),out,ai);
		}
		return total;
	}

	// A.I. Makes Second Move

	public int aiMakes2ndMove(Ai ai,BufferedWriter out) throws IOException
	{
		out.write("A.I. Makes the 2nd move.\n");
		out.write("()            = 1st move location.\n");
		out.write("[]            = 3rd move location.\n");
		out.write("||            = 5th move.\n");
		out.write("~=0,1,2,...,9 = 7th and 9th move. (Number is number of losses)\n");

		int move[] = {1,1,1,1,1};
		int totalGoodGames = humanMakes1stMove(move.clone(),out,ai); 
		
		return totalGoodGames;
	}
	
	private int humanMakes1stMove(int [] move,BufferedWriter out,Ai ai) throws IOException
	{
		out.write("\n\n(" + move[0] + ")");
		int total = 0;
		total += humanMakes3rdMove(move.clone(),out,ai);
		if(move[0]<9)
		{
			move[0]++;
			total += humanMakes1stMove(move.clone(),out,ai);
		}
		return total;	
	}

	private int humanMakes3rdMove(int [] move,BufferedWriter out,Ai ai) throws IOException
	{
		out.write("\n[" + move[1] + "]");
		int total = 0;
		total += humanMakes5thMove(move.clone(),out,ai);
		if(move[1]<9)
		{
			move[1]++;
			total +=  humanMakes3rdMove(move.clone(),out,ai);
		}
		return total;
	}
	
	private int humanMakes5thMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		out.write("|");
		int total = 0;
		total += humanMakes7thMove(move.clone(),out,ai);
		if(move[2]<9)
		{
			move[2]++;
			total += humanMakes5thMove(move.clone(),out,ai);
		}
		return total;
	}
	
	private int humanMakes7thMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		int total = 0;
		total += humanMakes9thMove(move.clone(),out,ai);
		if(total==9)
		{
			out.write("~");
		}
		else
		{
			out.write(""+(9-total));							
		}

		if(move[3]<9)
		{
			move[3]++;
			total += humanMakes7thMove(move.clone(),out,ai);
		}
		return total;
	}
	
	private int humanMakes9thMove(int move[],BufferedWriter out,Ai ai) throws IOException
	{
		int total = 0; 
		String outcome = play(move,ai);
		if(outcome.equals("O has won! or It's a Tie! or Invalid Sequence."))
		{
			total++;
		}
		if(move[4]<9)
		{
			move[4]++;
			total += humanMakes9thMove(move.clone(),out,ai);
		}
		return total;
	}
}
