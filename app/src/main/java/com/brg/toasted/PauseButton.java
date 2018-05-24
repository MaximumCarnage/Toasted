package com.brg.toasted;

import android.graphics.Bitmap;

public class PauseButton {

    private Bitmap m_buttonPlay;
    private Bitmap m_buttonPause;
    private int m_x,m_y;

    private boolean m_play;
    private Bitmap m_sprite;


    public PauseButton(int x,int y,Bitmap sprite){
        m_x = x;
        m_y = y;
        m_sprite = sprite;


    }


    public int getX(){return m_x; }
    public void setX(int newx){ m_x = newx; }
    public int getY(){return m_y; }
    public void setY(int newy){m_x = newy; }
    public Bitmap getSprite(){
        return m_sprite;
    }
    public void setSprite(Bitmap newSprite){ m_sprite = newSprite;}



}
