package com.jasonv.tictactoe;

public class BoardException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public BoardException() {
		super();
	}

	public BoardException(String message) {
		super(message);
	}

}
