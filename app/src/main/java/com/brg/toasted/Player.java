package com.brg.toasted;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {

    private Bitmap sprite;
    private int x,y;
    private boolean down;

    private int lane1;
    private int lane2;
    private int lane3;




    public Player(Context context, int screenW, int screenH){
        lane1 = screenW/4 + 120;
        lane2 = screenW/4 + 240;
        lane3 = screenW/4 + 340;



        x = screenH/4;
        y = lane1;

        sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.toastplayer);
        sprite = Bitmap.createScaledBitmap(sprite,150,150,false);

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

    public boolean Collision(Enemy enemy){
        boolean collision = false;

        Rect r1 = new Rect(x, y, x
                + sprite.getWidth(),  y + sprite.getWidth());

        Rect r2 = new Rect(enemy.getX(), enemy.getY(), enemy.getX()
                + enemy.getSprite().getWidth(),  enemy.getY() + enemy.getSprite().getWidth());



        if(y== enemy.getY()&& r1.intersect(r2)){
            collision =  true;
        }
        return collision;

    }
}
