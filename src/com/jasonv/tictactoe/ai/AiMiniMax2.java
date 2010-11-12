package com.jasonv.tictactoe.ai;

import com.jasonv.tictactoe.Board;



public class AiMiniMax2 extends Ai 
{

	@Override
	public int makeMove(char player, char[] board) {
		// TODO Auto-generated method stub
		return 0;
	}
//	public static final int POSITIVE_INFINITY = 500000;
//	public static final int NEGATIVE_INFINITY = -500000;
//	public int makeMove(char player, char[] boardArray)
//	{
//		
//		return -1;
//	}
//	
//    public MoveInfo moveOpponent(char playersCharacter)
//	{	  
//		  MoveInfo finalMove = new MoveInfo();
//	  	  maxValue('O',4,this,finalMove, NEGATIVE_INFINITY, POSITIVE_INFINITY);
//		  
//		  // Update Game Board
//		  int moveX = finalMove.getMoveX();
//		  int moveY = finalMove.getMoveY();
//		  System.out.println("Value of Final move: " + finalMove.getScore() + " (" + moveX + "," + moveY + ")");
//          setTile(moveX,moveY,playersCharacter);
//		  fillSquare();
//		  checkWinner(moveX,moveY,playersCharacter);
//          return finalMove;
//    }
//	 
//	 
//	
//
////Minimax function for max nodes
//public static int maxValue(char c, int depth, char board[], MoveInfo finalMove, int alpha, int beta)
//{
//		//Determine who our opponent is
//		char next;
//		if (c == 'O')
//			next = 'X';
//		else
//			next = 'O';	
//
//		//IF GAME IS OVER in current board position, return board value
//	 	if(board.getWinner() == OWIN)
//		{
//			return POSITIVE_INFINITY;
//		}
//		else if(board.getWinner() == XWIN)
//		{
//			return NEGATIVE_INFINITY;
//		}
//		else if(board.getWinner() == TIE)
//		{
//			return 0;
//		}
//		else if(depth <=0)
//		{
//			return board.evaluation(c);
//		}
//		
//		int v = NEGATIVE_INFINITY;
//		
//			//For all children (all legal moves for the player from the current board)
//			for(int i = 0; i < board.getSize(); i++)
//			{
//				for(int j = 0; j < board.getSize(); j++)
//				{
//					//Make sure this is a legal move, square is not occupied
//					if(board.getTile(i,j) == ' ')
//					{
//						//Create and set child node
//						GameBoard child = new GameBoard(board, 3, 3, board.getEmptySquares());
//						child.setTile(i,j,c);
//						child.fillSquare();
//						child.checkWinner(i,j,c);
//						
//						//For each board, print the board, overall score, alpha and beta
//						//System.out.println("\n----");
//						//System.out.println("Player O's Move");
//						//child.printBoard();
//						
//						//Value of this node
//						int score = minValue(next,depth-1,child,finalMove,alpha,beta);
//						
//						//System.out.println("Score:" + score + " Alpha:" + alpha + " Beta:" + beta);
//						//System.out.println("----\n");
//						
//						if(score > v)
//						{
//							v = score;
//						}
//						
//						//Found a better move?
//						if(v > alpha)
//						{
//							alpha = v;
//							finalMove.setMoveX(i);
//							finalMove.setMoveY(j);
//							finalMove.setScore(v);
//							//System.out.println("\n--");
//							//System.out.println("Found Better Move");
//							//child.printBoard();
//							//System.out.println("MAXVALUEScore: " + score + " Alpha:" + alpha + " Beta:" + beta);
//							//System.out.println("New Best move:" + finalMove.getMoveX() + " " + finalMove.getMoveY());
//							//System.out.println("----\n");
//						}
//						
//						
//						//Found a better move??
//						if(v >= beta)
//						{
//							return v;
//						}
//						
//					}//end large if
//				}//end for j
//			}//end for i
//        return v;
//	
//}//end maxvalue
//
//
////Minimax function for min nodes
//public static int minValue(char c, int depth, GameBoard board,MoveInfo finalMove, int alpha, int beta)
//{
//		//Determine who our opponent is
//		char next;
//		if (c == PLAYERO)
//				next = PLAYERX;
//		else
//				next = PLAYERO;
//		
//		//IF GAME IS OVER in current board position, return board value
//	 	if(board.getWinner() == XWIN)
//		{
//			return NEGATIVE_INFINITY;
//		}
//		else if(board.getWinner() == OWIN)
//		{
//			return POSITIVE_INFINITY;
//		}
//		else if(board.getWinner() == TIE)
//		{
//			return 0;
//		}
//		else if(depth <=0)
//		{
//			return board.evaluation(next);
//		}
//		
//		int v = POSITIVE_INFINITY;
//		
//			//For all children (all legal moves for the player from the current board)
//			for(int i = 0; i < board.getSize(); i++)
//			{
//				for(int j = 0; j < board.getSize(); j++)
//				{
//					//Make sure this is a legal move, square is not occupied
//					if(board.getTile(i,j) == EMPTY)
//					{
//						//Create and set child node
//						GameBoard child = new GameBoard(board, board.getSize(), board.getWinLength(), board.getEmptySquares());
//						child.setTile(i,j,c);
//						child.fillSquare();
//						child.checkWinner(i,j,c);
//						
//						//For each board, print the board, overall score, alpha and beta
//						//System.out.println("\n----");
//						//System.out.println("Player X's Move");
//						//child.printBoard();
//						
//						//Value of this node
//						int score = maxValue(next,depth-1,child,finalMove,alpha,beta);
//						
//						//System.out.println("Score:" + score + " Alpha:" + alpha + " Beta:" + beta);
//						//System.out.println("----\n");
//						
//						if(score < v)
//						{
//							v = score;
//						}
//						
//						if(v < beta)
//						{
//							beta = v;
//						}
//						
//            		if(v <= alpha)
//						{
//							//Best move for X
//							//child.printBoard();
//							//System.out.println("MINVALUEScore: " + score + " Alpha:" + alpha);
//							//System.out.println("New Best move:" + i + " " + j);
//							return v;
//						}
//					}//end large if
//				}//end for j
//			}//end for i
//			
//        return v;
//	
//}//end minvalue
//
//}//end GameBoard
//
//class MoveInfo {
//
//   public static final int NEGATIVE_INFINITY = -500000;
//
//	private int moveX;
//	private int moveY;
//	private int score;
//
//	//Constructor
//	public MoveInfo()
//	{
//		moveX = -1;
//		moveY = -1;
//		score = NEGATIVE_INFINITY;
//	}
//	
//	//Get X value of best move
//	public int getMoveX()
//	{
//		return moveX;
//	}
//	
//	//Set X value of best move
//	public void setMoveX(int x)
//	{
//		moveX = x;
//	}
//	
//	//Get Y value of best move
//	public int getMoveY()
//	{
//		return moveY;
//	}
//	
//	//Set Y value of best move
//	public void setMoveY(int y)
//	{
//		moveY = y;
//	}
//	
//	//Get value of the score associated with this move
//	public int getScore()
//	{
//		return score;
//	}
//	
//	//Set value of the score associated with this move
//	public void setScore(int s)
//	{
//		score = s;
//	}
//

}