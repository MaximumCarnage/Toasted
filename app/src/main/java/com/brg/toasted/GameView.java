package com.brg.toasted;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;


import android.util.Log;
import android.view.MotionEvent;


import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private boolean m_dBugging = true; // change to false before completion
    private volatile boolean m_playing;
    private Thread m_gameThread = null;

   private Player m_player;
   private List m_enemies;
   private Enemy m_testenemy;
//    private Enemy m_enemy;

    private Paint m_paint;
    private Canvas m_canvas;
    private SurfaceHolder m_holder;
    private Context m_context;
    private long m_startTime;
    private long m_deltaTime;
    private long m_fps;
    private int m_playLane=1;

    private Bitmap bg;
    private Bitmap groundTiles;

//    private LevelManager m_lm;
//    private Viewport m_vp;
//    public InputController m_ic

    public GameView(Context context,int screenW,int screenH){
        super(context);

        m_context = context;
        m_holder = getHolder();
        m_paint = new Paint();

       m_player = new Player(context,screenW,screenH);
       for(int i = 0; i < 3; i++){
           m_enemies.add(new Enemy(context,screenW,screenH,"pepper"));
       }
       m_testenemy = new Enemy(context,screenW,screenH,"pepper");
//        m_enemy = new Enemy(context,screenW,screenH);


        bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundimage);
        bg = Bitmap.createScaledBitmap(bg,screenW,screenH,false);
        groundTiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.kitchentiles);
        groundTiles = Bitmap.createScaledBitmap(groundTiles,screenW/2,screenH/3,false);
    }

    @Override
    public void run(){
        while(m_playing){
            m_startTime = System.currentTimeMillis();
            update();
            draw();

            //calc FPS
            m_deltaTime = System.currentTimeMillis() - m_startTime;
            if(m_deltaTime >= 1){
                m_fps = 1000 / m_deltaTime;
            }
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){

        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            //lift finger
//            case MotionEvent.ACTION_UP:
//                m_player.setLane();
//                break;
            //touch screen
            case MotionEvent.ACTION_DOWN:
                if(m_player.getDown()){
                    m_playLane++;
                }else{
                    m_playLane--;
                }
                m_player.setLane(m_playLane);
                break;
        }

        return true;
    }

    public void update(){
        m_testenemy.update();
        if(m_player.Collision(m_testenemy)){
            Log.i("collision", "Player Collided");
        }
    }

    private void draw(){
        if(m_holder.getSurface().isValid()) {
            m_canvas = m_holder.lockCanvas();

            m_canvas.drawColor(Color.argb(255,0,0,0));
            m_canvas.drawBitmap(bg,0,0,m_paint);
            m_canvas.drawBitmap(groundTiles,0,m_canvas.getHeight()-groundTiles.getHeight(),m_paint);
            m_canvas.drawBitmap(groundTiles,m_canvas.getWidth()/2,m_canvas.getHeight()-groundTiles.getHeight(),m_paint);
            m_canvas.drawBitmap(m_testenemy.getSprite(),m_testenemy.getX(),m_testenemy.getY(),m_paint);


            m_canvas.drawBitmap(m_player.getSprite(),m_player.getX(), m_player.getY(),m_paint);

            //TODO: add draw

            m_holder.unlockCanvasAndPost(m_canvas);
        }
    }

    public void pause(){
        m_playing = false;
        try {
            m_gameThread.join();
        } catch(InterruptedException e){
            Log.d("error in GameView.Java - Pause", e.getMessage());
        }
    }
    public void resume(){
        m_playing = true;
        m_gameThread = new Thread(this);
        m_gameThread.start();
    }


}
