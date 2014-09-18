package com.eddie.executiveexperience.Entity.UserData;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.eddie.executiveexperience.Constants;
import com.eddie.executiveexperience.GameStage;
import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;

public class PlayerUserData extends EntityUserData
{
    private Fixture playerBodyFixture;
    private Fixture playerSensorFixture;

    private int jumpingImpulseMagnitude;

    public PlayerUserData(GameStage gameStage, float width, float height, Fixture playerBodyFixture, Fixture playerSensorFixture)
    {
        super(width, height);

        this.playerBodyFixture = playerBodyFixture;
        this.playerSensorFixture = playerSensorFixture;

        jumpingImpulseMagnitude = Constants.PLAYER_JUMPING_IMPULSE_MAGNITUDE;

        spriteAnimationData = gameStage.getAssetManager().get("assets/player/PlayerWalk.json");
        animatedSprite = new AnimatedSprite(spriteAnimationData.getAnimation("walk"));
        animatedBox2DSprite = new AnimatedBox2DSprite(animatedSprite);
//        animatedBox2DSprite.setAutoUpdate(true);
//        animatedBox2DSprite.setWidth(width);
//        animatedBox2DSprite.setHeight(height);
//        animatedBox2DSprite.setAdjustSize(false);
        animatedBox2DSprite.setOrigin(animatedBox2DSprite.getWidth() / 2, animatedBox2DSprite.getHeight() / 2);
        animatedBox2DSprite.play();

        userDataType = UserDataType.PLAYER;
    }

    public void setJumpingImpulseMagnitude(int jumpingImpulseMagnitude)
    {
        this.jumpingImpulseMagnitude = jumpingImpulseMagnitude;
    }

    public int getJumpingImpulseMagnitude()
    {
        return jumpingImpulseMagnitude;
    }

    public float getHitAngularImpulse()
    {
        return Constants.PLAYER_HIT_ANGULAR_IMPULSE;
    }

    public Fixture getBodyFixture()
    {
        return playerBodyFixture;
    }

    public Fixture getPhysicsSensor()
    {
        return playerSensorFixture;
    }
}
