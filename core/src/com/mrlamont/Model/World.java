/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.Model;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author isles3536
 */
public class World {
    private Wheely wheely;
    private Array<Block> blocks;
    
    public World(){
       blocks = new Array<Block>();
       demoLevel();
       
       wheely = new Wheely(16,16, 16, 32);
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
