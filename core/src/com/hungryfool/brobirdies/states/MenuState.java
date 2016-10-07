package com.hungryfool.brobirdies.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hungryfool.brobirdies.BroBirdies;
import com.sun.org.apache.xpath.internal.operations.String;



/**
 * Created by Aman Pandey on 6/30/2016.
 */
public class MenuState extends State implements InputProcessor{

    private Texture background;
    private Texture btn;
    private Texture logo;

    private static final java.lang.String TAG = "my message";

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("myPerfectBackground3.png");
        btn = new Texture("btn.png");
        logo = new Texture("logo.png");

        cam.setToOrtho(false, BroBirdies.WIDTH, BroBirdies.HEIGHT);
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void handleInput() {
        //remember these functions give the coordinates in UI form not in libGDX.

        /*-------------------------------------------------------------------------------
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();

        boolean flag2 = ((y >= (BroBirdies.HEIGHT/2 - btn.getHeight())) && (y <= (BroBirdies.HEIGHT/2) ));
        boolean flag1 = ((x <= (BroBirdies.WIDTH/2 + btn.getWidth()/2)) && (x >= (BroBirdies.WIDTH/2 - btn.getWidth()/2) ));

        if(flag2 && flag1 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                gsm.set(new PlayState(gsm));
                dispose();
        }
        --------------------------------------------------------------------------------*/

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            gsm.set(new PlayState(gsm));
            dispose();
        }

    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background,cam.position.x - cam.viewportWidth/2 ,0,background.getWidth(),background.getHeight());
        sb.draw(logo,cam.position.x - logo.getWidth()/2, cam.position.y + cam.viewportHeight/2 - logo.getHeight() - 20,logo.getWidth(),logo.getHeight());
        sb.draw(btn, BroBirdies.WIDTH/2 - btn.getWidth()/2, BroBirdies.HEIGHT/2 - 20, btn.getWidth(), btn.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        btn.dispose();
    }

    /*--------------------------------INPUT HANDLING <EVENT-DRIVEN>------------------------*/

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        System.out.println("touch Up at Menu screen");
        Gdx.app.log(TAG, "touch Up at Menu screen");
        float x = screenX;
        float y = screenY;

        boolean flag2 = true; //((y >= (BroBirdies.HEIGHT/2 - 20) && (y <= (BroBirdies.HEIGHT/2 - 20 + btn.getHeight()) )));
        boolean flag1 = true; //((x <= (BroBirdies.WIDTH/2 + btn.getWidth()/2)) && (x >= (BroBirdies.WIDTH/2 - btn.getWidth()/2) ));

        if(flag2 && flag1){
            Gdx.app.log(TAG, "within limits at Menu screen");
            gsm.set(new PlayState(gsm));
            dispose();
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    /*-------------------------------------------------------------------------------------*/
}
