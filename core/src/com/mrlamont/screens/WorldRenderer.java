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
import com.badlogic.gdx.math.Vector3;
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
    public final int V_WIDTH = 1500;
    public final int V_HEIGHT = 1000;
    private World world;
    private Wheely player;
    
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> collisionblocks;
    
    
    
    
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
        
        
        
        for(int x = 0; x<mapWidth;x++){
            for(int y = 0; y<mapHeight;y++){
                if(solidBlocks.getCell(x,y) != null){
                    Rectangle a = new Rectangle(x*32,y*32,tileWidth,tileHeight);
                    collisionblocks.add(a);
                    
                }
            }
        }
        
       
        
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
        
         for (Rectangle r: collisionblocks) {

            if (player.getBounds().overlaps(r)) {
                float overX = Math.min(r.getX() + r.getWidth(), player.getX() + player.getWidth()) - Math.max(r.getX(), player.getX());
                float overY = Math.min(r.getY() + r.getHeight(), player.getY() + player.getHeight()) - Math.max(r.getY(), player.getY());
                if (player.getVelocityX() == 0) {
                    if (player.getY() < r.getY()) {
                        player.addToPosition(0, -overY);
                    } else {
                        player.addToPosition(0, overY);
                        
                    }
                    player.setVelocityY(0);
                }

                if (overX < overY) {
                    if (player.getX() < r.getX() ) {
                        player.addToPosition(-overX, 0);
                    } else {
                        player.addToPosition(overX, 0);
                    }
                } else {
                    if (player.getY() < r.getY() ) {
                        player.addToPosition(0, -overY);
                    } else {
                        player.addToPosition(0, overY);
                        
                    }
                    player.setVelocityY(0);
                }
            }
        }
            
        
      
        //update the camera
        camera.position.x = Math.max(player.getX(), V_WIDTH/2);
        camera.position.y = Math.max(player.getY(), V_HEIGHT/2);
        camera.update();
        
        
        //links the renderer to the camera
        batch.setProjectionMatrix(camera.combined);
        // tells the renderer this is the list
        
        renderer.setView(camera);
        renderer.render();
        
        batch.begin();
        
        //Left click makes Wheely move
    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
       Vector3 mouseClick = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
       camera.unproject(mouseClick);

        System.out.println("x: "+ mouseClick.x + "    y: " + mouseClick.y);
        if(mouseClick.y <= player.getY() + 89 && mouseClick.y >= player.getY()  && mouseClick.x <= player.getX() + 149 && mouseClick.x >= player.getX() && player.isMoving() == false)
        {
            player.setVelocityX(2f);
            player.setState(Wheely.State.MOVING);
        } 
    }
    
    //Right click makes Wheely stop
    if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
        Vector3 mouseClick = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        camera.unproject(mouseClick);
        
         if (mouseClick.y <= player.getY() + 89 && mouseClick.y >= player.getY()  && mouseClick.x <= player.getX() + 149 && mouseClick.x >= player.getX() && player.isMoving() == true){
            player.setVelocityX(0);
            player.setState(Wheely.State.STOPPED);
        }
    }
    
            
       
            
        //DRAW
        
        
        if (player.getState() == Wheely.State.STOPPED || player.getState() == Wheely.State.MOVING){
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
