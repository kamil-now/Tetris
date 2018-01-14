package com.qwerty.tetriz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public final class SidePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static SidePanel instance;

    public static SidePanel getInstance()
    {
        return instance;
    }

    private SideThread thread;
    private Paint paint;

    public SidePanel(Context context)
    {
        super(context);
        if (instance == null)
        {
            instance = this;

            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Style.FILL);

            getHolder().addCallback(this);
            setFocusable(true);
            thread = new SideThread(getHolder(), this);
        }
    }


    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawPaint(paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }
}
