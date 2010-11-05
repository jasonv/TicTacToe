package com.jasonv.tictactoe.ai;

public class AiMove extends Exception 
{
	String moveMessage = "";
	int square;
	char[] boardArray;
	private static final long serialVersionUID = 1L;
	public AiMove(String moveMessage,char[] boardArray, int square)
	{
		this.moveMessage = moveMessage;
		this.square = square;
		this.boardArray = boardArray;
	}
	
	public String getMoveMessage() {
		return moveMessage;
	}
	public void setMoveMessage(String moveMessage) {
		this.moveMessage = moveMessage;
	}
	public int getSquare() {
		return square;
	}
	public void setSquare(int square) {
		this.square = square;
	}
	public char[] getBoardArray() {
		return boardArray;
	}
	public void setBoardArray(char[] boardArray) {
		this.boardArray = boardArray;
	}
	
	public String getMoveDisplay()
	{
		String s = "";
		s += "===========\n";
		s += sq(1) + "|" + sq(2) + "|" + sq(3) + "\n"; 
		s += "--- --- ---\n";
		s += sq(1) + "|" + sq(2) + "|" + sq(3) + "\n"; 
		s += "--- --- ---\n";
		s += sq(1) + "|" + sq(2) + "|" + sq(3) + "\n"; 
		s += " " + getMoveMessage() +"\n";
		s += "===========\n";
		return s;
	}
	
	private String sq(int squareParam)
	{
		if(squareParam==square)
		{
			return "(" + boardArray[squareParam-1] + ")";
		}
		else
		{
			return " " + boardArray[squareParam-1] + " ";
		}
	}
}
