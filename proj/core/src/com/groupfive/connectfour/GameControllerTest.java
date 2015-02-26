package com.groupfive.connectfour;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameControllerTest {
	GameController g;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGameController() {
	g = new GameController(true);
	assertTrue(g.isFreePlacing());
	g = new GameController(false);
	assertFalse(g.isFreePlacing());
	}

	@Test
	public final void testGetBoard() {
		g = new GameController(true);
		assert(g.getBoard().getToken(0, 0).getState()==TokenState.EMPTY);
		g.insertPiece(0, 0);
		assert(g.getBoard().getToken(0, 0).getState()!=TokenState.EMPTY);
	}

	@Test
	public final void testEndFreePlace() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSwitchColour() {
		fail("Not yet implemented"); // TODO
	}

	@Test
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

	@Test
	public final void testIsFreePlacing() {
		fail("Not yet implemented"); // TODO
	}

}
