/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.mrlamont.Model.TitleScreen;
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
    private TitleScreen screen;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Array<Rectangle> collisionblocks;
    private Array<Rectangle> interactBlocks;
    private boolean isbuttonpressed;
    private static boolean switchflipped = false;
    
    
    
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
        
        
        
        //Left click
    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
       Vector3 mouseClick = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
       camera.unproject(mouseClick);

//        System.out.println("x: "+ mouseClick.x + "    y: " + mouseClick.y);
        //Left click makes Wheely move
        if(mouseClick.y <= player.getY() + 89 && mouseClick.y >= player.getY()  && mouseClick.x <= player.getX() + 149 && mouseClick.x >= player.getX() && player.isMoving() == false)
        {
            player.setVelocityX(2f);
            player.setState(Wheely.State.MOVING);
        } 
        if(mouseClick.y <= player.getY() + 89 && mouseClick.y >= player.getY()  && mouseClick.x <= player.getX() + 149 && mouseClick.x >= player.getX() && player.isMoving() == false && player.isFacingLeft())
        {
            player.setVelocityX(-2f);
            player.setState(Wheely.State.MOVING);
        } 
        //Make bridge appear
        if(mouseClick.y <= 2811 && mouseClick.y >= 2714  && mouseClick.x <= 952 && mouseClick.x >= 850 ){
            isbuttonpressed = true;
        }
         
        //Flip Wheely
        if(mouseClick.y <= 1211.2283 && mouseClick.y >= 1018.53955  && mouseClick.x <= 3099.032 && mouseClick.x >= 2911.5317 && player.getX()>= 2911.5317 && player.getX() <= 3099.032 && player.getY() >=1018.53955 && player.getY() <=2211.2283){
            if (player.getVelocityY() == 0){
           player.setState(Wheely.State.FLIPPING);
           player.setVelocityY(15f);
           }
        }
        
        //flip the switch
        if(mouseClick.y <= 2693.8335 && mouseClick.y >= 2629.2502  && mouseClick.x <= 1901.7502 && mouseClick.x >= 1878.3127 ){
            switchflipped = true;
        } 
              
    }
        
    
    
    //Right click
    if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
        Vector3 mouseClick = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        camera.unproject(mouseClick);
        //Makes Wheely stop
         if (mouseClick.y <= player.getY() + 89 && mouseClick.y >= player.getY()  && mouseClick.x <= player.getX() + 149 && mouseClick.x >= player.getX() && player.isMoving() == true){
            player.setVelocityX(0);
            player.setState(Wheely.State.STOPPED);
        }
    }
    
    
            
       
            
        //DRAW
    
    //Draw bridge if the button is pressed
    if (isbuttonpressed == true){
            batch.draw(AssetManager.block,448 , 2698);
            batch.draw(AssetManager.block,600 , 2698);
            world.update(delta);
    }
        
    if (switchflipped == true){
        batch.draw(AssetManager.theswitchFlip, 1793, 2470);
    }
     
        //Wheely green
        if (TitleScreen.isGreen() == true){
        if (player.getState() == Wheely.State.STOPPED|| player.getState() == Wheely.State.MOVING){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyGreenL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyGreen, player.getX(), player.getY());
            }
        }
        }
        
        
        //Wheely red
        if (TitleScreen.isRed() == true){
         if (player.getState() == Wheely.State.STOPPED|| player.getState() == Wheely.State.MOVING){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyRedL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyRed, player.getX(), player.getY());
            }
        }
        }
        
        //Wheely Orange
        if (TitleScreen.isOrange() == true){
         if (player.getState() == Wheely.State.STOPPED|| player.getState() == Wheely.State.MOVING){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyOrangeL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyOrange, player.getX(), player.getY());
            }
        }
        }
        
        //Wheely Yellow
        if (TitleScreen.isYellow() == true){
         if (player.getState() == Wheely.State.STOPPED|| player.getState() == Wheely.State.MOVING){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyYellowL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyYellow, player.getX(), player.getY());
            }
        }
        }
        
        //Wheely Blue
        if (TitleScreen.isBlue() == true){
         if (player.getState() == Wheely.State.STOPPED|| player.getState() == Wheely.State.MOVING){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyBlueL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyBlue, player.getX(), player.getY());
            }
        }
        }
        
        //Wheely Purple
        if (TitleScreen.isPurple() == true){
         if (player.getState() == Wheely.State.STOPPED|| player.getState() == Wheely.State.MOVING){
            if (player.isFacingLeft()){
                 batch.draw(AssetManager.wheelyPurpleL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.wheelyPurple, player.getX(), player.getY());
            }
        }
        }
        
        //finished listing things to draw
        batch.end();
    }
    
    
    
     
    /**
     * 
     * @param width of screen
     * @param height of screen
     */
    public void resize(int width, int height){
          viewport.update(width, height);
    }
 
    /**
     * Check if the switch is flipped
     * @return the state of the switch
     */
    public static boolean switchflipped(){
        return switchflipped;
    }
   
}
