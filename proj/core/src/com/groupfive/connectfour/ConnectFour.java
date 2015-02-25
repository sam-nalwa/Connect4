package com.groupfive.connectfour;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectFour extends ApplicationAdapter {
	
	//We need to load all of the assets here
	private Texture redToken;
	private Texture blueToken;
	private Texture board;
	private Sprite redSwitch;
	private Sprite blueSwitch;
	
	//SpriteBatch is used to draw 2D images
	private SpriteBatch batch;
	
	//Orthographic Camera to display world
	private OrthographicCamera camera;
	int gameHeight = 600;
	int gameWidth = 800;
	int boardOffsetX = 140; // Distance from left edge of screen
	int boardOffsetY = 50; //Distance from bottom
	
	//Game Controller & Input Handler
	private GameController gameController;
	private EventListener eventListener;
	
	//Board state saved here
	private Board boardState;
	
	
	@Override
	public void create () {
		
		//Add the SpriteBatch
		batch = new SpriteBatch();
		
		//Load our images
		redToken = new Texture("reds.png");
		blueToken = new Texture("blues.png");
		board = new Texture("sboard.png");
		
		redSwitch = new Sprite(redToken);
		blueSwitch = new Sprite(blueToken);
		
		//Set up the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gameWidth,this.gameHeight);
		
		//Set up the game logic (initializing in free place mode)
		gameController = new GameController(true);
		
		//Draw the two color switchers
		redSwitch.setPosition(25, this.gameHeight-100);
		blueSwitch.setPosition(this.gameWidth-100,this.gameHeight-100);

		
		//Event listener!
		eventListener = new EventListener();
		Gdx.input.setInputProcessor(eventListener);
		eventListener.gc = gameController;
		eventListener.gameHeight = this.gameHeight;
		eventListener.boardOffsetX = this.boardOffsetX;
		eventListener.boardOffsetY = this.boardOffsetY;
		eventListener.blueSwitch = this.blueSwitch;
		eventListener.redSwitch = this.redSwitch;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.45f, 0.55f,0.65f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Load the current board state
		boardState = gameController.getBoard();

		
		//DRAW ERRTHING
		batch.begin();
		
		redSwitch.draw(batch);
		blueSwitch.draw(batch);
		
		//Draw the two player switchers
		//batch.draw(redToken,25,this.gameHeight-100);
		//batch.draw(blueToken,this.gameWidth-100,this.gameHeight-100);

		for (int c = 0; c < boardState.colLength; c++){
			for (int r = 0; r < boardState.rowLength; r++){
				if (boardState.getToken(c,r).getState() == TokenState.RED){
					//Draw a red token in the correct location
					batch.draw(redToken,boardOffsetX+c*75,boardOffsetY+r*75);
				}else if (boardState.getToken(c,r).getState() == TokenState.BLUE){
					batch.draw(blueToken,boardOffsetX+c*75,boardOffsetY+r*75);
				}else if (boardState.getToken(c,r).getState() == TokenState.EMPTY){
					//Don't draw anything
				}
			}
		}
		
		//Finally, draw the board on top of everything else
		batch.draw(board, boardOffsetX, boardOffsetY);
		batch.end();
	}
}
