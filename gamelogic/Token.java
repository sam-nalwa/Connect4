package gamelogic;

//This class represents an individual token/piece in its current state
public class Token{
	TokenState currentState;
	public Token(){
		currentState = TokenState.EMPTY;
	}
	//Method to change the token to whatever the desired state is
	//(this really shouldnt be used more than once per token)
	public void changeState(TokenState newState){
		currentState = newState;
	}
}
