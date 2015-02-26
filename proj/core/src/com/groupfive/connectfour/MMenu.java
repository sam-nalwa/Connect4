package com.groupfive.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MMenu implements Screen{
	
	final ConnectFour game;
	OrthographicCamera camera;
	
	 private TextureRegion textureframe;
	 private SpriteBatch batch;
	 private Stage stage;
	 private TextureAtlas atlas;
	 private Skin skin;
	 private Table table;
	 private TextButton buttonExit, buttonPlay, buttonCreate;
	 
	
	
	public MMenu(ConnectFour gamee){
		game=gamee;
		camera=new OrthographicCamera();
		camera.setToOrtho(false,600,800);
	}
		

	@Override
	public void render(float delta) {
		//render the main screen graphics
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		stage.act(delta);
		batch.begin();
		batch.draw(textureframe, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		stage.draw();
		 
		game.batch.begin();
		game.batch.end();
				 
		
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		 textureframe = new TextureRegion(new Texture(Gdx.files.internal("background.png")),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		 stage = new Stage();
		 
		 Gdx.input.setInputProcessor(stage);
		 
		 atlas = new TextureAtlas("blueButtons.pack");
		 skin = new Skin(Gdx.files.internal("menuSkin.json"), atlas);
		 
		 table = new Table(skin);
		 table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		 //Create and implent the PLAY button
		 buttonPlay = new TextButton("Play", skin);
		 buttonPlay.pad(20);
		 buttonPlay.addListener(new ClickListener(){
			 
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
		 game.setScreen(new GameScreen(game,false));
		 }
		 });
		 
		//Create and implement the Create Board Button
		 buttonCreate = new TextButton("Create Board", skin);
		 buttonCreate.pad(20);
		 buttonCreate.addListener(new ClickListener(){
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
		 game.setScreen(new GameScreen(game,true));
		 }
		 });
		 
		//Create and implement the EXIT button
		 buttonExit = new TextButton("Exit", skin);
		 buttonExit.pad(20);
		 buttonExit.addListener(new ClickListener(){
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
		 Gdx.app.exit();
		 }
		 });
		 

		 table.add(buttonPlay);
		 table.getCell(buttonPlay).spaceBottom(20);
		 table.row();
		 table.add(buttonCreate);
		 table.getCell(buttonCreate).spaceBottom(20);
		 table.row();
		 table.add(buttonExit);
		 table.getCell(buttonExit).spaceBottom(20);
		 table.bottom();
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
