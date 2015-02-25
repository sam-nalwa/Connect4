package com.groupfive.connectfour;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//This class is used for controlling the rest of the game logic
public class GameController{
	private TokenState colourPlacing;//The colour to be placed at any given time
	private Board gameBoard;
	private boolean freePlaceMode;//true if the game is in free placing mode
	private TokenState firstMove;//Used in validating the board in free place mode
	//This constructor just intializes the game with a randomly chosen player
	//Used for normal game beginning//
	public GameController(boolean freePlaceMode){
		this.freePlaceMode = freePlaceMode;//Storing requested mode

		colourPlacing = pickRandomColour();
		gameBoard = new Board();
	}
	//Returns the board itself for manipulation
	public Board getBoard(){
		return gameBoard;
	}

	//ends free place mode, returns errors
	//SHITTY IMPLEMENTATION is the following:
	//returns errors in an ErrorCode[]
	//or if there are no errors, returns null
	public List<ErrorCode> endFreePlace(){
		if (this.freePlaceMode){
			List<ErrorCode> errs = gameBoard.findErrors(firstMove);
			if (errs.isEmpty()){
				freePlaceMode = !freePlaceMode;
			}
			return gameBoard.findErrors(firstMove);
		}else{
			return null;
		}
	}
	//Chooses a random int (0 or 1) to represent which colour to use
	private TokenState pickRandomColour(){
		Random generator = new Random();
		int firstMove = generator.nextInt(2 - 0);
		switch (firstMove) {
			case 0:
			return TokenState.RED;
			case 1:
			return TokenState.BLUE;
			default:
			System.out.println("generator failed");
		}
		return null;
	}
	//Switches to opposite colour
	public void switchColour(){
		if (colourPlacing == TokenState.RED){
			colourPlacing = TokenState.BLUE;
		} else if (colourPlacing == TokenState.BLUE){
			colourPlacing = TokenState.RED;
		}
	}
	//Switches to given colour
	public void switchColour(TokenState newState){
		colourPlacing = newState;
	}
	//Resets board to empty
	public void reset(){
		gameBoard.clear();
	}
	//Method used for inserting piece in a certain position
	//Returns false in unsuccesful, and true otherwise
	public boolean insertPiece(int col, int row){
		if (freePlaceMode){
			if (firstMove == null){
				//Saving the colour of the first move for validating purposes
				firstMove = colourPlacing;
			}
			gameBoard.freePlace(col,row,colourPlacing);
			return true;
		}else{
			boolean success = gameBoard.normalPlace(col,colourPlacing);
			switchColour();
			return success;
		}
	}
	
	public boolean isFreePlacing(){
		return this.freePlaceMode;
	}
}