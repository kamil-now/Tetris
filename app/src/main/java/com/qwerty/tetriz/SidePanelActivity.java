package com.qwerty.tetriz;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;

public final class SidePanelActivity extends LinearLayout// implements SurfaceHolder.Callback
{
    private static SidePanelActivity instance;

    public static SidePanelActivity getInstance()
    {
        return instance;
    }

    private SideThread thread;
    private Paint paint;

    public SidePanelActivity(Context context)
    {
        super(context);
        View.inflate(context, R.layout.activity_sidepanel,this);
        if (instance == null)
        {
            instance = this;

            //paint = new Paint();
            //paint.setColor(Color.WHITE);
            //paint.setStyle(Style.FILL);

            //getHolder().addCallback(this);
            //setFocusable(true);
            //thread = new SideThread(getHolder(), this);
        }
    }


//    @Override
//    public void draw(Canvas canvas)
//    {
//        super.draw(canvas);
//        //canvas.drawPaint(paint);
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder)
//    {
//        //thread.setRunning(true);
//        //thread.start();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
//    {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder)
//    {
//
//    }
}
