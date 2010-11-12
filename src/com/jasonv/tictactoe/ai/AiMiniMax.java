package com.jasonv.tictactoe.ai;

/**
 * Parts of the minimax algorithem were taken from github.com/chestergrant's tictactoe code. 
 */
import java.util.*;
import java.io.*;
public class AiMiniMax extends Ai {
	@Override
	public int makeMove(char player, char[] board) {
		return minimax(player,board);
	}
	
	
	public static int minimax(char player,char charBoard[])
	{
		int piece = getPiece(player);
		int board[][] = get3x3Board(charBoard);

		//System.out.println("-------------------------------");
		//printBoard(board);
		int pos = AiMiniMax.minimax(board,piece);  
		//System.out.println("" + pos + "<=minimax(board," + piece + ")");
		//AiMiniMax.printBoard(board);
		//System.out.println("-------------------------------");
		return pos;		
	}
	
	public static int getPiece(char player)
	{
		if(player=='O')
		{
			return 0;
		}
		if(player=='X')
		{
			return 1;
		}
		return -1;
	}

	public static int[][] get3x3Board(char charBoard[])
	{
		int board[][] = new int [][] {{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				board[i][j] = getPiece(charBoard[((i*3)+(j+1))-1]);
			}
		}
		return board;
	}
	
	public static int minimax(int board[][], int piece){
		int ans[] = new int[2];
		if(piece == 1){
			 ans = maxMove(board, piece,0);
			return ans[0];
		}else{
			ans = minMove(board, piece,0);
			return ans[0];
		}				

	}


	public static int[] maxMove(int [][]board, int piece, int depth){
		int bestMove[] = new int[2];
		int newBoard[][] = copyBoard(board);

		if(gameOver(board)){
			//printBoard(board);
			//System.exit(1);
			return maxEvaluation(board, depth);
		}
		int first = 0;
		for(int i = 0; i< board.length; i++){
			for(int j=0; j<board.length; j++){
				if(newBoard[i][j] == -1){
					newBoard[i][j] = piece;
					int value = minMove(newBoard, getOpponent(piece),depth+1)[1];
					if(first==0){
			bestMove[0] = pos(i,j);
						bestMove[1] = value;
						first++;
					}				
					if(bestMove[1] < value){
						bestMove[0] = pos(i,j);
						bestMove[1] = value;
						/*System.out.println("The best move "+bestMove[0]+" Eval: "+ bestMove[1]);
						printBoard(board);
						System.out.println();*/
					}

					newBoard[i][j] = -1;
				}
			}
		}

		return bestMove;


	}
	public static int pos(int i , int j){
		return (i*3) +(j+1);
	}
	public static int[] maxEvaluation(int[][] board, int depth){
		int ans[] = new int[2];
		ans[0] = 0;
		ans[1] = 0;
		if(checkWin(board,1)){			
			ans[1] = 2*(10-depth);
			return ans;
		}
		if(checkWin(board,0)){			
			ans[1] = -2*(10-depth);
			return ans;
		}
		return ans;
	}
	public static int[] minEvaluation(int[][] board, int depth){
		int ans[] = new int[2];
		ans[0] = 0;
		ans[1] = 0;
		if(checkWin(board,1)){			
			ans[1] = -2*(10-depth);
			return ans;
		}
		if(checkWin(board,0)){			
			ans[1] = 2*(10-depth);
			return ans;
		}
		return ans;
	}

	public static boolean gameOver(int[][] board){
		//printBoard(board);
		if(checkWin(board,1)){
			return true;
		}
		if(checkWin(board,0)){
			return true;
		}
		if((checkDraw(board,1))&&(checkDraw(board,0))){
			return true;
		}	

		return false;
	}
	public static int[] minMove(int [][]board, int piece, int depth){
		int bestMove[] = new int[2];
		int newBoard[][] = copyBoard(board);

		if(gameOver(board)){
			return maxEvaluation(board, depth);
		}
		int first = 0;
		for(int i = 0; i< board.length; i++){
			for(int j=0; j<board.length; j++){
				if(newBoard[i][j] == -1){
					newBoard[i][j] = piece;
					int value = maxMove(newBoard, getOpponent(piece),depth+1)[1];
					//System.out.println(value);
					if(first==0){
						bestMove[0] = pos(i,j);
						bestMove[1] = value;
						first++;
					}				
					if(bestMove[1] > value){
						bestMove[0] = pos(i,j);
						bestMove[1] = value;
						/*System.out.println("The best move "+bestMove[0]+" Eval: "+ bestMove[1]);
						printBoard(board);
						System.out.println();*/
					}

					newBoard[i][j] = -1;
				}
			}
		}

		return bestMove;

	}
	public static void whoWon(int board[][]){
		if(checkWin(board,0)){
			System.out.println(piece(0)+" Wins");
			return;
		}
		if(checkWin(board,1)){
			System.out.println(piece(1)+ " Wins");
			return;
		}
		System.out.println("Game was Drawn");
	}
	public static Boolean checkWin(int[][] board, int AI){
		for(int i=0; i<board.length;i++){
			if((board[i][0]==AI)&&(board[i][1]==AI)&&(board[i][2]==AI)){
				//System.out.println("win 1");
				return true;
			}
			if((board[0][i]==AI)&&(board[1][i]==AI)&&(board[2][i]==AI)){
				//System.out.println("win 2");
				return true;
			}

		}
		if((board[0][0]==AI)&&(board[1][1]==AI)&&(board[2][2]==AI)){
			    //System.out.println("win 3");
				return true;
			}
			if((board[0][2]==AI)&&(board[1][1]==AI)&&(board[2][0]==AI)){
				//System.out.println("win 4");
				return true;
			}

		return false;
	}

