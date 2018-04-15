package com.spoqk.tetris.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.spoqk.tetris.R;
import com.spoqk.tetris.fragments.GamePanelFragment;

public class MainActivity extends AppCompatActivity
{
    private static MainActivity instance;

    public static MainActivity getInstance()
    {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
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
        //GamePanelFragment.onResume();
//        setContentView(layout);
    }


}
