package com.hungryfool.brobirdies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hungryfool.brobirdies.states.GameStateManager;
import com.hungryfool.brobirdies.states.MenuState;

public class BroBirdies extends ApplicationAdapter {

	public static final int WIDTH = 1245;
	public static final int HEIGHT = 796;
	public static final String TITLE = "Bro Birdies";

	public SpriteBatch batch;
	public GameStateManager gsm;
	
	@Override
	public void create () {

		gsm = new GameStateManager();
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
