package com.qwerty.tetriz;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
{

    public GamePanel GamePanel;
    public SidePanelActivity SidePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        GamePanel =new GamePanel(this);
        SidePanel = new SidePanelActivity(this);
        //int width = (int)((double)layout.getMeasuredWidth()/2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int width = (int)((double)deviceWidth*0.6);
        int sidePanelWidth = deviceWidth-width;
        GamePanel.setLayoutParams(new LinearLayout.LayoutParams(
                width,
                LinearLayout.LayoutParams.MATCH_PARENT));

        SidePanel.setLayoutParams(new LinearLayout.LayoutParams(
                sidePanelWidth,
                LinearLayout.LayoutParams.MATCH_PARENT));
        layout.addView(SidePanel);
        layout.addView(GamePanel);
        setContentView(layout);
    }
}
