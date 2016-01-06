/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mrlamont.Model.Block;
import com.mrlamont.Model.Wheely;
import com.mrlamont.Model.World;

/**
 *
 * @author isles3536
 */
public class WorldRenderer {
    //The games virtual width and height
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    private World world;
    private Wheely player;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    
    
    
    
    public WorldRenderer(World w){
        world = w;
        player = world.getPlayer();
        
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH,V_HEIGHT,camera);
        batch = new SpriteBatch();
        
         
        // move the x position of the camera
        camera.position.x = V_WIDTH/2f;
        //move the y position  of the camera
        camera.position.y = V_HEIGHT/2f;
        //update the camera
        camera.update();
        
        //loads in the images
        AssetManager.load();
    }
    
    public void render(float delta){
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
      
        //update the camera
        camera.position.x = Math.max(player.getX(), V_WIDTH/2);
        camera.update();
        
        
        //links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);
        // tells the renderer this is the list
        batch.begin();
        
        
        //DRAW
       
        
        if (player.getState() == Wheely.State.STOPPED){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyStandL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyStand, player.getX(), player.getY());
            }
        }
        
        
        //finished listing things to draw
        batch.end();
    }
    
    
    
     
    
    public void resize(int width, int height){
          viewport.update(width, height);
    }
    
    
    
}
