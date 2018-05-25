package com.brg.toasted;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SoundManager extends Activity {

    private SoundPool soundPool;
    int EnemyHit = -1;
    int EnemyWalk1 = -1;
    int EnemyWalk2 = -1;
    int EnemyWalk3 = -1;
    int Gameplaymusic = -1;
    int Loss = -1;
    int Loss2 = -1;
    int MenuMusic = -1;
    int PancakeBounce = -1;
    int PancakeBounce2 = -1;
    int pause = -1;
    int Step1 = -1;
    int Step2 = -1;
    int ToasterSlide = -1;
    int Unpause = -1;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();


        // soundPool = new SoundPool.Builder(); //new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        try {
            // Create objects of the 2 required classes
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor;

            //create our three fx in memory ready for use
            descriptor = assetManager.openFd("EnemyHit.ogg");
            EnemyHit = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("EnemyWalk1.ogg");
            EnemyWalk1 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("EnemyWalk2.ogg");
            EnemyWalk2 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("EnemyWalk3.ogg");
            EnemyWalk3 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("Gameplaymusic.ogg");
            Gameplaymusic = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("Loss.ogg");
            Loss = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("Loss2.ogg");
            Loss2 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("MenuMusic.ogg");
            MenuMusic = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("PancakeBounce.ogg");
            PancakeBounce = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("PancakeBounce2.ogg");
            PancakeBounce2 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("pause.ogg");
            pause = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("Step1.ogg");
            Step1 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("Step2.ogg");
            Step2 = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("ToasterSlide.ogg");
            ToasterSlide = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("Unpause.ogg");
            Unpause = soundPool.load(descriptor, 0);
        } catch (IOException e) {
            //catch exceptions here
        }

    }
    public void playSound(String soundType){
        switch(soundType){
            case "EnemyHit":
                soundPool.play(EnemyHit, 1, 1, 0, 0, 1);
                break;

            case "EnemyWalk1":
                soundPool.play(EnemyWalk1, 1, 1, 0, 0, 1);
                break;

            case "EnemyWalk2":
                soundPool.play(EnemyWalk2, 1, 1, 0, 0, 1);
                break;

            case "EnemyWalk3":
                soundPool.play(EnemyWalk3, 1, 1, 0, 0, 1);
                break;

            case "Gameplaymusic":
                soundPool.play(Gameplaymusic, 1, 1, 0, 1, 1);
                break;

            case "Loss":
                soundPool.play(Loss, 1, 1, 0, 0, 1);
                break;

            case "Loss2":
                soundPool.play(Loss2, 1, 1, 0, 0, 1);
                break;

            case "MenuMusic":
                soundPool.play(MenuMusic, 1, 1, 0, 1, 1);
                break;

            case "PancakeBounce":
                soundPool.play(PancakeBounce, 1, 1, 0, 0, 1);
                break;

            case "PancakeBounce2":
                soundPool.play(PancakeBounce2, 1, 1, 0, 0, 1);
                break;

            case "pause":
                soundPool.play(pause, 1, 1, 0, 0, 1);
                break;

            case "Step1":
                soundPool.play(Step1, 1, 1, 0, 0, 1);
                break;

            case "Step2":
                soundPool.play(Step2, 1, 1, 0, 0, 1);
                break;

            case "ToasterSlide":
                soundPool.play(ToasterSlide, 1, 1, 0, 0, 1);
                break;

            case "Unpause":
                soundPool.play(Unpause, 1, 1, 0, 0, 1);
                break;
        }
    }
}