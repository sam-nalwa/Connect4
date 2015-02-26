package com.groupfive.connectfour;

import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EventListener implements InputProcessor{
	
	public GameController gc;
	public Sprite redSwitch;
	public Sprite blueSwitch;
	public Sprite okayButton;
	
	public int gameHeight;
	public int boardOffsetX;
	public int boardOffsetY;
	
	public List<ErrorCode> errorList;
	
	private int tokenSize = 75;
	

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

		//Now that the player has clicked somewhere, let's figure out where exactly they clicked.
		int y = this.gameHeight - screenY;
		int x = screenX;
		
		if(x>boardOffsetX && x<(boardOffsetX+(7*tokenSize)) && y>boardOffsetY && y<(boardOffsetY+(6*tokenSize))){	
			//Clicked within the board
			System.out.println("Clicked on the board!");
			x-=boardOffsetX;
			y-=boardOffsetY;
			gc.insertPiece(x/tokenSize, y/tokenSize);
		} else if(x>redSwitch.getX() && x<redSwitch.getX() + redSwitch.getWidth() && y>redSwitch.getY() && y<redSwitch.getY() + redSwitch.getHeight()) {
			//Clicked on the red button
			gc.switchColour(TokenState.RED);
		} else if(x>blueSwitch.getX() && x<blueSwitch.getX() + blueSwitch.getWidth() && y>blueSwitch.getY() && y<blueSwitch.getY() + blueSwitch.getHeight()) {
			//Clicked on the blue button
			System.out.println("Clicked blue button");
			gc.switchColour(TokenState.BLUE);
		} else if(x>okayButton.getX() && x<okayButton.getX() + okayButton.getWidth() && y>okayButton.getY() && y<okayButton.getY() + okayButton.getHeight()) {
			System.out.println("Clicked the okay button");
			if(gc.isFreePlacing()){
				errorList = gc.endFreePlace();
			}
		} else {
			System.out.println("Out of range.");
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
