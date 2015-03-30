package com.groupfive.connectfour;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
	

	//We need to load all of the assets here
	private Texture redToken;
	private Texture blueToken;
	//private TextureAtlas atlas;
	//private Skin skin;
	private Texture board;
	private Stage stage;
	
	//Buttons
	private HashMap<String,Sprite> buttons;
	
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
	private Table table;
	private String show;
	private GameState oldGameState;
	private TextureAtlas atlas = new TextureAtlas("blueButtons.pack");
	private Skin skin = new Skin(Gdx.files.internal("menuSkin.json"), atlas); 
	
	
	public GameScreen(final ConnectFour gamee,boolean freeplacemode,boolean vscomputer){
		this.game=gamee;
		
		batch = new SpriteBatch();
		
		//Load our images
		redToken = new Texture("reds.png");
		blueToken = new Texture("blues.png");
		board = new Texture("sboard.png");
		
		//Load buttons
		buttons = new HashMap<String,Sprite>();
		buttons.put("redSwitch", new Sprite(redToken));
			buttons.get("redSwitch").setPosition(25, this.gameHeight-100);
		buttons.put("blueSwitch", new Sprite(blueToken));
			buttons.get("blueSwitch").setPosition(this.gameWidth-100,this.gameHeight-100);
		buttons.put("okayButton", new Sprite(new Texture("ok.png")));
			buttons.get("okayButton").setPosition(this.gameWidth/2 - 75,10);
		buttons.put("resetButton",new Sprite(new Texture("reset.png")));
			buttons.get("resetButton").setPosition(this.gameWidth-100, 100);
		buttons.put("saveButton", new Sprite(new Texture("save.png")));
			buttons.get("saveButton").setPosition(this.gameWidth-100, 200);
		
		//Set up the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gameWidth,this.gameHeight);
		
		//Set up the game logic (initializing in free place mode)
		gameController = new GameController(freeplacemode,vscomputer);
		
		
		//Event listener!
		eventListener = new EventListener();
		Gdx.input.setInputProcessor(eventListener);
		eventListener.gc = gameController;
		eventListener.gameHeight = this.gameHeight;
		eventListener.boardOffsetX = this.boardOffsetX;
		eventListener.boardOffsetY = this.boardOffsetY;
		eventListener.buttons = this.buttons;
		
	}
	
	public GameScreen(final ConnectFour gamee,GameController control){
		this.game=gamee;
		
		batch = new SpriteBatch();
		
		//Load our images
		redToken = new Texture("reds.png");
		blueToken = new Texture("blues.png");
		board = new Texture("sboard.png");
		
		//Load buttons
		buttons = new HashMap<String,Sprite>();
		buttons.put("redSwitch", new Sprite(redToken));
			buttons.get("redSwitch").setPosition(25, this.gameHeight-100);
		buttons.put("blueSwitch", new Sprite(blueToken));
			buttons.get("blueSwitch").setPosition(this.gameWidth-100,this.gameHeight-100);
		buttons.put("okayButton", new Sprite(new Texture("ok.png")));
			buttons.get("okayButton").setPosition(this.gameWidth/2 - 75,10);
		buttons.put("resetButton",new Sprite(new Texture("reset.png")));
			buttons.get("resetButton").setPosition(this.gameWidth-100, 100);
		buttons.put("saveButton", new Sprite(new Texture("save.png")));
			buttons.get("saveButton").setPosition(this.gameWidth-100, 200);
		
		//Set up the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gameWidth,this.gameHeight);
		
		//Set up the game logic (initializing in free place mode)
		gameController = control;
		
		
		//Event listener!
		eventListener = new EventListener();
		Gdx.input.setInputProcessor(eventListener);
		eventListener.gc = gameController;
		eventListener.gameHeight = this.gameHeight;
		eventListener.boardOffsetX = this.boardOffsetX;
		eventListener.boardOffsetY = this.boardOffsetY;
		eventListener.buttons = this.buttons;
		
	}
	

	@Override
	public void show() {
		stage = new Stage();
		Table table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		TextButton buttonIndicator = new TextButton(show, skin);
		buttonIndicator.setPosition(this.gameWidth/2, this.gameHeight - 200);
		table.add(buttonIndicator).height(80);
		table.bottom();
		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.45f, 0.55f,0.65f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Load the current board state
		boardState = gameController.getBoard();
		stage.act(delta);
		
		//Stage stage = new Stage();

		//DRAW ERRTHING
		batch.begin();
		
		
		
		
		//If we're in free placement mode, then draw the stuff associated with it
		if(gameController.isFreePlacing()){
			buttons.get("okayButton").draw(batch);
			buttons.get("redSwitch").draw(batch);
			buttons.get("blueSwitch").draw(batch);
		}
		buttons.get("resetButton").draw(batch);
		buttons.get("saveButton").draw(batch);
		
		//Draw all of the placed tokens
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
		
		//Draw the board on to of the tokens
		batch.draw(board, boardOffsetX, boardOffsetY);
		
		
		//If we have any errors, draw their error screens
		// We check if the error exists, then we add the button to the list of buttons and draw it.
		if(eventListener.errorList != null && !eventListener.errorList.isEmpty()){
			if(eventListener.errorList.contains(ErrorCode.DEFIESGRAVITY)){
				//Check if it's already in the map of buttons
				if(!buttons.containsKey("gravityError")){
					buttons.put("gravityError", new Sprite(new Texture("gravityError.jpg")));
					buttons.get("gravityError").setPosition(this.gameWidth/2 - 175,this.gameHeight/2 - 75);
				}
				
				//Draw dat
				buttons.get("gravityError").draw(batch);
			} else if(eventListener.errorList.contains(ErrorCode.BADRATIO)){
				//Check if it's already in the map of buttons
				if(!buttons.containsKey("ratioError")){
					buttons.put("ratioError", new Sprite(new Texture("ratioError.jpg")));
					buttons.get("ratioError").setPosition(this.gameWidth/2 - 175,this.gameHeight/2 - 75);
				}
				
				//Draw dat
				buttons.get("ratioError").draw(batch);
			} else if(eventListener.errorList.contains(ErrorCode.NOWINNINGALLOWED)){
				//Check if it's already in the map of buttons
				if(!buttons.containsKey("winError")){
					buttons.put("winError", new Sprite(new Texture("winError.jpg")));
					buttons.get("winError").setPosition(this.gameWidth/2 - 175,this.gameHeight/2 - 75);
				}
				
				//Draw dat
				buttons.get("winError").draw(batch);
			}
		}
		
		GameState display=gameController.getCurrentState();
		if (oldGameState!=gameController.getCurrentState()){
			if (display==GameState.REDWIN){
				show="Red Has Won!!";
			}else if(display==GameState.BLUEWIN){
				show="Blue has Won!!";
			}else if(display==GameState.DRAW){
				show="Its A Draw!!";
			}else{
				show="Game In Progress!!";
			}
		}
		oldGameState=gameController.getCurrentState();
		show();
		//System.out.println(show);
/*
		if (show!=gameController.display){
			System.out.println("its changed");
			show();
			show=gameController.display;
		}
*/
		batch.end();
		stage.draw();
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
