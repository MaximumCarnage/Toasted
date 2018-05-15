package com.brg.toasted;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Enemy {


    private Bitmap sprite;
    private int x,y;
    private boolean down;
    private String name;

    private int lane1;
    private int lane2;
    private int lane3;

    private double xV = -0.02;

    private int playWidth;



    public Enemy(Context context, int screenW, int screenH,String spritename){
        lane1= screenW/4 + 120;
        lane2 = screenW/4 + 240;
        lane3 = screenW/4 + 340;
        name = spritename;

        playWidth = screenW;
        int resID = context.getResources().getIdentifier(spritename,"drawable", context.getPackageName());


        sprite = BitmapFactory.decodeResource(context.getResources(), resID);
        sprite = Bitmap.createScaledBitmap(sprite,150,150,false);

        x =  screenW-sprite.getWidth();
        y = lane1;

        down = true;


    }

    public void update(){
        x+=xV;
        if(x == 0){
            x =  playWidth-sprite.getWidth();
        }
       // Log.i("enemy x", ""+ x);

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
