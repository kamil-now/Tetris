package com.spoqk.tetris.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.spoqk.tetris.GamePanel;

public class MainActivity extends AppCompatActivity
{
    private static MainActivity instance;

    public static MainActivity getInstance()
    {
        return instance;
    }

    public GamePanel GamePanel;
    public SidePanelActivity SidePanel;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        instance = this;
        layout = new LinearLayout(this);

        initPanels();

        layout.addView(SidePanel);
        layout.addView(GamePanel);
        setContentView(layout);
    }

    private void initPanels()
    {
        GamePanel = new GamePanel(this);
        SidePanel = new SidePanelActivity(this);

        int deviceWidth = getDeviceWidth();

        int width = (int) ((double) deviceWidth * 0.6);
        int sidePanelWidth = deviceWidth - width;

        GamePanel.setLayoutParams(new LinearLayout.LayoutParams(
                width,
                LinearLayout.LayoutParams.MATCH_PARENT));

        SidePanel.setLayoutParams(new LinearLayout.LayoutParams(
                sidePanelWidth,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    private int getDeviceWidth()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public void exitApp()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        GamePanel.onResume();
        setContentView(layout);
    }
}
