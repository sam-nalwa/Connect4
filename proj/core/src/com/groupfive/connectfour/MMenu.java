package com.groupfive.connectfour;

import java.io.IOException;

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
	 private Table table, loadTable;
	 private TextButton buttonExit, buttonPlay, buttonCreate, buttonLoad;
	 
	
	
	public MMenu(ConnectFour c4game){
		game=c4game;
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
		 
		 //Create and implement the PLAY button
		 buttonPlay = new TextButton("Play", skin);
		 buttonPlay.pad(20);
		 buttonPlay.addListener(new ClickListener(){
			 
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
		 game.setScreen(new GameScreen(game,false));
		 }
		 });
		 
		 //Create and implement the load game button
		 
		 buttonLoad = new TextButton("Load Game", skin);
		 buttonLoad.pad(20);
		 buttonLoad.addListener(new ClickListener(){
		 @Override
		 public void clicked(InputEvent event, float x, float y){
		 
		 displayLoader(FileIO.getSaveCount());
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
		 
		 
		 table.add(buttonPlay).height(80);
		 table.getCell(buttonPlay).spaceBottom(10);
		 table.row();
		 table.add(buttonCreate).height(80);;
		 table.getCell(buttonCreate).spaceBottom(10);
		 table.row();
		 table.add(buttonLoad).height(80);
		 table.getCell(buttonLoad).spaceBottom(10);
		 table.row();
		 table.add(buttonExit).height(80);
		 table.getCell(buttonExit).spaceBottom(10);
		 table.bottom();
		 stage.addActor(table);
	
		 
	}
	//Called when buttonLoad is pressed
	
	public void displayLoader(int count) {
		//Hide our table of buttons
		table.setVisible(false);
		
		//Start drawing a new table of buttons
		
		 loadTable = new Table(skin);
		 loadTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 int r = 0;
		 //Create a new button for every save slot
		for (int i=0; i < count; i++) {
			//Increment r
			 r++;
			 TextButton buttonSelectLoad = new TextButton(String.format("Save #%d", i), skin);
			 buttonSelectLoad.pad(20);
			 //We must redefine i in order for it to be used in the following method
			 
			 final int j = i;
			 
			 //Create a method which is called when the button is clicked
			 buttonSelectLoad.addListener(new ClickListener(){
			 @Override
			 public void clicked(InputEvent event, float x, float y) {
				 loadGame(j);
			 }
			 });
			 
			 //Add our created button to the table
			 
			 loadTable.add(buttonSelectLoad).height(80);
			 loadTable.getCell(buttonSelectLoad).spaceBottom(10);
			 
			 //On every second value of r, start a new row.
			 
			 if (r == 2) { 
				 loadTable.row();
				 r = 0;
			 }

		}
		loadTable.bottom();
		stage.addActor(loadTable);
		
		
	}
	
	//Loads the game at the selected index
	
	public void loadGame(int index) {
		try {
			//Replace game
			FileIO.load(index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
