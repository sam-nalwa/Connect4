package com.groupfive.connectfour;

//This class represents an individual token/piece in its current state
public class Token{
	private TokenState currentState;
	Token(){
		currentState = TokenState.EMPTY;
	}
	//Method to change the token to whatever the desired state is
	//(this really shouldnt be used more than once per token)
	void changeState(TokenState newState){
		if (newState != null){
			// System.out.println("New state " + newState);
			currentState = newState;
		}
	}
	public TokenState getState(){
		//Don't want to return a reference because it coould be changed
		if (currentState == TokenState.EMPTY){
			return TokenState.EMPTY;
		}
		if (currentState == TokenState.RED){
			return TokenState.RED;
		}
		//Because java can't tell that its disjoint
		assert (currentState == TokenState.BLUE) : "Impossible state";
		return TokenState.BLUE;
	}
}
