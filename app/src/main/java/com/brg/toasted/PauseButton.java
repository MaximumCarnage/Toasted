package com.brg.toasted;

import android.graphics.Bitmap;

public class PauseButton {

    private Bitmap m_buttonPlay;
    private Bitmap m_buttonPause;
    private int x,y;

    private boolean m_play;


    public PauseButton() {

    }


    public int getX(){return x; }
    public void setX(int newx){ x = newx; }
    public int getY(){return y; }
    public void setY(int newy){x = newy; }


}