	public static boolean checkDraw(int[][] board, int AI){
			int opp = getOpponent(AI);
			boolean draw = false;
			for(int i=0; i<board.length;i++){
				if(((board[i][0]!=opp)&&(board[i][1]!=opp)&&(board[i][2]!=opp))&&( (board[i][0]!=opp)||(board[i][1]!=opp)||(board[i][2]!=opp) )){
					//System.out.println("Check Draw 1 "+ AI);
					return false;
				}
				if(((board[0][i]!=opp)&&(board[1][i]!=opp)&&(board[2][i]!=opp))&&( (board[0][i]!=opp)||(board[1][i]!=opp)||(board[2][i]!=opp) ) ){
					//System.out.println("Check Draw 2 "+ AI);
					return false;
				}

			}
			if(( (board[0][0]!=opp)&&(board[1][1]!=opp)&&(board[2][2]!=opp)) &&( (board[0][0]!=opp)||(board[1][1]!=opp)||(board[2][2]!=opp) ) ){
				//System.out.println("Check Draw 3 "+ AI);
				return false;
			}
			if(((board[0][2]!=opp)&&(board[1][1]!=opp)&&(board[2][0]!=opp))&&((board[0][2]!=opp)||(board[1][1]!=opp)||(board[2][0]!=opp)) ){
				//System.out.println("Check Draw 4 "+ AI);
				return false;
			}
	        //System.out.println("Check Draw 5 "+ AI);
			return true;
	}
	public static int getOpponent(int you){
		if(you == 1){
			return 0;
		}
		return 1;
	}




    public static boolean isFull(int[][] board){
    	for(int i =0; i< 3; i++){
    		for(int j =0; j<3;j++){
    			if(board[i][j]==-1){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    public static int[][] reset(){
    	int[][] newBoard = new int[3][3];
    	for(int i =0; i< 3; i++){
    		for(int j =0; j<3;j++){
    			newBoard[i][j]=-1;
    		}
    	}
    	return newBoard;
    }
    public static int[][] copyBoard(int[][] board){
    	int[][] newBoard = new int[3][3];
    	for(int i =0; i< 3; i++){
    		for(int j =0; j<3;j++){
    			newBoard[i][j]= board[i][j];
    		}
    	}
    	return newBoard;
    }
    public static String piece(int i){
    	if(i == 1) return "X";
    	if(i == 0) return "O";
    	return " ";
    }
    public static void printBoard(int[][] board){
    	System.out.println();
    	for(int i =0; i< 3; i++){
    		System.out.println(piece(board[i][0])+"|"+piece(board[i][1])+"|"+piece(board[i][2]));
    	    if(i!=2)System.out.println("_____");
    	    
    	}
    }
    public static int humanPlay(int board[][]){
    	int ans;
    	int ansInt= -1;
      
    	try{
    		BufferedReader input = new BufferedReader(new InputStreamReader(System.in) );
    		while(ansInt == -1){
    			
    			ans = input.read();
    			ans = ans-48;
    			//System.out.println("Got: "+ ans);
    		    
    			if((ans>0)&&(ans<10)){
    				ansInt= ans;//Integer.parseInt(""+ans);
    				//System.out.println("In side");
    				int pos = ansInt;
    				int i = (pos-1) /3;
    		    	int j = ((pos-1) % 3);
    		    	if(board[i][j]==-1){
    		    		return ansInt;
    		    	}
    		    	ansInt = -1;
    			}
    			
    			
    		}
    		
    			
    		
    	}catch(IOException ex){
			System.out.println("Unable to read in data");
			System.exit(1);
    	}
    	return ansInt;
    	
    }
    
    public static void gameLoop(){
    	Random rand = new Random();
    	int AI = rand.nextInt() % 2;
    	AI = AI*AI;
    	System.out.println("You are "+ piece(getOpponent(AI)) );
    	System.out.println("X Plays first ");
    	int[][] board = reset();   	
        int turn = 1;
    	printBoard(board);
    	while((!checkWin(board,0))&&((!checkWin(board,1))||(!checkDraw(board,0)))&&(!checkDraw(board,1))&&(!isFull(board))){
    		int pos= -1;
    		if(turn == AI){
    			System.out.println("-------------------------------");
        		printBoard(board);
    			pos = minimax(board,AI);//AI(board,AI);  
    			System.out.println("" + pos + "<=minimax(board," + AI + ")");
        		printBoard(board);
    			System.out.println("-------------------------------");
    			/*System.out.println(pos);
    			System.out.flush();*/  			
    		}else{
    			System.out.print("Please enter a position between 1 and 9 to play: ");
    			pos = humanPlay(board);//minimax(board,turn);    			
    		}
    		int i = (pos-1) /3;
    		int j = ((pos-1) % 3);
    		board[i][j] = turn;
    		printBoard(board);
    	
    		turn = getOpponent(turn);
    		if(gameOver(board)) break;
    	}
    	whoWon(board);    	
    }
    public static int play(){
    	int ans = -2;
    	try{
    		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    		
    		in.readLine();
    		ans = in.read();
    		ans = ans -48;    			
    	
            
    	}catch(IOException ex){
    		System.exit(1);
    	}
    	return ans;
    }
    public static void main(String[] args) {
    	boolean playagain = true;
    	while(playagain){
    		gameLoop();
    		System.out.print("Do you want to play again?(1 for yes and any other no. for no)");
    		if(play()!=1)
    			playagain = false;
    	}
        
    	
    }


}