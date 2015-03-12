package com.groupfive.connectfour;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileIO{
  
  //get the current boardstate
  private Board currBoard = gameController.getBoard();
  
  //should actually be void not sure what is actually passed
  public static void save(String fileName)throws IOException {
  
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
    }
}
