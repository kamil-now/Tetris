package com.qwerty.tetriz;

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
        setContentView(R.layout.menu_activity);
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
                LoadMainActivity();
                break;
            case R.id.highScoresBtn:
                DisplayHighScores();
                break;
            case R.id.helpBtn:
                ShowHelp();
                break;
            case R.id.exitBtn:
                Exit();
                break;
        }
    }

    private void LoadMainActivity()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void DisplayHighScores()
    {

    }

    private void ShowHelp()
    {

    }

    private void Exit()
    {

    }
}
