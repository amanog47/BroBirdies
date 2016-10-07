package com.hungryfool.brobirdies.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

/**
 * Created by Aman Pandey on 7/2/2016.
 */
public class Tube {
    private static final int FLUCTUATION = 400;
    public static final int TUBE_WIDTH = 100;
    private static final int TUBE_GAP = 175;
    private static final int LOWEST_OPENING = 120;

    private Texture topTube, botTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;

    private Rectangle boundsTop, boundsBot;

    public Tube(float x){
        topTube = new Texture("pipeTop.png");
        botTube = new Texture("pipeBottom.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x,posTopTube.y - TUBE_GAP - botTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, botTube.getWidth(), botTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x,posTopTube.y - TUBE_GAP - botTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collide(Rectangle player){
        return (player.overlaps(boundsBot) || player.overlaps(boundsTop));
    }

    public void dispose() {
        topTube.dispose();
        botTube.dispose();
    }
}
