package com.groupfive.connectfour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Client{
	public static void main(String[] args) {
		GameController game = new GameController(false);
		System.out.println("Begin 'Normal mode' Testing");
		printBoard(game.getBoard());
		game.insertPiece(0,0);
		System.out.println("Place: (0,0)");
		printBoard(game.getBoard());
		game.insertPiece(0,1);
		System.out.println("Place: (0,1)");
		printBoard(game.getBoard());
		game.insertPiece(3,0);
		System.out.println("Place: (3,0)");
		printBoard(game.getBoard());
		game.insertPiece(4,0);
		System.out.println("Place: (4,0)");
		printBoard(game.getBoard());
		
		game = new GameController(true);
		System.out.println("Begin 'Free place mode' Testing");
		printBoard(game.getBoard());
		game.insertPiece(0,0);
		System.out.println("Place: (0,0)");
		printBoard(game.getBoard());
		game.switchColour();
		game.insertPiece(1,2);
		System.out.println("Colour switch");
		System.out.println("Place: (1,2)");
		printBoard(game.getBoard());
		game.insertPiece(3,0);
		System.out.println("Place: (3,0)");
		printBoard(game.getBoard());
		System.out.println("Copy board:");//Test copy constructor
		Board copyboard = new Board (game.getBoard());
		printBoard(copyboard);
		GameController copygame = new GameController(true);
		copygame.setBoard(copyboard);
		copygame.insertPiece(4, 4);
		System.out.println("Placed (4,4) on the copied board");
		printBoard(copygame.getBoard());
		System.out.println("Did the original board remain unchanged?");
		printBoard(game.getBoard());
		//testing error throwing
		game.reset();
		game.insertPiece(0, 1);
		game.switchColour();
		game.insertPiece(0, 5);
		game.insertPiece(0, 0);
		game.insertPiece(2, 5);
		game.insertPiece(6, 0);
		game.insertPiece(5, 1);
		game.insertPiece(4, 2);
		game.insertPiece(3, 3);
		
		printBoard(game.getBoard());
		HashSet<ErrorCode> errors =  game.endFreePlace();
		if (!errors.isEmpty()){
			for (ErrorCode c : errors){
				System.out.println(c);
			}
		}
	}
	
	static void printBoard(Board brd){
		String[] rows = new String[6];
		for (int i = 0; i < rows.length;i++){
			rows[i] = "";
		}
		for (int i = 0; i < brd.colLength; i++){
			for (int j = 0; j < brd.rowLength; j++){
				if (brd.getToken(i,j).getState() == TokenState.RED){
					rows[j] += " R ";
				}else if (brd.getToken(i,j).getState() == TokenState.BLUE){
					rows[j] += " B ";
				}else if (brd.getToken(i,j).getState() == TokenState.EMPTY){
					rows[j] += " X ";
				}
			}
		}
		
		System.out.println("");
		System.out.println("====================");
		for (int i = rows.length - 1; i >= 0; i--){
			System.out.println(rows[i]);
		}
		System.out.println("====================");
		System.out.println("");
	}
}