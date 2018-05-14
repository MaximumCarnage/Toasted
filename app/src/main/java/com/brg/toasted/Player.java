package com.brg.toasted;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap sprite;
    private int x,y;
    private boolean down;

    private int lane1;
    private int lane2;
    private int lane3;




    public Player(Context context, int screenW, int screenH){
        lane1= screenW/4 + 100;
        lane2 = screenW/4 + 200;
        lane3 = screenW/4 + 300;



        x = screenH/4;
        y = lane1;

        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.toastplayer);
        sprite = Bitmap.createScaledBitmap(sprite,200,200,false);

        down = true;


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
    public void setLane(int lanenum){
        switch(lanenum){
            case 1: y=lane1;
            setDown();
            break;
            case 2: y=lane2;
            break;
            case 3: y=lane3;
            setUp();
        }

    }
    public void setDown(){
        down = true;
    }
    public boolean getDown(){
        return down;
    }

    public void setUp(){
        down = false;
    }


}
