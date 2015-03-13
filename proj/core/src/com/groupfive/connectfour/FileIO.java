package com.groupfive.connectfour;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileIO{
	
	
  
  //Saves the current gamestate into a textfile
  public static void save(Board currBoard, String pTurn)throws IOException {

	  //Read contents of file into arraylist data
	  BufferedReader br = null;
	  try{
			br = new BufferedReader(new FileReader("save.txt"));
			while((line = br.readLine()) != null){
				String[] temp = line.split(",");
				data.add(temp);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
			if (br != null){
				try{
					br.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	
	  	//saves the current date/time
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:HH-mm-ss");
		String date = sdf.format(myDate);
		
		
		//Construct a string of the format RB- based on the state of the gameBoard
		String gameTokens = "";
		
    	for (int i = 0; i < 8; i++){
      		for (int j = 0; j < 7; j++){
        			Token temp = currBoard.getToken(i,j);
        
			if (temp.getState() == TokenState.RED){
				gameTokens = gameTokens.append("R");
			}
			else if(temp.getState() == TokenState.BLUE){
				gameTokens = gameTokens.append("B");
			}
			else{
				gameTokens = gameTokens.append("-");
        		}
      		}
    	}
		
    	
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
		finally{
			if (wr != null){
				try{
					wr.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
  }

 //Loads the current gameState from a textFile
  public static void load(String fileName, GameController currGame) throws IOException{
  	BufferedReader input = new BufferedReader(new FileReader(fileName));
  	
  	
  	int value = 0;
  	//buffRead.read reads as ascii value and returns -1 if end of stream has been reached
	 while((value = input.read()) != -1){
	 	
         	// converts int to character
        	char c = (char)value;
        	if( c == 'R'){
        		//currGame.insertPiece(col, row,TokenState.RED)
        	}
        	else if( c == 'B'){
        		//the token is blue
        	}
        	else if (c == '-'){
        		//the place is empty
        	}
         }
  	input.close();
  }
  
}
