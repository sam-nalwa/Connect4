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
	
	//Game Controller
	private GameController gameController;
	
	
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
		camera.setToOrtho(false,800,600);
		
		//Set up the game logic (initializing in free place mode)
		gameController = new GameController(true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.45f, 0.55f,0.65f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(board, 50, 25);
		batch.end();
	}
}
