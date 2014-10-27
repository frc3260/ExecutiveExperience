package com.eddie.executiveexperience.Entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eddie.executiveexperience.Constants;
import com.eddie.executiveexperience.Entity.UserData.MovingPlatformUserData;
import com.eddie.executiveexperience.GameStage;

public class MovingPlatform extends GameActor
{
    protected Vector2 startPos;
    protected Vector2 finalPos;

    protected int movingTo;

    public MovingPlatform(GameStage gameStage, float x, float y, MapObject mapObject)
    {
        super(gameStage);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        bodyDef.position.set(new Vector2(x, y));

        Body body = gameStage.getWorld().createBody(bodyDef);
        Fixture fixture = body.createFixture(shape, Constants.ENTITY_DENSITY);
        fixture.setFriction(100f);
        body.resetMassData();

        MovingPlatformUserData entityData = new MovingPlatformUserData(gameStage, getClass().getSimpleName(), 1f, 1f);

        body.setUserData(entityData);

        shape.dispose();

        setBody(body);

        movingTo = 1;

        startPos = new Vector2(x, y);

        finalPos = new Vector2(Float.parseFloat(mapObject.getProperties().get("finalPosX", "0.0", String.class)), Float.parseFloat(mapObject.getProperties().get("finalPosY", "0.0", String.class)));
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        getUserData().getAnimatedBox2DSprite().draw(batch, body);
    }

    @Override
    public void act(float delta)
    {
        if(body == null)
        {
            return;
        }

        super.act(delta);

        Vector2 diffVector = new Vector2(0f, 0f);

        if(movingTo == 0)
        {
            if(body.getPosition().equals(startPos))
            {
                movingTo = 1;

                body.setLinearVelocity(0f, 0f);
            }
            else
            {
                diffVector = body.getPosition().cpy().sub(startPos);
            }
        }
        else
        {
            if(body.getPosition().equals(finalPos))
            {
                movingTo = 0;

                body.setLinearVelocity(0f, 0f);
            }
            else
            {
                diffVector = finalPos.cpy().sub(body.getPosition());
            }
        }

        Vector2 velocityChange = diffVector.cpy().scl(0.001f);

        body.setLinearVelocity(body.getLinearVelocity().cpy().add(velocityChange));

        if(body.getLinearVelocity().x > 10f)
        {
            body.setLinearVelocity(10f, body.getLinearVelocity().y);
        }
        else if(body.getLinearVelocity().x < -10f)
        {
            body.setLinearVelocity(-10f, body.getLinearVelocity().y);
        }

        if(body.getLinearVelocity().y > 10f)
        {
            body.setLinearVelocity(body.getLinearVelocity().x, 10f);
        }
        else if(body.getLinearVelocity().y < -10f)
        {
            body.setLinearVelocity(body.getLinearVelocity().x, -10f);
        }
    }


    @Override
    public MovingPlatformUserData getUserData()
    {
        return (MovingPlatformUserData) userData;
    }
}