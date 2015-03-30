package com.groupfive.connectfour;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileIO{


	public static Integer getSaveCount(){
		FileHandle text = Gdx.files.internal("assets/save.txt"); //Load the text file to which the all the games are saved
		String s = text.readString(); //read the file and store it into a string 's'
		int counter = 0; //set a counter to count the number of lines
		for( int i=0; i<s.length(); i++ ) { 
			System.out.println(s.charAt(i));//print the character
			if( s.charAt(i) == '\n' ) { //if the character is a newline character 
				counter++; //increment the counter by 1
			} 
		}
		return counter;
	}
	//Function to saves the current gamestate into a textfile
	public static void save(Board currBoard, String pTurn, String gameMode)throws IOException {
	
		
		String gameTokens = "";//create a blank string which the game token values will be stored into

		for (int i = 0; i < 7; i++){//run a for loop for the 7 columns
			for (int j = 0; j < 6; j++){ //run a for loop for the 6 rows
				Token temp = currBoard.getToken(i,j); //create a temporary token value which reads the token at the specified row and column

				if (temp.getState() == TokenState.RED){//if the token is red, add a 'R' to the string
					gameTokens +="R";
				}
				else if(temp.getState() == TokenState.BLUE){//if the token is blue, add a 'B' to the string
					gameTokens += "B";
				}
				else{
					gameTokens += "-";//if the spot is empty, add a '-' to the string
				}
			}
		}

		//saves the current date/time
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM-HH:mm");//set the format for the date and time stamp
		String date = sdf.format(myDate);


		//Append text to file
		FileHandle file = Gdx.files.local("save.txt");//open the save file
		file.writeString(date+"@"+gameTokens+"@"+pTurn+"@"+gameMode+"\n", true); //write the game state to it

		System.out.println("GameState Saved.");//print out that the game has been saved

	}


	//Loads the gameState specified at index loadGame
	public static GameController load(Integer loadGame) throws IOException{
		System.out.println(loadGame);
		//Load the file
		FileHandle file = Gdx.files.internal("save.txt");
		String[] text = file.readString().split("@",0);//read the file and split it at the "@" symbols
		String tokenPlaces = text[(1+(loadGame*3))];//get all the token places in the form of a string from the text array
		String playerTurn = text[(2+(loadGame*3))].split(",")[0];//get all the player turn information in the form of a string from the text array
		boolean againstAI;
		if (text[3+(loadGame*2)] == "H"){
			againstAI = false;
		}
		againstAI = true;
		
		GameController gc = new GameController(true,againstAI);//create a new game controller in freeplace mode
		
		Board freshBoard = new Board();//create a new clean board
		
		//Make the board
		for (int i = 0; i < 7; i++){ //columns
			for (int j = 0; j < 6; j++){ //rows
				if(tokenPlaces.charAt(6*i + j) == 'R'){
					freshBoard.freePlace(i, j, TokenState.RED);//add the red tokens
				} else if(tokenPlaces.charAt(6*i + j) == 'B'){
					freshBoard.freePlace(i, j, TokenState.BLUE);//add the blue tokens
				} else {
					freshBoard.freePlace(i, j, TokenState.EMPTY);//add the empty tokens 
				}
			}
		}

		gc.setBoard(freshBoard);
		if(playerTurn == "RED"){//if the player turn is red
			gc.setTurn(TokenState.RED);//set the tokenState to RED
		} else {
			gc.setTurn(TokenState.BLUE);//else set it to blue
		}
		
		gc.endFreePlace();//end the free placing mode
		
		return gc;//return the game controller to be called into the game screen

	}

}
