/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.Model;

import com.badlogic.gdx.utils.Array;
import com.mrlamont.screens.WorldRenderer;


/**
 *
 * @author isles3536
 */
public class World {
    
    private Wheely wheely;
    private Array<Block> blocks;
    private WorldRenderer renderer;
    public World(){
       wheely = new Wheely(16,2850, 16, 32);
       blocks = new Array<Block>();       
       demoLevel();
       
        
      
    }
    
    private void demoLevel(){
//        blocks.add(new Block (2044,2698,520,150));
        blocks.add(new Block(2910,1173,500,42));
    }
    
    public void update(float delta){
        blocks.add(new Block(448,2698,352,150));
    }
    

    
    public Wheely getPlayer(){
       return wheely;
    }
    public Array<Block> getBlocks(){
        return blocks;
    }
}
