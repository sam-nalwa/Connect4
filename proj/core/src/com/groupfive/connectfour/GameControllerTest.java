package com.groupfive.connectfour;


import static org.junit.Assert.*;

import org.junit.*;

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

	}

	/*@Test
	public final void testSwitchColourTokenState() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testReset() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testInsertPiece() {
		fail("Not yet implemented"); // TODO
	}
*/
}
