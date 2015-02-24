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
		for (Token[] col: slots){
			for (Token row : col ){
				row = new Token();
			}
		}
	}
	//Method to place a token in a particular spot
	public place(int col, int row, TokenState colour){
		slots[col][row].changeState(colour);
	}
}