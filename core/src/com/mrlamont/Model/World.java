/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.Model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author isles3536
 */
public class World {
    private SpriteBatch batch;
    private Wheely wheely;
    private Array<Block> blocks;
    
    
    
    public World(){
       
      wheely = new Wheely(16,400, 16, 32);
       blocks = new Array<Block>();
       demoLevel();
      
    }
    
    private void demoLevel(){
       for (int i = 0; i < 50; i ++){
            //blocks along the floor
            Block b = new Block(i*16, 0, 16,16);
            blocks.add(b);
        }
    }
    
    public void update(float delta){
        
    }
    
    public Wheely getPlayer(){
       return wheely;
    }
    public Array<Block> getBlocks(){
        return blocks;
    }
}
