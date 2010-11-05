package com.jasonv.tictactoe.ai;

public abstract class Ai 
{
	public Ai() {}
	String moveHistory = "";
	public abstract int makeMove(char player,char board[]);
	public String getMoveHistory() {
		return moveHistory;
	}
	public void setMoveHistory(String moveHistory) {
		this.moveHistory = moveHistory;
	}
	
	String stepHistory = "";
	public String getStepHistory() {
		return stepHistory;
	}
	public void setStepHistory(String stepHistory) {
		this.stepHistory = stepHistory;
	}
	public void clearStepHistory() {
		this.stepHistory = "";
	}
}
