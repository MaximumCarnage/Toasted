package com.brg.toasted;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap sprite;
    private int x,y;
    private int vx,vy;
    private boolean jumping;

    private final int JUMPSPEED = 120;
    private final int GRAVITY = -60;

    private int minY;
    private int maxY;
    private int minX;
    private int maxX;


    public Player(Context context, int screenW, int screenH){
        x = 10;
        y = 10;
        vx = 0;
        vy = 0;


        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.toastplayer);
        sprite = Bitmap.createScaledBitmap(sprite,200,200,false);
        jumping = false;

        maxY = screenH - sprite.getHeight()-50;
        minY = 0;

        maxX = screenW - sprite.getWidth();
        minX = 0;
    }

    public void update(){
        if(jumping){
            y -= JUMPSPEED;
        }else{
            y -= GRAVITY;
        }

        if(y < minY) {
            y = minY;
        }
        if(y >maxY) {
            y = maxY;
        }
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

    public void setJumping(){
        jumping = true;
    }

    public void stopJumping(){
        jumping = false;
    }


}
