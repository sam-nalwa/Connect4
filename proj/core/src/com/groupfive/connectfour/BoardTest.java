package com.groupfive.connectfour;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import org.junit.*;

public class BoardTest {
	Board b;
	
	@Before
	public void setUp() throws Exception {
		b = new Board();	
	}

	@After
	public void tearDown() throws Exception {
		
	}
	@Test
	public final void testBoard(){
		//check that there are tokens in all slots, and that all these tokens are empty
		for (int i = 0; i < b.colLength; i++){
			for (int j = 0; j < b.rowLength; j++){
				assertTrue(b.getToken(i, j).getState()==TokenState.EMPTY);
			}
		}
	}
	
	@Test
	public final void testFreePlace() {
		b.freePlace(0, 1, TokenState.BLUE);
		assertTrue(b.getToken(0, 1).getState()==TokenState.BLUE);
		b.freePlace(5, 5, TokenState.RED);
		assertTrue(b.getToken(5, 5).getState()==TokenState.RED);
	}

	@Test
	public final void testNormalPlace() {
		b.normalPlace(0, TokenState.BLUE);
		assertTrue(b.getToken(0, 0).getState()==TokenState.BLUE);
		b.normalPlace(0, TokenState.RED);
		assertTrue(b.getToken(0, 0).getState()==TokenState.BLUE);
		assertTrue(b.getToken(0, 1).getState()==TokenState.RED);
		b.normalPlace(3, TokenState.BLUE);
		assertTrue(b.getToken(3, 0).getState()==TokenState.BLUE);
		b.freePlace(3, 3, TokenState.BLUE);
		b.normalPlace(3, TokenState.RED);
		assertTrue(b.getToken(3, 3).getState()==TokenState.BLUE);
		assertTrue(b.getToken(3, 4).getState()==TokenState.RED);
	}

	@Test
	public final void testClear() {
		b.freePlace(3, 3, TokenState.BLUE);
		b.normalPlace(3, TokenState.RED);
		b.clear();
		//check that every token is empty
		for (int i = 0; i < b.colLength; i++){
			for (int j = 0; j < b.rowLength; j++){
				assertTrue(b.getToken(i, j).getState()==TokenState.EMPTY);
			}
		}
		//fill board, then clear
		for (int i = 0; i < b.colLength; i++){
			for (int j = 0; j < b.rowLength; j++){
				b.freePlace(3, 3, TokenState.BLUE);
			}
		}
		b.clear();
		//check that every token is empty
		for (int i = 0; i < b.colLength; i++){
			for (int j = 0; j < b.rowLength; j++){
				assertTrue(b.getToken(i, j).getState()==TokenState.EMPTY);
			}
		}
	}

	@Test
	public final void testFindErrors() {
		//there should be no errors in an empty board
		assertTrue(b.findErrors(TokenState.RED)==null);
		b.freePlace(0, 3, TokenState.BLUE);
		//check for 1 defy-gravity error
		HashSet<ErrorCode> errs = b.findErrors(TokenState.BLUE);
		assertTrue(errs.size()==1);
		assertTrue(errs.contains(ErrorCode.DEFIESGRAVITY));
		b.clear();
		//check for 2 defy-gravity errors and a bad-ratio error
		b.freePlace(1, 4, TokenState.RED);
		b.freePlace(1, 2, TokenState.BLUE);
		errs = b.findErrors(TokenState.RED);
		assertTrue(errs.size()==3);
		int occurrences = Collections.frequency(errs, ErrorCode.DEFIESGRAVITY);
		assertTrue(occurrences==2);
		occurrences = Collections.frequency(errs, ErrorCode.BADRATIO);
		assertTrue(occurrences==1);
		//check for a bad-ratio error and a win-error
		
	}

/*	@Test
	public final void testCheckWin() {
		fail("Not yet implemented"); // TODO
	}*/

}
