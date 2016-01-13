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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
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
    public final int V_WIDTH = 2000;
    public final int V_HEIGHT = 1500;
    private World world;
    private Wheely player;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> collisionblocks;
    private int levelWidth;
    
    
    
    public WorldRenderer(World w){
        world = w;
        player = world.getPlayer();
        
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH,V_HEIGHT,camera);
        batch = new SpriteBatch();
        
         
        
       map = new TmxMapLoader().load("Level One.tmx");
       renderer = new OrthogonalTiledMapRenderer(map, batch);
       
       collisionblocks = new Array<Rectangle>();
       TiledMapTileLayer solidBlocks = (TiledMapTileLayer)map.getLayers().get("Colision");
       
       
        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);
        
        levelWidth = mapWidth;

        
        
        
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
        
        renderer.setView(camera);
        renderer.render();
        
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
