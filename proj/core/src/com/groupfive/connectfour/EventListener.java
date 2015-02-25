package com.groupfive.connectfour;

import com.badlogic.gdx.InputProcessor;

public class EventListener implements InputProcessor{
	
	public GameController gc;
	public int gameHeight;
	private int tokenSize = 75;
	private int leftOffset = 50;
	private int bottomOffset = 25;

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("SOMEONE CLICKED SOMEWHERE");
		System.out.println("more specifically, they clicked at: ("+screenX+","+screenY+")");

		//Now that the player has clicked somewhere, let's figure out where exactly they clicked.
		int y = this.gameHeight - screenY;
		int x = screenX;
		
		System.out.println(y);
		System.out.println(x);
		if(x<50 || x>(leftOffset+(7*tokenSize)) || y<25 || y>(bottomOffset+(6*tokenSize))){
			System.out.println("Out of range.");
		} else {
			System.out.println("Clicked on the board!");
			x-=leftOffset;
			y-=bottomOffset;
			gc.insertPiece(x/tokenSize, y/tokenSize);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
