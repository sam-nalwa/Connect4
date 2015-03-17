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

public class FileIO{

  
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
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:HH-mm-ss");
  String date = sdf.format(myDate);
  
  
  //Append text to file
  FileHandle file = Gdx.files.local("save.txt");
  file.writeString(date+"@"+gameTokens+"@"+pTurn+"\n", true); 
	  
  System.out.println("GameState Saved.");
  
  FileHandle test = Gdx.files.internal("save.txt");
  String text = test.readString();
  System.out.println(text);
		

}
/*		
    	
    	//add the current state to the list of saved games
    	String[] temp1 = new String[]{date, gameTokens, pTurn};
    	data.add([temp1]);
		
		//Write the list of saved games to save.txt 
    	Writer wr = null;
    	try {
    		File f = new File("save.txt");
    		wr = new BufferedWriter(new FileWriter(f));
    	
    		for(int k = 0; k < data.size(); k++){
    			for(int l = 0; l<3; l++){
    				wr.write(data.get(k)[l] + "@");
    			}
    			wr.write("\n");
    		}

      	}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		wr.close();
  }
 */

 //Loads the gameState specified at index loadGame
  public static void load(Integer loadGame) throws IOException{
	  
		
	 String tokenStates = "";
	 String playerTurn = "";
	 int lineCount = 0;
	 
	  //Read contents of file to get token Locations and player turn
	  BufferedReader br = null;
	 /*
	  try{
			br = new BufferedReader(new FileReader("save.txt"));
			while((line = br.readLine())  != null ){
				
				if(lineCount = loadGame){
					String[] temp = line.split("@");
					tokenStates = temp[1];
					playerTurn = temp[2];
				}
				
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		br.close();
		*/
		
	 
	  for(int i = 0; i<tokenStates.length(); i++){
		  char currToken = tokenStates.charAt(i);
		  if (currToken == 'R'){
			  //make a red token object
		  }
		  else if(currToken == 'B'){
			  //make a blue token object
		  }
		  else{
			  //make a EMPTY token object
		  }
		  //do something with these token objects
	  }
  	
	  //return something important
  	
  }
  
}
