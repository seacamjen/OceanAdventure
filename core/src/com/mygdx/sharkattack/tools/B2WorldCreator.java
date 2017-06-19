package com.mygdx.sharkattack.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.sharkattack.SharkAttack;
import com.mygdx.sharkattack.sprites.Obstacles;
import com.mygdx.sharkattack.sprites.Plants;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //ground
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SharkAttack.PPM, (rect.getY() + rect.getHeight() / 2) / SharkAttack.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / SharkAttack.PPM, rect.getHeight() / 2 / SharkAttack.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //plants

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Plants(world, map, rect);
        }
        //finish

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SharkAttack.PPM, (rect.getY() + rect.getHeight() / 2) / SharkAttack.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / SharkAttack.PPM, rect.getHeight() / 2 / SharkAttack.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //obstacles

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Obstacles(world, map, rect);
        }
    }
}
