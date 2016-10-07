package com.hungryfool.brobirdies.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.hungryfool.brobirdies.BroBirdies;
import com.hungryfool.brobirdies.sprites.Bird;
import com.hungryfool.brobirdies.sprites.Tube;

/**
 * Created by Aman Pandey on 6/30/2016.
 */
public class PlayState extends State implements InputProcessor{

    private Bird bird1;
    private Bird bird2;

    private Array<Tube> tubes;
    private static final int TUBE_COUNT = 5;
    private static final int TUBE_SPACING = 400;

    private Texture bg;
    private Vector3 bgPosition1, bgPosition2;
    private Texture ground;

    private Animation birdAni1;
    private Animation birdAni2;

    private float timePassed = 0;

    private static final java.lang.String TAG = "my message";

    protected PlayState(GameStateManager gsm) {

        super(gsm);

        bird1 = new Bird(new TextureAtlas(Gdx.files.internal("BirdAtlas1.atlas")), BroBirdies.WIDTH/3 - 150,BroBirdies.HEIGHT/2);
        bird2 = new Bird(new TextureAtlas(Gdx.files.internal("BirdAtlas1.atlas")), 2*(BroBirdies.WIDTH/3),BroBirdies.HEIGHT/2);

        birdAni1 = new Animation(1/15f, bird1.getBird().getRegions());
        birdAni2 = new Animation(1/15f, bird2.getBird().getRegions());

        bg = new Texture("myPerfectBackground2.png");
        bgPosition1 = new Vector3((cam.position.x - cam.viewportWidth/2),0,0);
        bgPosition2 = new Vector3((cam.position.x - cam.viewportWidth/2) + bg.getWidth(),0,0);

        ground = new Texture("ground3.png");

        tubes = new Array<Tube>();
        tubes.add(new Tube(BroBirdies.WIDTH - Tube.TUBE_WIDTH));
        for (int i = 1; i < TUBE_COUNT; ++i)
        {
            tubes.add(new Tube(tubes.peek().getPosTopTube().x + TUBE_SPACING + Tube.TUBE_WIDTH));
        }

        //choosing left hand corner as origin by taking first parameter false.
        cam.setToOrtho(false, BroBirdies.WIDTH, BroBirdies.HEIGHT);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void handleInput() {
        /*---------------------------------------------------------------------
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

            float x = Gdx.input.getX();
            float y = BroBirdies.HEIGHT - Gdx.input.getY();

            //bird1 movement.
            boolean hor1 = ((x <= (bird1.getPosition().x + bird1.WIDTH/2) )&& (x >= (bird1.getPosition().x - bird1.WIDTH/2 )));
            boolean ver1 = ((y <= (bird1.getPosition().y + bird1.HEIGHT/2) )&& (y >= (bird1.getPosition().y - bird1.HEIGHT/2 )));

            if(hor1 && ver1){
                bird1.setPosition(new Vector3(bird1.getPosition().x, y, 0));
            }

            //bird2 movement.
            boolean hor2 = ((x <= (bird2.getPosition().x + bird2.WIDTH/2) )&& (x >= (bird2.getPosition().x - bird2.WIDTH/2 )));
            boolean ver2 = ((y <= (bird2.getPosition().y + bird2.HEIGHT/2) )&& (y >= (bird2.getPosition().y - bird2.HEIGHT/2 )));

            if(hor2 && ver2){
                bird2.setPosition(new Vector3(bird2.getPosition().x, y, 0));
            }

            bird1.boundsBird.setPosition(bird1.getPosition().x, bird1.getPosition().y);
            bird2.boundsBird.setPosition(bird2.getPosition().x, bird2.getPosition().y);
        }
        ------------------------------------------------------------------------*/

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            Vector3 pos2 = bird2.getPosition();
            if(pos2.y + 10 + bird2.HEIGHT <= BroBirdies.HEIGHT)
                pos2.add(0,10,0);
            bird2.setPosition(pos2);
            bird2.boundsBird.setPosition(bird2.getPosition().x, bird2.getPosition().y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            Vector3 pos2 = bird2.getPosition();
            if(pos2.y - 10 >= ground.getHeight())
                pos2.add(0,-10,0);
            bird2.setPosition(pos2);
            bird2.boundsBird.setPosition(bird2.getPosition().x, bird2.getPosition().y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            Vector3 pos2 = bird1.getPosition();
            if(pos2.y + 10 + bird1.HEIGHT <= BroBirdies.HEIGHT)
                pos2.add(0,10,0);
            bird1.setPosition(pos2);
            bird1.boundsBird.setPosition(bird1.getPosition().x, bird1.getPosition().y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            Vector3 pos2 = bird1.getPosition();
            if(pos2.y - 10 >= ground.getHeight())
                pos2.add(0,-10,0);
            bird1.setPosition(pos2);
            bird1.boundsBird.setPosition(bird1.getPosition().x, bird1.getPosition().y);
        }

        //condition for pause
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            gsm.push(new PauseState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        //utilise this delta time to animate your sprites.
        //also play state changes due to occurence of pipes.
        handleInput();
        updateScreen(dt);
        updateTube(dt);
        bird1.update(dt);
        bird2.update(dt);

        cam.position.x += Bird.HORIZONTAL_VELOCITY * dt;
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(bg,bgPosition1.x,bgPosition1.y);
        sb.draw(bg,bgPosition2.x,bgPosition2.y);

        timePassed += Gdx.graphics.getDeltaTime();
        sb.draw(birdAni1.getKeyFrame(timePassed, true),bird1.getPosition().x,bird1.getPosition().y);
        sb.draw(birdAni2.getKeyFrame(timePassed, true),bird2.getPosition().x,bird2.getPosition().y);

        for (Tube tube : tubes)
        {
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }

        sb.draw(ground,bgPosition1.x,bgPosition1.y);
        sb.draw(ground,bgPosition2.x,bgPosition2.y);
        sb.end();
    }

    public void updateScreen(float dt){
        if((cam.position.x - cam.viewportWidth/2) > (bgPosition1.x + bg.getWidth())){
            bgPosition1.x = bgPosition2.x + bg.getWidth();
        }
        if((cam.position.x - cam.viewportWidth/2) > (bgPosition2.x + bg.getWidth())){
            bgPosition2.x = bgPosition1.x + bg.getWidth();
        }
    }

    public void updateTube(float dt){
        for(Tube tube : tubes)
        {
            if(tube.getPosTopTube().x + Tube.TUBE_WIDTH + 100 < ((cam.position.x - cam.viewportWidth/2)))
            {
                tube.reposition(tube.getPosTopTube().x  + TUBE_COUNT *(TUBE_SPACING + Tube.TUBE_WIDTH));
            }

            if(tube.collide(bird1.boundsBird) || tube.collide(bird2.boundsBird))
            {
                gsm.set(new MenuState(gsm));
            }
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird1.dispose();
        bird2.dispose();

        for (Tube tube: tubes)
        {
            tube.dispose();
        }
    }
/*------------------------INPUT HANDLING (EVENT-DRIVEN)--------------*/
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

        Gdx.app.log(TAG, "touch Down at Play screen");

        float x = screenX;
        float y = cam.viewportHeight - screenY;

        //bird1 movement.
        boolean hor1 = ((x <= (bird1.getPosition().x + bird1.WIDTH/2) )&& (x >= (bird1.getPosition().x - bird1.WIDTH/2 )));
        boolean ver1 = true; //((y <= (bird1.getPosition().y + bird1.HEIGHT/2) )&& (y >= (bird1.getPosition().y - bird1.HEIGHT/2 )));

        if(hor1 && ver1){
          //  if(y + bird1.HEIGHT/2 <= BroBirdies.HEIGHT && (y - bird1.HEIGHT/2 >= ground.getHeight()))
            bird1.setPosition(new Vector3(bird1.getPosition().x, y, 0));
        }

        //bird2 movement.
        boolean hor2 = ((x <= (bird2.getPosition().x + bird2.WIDTH/2) )&& (x >= (bird2.getPosition().x - bird2.WIDTH/2 )));
        boolean ver2 = true; //((y <= (bird2.getPosition().y + bird2.HEIGHT/2) )&& (y >= (bird2.getPosition().y - bird2.HEIGHT/2 )));

        if(hor2 && ver2){
           // if(y + bird2.HEIGHT/2 <= BroBirdies.HEIGHT && (y - bird2.HEIGHT/2 >= ground.getHeight()))
            bird2.setPosition(new Vector3(bird2.getPosition().x, y , 0));
        }

        bird1.boundsBird.setPosition(bird1.getPosition().x, bird1.getPosition().y);
        bird2.boundsBird.setPosition(bird2.getPosition().x, bird2.getPosition().y);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        /*-----------------------------------------------------------------------
        float x = screenX;
        float y = BroBirdies.HEIGHT - screenY;

        //bird1 movement.
        boolean hor1 = ((x <= (bird1.getPosition().x + bird1.WIDTH/2) )&& (x >= (bird1.getPosition().x - bird1.WIDTH/2 )));
        boolean ver1 = ((y <= (bird1.getPosition().y + bird1.HEIGHT/2) )&& (y >= (bird1.getPosition().y - bird1.HEIGHT/2 )));

        if(hor1 && ver1){
            bird1.setPosition(new Vector3(bird1.getPosition().x, y - bird1.HEIGHT/2, 0));
        }

        //bird2 movement.
        boolean hor2 = ((x <= (bird2.getPosition().x + bird2.WIDTH/2) )&& (x >= (bird2.getPosition().x - bird2.WIDTH/2 )));
        boolean ver2 = ((y <= (bird2.getPosition().y + bird2.HEIGHT/2) )&& (y >= (bird2.getPosition().y - bird2.HEIGHT/2 )));

        if(hor2 && ver2){
            bird2.setPosition(new Vector3(bird2.getPosition().x, y - bird2.HEIGHT/2, 0));
        }

        bird1.boundsBird.setPosition(bird1.getPosition().x, bird1.getPosition().y);
        bird2.boundsBird.setPosition(bird2.getPosition().x, bird2.getPosition().y);
        ---------------------------------------------------------------------------*/
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
/*----------------------------------------------------------------------*/
}
