package com.groupfive.connectfour;

import java.util.HashSet;
import java.util.Random;

//This class is used for controlling the rest of the game logic
public class GameController{
	private TokenState colourPlacing;//The colour to be placed at any given time
	private Board gameBoard;
	private boolean freePlaceMode;//true if the game is in free placing mode
	private boolean vsComputer = false;//true if the person is playing again computer
	private TokenState firstMove;//Used in validating the board in free place mode
	private boolean gameOver = false;

	//This constructor just intializes the game with a randomly chosen player
	public GameController(boolean freePlaceMode){
		this.freePlaceMode = freePlaceMode;//Storing requested mode
		colourPlacing = pickRandomColour();
		gameBoard = new Board();
	}
	public GameController(TokenState aiColor){
		this(false);//Delegating gamecontroller creation
		vsComputer = true;
		if (aiColor == colourPlacing){
			aiPlace();
		}
	}
	//Returns the board itself for manipulation
	public Board getBoard(){
		return gameBoard;
	}
	
	public void setBoard(Board b){
		this.gameBoard = b;
	}

	//ends free place mode, returns errors in an HashSet<ErrorCode>
	//or if there are no errors, returns null
	public HashSet<ErrorCode> endFreePlace(){
		if (this.freePlaceMode){
			HashSet<ErrorCode> errs = gameBoard.findErrors(firstMove);
			if (errs.isEmpty()){
				freePlaceMode = !freePlaceMode;
			}
			return gameBoard.findErrors(firstMove);
		}else{
			return null;
		}
	}
	//Chooses a random int (0 or 1) to represent which colour to use
	public TokenState pickRandomColour(){
		Random generator = new Random();
		int firstMove = generator.nextInt(2 - 0);
		switch (firstMove) {
			case 0:
			return TokenState.RED;//returns the red player's turn 
			case 1:
			return TokenState.BLUE;//returns the blue play
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
		gameOver = false;
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
		}else if (!gameOver&&(gameBoard.getToken(col,row).getState()==TokenState.EMPTY)){//We dont insert after the end of the game
			boolean success = gameBoard.normalPlace(col,colourPlacing);
			switchColour();
			return success;
		}else{
			return false;
		}
	}
	//A insertpiece that can be used to place a specifc colour
	//Only valid in freePlaceMode
	public void insertPiece(int col, int row, TokenState colour){
		if (freePlaceMode){
			colourPlacing = colour;
			insertPiece(col,row);
		}
	}
	//An ai that places a piece semi intelligently
	//Returns true on success
	public boolean aiPlace(){
		//This ai goes through 3 phases of logic when deciding where to place
		//PHASE 1: Checks for an imediate win and places if there is one
		for (int i = 0; i < gameBoard.colLength; i++){
			//Cloning board for simulation
			Board gameCopy = new Board(gameBoard);
			gameCopy.normalPlace(i,colourPlacing);
			//Here is where we check if this is a winning condition
			if (gameCopy.checkWin() == colourPlacing){
				return insertPiece(i,gameCopy.rowLength - 1);
			}
		}
		//PHASE 2: Checks if the other player has a winning move, and blocks it
		for (int i = 0; i < gameBoard.colLength; i++){
			//Cloning board with other players move
			switchColour();
			Board gameCopy = new Board(gameBoard);
			gameCopy.normalPlace(i,colourPlacing);
			switchColour();
			//If this is a winning move for the other player, we place here instead
			if (gameCopy.checkWin() != colourPlacing && gameCopy.checkWin() != null){
				return insertPiece(i,gameCopy.rowLength - 1);
			}
		}
		//PHASE 3: Checks all possible moves and chooses the one with the most
		//Adjacent pieces that it owns

		//Storing the best move so far
		int bestCol  = 0;
		int bestMovQuality = 0;
		for (int i = 0; i < gameBoard.colLength; i++){
			//Quickly finding where the piece would drop according to gravity
			Integer row = null; 
			for (int j = 0; j < gameBoard.rowLength; j++){
				if (gameBoard.getToken(i,j).getState() == TokenState.EMPTY){
					row = j;
					break;
				}
			}
			//Looks around the potential position and adds up the number of adjacent pieces
			//of the ai's colour to rate the position
			int moveQuality = 0;
			if (row != null){
				for (int k = -1; k <=1; k++){
					if ((i-1) >= 0 && (row+k) >= 0 && (row +k) < gameBoard.rowLength && gameBoard.getToken(i-1,row+k).getState() == colourPlacing){
						moveQuality++;
					}
					if ((row-1) >= 0 && gameBoard.getToken(i,row-1).getState() == colourPlacing){
						moveQuality++;
					}
				}
			}
			//If this is a higher rated move it replaces the previos higher rated move
			if (moveQuality > bestMovQuality){
				bestCol = i;
				bestMovQuality = moveQuality;
			}
		}
		//Places the highest rated move
		return insertPiece(bestCol,gameBoard.rowLength - 1);
	}
	public void playerPlace(int col,int row){
		if (insertPiece(col,row)){
			if(this.vsComputer){
				aiPlace();
			}
		}
	}
	public GameState getCurrentState(){
		TokenState winner = gameBoard.checkWin();

		if (winner == TokenState.RED){//if the token is red and the game is won
			gameOver = true;//set game over to true 
			return GameState.REDWIN;//declare that it is a win of the red player
		} else if (winner == TokenState.BLUE){//if the token is blue and the game is won
			gameOver = true;//set game over to true 
			return GameState.BLUEWIN;//declare that it is a win of the blue player
		}else if (gameBoard.checkDraw()){//check for a draw
			return GameState.DRAW;//declare a draw
		}else{
			return GameState.INPROGRESS;//declare the game still in progress
		}
	}
	
	public String playingAgainst(){
		if(vsComputer){
			return "c";
		} else {
			return "h";
		}
	}
	
	public TokenState getTurn(){
		return this.colourPlacing;
	}
	
	public boolean isFreePlacing(){
		return this.freePlaceMode;
	}
	
	public void setTurn(TokenState t){
		this.colourPlacing = t;
	}
}