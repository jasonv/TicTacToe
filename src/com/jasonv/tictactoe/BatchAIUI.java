package com.jasonv.tictactoe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
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
public class BatchAIUI {
	public static void main(String args[]) {
		//step01_createAllPossibleMoveSequencesFile();
		step02_createAllPossibleGamesFile();
	}

	// Main Steps 
	
	public static void step01_createAllPossibleMoveSequencesFile() {
		System.out.println("Creating 01AllPossibleMoveSequences.txt...");
		try {
			// Create file
			FileWriter fstream = new FileWriter("output/01AllPossibleMoveSequences.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			int j = 0;
			for (long n = 123456789; n < 999999999; n++) {
				if (n % 1000000 == 0) {
					System.out.println(n);
				}

				if (hasNoRepeateDigits(n)) {
					j++;
					out.write(""+n+"\n");
					//if (j>10) break;
				}
			}
			System.out.println("done:" + j);
			out.close();
		} 
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void step02_createAllPossibleGamesFile()
	{
		System.out.println("Creating 02AllPossibleGamesFile.txt...");
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fistream = new FileInputStream("output/01AllPossibleMoveSequences.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fistream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			FileWriter fostream = new FileWriter("output/02AllPossibleGamesFile.txt");
			BufferedWriter out = new BufferedWriter(fostream);

			// Read File Line By Line
			int lineCount = 0;
			while ((strLine = br.readLine()) != null) 
			{
				if (lineCount % 36280 == 0) {
					System.out.print(".");
				}
				String result = "";
				result = play(strLine);
				out.write(result + "\n");
				lineCount++;
			}
			System.out.println("\nTotal number of lines:" + lineCount);
			// Close the input stream
			in.close();
			out.close();
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	
	
	// Helper Functions
	
	private static String play(String sequence)
	{
		String gameStatus = "";
		Board b = new Board();
		char player = 'X';
		int gameLength = 0;
		for(char move:sequence.toCharArray())
		{
			gameLength++;
			b.place(player,Integer.parseInt(String.valueOf(move)));
			if(player=='X')
			{
				player='O';
			}
			else
			{
				player='X'; 
			}
			gameStatus = b.getGameStatus();
			if(!gameStatus.equals(""))
			{
				break;
			}
		}	
		return sequence + " " + b.getBoardLayout() + " " + gameStatusCode(gameStatus) + " " + gameLength;
	}
	
	private static int gameStatusCode(String gameStatus)
	{
		if(gameStatus.equals(""))
		{
			return 0;
		}
		if(gameStatus.equals("X has won!"))
		{
			return 1;
		}
		if(gameStatus.equals("O has won!"))
		{
			return 2;
		}
		if(gameStatus.equals("It's a tie!"))
		{
			return 3;
		}
		return -1;
	}
	
	private static boolean hasNoRepeateDigits(long n) {
		String s = Long.toString(n);
		int found[] = new int[s.length() + 1];
		int num[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			num[i] = Integer.parseInt(s.substring(i, i + 1));
			if (num[i] == 0) {
				return false;
			}
			found[i] = 0;
		}

		for (int i = 0; i < s.length(); i++) {
			int index = num[i];
			found[index]++;
			if (found[index] > 1) {
				return false;
			}
		}
		return true;
	}

}
