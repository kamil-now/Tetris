package com.qwerty.tetriz;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public final class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private static GamePanel instance;

    public static GamePanel getInstance()
    {
        return instance;
    }

    private MainThread thread;
    private float initEventX;
    private float initEventY;
    private final int MAX_CLICK_DURATION = 200;
    private long startClickTime;
    private Grid grid;
    private Block block;

    public Grid getGrid()
    {
        return grid;
    }

    public GamePanel(Context context)
    {
        super(context);
        if (instance == null)
        {
            instance = this;
            getHolder().addCallback(this);
            setFocusable(true);

            init();
        }
    }

    private void init()
    {
        thread = new MainThread(getHolder(), this);
        grid = new Grid(22, 10, Color.GRAY);
        new RandomBlockGenerator(grid.getColumns() / 2, -2);
        block = RandomBlockGenerator.getRandom();

        new Timer().scheduleAtFixedRate(
                new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        boolean fixed = !block.moveDown();
                        if (fixed)
                            createNewBlock();
                    }
                }, 0, 1000);
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        grid.draw(canvas);
        block.draw(canvas);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while (retry)
        {
            try
            {
                thread.join();
                retry = false;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startClickTime = Calendar.getInstance().getTimeInMillis();
                initEventX = event.getX();
                initEventY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                moveBlock(event);
                return true;
            case MotionEvent.ACTION_UP:
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                float y = event.getY();
                if (clickDuration < MAX_CLICK_DURATION)
                {
                    if (block instanceof IRotate)
                        ((IRotate) block).rotate();
                } else if (y > initEventY + 2 * grid.getSpacing())
                {
                    block.drop();
                }
        }
        return super.onTouchEvent(event);
    }

    private void createNewBlock()
    {
        if (block != null)
        {
            grid.addToFixedBlocks(block);
        }
        block = RandomBlockGenerator.getRandom();
    }

    //slide right/left logic
    private void moveBlock(MotionEvent event)
    {
        float x = event.getX();
//        float y = event.getY();
        double blockPosX = block.position.x * grid.getSpacing() + grid.getLeftMargin();
//        double blockPosY = block.position.y * grid.getSpacing() + grid.getTopMargin();
        if (x > blockPosX + grid.getSpacing() && x > initEventX)
            block.moveRight();
        else if (x < blockPosX - grid.getSpacing() && x < initEventX)
            block.moveLeft();
//        else if (y > blockPosY + 2 * grid.getSpacing() && y > initEventY && y < blockPosY + 3 * grid.getSpacing())
//            block.moveDown();
//        else if (y > initEventY + 7 * grid.getSpacing())
//        {
//            block.drop();
//        }
    }


}

