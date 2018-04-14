package com.spoqk.tetris;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spoqk.tetris.models.base.Block;

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
    private boolean isPaused;

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

    public void pauseClick()
    {
        isPaused = !isPaused;
    }

    private void init()
    {
        thread = new MainThread(getHolder(), this);
        grid = new Grid(24, 10, Color.GRAY);
        Point startingPoint = new Point(grid.getColumns() / 2, -2);
        new RandomBlockGenerator(startingPoint);
        block = RandomBlockGenerator.getRandom();
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
        if (thread.getState() == Thread.State.TERMINATED)
        {
            getHolder().addCallback(this);
            thread = new MainThread(getHolder(), this);
        }
        thread.setRunning(true);
        thread.start();
    }

    public void onResume()
    {

        new Timer().scheduleAtFixedRate(
                new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        if (!isPaused)
                        {
                            boolean canMoveDown = block.canMoveDown();
                            if (canMoveDown)
                                block.moveDown();
                            else
                                createNewBlock();
                        }
                    }
                }, 0, 1000);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (!isPaused)
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
                    float x = event.getX();
                    if (clickDuration < MAX_CLICK_DURATION && Math.abs(y - initEventY) < 1 && Math.abs(x - initEventX) < 20)
                    {
                        if (block instanceof IRotate)
                            ((IRotate) block).rotate();
                    }
                    else if (y > initEventY && Math.abs(x - initEventX) < 20)
                    {
                        block.drop();
                    }
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
        double blockPosX = block.position.x * grid.getSpacing() + grid.getLeftMargin();
        if (x > blockPosX + grid.getSpacing() && x > initEventX)
        {
            if (block.canMoveRight())
            {
                block.moveRight();
            }
        }
        else if (x < blockPosX - grid.getSpacing() && x < initEventX)
        {
            if (block.canMoveLeft())
            {
                block.moveLeft();
            }
        }
    }
}

