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
}