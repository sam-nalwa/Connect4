package com.groupfive.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class MMenu implements Screen{
	
	final ConnectFour game;
	OrthographicCamera camera;
	
	public MMenu(ConnectFour gamee){
		game=gamee;
		camera=new OrthographicCamera();
		camera.setToOrtho(false,600,800);
	}
		
		
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 10f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
 
		game.batch.begin();
		((BitmapFont) game.font).draw(game.batch, "AA4 KNAVES : NEW EDITION CONNECT 4", 100, 500);
		((BitmapFont) game.font).draw(game.batch, "Tap anywhere to begin!", 100, 450);
		game.batch.end();
 
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			game.setScreen(new GameScreen(game));
			dispose();
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
		// TODO Auto-generated method stub
		
	}

	
	
}
