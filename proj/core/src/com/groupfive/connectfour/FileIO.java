package com.groupfive.connectfour;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileIO{
  

  //should actually be void not sure what is actually passed
  //Saves the current gamestate into a textfile
  public static void save(String fileName)throws IOException {
  	 
  	 //get the current boardstate
	Board currBoard = gameController.getBoard();
  
    	Writer wr = null;
	try {
		File f = new File(fileName);
		wr = new BufferedWriter(new FileWriter(f));
  
        	for (int i = 0; i < 8; i++){
          		for (int j = 0; j < 7; j++){
            			Token temp = currBoard.getToken(i,j);
            
				if (temp.getState() = TokenState.RED){
					wr.write("R");
				}
				else if(temp.getState() = TokenState.BLUE){
					wr.write("B");
				}
				else{
					wr.write("-");
            			}
          		}
        	}
      	}
      	finally {
		if (wr != null){
			wr.close();
		}
    
      	}	
  }
  
  //Loads the current gameState from a textFile
  public static void load(String fileName){
  	BufferedReader input = new BufferedReader(new FileReader(fileName));
  	
  	
  	int value = 0;
  	//buffRead.read reads as ascii value and returns -1 if end of stream has been reached
	 while((value = input.read()) != -1){
	 	
         	// converts int to character
        	char c = (char)value;
        	if( c = 'R'){
        		//the token is red
        	}
        	else if( c = 'B'){
        		//the token is blue
        	}
        	else{
        		//the place is empty
        	}
         }
  	
  }
  
}
