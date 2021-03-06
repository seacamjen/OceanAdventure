package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.sharkattack.SharkAttack;

public class Plants extends InteractiveTileObject {
    public Plants(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SharkAttack.PLANT_BIT);
    }

    @Override
    public void onMouthHit() {
        Gdx.app.log("Plant", "Collision");
    }
}
