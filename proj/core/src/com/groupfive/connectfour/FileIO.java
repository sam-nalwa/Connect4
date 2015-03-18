package com.groupfive.connectfour;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.sun.xml.internal.ws.util.StringUtils;

public class FileIO{

  public static Integer getSaveCount(){
	  FileHandle text = Gdx.files.internal("assets/save.txt");
	  String s = text.readString();
	  int counter = 0;
	  for( int i=0; i<s.length(); i++ ) {
	    	System.out.println(s.charAt(i));
		    if( s.charAt(i) == '\n' ) {
		        counter++;
		    } 
		}
	  return counter;
  }
  //Saves the current gamestate into a textfile
  public static void save(Board currBoard, String pTurn)throws IOException {

		
	 ArrayList<String[]> data = new ArrayList<String[]>();
	  //Read contents of file into arraylist data
	 // PrintStream output = new PrintStream(new File("save.txt"));
	  String line="";
	  int count=0;
	  //Construct a string of the format RB- based on the state of the gameBoard
  String gameTokens = "";

  for (int i = 0; i < 7; i++){
	  for (int j = 0; j < 6; j++){
		  Token temp = currBoard.getToken(i,j);

		  if (temp.getState() == TokenState.RED){
			  gameTokens +="R";
		  }
		  else if(temp.getState() == TokenState.BLUE){
			  gameTokens += "B";
		  }
		  else{
			  gameTokens += "-";
		  }
	  }
  }
  
  //saves the current date/time
  Date myDate = new Date();
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM-HH:mm");
  String date = sdf.format(myDate);
  
  
  //Append text to file
  FileHandle file = Gdx.files.local("save.txt");
  file.writeString(date+"@"+gameTokens+"@"+pTurn+"\n", true); 
	  
  System.out.println("GameState Saved.");
  
  FileHandle test = Gdx.files.internal("save.txt");
  String text = test.readString();
  System.out.println(text);
		

}


 //Loads the gameState specified at index loadGame
  public static GameController load(Integer loadGame) throws IOException{
	 
	 //Load the file
	 FileHandle file = Gdx.files.internal("save.txt");
	 String[] text = file.readString().split("@",0);
	 String tokenPlaces = text[1];
	 String playerTurn = text[2];
	 int lineCount = 0;
	 
	 GameController gc = new GameController(true);
	 
	 Board freshBoard = new Board();
	 
	 //Make the board
	  for (int i = 0; i < 7; i++){ //columns
		  for (int j = 0; j < 6; j++){ //rows
			  if(tokenPlaces.charAt(6*i + j) == 'R'){
				  freshBoard.freePlace(i, j, TokenState.RED);
			  } else if(tokenPlaces.charAt(6*i + j) == 'B'){
				  freshBoard.freePlace(i, j, TokenState.BLUE);
			  } else {
				  freshBoard.freePlace(i, j, TokenState.EMPTY);
			  }
		  }
	  }
	  
	  gc.setBoard(freshBoard);
	  if(playerTurn == "RED"){
		  gc.setTurn(TokenState.RED);
	  } else {
		  gc.setTurn(TokenState.BLUE);
	  }
  	
	  gc.endFreePlace();
	  return gc;
  }
  
}
