package com.qwerty.tetriz;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SideThread extends Thread
{
    private SurfaceHolder surfaceHolder;
    private SidePanelActivity sidePanel;
    private boolean running;

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    public static Canvas canvas;

    public SideThread(SurfaceHolder surfaceHolder, SidePanelActivity sidePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.sidePanel = sidePanel;

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
                        this.sidePanel.draw(canvas);
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


