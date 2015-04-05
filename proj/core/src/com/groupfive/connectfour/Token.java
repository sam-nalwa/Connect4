package com.groupfive.connectfour;

//This class represents an individual token/piece in its current state
public class Token{
	private TokenState currentState;
	Token(){
		currentState = TokenState.EMPTY;
	}
	
	//Copy constructor
	public Token(Token old){
		this.currentState = old.currentState;
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
		return currentState;
	}
	
}
