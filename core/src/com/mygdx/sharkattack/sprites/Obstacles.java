package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacles extends InteractiveTileObject {
    public Obstacles(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onMouthHit() {
        Gdx.app.log("Obstacle", "Collision");
    }
}
