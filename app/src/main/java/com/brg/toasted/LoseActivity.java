package com.brg.toasted;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class LoseActivity extends AppCompatActivity implements View.OnClickListener{
    MediaPlayer soundplayer;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        score = GameView.SCORE;

        final Button buttonMenu = (Button)findViewById(R.id.menu);
        final TextView scoreBox = (TextView)findViewById(R.id.finalscore);

        scoreBox.setText("FinalScore= "+score);
        buttonMenu.setOnClickListener(this);
        soundplayer = MediaPlayer.create(this,R.raw.toasterslide);
    }







    @Override
    public void onClick(View view) {

        soundplayer.start();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();


    }

    public void setFinalScore(int gameScore){
        score = gameScore;
    }
}





