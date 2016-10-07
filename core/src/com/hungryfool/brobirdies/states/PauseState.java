package com.hungryfool.brobirdies.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hungryfool.brobirdies.BroBirdies;

/**
 * Created by Aman Pandey on 7/2/2016.
 */
public class PauseState extends State {

    private Texture pauseScreen , play;

    protected PauseState(GameStateManager gsm) {
        super(gsm);
        pauseScreen = new Texture("pauseScreen.png");
        play = new Texture("btn.png");
    }

    @Override
    protected void handleInput() {
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();

        boolean flag2 = ((y >= (BroBirdies.HEIGHT/2 - play.getHeight())) && (y <= (BroBirdies.HEIGHT/2) ));
        boolean flag1 = ((x <= (BroBirdies.WIDTH/2 + play.getWidth()/2)) && (x >= (BroBirdies.WIDTH/2 - play.getWidth()/2) ));

        if(flag2 && flag1 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

            gsm.pop();
            dispose();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            gsm.pop();
            dispose();
        }
        cam.setToOrtho(false, BroBirdies.WIDTH, BroBirdies.HEIGHT);
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(pauseScreen, cam.position.x - pauseScreen.getWidth()/2,0,pauseScreen.getWidth(),pauseScreen.getHeight());
        sb.draw(play, cam.position.x - play.getWidth()/2, BroBirdies.HEIGHT/2 + 20, play.getWidth(), play.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        //pause.dispose();
        play.dispose();
        pauseScreen.dispose();
    }
}
