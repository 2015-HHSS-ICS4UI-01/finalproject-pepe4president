/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.libgdxdemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mrlamont.Model.Block;
import com.mrlamont.Model.Wheely;
import com.mrlamont.Model.World;
import com.mrlamont.screens.WorldRenderer;

/**
 *
 * @author isles3536
 */
public class MainGame implements Screen {
    private OrthographicCamera camera;
    private World theWorld;
    private Wheely player;
    private WorldRenderer renderer;

    public MainGame() {
        theWorld = new World();
        player = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);

 
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        //Controls
       if (Gdx.input.isKeyPressed(Keys.D)){
            player.setVelocityX(2f);
        } else if (Gdx.input.isKeyPressed(Keys.A)){
            player.setVelocityX(-2f);
        }
       if (Gdx.input.isKeyJustPressed(Keys.S)){
           player.setVelocityX(0);
           player.setState(Wheely.State.STOPPED);
       }

    




        //collisions
        //go through each block
        player.update(deltaTime);
        for (Block b : theWorld.getBlocks()) {
            //if player is hitting a block
            if (player.isColiding(b)) {
                //get overlapping amount
                float overX = player.getOverlapX(b);
                float overY = player.getOverlapY(b);

                //just fixing y if not moving
                if (player.getVelocityX() == 0) {
                    //player is above the block
                    if (player.getY() > b.getY()) {
                        player.addToPosition(0, overY);
                        player.setState(Wheely.State.STOPPED);
                    } else {
                        player.addToPosition(0, -overY);
                    }
                    player.setVelocityY(0);
                } else {
                    //fix the smallest overlap
                    if (overX < overY) {
                        if (player.getX() < b.getX()) {
                            player.addToPosition(-overX, 0);
                        } else {
                            player.addToPosition(overX, 0);
                        }
                    }else {
                        //above block
                         if (player.getY() > b.getY()){
                             player.addToPosition(0, overY);
                             if (player.getState() == Wheely.State.MOVING){
                                 player.setState(Wheely.State.STOPPED);
                             }                           
                         } else {
                             player.addToPosition(0, -overY);
                         }
                         player.setVelocityY(0);
                    }

                }

            }
        }
        //draws the screen
        renderer.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
