package com.jasonv.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextUI 
{
	public static void main(String args[])
	{

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    Board b;
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
		    player = 'X';
		    while(true)
		    {
		    	printBoard(b.getBoardArray());
			    try {
				    String command = null;
				    System.out.print(player + ">");
	 		        command = br.readLine();

			        if(command.equalsIgnoreCase("Exit"))
			        {
			        	System.out.println("Done.");
			        	System.exit(0);
			        }
			        else
			        {
			        	int location = Integer.parseInt(command);
			        	b.place(player,location);
			        }
			        String gameStatus = b.getGameStatus();
			        if(!gameStatus.equals(""))
			        {
						System.out.println("---------------------------------------------");
			        	System.out.println(gameStatus);
				    	printBoard(b.getBoardArray());
						System.out.println("---------------------------------------------");
				    	break;
			        }
			        else
			        {
				    	if(player=='X')
				    	{
				    		player='O';
				    	}
				    	else
				    	{
				    		player='X';
				    	}		        	
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
}
