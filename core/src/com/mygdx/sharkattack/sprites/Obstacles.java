package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.sharkattack.SharkAttack;
import com.mygdx.sharkattack.scenes.Hud;

public class Obstacles extends InteractiveTileObject {
    public Obstacles(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SharkAttack.NET_BIT);
    }

    @Override
    public void onMouthHit() {
        Gdx.app.log("Obstacle", "Collision");
        setCategoryFilter(SharkAttack.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
    }
}
