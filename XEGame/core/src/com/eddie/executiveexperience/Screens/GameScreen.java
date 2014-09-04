package com.eddie.executiveexperience.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.eddie.executiveexperience.GameStage;
import com.eddie.executiveexperience.XEGame;

public class GameScreen extends ScreenAdapter
{
    XEGame game;

    private GameStage stage;

    public GameScreen(XEGame game)
    {
        this.game = game;

        stage = new GameStage();

        Gdx.input.setInputProcessor(stage);
    }

    public void update(float delta)
    {
        stage.act(delta);
    }

    public void draw()
    {
        GL20 gl = Gdx.gl20;

        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void render(float delta)
    {
        update(delta);
        draw();
    }

    @Override
    public void pause()
    {
    }
}
