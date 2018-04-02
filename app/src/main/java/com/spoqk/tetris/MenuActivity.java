package com.spoqk.tetris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.playBtn).setOnClickListener(this);
        findViewById(R.id.highScoresBtn).setOnClickListener(this);
        findViewById(R.id.helpBtn).setOnClickListener(this);
        findViewById(R.id.exitBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.playBtn:
                loadMainActivity();
                break;
            case R.id.highScoresBtn:
                displayHighScores();
                break;
            case R.id.helpBtn:
                showHelp();
                break;
            case R.id.exitBtn:
                exit();
                break;
        }
    }

    private void loadMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void displayHighScores()
    {

    }

    private void showHelp()
    {

    }

    private void exit()
    {
        MainActivity.getInstance().exitApp();
    }
}
