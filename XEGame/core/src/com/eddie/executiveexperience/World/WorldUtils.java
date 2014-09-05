package com.eddie.executiveexperience.World;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.eddie.executiveexperience.Constants;
import com.eddie.executiveexperience.Entity.EnemyType;
import com.eddie.executiveexperience.Entity.UserData.EnemyUserData;
import com.eddie.executiveexperience.Entity.UserData.GroundUserData;
import com.eddie.executiveexperience.Entity.UserData.PlayerUserData;

public class WorldUtils
{
    private static World world;

    public static void createWorld()
    {
        world = new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround()
    {
        if(world == null)
        {
            createWorld();
        }

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createPlayer()
    {
        if(world == null)
        {
            createWorld();
        }

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.PLAYER_WIDTH / 2, Constants.PLAYER_HEIGHT);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.PLAYER_DENSITY);
        body.resetMassData();
        body.setGravityScale(Constants.PLAYER_GRAVITY_SCALE);
        body.setUserData(new PlayerUserData(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createEnemy()
    {
        EnemyType enemyType = EnemyType.SLIME_SMALL;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData entityData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight());
        body.setUserData(entityData);
        shape.dispose();
        return (body);
    }

    public static World getWorld()
    {
        return world;
    }
}