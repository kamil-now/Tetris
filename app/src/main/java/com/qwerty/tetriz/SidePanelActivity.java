package com.qwerty.tetriz;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class SidePanelActivity extends LinearLayout {
    private static SidePanelActivity instance;

    public static SidePanelActivity getInstance() {
        return instance;
    }

    private int level;
    private TextView levelTv;

    public void setLevel(int value)
    {
        level=value;
        levelTv.setText(String.valueOf(level));
    }

    public int getLevel() {
        return level;
    }

    public SidePanelActivity(Context context) {
        super(context);


        if (instance == null) {
            instance = this;
            View.inflate(context, R.layout.activity_sidepanel, this);
            levelTv = findViewById(R.id.level);
            setLevel(0);
        }
    }
}
