package com.hungryfool.brobirdies.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Aman Pandey on 6/30/2016.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        if(!states.empty())
            states.pop().dispose();
    }

    public void set(State state){
        if(!states.empty())
            states.pop();
        states.push(state);
    }

    //updating the topmost state
    // Also note that the update function used below is actually the update function in State class
    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
