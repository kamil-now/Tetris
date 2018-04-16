package com.spoqk.tetris;

import android.app.Application;
import android.content.Context;

import com.spoqk.tetris.fragments.GamePanelFragment;


public class App extends Application
{
    private static Application application;

    public static Application getApplication()
    {
        return application;
    }
    private static Context appContext;
    public static Context getContext()
    {
        return GamePanelFragment.getInstance().getActivity();//appContext;//getApplication().getApplicationContext();
    }
    public static void setContext(Context context)
    {
       appContext = context;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        application = this;
    }
}
