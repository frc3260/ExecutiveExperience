package com.eddie.executiveexperience;

import com.badlogic.gdx.math.Vector2;

public class Constants
{
    public static final int GAME_WIDTH = 700;
    public static final int GAME_HEIGHT = 700;

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    public static final float PIXELS_PER_METER = 32f;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0.0f, -10.0f);

    public static final float GROUND_X = 0.1f;
    public static final float GROUND_Y = 0.1f;
    public static final float GROUND_WIDTH = 25f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final float PLAYER_X = 2;
    public static final float PLAYER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float PLAYER_WIDTH = 1.5f;
    public static final float PLAYER_HEIGHT = 1.75f;
    public static float PLAYER_DENSITY = 0.5f;
    public static final float PLAYER_GRAVITY_SCALE = 3f;
    public static final Vector2 PLAYER_JUMPING_LINEAR_IMPULSE = new Vector2(0f, 13f);
    public static final float PLAYER_HIT_ANGULAR_IMPULSE = 10f;

    public static final float ENEMY_X = 25f;
    public static final float ENEMY_DENSITY = PLAYER_DENSITY;
    public static final float SLIME_SMALL_Y = 1.5f;
    public static final float SLIME_LARGE_Y = 2f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-10f, 0);
}