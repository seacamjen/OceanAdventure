package com.mygdx.sharkattack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.sharkattack.screens.PlayScreen;

public class SharkAttack extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 1000;
	public static final int V_HEIGHT = 750;
	public static float PPM = 250;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
