/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.Model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author isles3536
 */
public class Wheely extends Entity{
   private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 4.0f;
    
   
    
    
    //State of Wheely
    public enum State{
        STOPPED, FLIPPING, MOVING
    }
    private State state;
    private Vector2 velocity;
    private Vector2 acceleration;
    //Which way Wheely is facing
    boolean isFacingLeft;
    
    //animation state counter
    private float stateTime;
  
    
    
    
    public Wheely(float x,float y, float width, float height){
        super(x,y,width,height);
        state =  State.STOPPED;
         velocity = new Vector2(0,0);
        acceleration = new Vector2(0,0);
        isFacingLeft = false;
        stateTime = 0;
    }
    
    
    public void update(float delta){
        //gravity
        acceleration.y = -9.8f;
        velocity.mulAdd(acceleration, delta);
       //Moving right
        if (velocity.x > 0){
            isFacingLeft = false;
            state = State.MOVING;
        } else if (velocity.x < 0){
            isFacingLeft = true;
            state = State.MOVING;
        }
        
         else {
            state = State.STOPPED;
            stateTime = 0;
        }
        stateTime += delta;
    }
    
    public void flip(){
        isFacingLeft = true;
        state = State.FLIPPING;
    }
    
    public void setVelocityX(float x){
       velocity.x = x;
    }
    
    public void setVelocityY(float y){
       velocity.y = y;
    }
    
    public void setState(State s){
         if (state != s){
            stateTime = 0;
        }
        state = s;
    }
    public float getVelocityX(){
        return velocity.x;
    }
    
    public float getVelocityY(){
        return velocity.y;
    }
    
    public State getState(){
        return state;
    }
    public float getStateTime(){
       return stateTime;
    }
    public boolean isFacingLeft(){
        return isFacingLeft;
    }
    
}
