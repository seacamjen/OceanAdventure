package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.sharkattack.SharkAttack;

public class Shark extends Sprite {
    public World world;
    public Body b2body;

    public Shark(World world) {
        this.world = world;
        defineShark();
    }

    public void defineShark() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / SharkAttack.PPM, 32 / SharkAttack.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / SharkAttack.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
