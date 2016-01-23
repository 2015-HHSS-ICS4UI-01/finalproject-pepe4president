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
import com.mrlamont.Model.TitleScreen;
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
    private TitleScreen screen;
    private boolean startgame = false;
    private boolean endgame = false;
    public MainGame() {
        theWorld = new World();
        player = theWorld.getPlayer();
        renderer = new WorldRenderer(theWorld);
        screen = new TitleScreen();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        if (TitleScreen.isGreen() == true || TitleScreen.isRed() == true || TitleScreen.isOrange() == true || TitleScreen.isYellow() == true|| TitleScreen.isBlue() == true || TitleScreen.isPurple() == true){
            startgame = true;
        }
         if (startgame == false){
            screen.render(deltaTime);
        }
        
        
        
        
        else{
 
         if (player.getX() >= 1335.8125 && player.getX() <= 1845.3129 && player.getY() >= 1198.321 && player.getY() <=1459.8049){
             player.setVelocityX(-20);
         }
         
         if (player.getX() >= 89.06251 && player.getX() <= 248.43756 && player.getY() >= 708.1668 && player.getY() <=783.1668){
            endgame = true;
            screen.endScreen(deltaTime);
        } else {

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
         
    }
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
