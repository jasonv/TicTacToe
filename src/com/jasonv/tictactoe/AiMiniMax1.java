package com.jasonv.tictactoe;
public class AiMiniMax1 extends Ai 
{
	int indent = 0;
	public int makeMove(char player, char[] boardArray)
	{
		return minimax(player,boardArray);
	}
	private int minimax(char player,char[] boardArrayParam)
	{
		char opponent = getOpponent(player);
		int boardValue = boardValue(boardArrayParam);
		if(boardValue!=0)
		{
			return boardValue;
		}
		indent++;
		char[] boardArray = copy(boardArrayParam);
		int[] score = new int[boardArrayParam.length];
		for(int i=0;i<boardArray.length;i++)
		{
			score[i]=0;
			if(boardArray[i]==' ')
			{
				boardArray[i]=player;
				score[i] = minimax(opponent,boardArray);
				boardArray[i]=' ';
			}
		}
		int value = max(score);
		indent--;
		//print(value,indent,boardArrayParam);
		return value;
	}
	
	private char getOpponent(char player) 
	{
		if(player=='X')
		{
			return 'O';
		}
		else
		{
			return 'X';
		}
	}
	private char[] copy(char[] source)
	{
		char[] result = new char[9];
		int i=0;
		for(char square:source)
		{
			result[i]=source[i];
			i++;
		}
		return result;
	}
	
	private int boardValue(char board[])
	{
		if(isWinner(board,'O'))
		{
			return 1;
		}
		if(isWinner(board,'X'))
		{
			return -1;
		}
		return 0;		
	}
	
	private boolean isWinner(char board[],char player)
	{
		// Across
		if(board[0]==player && board[1]==player && board[2]==player)
		{
			return true;
		}
		if(board[3]==player && board[4]==player && board[5]==player)
		{
			return true;
		}

		// Up and Down
		if(board[0]==player && board[3]==player && board[6]==player)
		{
			return true;
		}
		if(board[1]==player && board[4]==player && board[7]==player)
		{
			return true;
		}
		if(board[2]==player && board[5]==player && board[8]==player)
		{
			return true;
		}
		
		// Diagonals
		if(board[0]==player && board[4]==player && board[8]==player)
		{
			return true;
		}
		if(board[2]==player && board[4]==player && board[6]==player)
		{
			return true;
		}
		return false;
	}
	
	int min(int[] list)
	{
		int minValue = list[0];
		int minIndex = 0;
		for(int i=0;i<list.length;i++)
		{
			if(list[i]<minValue)
			{
				minValue=list[i];
				minIndex = i;
			}
		}
		return minIndex + 1;
	}
	
	public int max(int[] list)
	{
		int maxValue = list[0];
		int maxIndex = 0;
		for(int i=0;i<list.length;i++)
		{
			if(list[i]>maxValue)
			{
				maxValue=list[i];
				maxIndex = i;
			}
		}
		return maxIndex + 1;
	}
	
	private void print(int value,int indent,char[] board)
	{
		String s = "";
		String indentString = "";
		for(int i=0;i<indent;i++)
		{
			indentString+="   ";
		}
		s += indentString + board[0] + board[1] + board[2] + "\n";
		s += indentString + board[3] + board[4] + board[5] + "-->" + value + "\n";
		s += indentString + board[6] + board[7] + board[8];
		System.out.println(s);
	}
}