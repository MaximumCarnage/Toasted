package com.brg.toasted;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy {


    private Bitmap sprite;
    private int x,y;
    private boolean down;
    private String name;

    private int lane1;
    private int lane2;
    private int lane3;



    public Enemy(Context context, int screenW, int screenH,String spritename){
        lane1= screenW/4 + 100;
        lane2 = screenW/4 + 200;
        lane3 = screenW/4 + 300;
        name = spritename;




        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.jalapenosprite);
        sprite = Bitmap.createScaledBitmap(sprite,200,200,false);

        x =  screenW-sprite.getWidth();
        y = lane1;

        down = true;


    }

    public void update(float fps){
        //x += 1;

    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Bitmap getSprite(){
        return sprite;
    }

}
