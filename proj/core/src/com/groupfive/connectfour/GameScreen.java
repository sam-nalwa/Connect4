package com.groupfive.connectfour;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	

	//We need to load all of the assets here
	private Texture redToken;
	private Texture blueToken;
	private Texture board;
	private Sprite redSwitch;
	private Sprite blueSwitch;
	private Sprite okayButton;
	
	//SpriteBatch is used to draw 2D images
	SpriteBatch batch;
	
	//Orthographic Camera to display world
	private OrthographicCamera camera;
	int gameHeight = 600;
	int gameWidth = 800;
	int boardOffsetX = 140; // Distance from left edge of screen
	int boardOffsetY = 100; //Distance from bottom
	
	//Game Controller & Input Handler
	private GameController gameController;
	private EventListener eventListener;
	
	//Board state saved here
	private Board boardState;
	public Object font;
	final ConnectFour game;
	
	public GameScreen(final ConnectFour gamee){
		this.game=gamee;
		
		batch = new SpriteBatch();
		
		//Load our images
		redToken = new Texture("reds.png");
		blueToken = new Texture("blues.png");
		board = new Texture("sboard.png");
		
		redSwitch = new Sprite(redToken);
		blueSwitch = new Sprite(blueToken);
		okayButton = new Sprite(new Texture("ok.png"));
		
		//Set up the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gameWidth,this.gameHeight);
		
		//Set up the game logic (initializing in free place mode)
		gameController = new GameController(true);
		
		//Draw the two color switchers & the button
		redSwitch.setPosition(25, this.gameHeight-100);
		blueSwitch.setPosition(this.gameWidth-100,this.gameHeight-100);
		okayButton.setPosition(this.gameWidth/2 - 75,10);
		
		//Event listener!
		eventListener = new EventListener();
		Gdx.input.setInputProcessor(eventListener);
		eventListener.gc = gameController;
		eventListener.gameHeight = this.gameHeight;
		eventListener.boardOffsetX = this.boardOffsetX;
		eventListener.boardOffsetY = this.boardOffsetY;
		eventListener.blueSwitch = this.blueSwitch;
		eventListener.redSwitch = this.redSwitch;
		eventListener.okayButton = this.okayButton;
		
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.45f, 0.55f,0.65f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Load the current board state
		boardState = gameController.getBoard();

		
		//DRAW ERRTHING
		batch.begin();
		
		redSwitch.draw(batch);
		blueSwitch.draw(batch);
		okayButton.draw(batch);
		
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
		
		
		//When I said finally last time I lied, this is actually what we do finally.
		if(eventListener.errorList != null){
			System.out.println("yo");
		}
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
