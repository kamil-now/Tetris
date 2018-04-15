package com.spoqk.tetris;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.spoqk.tetris.fragments.GamePanelFragment;
import com.spoqk.tetris.fragments.GamePanelView;

public class MainThread extends Thread
{
    private SurfaceHolder surfaceHolder;
    private GamePanelView gamePanelView;
    private boolean running;
    public void setRunning(boolean running)
    {
        this.running = running;
    }
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanelView gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanelView = gamePanel;

    }

    @Override
    public void run()
    {
        canvas = null;

        while (running)
        {
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                if(canvas!=null)
                    synchronized (surfaceHolder)
                    {
                        this.gamePanelView.draw(canvas);
                    }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (canvas != null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}

