package gamelogic;

//This class represents the game board/grid and all its legal operations
public class Board{
	Token[][] slots; //This 2d array will store all of the game Tokens

	//Default 7x6 board constructor to be used unless req change
	public Board(){
		//Delagating to more specific constuctor
		this(7,6);
	}
	//Future proofing in case we need different sized boards
	public Board(int colSize, int rowSize){
		slots = new Token[colSize][rowSize];
		clear();
	}
	//Method to place a token when in free mode
	public void freePlace(int col, int row, TokenState colour){
		slots[col][row].changeState(colour);
	}
	//Methods to place a token in normal mode
	//Returns true if succseful, false if unsuccseful
	public boolean normalPlace(int col, TokenState colour){
		return normalPlace(col,0,colour);//Delegating to recursive function
	}
	//This really shouldnt be called with a row != 0 other unless done recursively
	private boolean normalPlace(int col, int row, TokenState colour){
		if (!(col < slots.length)){
			//Out of bounds
			return false;
		}
		if (slots[col][row].getState() != TokenState.EMPTY){
			//Try the next slot up
			return normalPlace(col, row + 1);
		}else{
			//The token can be placed here, proceed to do so.
			slots[col][row].changeState(colour);
			return true;
		}
	}
	//Sets board to all empty tokens
	public void clear(){
		for (Token[] col: slots){
			for (Token row : col ){
				row = new Token();
			}
		}
	}
	//Method to check if the state of the board is legal (after freeplacemode)
	public boolean isValid(TokenState firstMove){
		int redCount = 0;
		int blueCount = 0;
		for (int i = 0; i < slots.length; i++){
			for (int j = 0; j < slots[i].length; j++){
				if (slots[i][j].getState() != TokenState.EMPTY){
					//Checking if piece is supported
					if ((j-1 > -1) && (slots[i][j-1].getState() == TokenState.EMPTY)){
						return false;
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
		//TODO figure out conditions for invalid ratio of pieces
		if (firstMove == TokenState.RED && conditionForRedFirstBeingInValid){
			return false;
		} else if (firstMove == TokenState.BLUE && conditionForBlueFirstBeingInValid){
			return false;
		}
		//If there is a winning configuration then the board is invalid
		if (checkWin() != null){
			return false;
		}
		//Otherwise the board is valid
		return true;
	}
	private TokenState checkWin(){
		
	}
}