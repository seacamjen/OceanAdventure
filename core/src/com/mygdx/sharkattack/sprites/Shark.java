package com.mygdx.sharkattack.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.sharkattack.SharkAttack;
import com.mygdx.sharkattack.screens.PlayScreen;

public class Shark extends Sprite {
    public enum State { SWIMMINGRIGHT, RESTINGRIGHT, SWIMMINGLEFT, RESTINGLEFT, WADING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion sharkStill;
    private Animation<TextureRegion> sharkSwim;
    private Animation<TextureRegion> sharkSwimLeft;
    private Animation<TextureRegion> sharkRest;
    private Animation<TextureRegion> sharkRestLeft;
    private float stateTimer;

    public Shark(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("a_swim_to_right_sheet_small"));
        this.world = world;
        currentState = State.WADING;
        previousState = State.WADING;
        stateTimer = 0;

        //swimming animation right
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 128, 0, 128, 128));
         sharkSwim = new Animation(0.1f, frames);
         frames.clear();

        //resting animation right
        for(int i = 6; i < 11; i++)
            frames.add(new TextureRegion(getTexture(), i * 128, 0, 128, 128));
        sharkRest = new Animation(0.1f, frames);
        frames.clear();

        //swimming animation left
        for(int i = 0; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 128, 128, 128, 128));
        sharkSwimLeft = new Animation(0.1f, frames);
        frames.clear();

        //resting animation left
        for(int i = 6; i < 11; i++)
            frames.add(new TextureRegion(getTexture(), i * 128, 128, 128, 128));
        sharkRestLeft = new Animation(0.1f, frames);
        frames.clear();

        sharkStill = new TextureRegion(getTexture(), 0, 0, 128, 128);

        defineShark();
        setBounds(0, 0, 128 / SharkAttack.PPM, 128 / SharkAttack.PPM);
        setRegion(sharkStill);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame (float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState){
            case SWIMMINGRIGHT:
                region = sharkSwim.getKeyFrame(stateTimer, true);
                        break;
            case RESTINGRIGHT:
                region = sharkRest.getKeyFrame(stateTimer, true);
                break;
            case SWIMMINGLEFT:
                region = sharkSwimLeft.getKeyFrame(stateTimer, true);
                break;
            case RESTINGLEFT:
                region = sharkRestLeft.getKeyFrame(stateTimer, true);
                break;
            case WADING:
            default:
                region = sharkStill;
                break;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0 && b2body.getLinearVelocity().x > 0)
            return State.SWIMMINGRIGHT;
        else if (b2body.getLinearVelocity().y > 0 && b2body.getLinearVelocity().x < 0)
            return State.SWIMMINGLEFT;
        else if (b2body.getLinearVelocity().y < 0 && b2body.getLinearVelocity().x > 0)
            return State.RESTINGRIGHT;
        else if (b2body.getLinearVelocity().y < 0 && b2body.getLinearVelocity().x < 0)
            return State.RESTINGLEFT;
        else if (b2body.getLinearVelocity().x < 0 || (b2body.getLinearVelocity().x < 0 && previousState == State.SWIMMINGLEFT))
            return State.SWIMMINGLEFT;
        else if (b2body.getLinearVelocity().x > 0)
            return State.SWIMMINGRIGHT;
        else
            return State.WADING;
    }

    public void defineShark() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / SharkAttack.PPM, 32 / SharkAttack.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(40 / SharkAttack.PPM);


        fdef.filter.categoryBits = SharkAttack.SHARK_BIT;
        fdef.filter.maskBits = SharkAttack.DEFAULT_BIT | SharkAttack.NET_BIT | SharkAttack.PLANT_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape mouth = new EdgeShape();
        mouth.set(new Vector2(40 / SharkAttack.PPM, 30 / SharkAttack.PPM), new Vector2(40 / SharkAttack.PPM, -30 / SharkAttack.PPM));
        fdef.shape = mouth;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("mouth");
    }
}
