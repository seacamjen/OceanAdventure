package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.sharkattack.SharkAttack;

public class Plants extends InteractiveTileObject {
    public Plants(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

    }
}
