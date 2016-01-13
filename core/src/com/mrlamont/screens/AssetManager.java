/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.screens;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * @author isles3536
 */
public class AssetManager {
    private static TextureAtlas atlas;
    private static TextureAtlas atlas1;
    public static TextureRegion wheelyStand;
    public static TextureRegion wheelyStandL;
    public static TextureRegion wheelyFlipping;
    public  static TextureRegion block;
        
        
        public static void load(){
        atlas = new TextureAtlas("Wheely.pack");
        
        
        wheelyStand = atlas.findRegion("Green");
        wheelyStandL = new TextureRegion(wheelyStand);
        wheelyStandL.flip(true, false);
    }  
        
}
    

