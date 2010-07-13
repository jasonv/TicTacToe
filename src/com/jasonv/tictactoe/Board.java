package com.jasonv.tictactoe;

public class Board 
{
	private char boardArray[] = new char[9];
	private char lastPlayer;
	private char winningPlayer;	
//	private enum Status {START,FINISHED,INPLAY,TIE};
//	private Status gameStatus = Status.START;
	
	public Board()
	{
		this.boardArray = "         ".toCharArray();
		this.lastPlayer = ' ';
		this.winningPlayer = ' ';
	}
	
	public void place(char player,int location) 
	{
		place(player,location,lastPlayer);
	}
	
	public void place(char player,int x, int y) 
	{
		if(x<0 || x>3)
		{
			throw new BoardException("Row numbers must be between 1 and 3.");
		}
		if(y<0 || y>3)
		{
			throw new BoardException("Column numbers must be between 1 and 3.");
		}
		int location = (x) + 3*(y-1);
		place(player,location,lastPlayer);
	}

	private void place(char player,int location,char lastPlayer)
	{
		String gameStatus = getGameStatus();
		if(!gameStatus.equals(""))
		{
			throw new BoardException(gameStatus);
		}
		if(player==lastPlayer)
		{
			throw new BoardException("It is not " + player + "'s turn.");
		}
		if(player!='X' && player!='O')
		{
			throw new BoardException("Player must be X or O.");
		}
		if(location<1 || location>9)
		{
			throw new BoardException("Location must be between 1 and 9.");
		}
		if(boardArray[location-1]==' ')
		{
			boardArray[location-1] = player;			
		}
		else
		{
			throw new BoardException("That space is taken.");
		}
		this.lastPlayer=player;
	}

	public String getBoardLayout() 
	{
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<boardArray.length;i++)
		{
			sb.append(boardArray[i]);
			if((i+1) % 3 == 0 && i<8)
			{
				sb.append("|");				
			}
		}
		return sb.toString();
	}
	
	public String getGameStatus()
	{
		if(isWinner(boardArray,'X'))
		{
			return "X has won!";
		}
		if(isWinner(boardArray,'O'))
		{
			return "O has won!";
		}
		if(isFullBoard(boardArray))
		{
			return "It's a tie!";
		}
		return "";
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
		if(board[6]==player && board[7]==player && board[8]==player)
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
	
	private boolean isFullBoard(char board[])
	{
		for(int i=0;i<board.length;i++)
		{
			if(board[i]==' ')
			{
				return false;
			}
		}
		return true;
	}

	public char[] getBoardArray() {
		return boardArray;
	}

	public void setBoardArray(char[] boardArray) {
		this.boardArray = boardArray;
	}


}
