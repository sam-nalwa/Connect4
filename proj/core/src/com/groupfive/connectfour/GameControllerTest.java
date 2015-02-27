package com.groupfive.connectfour;


import static org.junit.Assert.*;

import org.junit.*;

import java.util.Random;

public class GameControllerTest {
	GameController gameTester;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGameController() {
		gameTester = new GameController(true);
		assertTrue(gameTester.isFreePlacing());
		gameTester = new GameController(false);
		assertFalse(gameTester.isFreePlacing());
	}

	@Test
	public final void testGetBoard() {
		gameTester = new GameController(true);
		assertTrue(gameTester.getBoard().getToken(0, 0).getState()==TokenState.EMPTY);
		gameTester.insertPiece(0, 0);
		assertTrue(gameTester.getBoard().getToken(0, 0).getState()!=TokenState.EMPTY);
	}

	@Test
	public final void testEndFreePlace() {
		gameTester = new GameController(true);
		gameTester.endFreePlace();
		assertFalse(gameTester.isFreePlacing());
		gameTester = new GameController(true);
		gameTester.insertPiece(0,5);
		gameTester.endFreePlace();
		assertTrue(gameTester.isFreePlacing());
	}

	@Test
	public final void testSwitchColour() {
		gameTester = new GameController(true);
		gameTester.insertPiece(0,0);
		gameTester.switchColour();
		gameTester.insertPiece(0,1);
		Board gmBrd= gameTester.getBoard();
		assertNotSame(gmBrd.getToken(0,0),gmBrd.getToken(0,1));
		gameTester.switchColour(gmBrd.getToken(0,0).getState());
		gameTester.insertPiece(0,2);
		assertEquals(gmBrd.getToken(0,0).getState(),gmBrd.getToken(0,2).getState());
	}
	@Test
	public final void testPickRandomColour() {
		gameTester = new GameController(true);
		int blueCount = 0;
		int redCount = 0;
		for (int i = 0; i <=1000;i++){
			TokenState randState = gameTester.pickRandomColour();
			if (randState == TokenState.RED){
				redCount++;
			}else if (randState == TokenState.BLUE){
				blueCount++;
			}else{
				fail("Unexpected output");
			}
		}
		assertTrue(Math.abs(redCount - blueCount) < 100);//Good enough randomness?
	}
	@Test
	public final void testReset() {
		gameTester = new GameController(true);
		Random generator = new Random();
		for (int i = 0; i <=15; i++){
			gameTester.insertPiece(generator.nextInt(6 - 0),generator.nextInt(2 - 0));
		}
		gameTester.reset();
		Board gmBrd = gameTester.getBoard();
		for (int i = 0; i < gmBrd.colLength; i++){
			for (int j = 0; j < gmBrd.rowLength; j++){
				assertEquals(gmBrd.getToken(i,j).getState(),TokenState.EMPTY);
			}
		}
	}
}
