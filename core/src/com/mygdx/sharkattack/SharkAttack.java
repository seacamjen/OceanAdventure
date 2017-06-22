package com.mygdx.sharkattack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.sharkattack.screens.PlayScreen;

public class SharkAttack extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 900;
	public static final int V_HEIGHT = 650;
	public static float PPM = 250;

	public static final short DEFAULT_BIT = 1;
	public static final short SHARK_BIT = 2;
	public static final short NET_BIT = 4;
	public static final short PLANT_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	
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
