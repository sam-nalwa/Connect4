package com.groupfive.connectfour;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EventListener implements InputProcessor{
	
	public GameController gc;
	public HashMap<String,Sprite> buttons;
	
	public int gameHeight;
	public int boardOffsetX;
	public int boardOffsetY;
	
	public HashSet<ErrorCode> errorList;
	
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
		
		//First we need to see if we've clicked any buttons
		String buttonName = null;
		for (Map.Entry<String, Sprite> entry : buttons.entrySet()) {
		    String k = entry.getKey();
		    Sprite s = entry.getValue();
		    if(x>s.getX() && x<s.getX() + s.getWidth() && y>s.getY() && y<s.getY() + s.getHeight()){
		    	buttonName = k;
		    }
		}
		
		if(buttonName != null){
			System.out.println("Clicked on "+buttonName);
			
			//If we've clicked on an error screen, let's remove the error from the list and remove its button as well
			if(buttonName == "gravityError"){
				errorList.remove(ErrorCode.DEFIESGRAVITY);
				buttons.remove("gravityError");
			} else if(buttonName == "ratioError"){
				errorList.remove(ErrorCode.BADRATIO);
				buttons.remove("ratioError");
			} else if(buttonName == "winError"){
				errorList.remove(ErrorCode.NOWINNINGALLOWED);
				buttons.remove("winError");
			} 
			
			else if(buttonName == "okayButton"){
				//If we click on the okay button, get out of placement mode
				System.out.println("Clicked the okay button");
				if(gc.isFreePlacing()){
					errorList = gc.endFreePlace();
				}
			} 
			
			else if(buttonName=="redSwitch") {
				//Swap to red
				gc.switchColour(TokenState.RED);
			} else if(buttonName=="blueSwitch"){
				//Swap to blue
				gc.switchColour(TokenState.BLUE);
			}
		} else if((errorList == null || errorList.isEmpty()) && x>boardOffsetX && x<(boardOffsetX+(7*tokenSize)) && y>boardOffsetY && y<(boardOffsetY+(6*tokenSize))){	
			//Clicked within the board
			System.out.println("Clicked on the board!");
			x-=boardOffsetX;
			y-=boardOffsetY;
			gc.insertPiece(x/tokenSize, y/tokenSize);
		} else {
			System.out.println("You can't do that.");
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
