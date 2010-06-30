package com.jasonv.tictactoe;

import junit.framework.TestCase;

public class MainTest extends TestCase {
	Main main;

	public MainTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.main = new Main();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetHelloWorld()
	{
		String value = this.main.getHelloWorld();
		assertEquals(value,"Hello World");
	}

}
