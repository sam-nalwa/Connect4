package com.groupfive.connectfour;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectFour extends ApplicationAdapter {
	
	//We need to load all of the assets here
	private Texture redToken;
	private Texture blueToken;
	private Texture board;
	
	//SpriteBatch is used to draw 2D images
	private SpriteBatch batch;
	
	//Orthographic Camera to display world
	private OrthographicCamera camera;
	int gameHeight = 600;
	int gameWidth = 800;
	
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
		redToken = new Texture("red.png");
		blueToken = new Texture("blue.png");
		board = new Texture("sboard.png");
		
		//Set up the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gameWidth,this.gameHeight);
		
		//Set up the game logic (initializing in free place mode)
		gameController = new GameController(true);
		
		//Event listener!
		eventListener = new EventListener();
		Gdx.input.setInputProcessor(eventListener);
		eventListener.gc = gameController;
		eventListener.gameHeight = this.gameHeight;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.45f, 0.55f,0.65f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Load the current board state
		boardState = gameController.getBoard();
		
		//Test
		gameController.insertPiece(0, 0);

		//DRAW ERRTHING
		batch.begin();
		for (int c = 0; c < boardState.colLength; c++){
			for (int r = 0; r < boardState.rowLength; r++){
				if (boardState.getToken(c,r).getState() == TokenState.RED){
					//Draw a red token in the correct location
					batch.draw(redToken,50+c*75,25+r*75);
				}else if (boardState.getToken(c,r).getState() == TokenState.BLUE){
					batch.draw(blueToken,50+c*75,25+r*75);
				}else if (boardState.getToken(c,r).getState() == TokenState.EMPTY){
					//Don't draw anything
				}
			}
		}
		
		//Finally, draw the board on top of everything else
		batch.draw(board, 50, 25);
		batch.end();
	}
}
