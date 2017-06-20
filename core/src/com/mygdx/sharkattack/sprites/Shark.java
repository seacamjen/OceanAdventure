package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.sharkattack.SharkAttack;
import com.mygdx.sharkattack.screens.PlayScreen;

public class Shark extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion sharkStill;

    public Shark(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("a_swim_to_right_sheet_small"));
        this.world = world;
        defineShark();
        sharkStill = new TextureRegion(getTexture(), 0, 0, 128, 128);
        setBounds(0, 0, 128 / SharkAttack.PPM, 128 / SharkAttack.PPM);
        setRegion(sharkStill);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineShark() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / SharkAttack.PPM, 32 / SharkAttack.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(40 / SharkAttack.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
