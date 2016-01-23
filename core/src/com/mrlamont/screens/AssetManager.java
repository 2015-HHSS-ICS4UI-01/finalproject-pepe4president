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
    public static TextureRegion wheelyGreen;
    public static TextureRegion wheelyGreenL;
    public static TextureRegion wheelyRed;
    public static TextureRegion wheelyRedL;
    public static TextureRegion wheelyOrange;
    public static TextureRegion wheelyOrangeL;
    public static TextureRegion wheelyYellow;
    public static TextureRegion wheelyYellowL;
    public static TextureRegion wheelyBlue;
    public static TextureRegion wheelyBlueL;
    public static TextureRegion wheelyPurple;
    public static TextureRegion wheelyPurpleL;
    public static TextureRegion wheelyFlipping;
    public static TextureRegion block;
    public static TextureRegion bridge;
    public static TextureRegion theswitch;
    public static TextureRegion theswitchFlip;
    public static TextureRegion flag;
        
        
        public static void load(){
        atlas = new TextureAtlas("Wheely.pack");
        atlas1 = new TextureAtlas("Tiles.pack");
        
         //Green Wheely
        wheelyGreen = atlas.findRegion("Green");
        wheelyGreenL = new TextureRegion(wheelyGreen);
        wheelyGreenL.flip(true, false);
        
        
        //Red Wheely
        wheelyRed = atlas.findRegion("Red");
        wheelyRedL = new TextureRegion(wheelyRed);
        wheelyRedL.flip(true, false);
        
        //Orange Wheely
        wheelyOrange = atlas.findRegion("Orange");
        wheelyOrangeL = new TextureRegion(wheelyOrange);
        wheelyOrangeL.flip(true, false);
        
        //Yellow Wheely
        wheelyYellow = atlas.findRegion("Yellow");
        wheelyYellowL = new TextureRegion(wheelyYellow);
        wheelyYellowL.flip(true, false);
        
        //Blue Wheely
        wheelyBlue = atlas.findRegion("Blue");
        wheelyBlueL = new TextureRegion(wheelyBlue);
        wheelyBlueL.flip(true, false);
        
        //Purple Wheely
        wheelyPurple = atlas.findRegion("Purple");
        wheelyPurpleL = new TextureRegion(wheelyPurple);
        wheelyPurpleL.flip(true, false);
        
        //Bridge and elevator
        block = atlas1.findRegion("object");
        
        //switch
        theswitch = atlas1.findRegion("switch");
        theswitchFlip = new TextureRegion(theswitch);
        theswitchFlip.flip(false, true);
        
        //Nate Thompson Flag
        flag = atlas1.findRegion("flag copy");
    }  
        
}
    

