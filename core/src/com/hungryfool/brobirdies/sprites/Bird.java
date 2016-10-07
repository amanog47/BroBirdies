package com.hungryfool.brobirdies.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Aman Pandey on 6/30/2016.
 */
public class Bird {
    //HORIZONTAL_VELOCITY WILL BE INCREASED WITH TIME
    public static int HORIZONTAL_VELOCITY = 200;
    public static final int HORIZONTAL_ACC = 57;

    private TextureAtlas bird;
    private Vector3 position;
    private Vector3 velocity;

    public Rectangle boundsBird;
    public static int TOLERANCE = 5;

    public int WIDTH = 100;
    public int HEIGHT = 88;

    public Bird(TextureAtlas bird, int x, int y) {
        this.bird = bird;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);

        HORIZONTAL_VELOCITY = 200;
        boundsBird = new Rectangle(x, y+TOLERANCE, WIDTH, HEIGHT - TOLERANCE);
    }

    public void update(float dt){
        position.add(HORIZONTAL_VELOCITY * dt,0,0);

        boundsBird.setPosition(position.x,position.y);
        HORIZONTAL_VELOCITY += HORIZONTAL_ACC * dt;
        //System.out.println(dt);
    }

    public void dispose()
    {
        bird.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public TextureAtlas getBird() {
        return bird;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBounds(){
        return boundsBird;
    }

    public void setBoundsPosition(float x, float y){
        boundsBird.setPosition(x, y);
    }
}
