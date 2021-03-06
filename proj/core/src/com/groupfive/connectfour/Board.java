package com.groupfive.connectfour;

import java.util.HashSet;

//This class represents the game board/grid and all its legal operations
public class Board{
	private Token[][] slots; //This 2d array will store all of the game Tokens
	public int colLength;
	public int rowLength;
	public int tokenCount;

	//Default 7x6 board constructor to be used unless req change
	public Board(){
		//Delagating to more specific constuctor
		this(7,6);
	}
	//Future proofing in case we need different sized boards
	public Board(int colLength, int rowLength){
		this.colLength = colLength;
		this.rowLength = rowLength;
		slots = new Token[colLength][rowLength];
		clear();
	}
	
	//Copy constructor
	public Board(Board oldBoard){
		this.colLength = oldBoard.colLength;
		this.rowLength = oldBoard.rowLength;
		slots = new Token[colLength][rowLength];
		clear();
		for (int i=0; i<colLength ;i++){
			for (int j=0; j<rowLength ;j++){
				slots[i][j] = new Token(oldBoard.slots[i][j]);
			}
		}
	}
	
	//Used for accessing token directly
	public Token getToken(int col, int row){
		return slots[col][row];
	}
	
	public int getTokenCount(){
		return tokenCount;
	}
	
	//Method to place a token when in free mode
	void freePlace(int col, int row, TokenState colour){
		slots[col][row].changeState(colour);
		tokenCount++;
	}
	//Methods to place a token in normal mode
	//Returns true if succseful, false if unsuccseful
	boolean normalPlace(int col, TokenState colour){
		return normalPlace(col,0,colour);//Delegating to recursive function
	}
	//This really shouldnt be called with a row != 0 other unless done recursively
	private boolean normalPlace(int col, int row, TokenState colour){
		if (row > slots[col].length - 1){
			//Out of bounds
			return false;
		}
		if (slots[col][row].getState() != TokenState.EMPTY){
			//Try the next slot up
			return normalPlace(col, row + 1,colour);
		}else{
			//The token can be placed here, proceed to do so.
			slots[col][row].changeState(colour);
			tokenCount++;
			return true;
		}
	}
	//Sets board to all empty tokens
	void clear(){
		for (int i = 0; i < slots.length; i++){
			for (int j = 0; j < slots[i].length; j++){
				slots[i][j] = new Token();
			}
		}
		tokenCount = 0;
	}
	//Method to check if the state of the board is legal (after freeplacemode)
	HashSet<ErrorCode> findErrors(TokenState firstMove){
		int redCount = 0;
		int blueCount = 0;
		HashSet<ErrorCode> errors = new HashSet<ErrorCode>();
		for (int i = 0; i < slots.length; i++){
			for (int j = 0; j < slots[i].length; j++){
				if (slots[i][j].getState() != TokenState.EMPTY){
					//Checks if piece is supported
					if ((j-1 > -1) && (slots[i][j-1].getState() == TokenState.EMPTY)){
						errors.add(ErrorCode.DEFIESGRAVITY);
					}
					if (slots[i][j].getState() == TokenState.RED){
						redCount++;
					} 
					if (slots[i][j].getState() == TokenState.BLUE){
						blueCount++;
					}
				}
			}
		}
		//Checks that the ratio of red to blue pieces is valid
		if (firstMove == TokenState.RED && ((blueCount > redCount) || (redCount-1 > blueCount))){
			errors.add(ErrorCode.BADRATIO);
		} else if (firstMove == TokenState.BLUE && ((redCount > blueCount) || (blueCount-1 > redCount))){
			errors.add(ErrorCode.BADRATIO);
		}
		//If there is a winning configuration then the board is invalid
		if (checkWin() != null){
			errors.add(ErrorCode.NOWINNINGALLOWED);
		}
		//Return errors
		return errors;
	}
	
	//Method to check if there is a winning configuration
	TokenState checkWin(){
		int numCols = slots.length;
		int numRows = slots[0].length;
		for (int i = 0; i < numCols; i++){ //cycle through columns
			for (int j = 0; j < numRows; j++){ // cycle through tokens in column
				if (slots[i][j].getState()!=TokenState.EMPTY){
					//check top->bottom win (if a token has 3 of the same color beneath it)
					if (j > 2){ //staying within array
						if (slots[i][j].getState()==slots[i][j-1].getState()&&slots[i][j].getState()==slots[i][j-2].getState()&&slots[i][j].getState()==slots[i][j-3].getState()){
							return slots[i][j].getState();
						}
					}
					//check right->left win (if a token has 3 of the same colour to its left)
					if (i > 2){ //staying within array
						if (slots[i][j].getState()==slots[i-1][j].getState()&&slots[i][j].getState()==slots[i-2][j].getState()&&slots[i][j].getState()==slots[i-3][j].getState()){
							return slots[i][j].getState();
						}
					}
					//check diagonal topleft->bottomright win
					if (i < 4 && j > 2){
						if (slots[i][j].getState()==slots[i+1][j-1].getState()&&slots[i][j].getState()==slots[i+2][j-2].getState()&&slots[i][j].getState()==slots[i+3][j-3].getState()){
							return slots[i][j].getState();
						}
					}
					//check diagonal topright->bottomleft win
					if (i > 2 && j > 2){
						if (slots[i][j].getState()==slots[i-1][j-1].getState()&&slots[i][j].getState()==slots[i-2][j-2].getState()&&slots[i][j].getState()==slots[i-3][j-3].getState()){
							return slots[i][j].getState();
						}
					}
				}
			}
		}
		return null;
	}
	//Checks if the board is in an unwinnable/draw state
	boolean checkDraw(){
		int numCols = slots.length;
		int numRows = slots[0].length;
		boolean possible = false;
		for (int i = 0; i < numCols; i++){
			for (int j = 0; j < numRows; j++){
				if (j > 2){
					int redCount = 0;
					int blueCount = 0;
					for (int k = 0; k < 4; k++){
						if (slots[i][j-k].getState() == TokenState.RED){
							redCount++;
						}else if (slots[i][j-k].getState() == TokenState.BLUE){
							blueCount++;
						}
					}
					if (!(redCount > 0 && blueCount > 0)){
						possible = true;
					}
				}
				if (i > 2){
					int redCount = 0;
					int blueCount = 0;
					for (int k = 0; k < 4; k++){
						if (slots[i-k][j].getState() == TokenState.RED){
							redCount++;
						}else if (slots[i-k][j].getState() == TokenState.BLUE){
							blueCount++;
						}
					}
					if (!(redCount > 0 && blueCount > 0)){
						possible = true;
					}
				}
				if (i < 4 && j > 2){
					int redCount = 0;
					int blueCount = 0;
					for (int k = 0; k < 4; k++){
						if (slots[i+k][j-k].getState() == TokenState.RED){
							redCount++;
						}else if (slots[i+k][j-k].getState() == TokenState.BLUE){
							blueCount++;
						}
					}
					if (!(redCount > 0 && blueCount > 0)){
						possible = true;
					}
				}
				if (i > 2 && j > 2){
					int redCount = 0;
					int blueCount = 0;
					for (int k = 0; k < 4; k++){
						if (slots[i-k][j-k].getState() == TokenState.RED){
							redCount++;
						}else if (slots[i-k][j-k].getState() == TokenState.BLUE){
							blueCount++;
						}
					}
					if (!(redCount > 0 && blueCount > 0)){
						possible = true;
					}
				}

			}
		}
		return !possible;
	}
}
	