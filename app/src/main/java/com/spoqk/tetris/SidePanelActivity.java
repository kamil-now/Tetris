package com.spoqk.tetris;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class SidePanelActivity extends LinearLayout implements View.OnClickListener
{
    private static SidePanelActivity instance;

    public static SidePanelActivity getInstance()
    {
        return instance;
    }

    private int level;
    private TextView levelTv;

    public void setLevel(int value)
    {
        level = value;
        MainActivity.getInstance().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                levelTv.setText(Integer.toString(level));
            }
        });
    }

    public int getLevel()
    {
        return level;
    }

    public SidePanelActivity(Context context)
    {
        super(context);


        if (instance == null)
        {
            instance = this;
            View.inflate(context, R.layout.activity_sidepanel, this);
            levelTv = findViewById(R.id.level);
            setLevel(1);
            findViewById(R.id.pauseBtn).setOnClickListener(this);
            findViewById(R.id.exitBtn).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.pauseBtn:
                GamePanel.getInstance().pauseClick();
                break;
            case R.id.exitBtn:
                exit();
                break;
        }
    }

    private void exit()
    {
        MainActivity.getInstance().exitApp();
    }
}
