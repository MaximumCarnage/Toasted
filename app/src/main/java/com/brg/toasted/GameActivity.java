package com.brg.toasted;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;

public class GameActivity extends Activity {
    private GameView m_gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        m_gameView = new GameView(this,size.x,size.y);
        setContentView(m_gameView);

    }

    @Override
    protected void onPause(){
        super.onPause();
        m_gameView.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        m_gameView.resume();
    }




}
