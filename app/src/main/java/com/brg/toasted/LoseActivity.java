package com.brg.toasted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);


        final Button buttonMenu = (Button)findViewById(R.id.menu);
        buttonMenu.setOnClickListener(this);

    }







    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();


    }
}





