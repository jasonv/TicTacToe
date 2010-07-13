package com.jasonv.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dell
 *
 */
/**
 * @author dell
 *
 */
public class TextAIUI 
{
	public static void main(String args[])
	{

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    Board b;
	    Ai ai;
	    char player;
		System.out.println("---------------------------------------------");
		System.out.println("Tic Tac Toe");
		System.out.println("---------------------------------------------");
		System.out.println("1|2|3    Enter the number of the square");
		System.out.println("-|-|-");
		System.out.println("4|5|6    and then press enter to make a move.");
		System.out.println("-|-|-");
		System.out.println("7|8|9    Type 'exit' to exit.  Have fun!");
		System.out.println("---------------------------------------------\n");		

	    while(true)
	    {
		    b = new Board();
		    ai = new AiWikipedia();
		    player = 'X';
		    while(true)
		    {
		    	printBoard(b.getBoardArray());
			    try {
				    String validCommands[] = {"1","2","3","4","5","6","7","8","9","Exit","exit"};
			    	String command = null;
				    boolean commandIsValid = false;
				    while(commandIsValid==false)
				    {
					    System.out.print("Enter a number(1-9) or 'exit' to quit. \n" + player + ">");
		 		        command = br.readLine();
		 		        for(String validCommand:validCommands)
		 		        {
		 		        	if(validCommand.equals(command))
		 		        	{
		 		        		commandIsValid=true;
		 		        		break;
		 		        	}
		 		        }
				    }

			        if(command.equalsIgnoreCase("Exit"))
			        {
			        	System.out.println("Done.");
			        	System.exit(0);
			        }

			        String gameStatus = "";
			        int location = Integer.parseInt(command);
		        	b.place('X',location);
			        gameStatus = b.getGameStatus();
			        if(!gameStatus.equals(""))
			        {
			        	printResult(gameStatus, b.getBoardArray());
				    	break;
			        }			        
		        	b.place('O',ai.makeMove('O',b.getBoardArray()));
			        gameStatus = b.getGameStatus();
			        if(!gameStatus.equals(""))
			        {
			        	printResult(gameStatus, b.getBoardArray());
				    	break;
			        }			        
			        
			    } catch (BoardException e) {
			    	System.out.println(e.getMessage());
			    } catch (IOException ioe) {
			       System.out.println("IO error trying to read your name!");
			       System.exit(0);
			    }
		    }
	    }
	}

	private static void printBoard(char[] boardArray)
	{
		char[] b = boardArray;
		System.out.println(b[0] + "|" + b[1] + "|" + b[2]);
		System.out.println("-|-|-");
		System.out.println(b[3] + "|" + b[4] + "|" + b[5]);
		System.out.println("-|-|-");
		System.out.println(b[6] + "|" + b[7] + "|" + b[8]);
	}
	
	private static void printResult(String gameStatus,char[] boardArray)
	{
		System.out.println("---------------------------------------------");
       	System.out.println(gameStatus);
    	printBoard(boardArray);
		System.out.println("---------------------------------------------");
	}
}
