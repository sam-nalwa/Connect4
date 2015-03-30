package com.groupfive.connectfour;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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
	
	final ConnectFour game;//declare the game variable
	OrthographicCamera camera;//set a 'camera' to be used by libgdx
	
	 private TextureRegion textureframe;//declare a 'textureframe' to which graphics can be added
	 private SpriteBatch batch;//declare sprites
	 private Stage stage;//declare the 'stage' to which 'actors'/elements can be added
	 private TextureAtlas atlas=new TextureAtlas("blueButtons.pack");//create a new 'atlas' (picture files for the buttons)
	 private Skin skin=new Skin(Gdx.files.internal("menuSkin.json"), atlas); //add the fonts and charachters in for the text of the buttons

	
	 private Table table, loadTable, playTable, loadDelete;//declare the main menu and load game tables
	 private TextButton buttonExit, buttonPlay, buttonCreate, buttonLoad, buttonSelectLoad, buttonHuman, buttonComputer, buttonBack;//declare all the text buttons implemted 
	 
	
	
	public MMenu(ConnectFour c4game){
		game=c4game;//create a new game
		camera=new OrthographicCamera();//create a new camera
		camera.setToOrtho(false,600,800);//set the dimentions 
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
		//render all the elements
		game.batch.begin();
		game.batch.end();
				 
		
	}
	
	
	@Override
	public void show() {
		//displaying all the parts on the screen
		batch = new SpriteBatch();
		 textureframe = new TextureRegion(new Texture(Gdx.files.internal("background.png")),0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		 stage = new Stage();
		 
		 Gdx.input.setInputProcessor(stage);
		  //declare the new table with the imported 'skin' file
		 table = new Table(skin);
		 //set the bounds for the table
		 table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 
		 //Create and implement the PLAY button
		 buttonPlay = new TextButton("Play", skin);
		 buttonPlay.pad(20);
		 buttonPlay.addListener(new ClickListener(){
			 
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
			 table.setVisible(false);
			 playTable=new Table(skin);
			 playTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			 buttonHuman = new TextButton("Play Versus Human", skin);
				buttonHuman.pad(20);
				playTable.add(buttonHuman).height(85).spaceBottom(10);
				buttonHuman.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.println("play agains human");
						game.setScreen(new GameScreen(game,false,false));
					}
			});
			playTable.row();
			 buttonComputer = new TextButton("Play Versus Computer", skin);
				buttonComputer.pad(20);
				playTable.add(buttonComputer).height(85).spaceBottom(10);
				buttonComputer.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.println("play agains computer");
						game.setScreen(new GameScreen(game,false,true));
					}
					
			});
			playTable.row();
			buttonBack = new TextButton("<  Go Back", skin);
			buttonBack.pad(20);
			playTable.add(buttonBack).height(85);
			buttonBack.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					playTable.setVisible(false);
					table.setVisible(true);
				}
			});
			playTable.bottom();
			stage.addActor(playTable);
			// game.setScreen(new GameScreen(game,false));//create a new empty game in regular more when play is clicked 
		 }
		 });
		 
		 //Create and implement the load game button
		 buttonLoad = new TextButton("Load Game", skin);
		 buttonLoad.pad(20);
		 buttonLoad.addListener(new ClickListener(){
		 @Override
		 public void clicked(InputEvent event, float x, float y){
			 
		 FileHandle file = Gdx.files.internal("save.txt");//import the file with all the saves 
		 String[] allgames=file.readString().split("\n");//split the string to find out how many saves there are
		 displayLoader(allgames.length,allgames[0].equals(""));//call the function to load the game in
		 }
		});
		 		 
		 
		//Create and implement the Create Board Button
		 buttonCreate = new TextButton("Create Board", skin);
		 buttonCreate.pad(20);
		 buttonCreate.addListener(new ClickListener(){
		 @Override
		 public void clicked(InputEvent event, float x, float y) {
		 game.setScreen(new GameScreen(game,true,false));
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
		 
		 //add all the buttons to the main menu
		 table.add(buttonPlay).height(90);
		 table.getCell(buttonPlay).spaceBottom(10);
		 table.row();
		 table.add(buttonCreate).height(90);;
		 table.getCell(buttonCreate).spaceBottom(10);
		 table.row();
		 table.add(buttonLoad).height(90);
		 table.getCell(buttonLoad).spaceBottom(10);
		 table.row();
		 table.add(buttonExit).height(90);
		 table.getCell(buttonExit).spaceBottom(10);
		 table.bottom();
		 stage.addActor(table);
	
		 
	}
	//Called when buttonLoad is pressed
	
	public void displayLoader(int count,boolean empty) {
		if (empty==false){
			//Hide our table of buttons
			table.setVisible(false);

			//Start drawing a new table of buttons

			loadTable = new Table(skin);
			loadTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			int r = 0;
			FileHandle file = Gdx.files.internal("save.txt");
			String[] allgames=file.readString().split("\n");
			//Create a new button for every save slot
			for (int i=0; i < count; i++) {
				//Increment r
				r++;
				String Stringdate=allgames[i].split("@")[0];
				buttonSelectLoad = new TextButton(Stringdate, skin);
				buttonSelectLoad.pad(20);
				//We must redefine i in order for it to be used in the following method

				final int j = i;

				//Create a method which is called when the button is clicked
				buttonSelectLoad.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						loadTable.setVisible(false);
						loadDelete = new Table(skin);
						loadDelete.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
						buttonSelectLoad = new TextButton("Load This Game", skin);
						buttonSelectLoad.addListener(new ClickListener(){
							@Override
							public void clicked(InputEvent event, float x, float y) {
								loadGame(j);
							}
						});
						//algorithms to load and delete save stores
						loadDelete.add(buttonSelectLoad);loadDelete.row();
						buttonSelectLoad = new TextButton("Delete This Game", skin);
						buttonSelectLoad.addListener(new ClickListener(){
							@Override
							public void clicked(InputEvent event, float x, float y) {
								 FileHandle file = Gdx.files.local("save.txt");
								 String[] allgames=file.readString().split("\n");
								 List result = new LinkedList();
								 for(String item : allgames){
									 if(!allgames[j].equals(item)){
										 result.add(item);
									 } 
								 }
								 result.toArray(allgames);
								 for (int d=0;d<allgames.length;d++){
									 file.writeString("",false);
								 }
								 for (int d=0;d<(allgames.length-1);d++){
									 file.writeString(allgames[d]+"\n",true);
								 }
								 loadDelete.setVisible(false);
								 table.setVisible(true);
							}
						});
						//add table to the display
						loadDelete.add(buttonSelectLoad);loadDelete.row();
						buttonSelectLoad.pad(20);
						loadDelete.bottom();
						stage.addActor(loadDelete);
						//
					}
				});

				//Add our created button to the table

				loadTable.add(buttonSelectLoad).width(350).height(80);
				loadTable.getCell(buttonSelectLoad).spaceBottom(10);

				//On every second value of r, start a new row.

				if (r == 2) { 
					loadTable.row();
					r = 0;
				}

			}
		}
		//if there are no saves display that there are no saved and give the options to go back 
		if (empty==true){
			table.setVisible(false);
			loadTable = new Table(skin);
			loadTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			buttonSelectLoad = new TextButton("There Are No Saves Yet", skin);
			buttonSelectLoad.pad(20);
			loadTable.add(buttonSelectLoad);
			loadTable.getCell(buttonSelectLoad).spaceBottom(150);
			loadTable.row();
			buttonSelectLoad = new TextButton("<  Go Back", skin);
			buttonSelectLoad.pad(20);
			loadTable.add(buttonSelectLoad).height(85);
			buttonSelectLoad.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					loadTable.setVisible(false);
					table.setVisible(true);
				}
			});
		}
		loadTable.bottom();
		stage.addActor(loadTable);
		
		
	}
	
	//Loads the game at the selected index
	
	public void loadGame(int index) {
		try {
			GameController controller=FileIO.load(index);
			game.setScreen(new GameScreen(game,controller));
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
