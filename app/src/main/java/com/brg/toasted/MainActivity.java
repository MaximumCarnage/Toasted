package com.brg.toasted;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer menuplayer,soundplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        menuplayer = MediaPlayer.create(this,R.raw.menumusic);

        menuplayer.start();

    }




    @Override
    public void onClick(View view) {
        soundplayer = MediaPlayer.create(this,R.raw.toasterslide);
        soundplayer.start();
        menuplayer.stop();
        soundplayer.stop();
        soundplayer.release();
        menuplayer.release();
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();


    }
    public void clickexit(View v){
        soundplayer = MediaPlayer.create(this,R.raw.toasterslide);
        soundplayer.start();
        menuplayer.stop();
        soundplayer.stop();
        soundplayer.release();
        menuplayer.release();
        moveTaskToBack(true);
        android.os.Process.killProcess( android.os.Process.myPid());
        System.exit(1);

    }
}
