package com.brg.toasted;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.view.SurfaceView;
import android.view.SurfaceHolder;



import android.util.Log;
import android.view.MotionEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    public static int SCORE;

    private boolean m_dBugging = true; // change to false before completion
    private volatile boolean m_playing;
    private boolean m_paused=false;
    private Thread m_gameThread = null;

   private Player m_player;
   private List<Enemy> m_enemies = new ArrayList<>();
   private int m_basepeed = -4;
   private int m_spawnSpeed = 3;

    private Paint m_paint;
    private Canvas m_canvas;
    private SurfaceHolder m_holder;
    private Context m_context;
    private long m_startTime;
    private long m_deltaTime;
    private long m_fps;
    private int m_playLane=1;
    private Rect m_pauseButton;

    private Bitmap bg;
    private Bitmap groundTiles;
    private Bitmap pausedButt,resumeButt;

    private PauseButton m_pauseSprites;

    int m_screenW;
    int m_screenH;

    private long base;
    private int elapsedtime;
    private int timePaused;
    private int onPause;

    private Typeface toastface;


//    private LevelManager m_lm;
//    private Viewport m_vp;
//    public InputController m_ic

    public GameView(Context context,int screenW,int screenH){
        super(context);
        int buttonWidth =screenW/8;
        int buttonHeight =screenH/7;
        int buttonpadding = screenW/80;
        m_pauseButton = new Rect(buttonpadding,  m_screenH/8,buttonWidth,m_screenH/8+buttonHeight);
        m_pauseSprites = new PauseButton(m_pauseButton.top,m_pauseButton.left,pausedButt);

        m_context = context;
        m_screenH = screenH;
        m_screenW = screenW;
        m_holder = getHolder();
        m_paint = new Paint();
        base = SystemClock.elapsedRealtime();
         m_player = new Player(context,screenW,screenH);
       //m_enemies.add(new Enemy(context,screenW,screenH,"jalapenosprite",1));

        toastface =  Typeface.createFromAsset(m_context.getAssets(), "texastoast.ttf");

        pausedButt = BitmapFactory.decodeResource(context.getResources(), R.drawable.pausebutt);
        pausedButt = Bitmap.createScaledBitmap(pausedButt,m_pauseButton.width(),m_pauseButton.height(),false);
        resumeButt = BitmapFactory.decodeResource(context.getResources(), R.drawable.playbutt);
        resumeButt = Bitmap.createScaledBitmap(resumeButt,m_pauseButton.width(),m_pauseButton.height(),false);


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

            if(((int)(SystemClock.elapsedRealtime() - base)/1000)-timePaused!=elapsedtime){
                elapsedtime = (int)((SystemClock.elapsedRealtime() - base)/1000)-timePaused;
                //Log.i("time", ""+ elapsedtime);
                enemyspawnSystem();
            }
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
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            //lift finger
//            case MotionEvent.ACTION_UP:
//                m_player.setLane();
//                break;
            //touch screen
            case MotionEvent.ACTION_DOWN:
                if(x >= m_pauseButton.left && x <= m_pauseButton.right
                        && y >= m_pauseButton.top && y <= m_pauseButton.bottom)
                {
                    if(!m_paused){
                        pause();
                        m_paused = true;
                    }else{
                        resume();
                        m_paused = false;
                    }

                }
                else if(m_player.getDown()){
                    m_playLane++;
                }else{
                    m_playLane--;
                }
                m_player.setLane(m_playLane);
                break;
        }

        return true;
    }
    public Activity getActivity(){
        return (Activity)m_context;
    }

    public void update(){


        for(int i = 0; i < m_enemies.size(); i++){
            m_enemies.get(i).update();
            if(m_player.Collision(m_enemies.get(i))){

                SCORE = elapsedtime;
                Intent intent = new Intent(getActivity(), LoseActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();

            }
        }



    }
    private void draw(){
        if(m_holder.getSurface().isValid()) {
            m_canvas = m_holder.lockCanvas();
            m_paint.setTextSize(200);
            m_paint.setTextAlign(Paint.Align.CENTER);
            m_paint.setColor(Color.argb(255, 139,69, 19));
            m_paint.setTypeface(toastface);


            m_canvas.drawColor(Color.argb(255,0,0,0));
            m_canvas.drawBitmap(bg,0,0,m_paint);
            m_canvas.drawBitmap(groundTiles,0,m_canvas.getHeight()-groundTiles.getHeight(),m_paint);
            m_canvas.drawBitmap(groundTiles,m_canvas.getWidth()/2,m_canvas.getHeight()-groundTiles.getHeight(),m_paint);
           //pause btn
            m_canvas.drawRect(m_pauseButton,m_paint);
            for(int i = 0; i < m_enemies.size(); i++) {
                m_canvas.drawBitmap(m_enemies.get(i).getSprite(),m_enemies.get(i).getX(),m_enemies.get(i).getY(),m_paint);
            }



            m_canvas.drawBitmap(m_player.getSprite(),m_player.getX(), m_player.getY(),m_paint);

            m_canvas.drawText("score: " + elapsedtime, m_screenW/2, m_screenH/8, m_paint);
            m_holder.unlockCanvasAndPost(m_canvas);
        }
    }

    public void enemyspawnSystem(){
        int randLane = new Random().nextInt(3)+1;
        int randsprite = new Random().nextInt(5)+1;

        if(elapsedtime % m_spawnSpeed == 0){
            m_enemies.add(new Enemy(m_context,m_screenW,m_screenH,"jalapenosprite",randLane,m_basepeed));
        }
        for(int i = 0; i < m_enemies.size(); i++) {
            if(m_enemies.get(i).getX() == 0){
                m_enemies.remove(i);
            }
        }
        if(elapsedtime  == 30) {
            m_spawnSpeed = 2;
            m_basepeed = m_basepeed * 2;
            for(int i = 0; i < m_enemies.size(); i++) {
               m_enemies.get(i).doubleSpeed();
            }
        }
        if(elapsedtime  == 60) {
            m_spawnSpeed = 1;
            m_basepeed = m_basepeed * 2;
            for(int i = 0; i < m_enemies.size(); i++) {
                m_enemies.get(i).doubleSpeed();
            }
        }

    }

    public void pause(){
        m_playing = false;
        onPause = elapsedtime;
        try {
            m_gameThread.join();
        } catch(InterruptedException e){
            Log.d("error in GameView.Java - Pause", e.getMessage());
        }
    }
    public void resume(){
        m_playing = true;
        timePaused = (int)((SystemClock.elapsedRealtime()-base)/1000)-onPause;
        m_gameThread = new Thread(this);
        m_gameThread.start();
    }


}
