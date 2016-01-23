/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrlamont.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mrlamont.screens.AssetManager;

/**
 *
 * @author Erman
 */
public class TitleScreen {
  private SpriteBatch batch;
    private ShapeRenderer g;
public final int V_WIDTH = 800;
public final int V_HEIGHT = 600;
private Viewport viewport;
private OrthographicCamera camera;
private Wheely player;

public static boolean green = false;
public static boolean red = false;
public static boolean orange = false;
public static boolean yellow = false;
public static boolean blue = false;
public static boolean purple = false;

      public void render(float delta){     
    camera = new OrthographicCamera();
    viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
    batch = new SpriteBatch();
          // clear the screen with black
    Gdx.gl20.glClearColor(0, 0, 0, 1);
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    g = new ShapeRenderer();
    batch = new SpriteBatch();
    g.begin(ShapeRenderer.ShapeType.Filled);
    g.setColor(Color.PURPLE);
    g.rect(550, 0, 110, 600);
    g.setColor(Color.BLUE);
    g.rect(440, 0, 110, 600);
    g.setColor(Color.GREEN);
    g.rect(330, 0, 110, 600);
    g.setColor(Color.YELLOW);
    g.rect(220, 0, 110, 600);
    g.setColor(Color.ORANGE);
    g.rect(110, 0, 110, 600);
    g.setColor(Color.RED);
    g.rect(0, 0, 110, 600);
    g.end();
    
        //draw different coloured Wheely's  on screen
        batch.begin();
        batch.draw(AssetManager.wheelyYellow, 190, -1);
        batch.draw(AssetManager.wheelyOrange, 90, -1);
        batch.draw(AssetManager.wheelyRed, -14, -1);
        batch.draw(AssetManager.wheelyGreenL, 320, -1);
        batch.draw(AssetManager.wheelyBlueL, 420, -1);
        batch.draw(AssetManager.wheelyPurpleL, 520, -1);
        batch.end();
    
        
    
    
           if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
       Vector3 mouseClick = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
       camera.unproject(mouseClick);
        System.out.println("x: "+ mouseClick.x + "    y: " + mouseClick.y);
        //Red
        if(mouseClick.y >= -1 && mouseClick.y <=0.991 && mouseClick.x >= -1 && mouseClick.x <=  -0.6625)
        {
            red = true;
        }
        //Green
        if (mouseClick.y >= -1 && mouseClick.y <= 1 && mouseClick.x >= 0.034374952  && mouseClick.x <= 0.36874998){
            green = true;  
        }
        //Orange
        if (mouseClick.y >=  -1 && mouseClick.y <=1 && mouseClick.x >= -0.653125  && mouseClick.x <= -0.32187498){
            orange = true;
        }
        //Yellow
        if (mouseClick.y >=  -1 && mouseClick.y <=1 && mouseClick.x >= -0.309375  && mouseClick.x <= 0.024999976){
            yellow = true;
        }
        //Blue
        if (mouseClick.y >=  -1 && mouseClick.y <=1 && mouseClick.x >= 0.37812495  && mouseClick.x <= 0.7125){
            blue = true;
        }
        
         //Purple
        if (mouseClick.y >=  -1 && mouseClick.y <=1 && mouseClick.x >= 0.71875  && mouseClick.x <= 0.99375){
            purple = true;
        }
             
        
       
   }

      
}
      
      public void endScreen(float delta){
          Gdx.gl20.glClearColor(0, 0, 0, 1);
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
          batch.begin();
          batch.draw(AssetManager.flag, 190, -1);
          batch.end();
         
          
      }
      
      public static boolean isGreen(){
          return green;
      }
      
      public static boolean isRed(){
          return red;
      }
      
      public static boolean isOrange(){
          return orange;
      }
      
      public static boolean isYellow(){
          return yellow;
      }
      
      public static boolean isBlue(){
          return blue;
      }
      
      public static boolean isPurple(){
          return purple;
      }
      
}
