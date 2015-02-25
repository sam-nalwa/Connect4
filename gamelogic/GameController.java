package gamelogic;

//This class is used for controlling the rest of the game logic
public class GameController{
	TokenState colourPlacing;//The colour to be placed at any given time
	Board gameBoard;
	boolean freePlaceMode;//true if the game is in free placing mode
	TokenState firstMove;//Used in validating the board in free place mode
	//This constructor just intializes the game with a randomly chosen player
	//Used for normal game beginning//
	public GameController(boolean freePlaceMode){
		this.freePlaceMode = freePlaceMode;//Storing requested mode
		//Delegate to appropriate game initializer
		if (freePlaceMode){
			initFreePlaceMode();
		}else{
			initNormalMode();
		}
	}
	//init for normal play mode
	private void initNormalMode(){
		//Sets random first colour and inits board
		colourPlacing = pickRandomColour();
		gameBoard = new Board();
	}
	//init for freeplace mode
	private void initFreePlaceMode(){
		//inits board
		gameBoard = new Board();
	}

	//ends free place mode, returns errors
	//SHITTY IMPLEMENTATION is the following:
	//returns errors in an ErrorCode[]
	//or if there are no errors, returns null
	public ErrorCode endFreePlace(){
		if (freePlaceMode){
			return gameBoard.findErrors(firstMove);
		}
	}
	//Chooses a random int (0 or 1) to represent which colour to use
	private int pickRandomColour(){
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
			boolean success = gameBoard.normalInsert(col,colourPlacing);
			//Switch to opposite colour
			if (colourPlacing == TokenState.RED){
				colourPlacing = TokenState.BLUE;
			} else if (colourPlacing == TokenState.BLUE){
				colourPlacing = TokenState.RED;
			}
		}
	}
}