package com.groupfive.connectfour;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectFour extends Game {

	
<<<<<<< Updated upstream
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
=======
    public SpriteBatch batch;
    public BitmapFont font;
>>>>>>> Stashed changes

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        this.setScreen(new MMenu(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
