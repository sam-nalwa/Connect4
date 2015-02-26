package com.groupfive.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MMenu implements Screen{
	
	final ConnectFour game;
	OrthographicCamera camera;
	
	 private TextureRegion bkTexture;
	 private SpriteBatch batch;
	 private Stage stage;
	 private TextureAtlas atlas;
	 private Skin skin;
	 private Table table;
	 private TextButton buttonExit, buttonPlay;
	 private Label heading;
	 private BitmapFont title;
	 
	
	
	public MMenu(ConnectFour gamee){
		game=gamee;
		camera=new OrthographicCamera();
		camera.setToOrtho(false,600,800);
	}
		

	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 10f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
 
 
		stage.act(delta);
		 batch.begin();
		 batch.draw(bkTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 batch.end();
		 stage.draw();
		 
			game.batch.begin();
			//((BitmapFont) game.font).draw(game.batch, "AA4 KNAVES : NEW EDITION CONNECT 4", 100, 700);
			//((BitmapFont) game.font).draw(game.batch, "Tap anywhere to begin!", 100, 650);
			game.batch.end();
			
		 
		/*
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		*/
		 
		
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		 bkTexture = new TextureRegion(new Texture(Gdx.files.internal("background.png")),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		 stage = new Stage();
		 
		 Gdx.input.setInputProcessor(stage);
		 
		 atlas = new TextureAtlas("blueButtons.pack");
		 skin = new Skin(Gdx.files.internal("menuSkin.json"), atlas);
		 
		 table = new Table(skin);
		 table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		 //Button PLAY
		 buttonPlay = new TextButton("Play", skin);
		 buttonPlay.pad(20);
		 buttonPlay.addListener(new ClickListener(){
			 
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
		 game.setScreen(new GameScreen(game));
		 }
		 });
		 
		 table.add(buttonPlay);
		 table.getCell(buttonPlay).spaceBottom(10);
		 table.row();
		 
		 table.debug();
		 stage.addActor(table);
		 
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
		 stage.dispose();
		 skin.dispose();
		 atlas.dispose();
		 batch.dispose();
		 
		
	}

	
	
}
