package com.spoqk.tetris;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public boolean isRunning(){return running;}
    public void setRunning(boolean running)
    {
        this.running = running;
    }
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

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
                        this.gamePanel.draw(canvas);
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

