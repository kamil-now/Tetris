package com.spoqk.tetris.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.spoqk.tetris.Grid;
import com.spoqk.tetris.IRotate;
import com.spoqk.tetris.MainThread;
import com.spoqk.tetris.RandomBlockGenerator;
import com.spoqk.tetris.models.base.Block;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public final class GamePanelView extends SurfaceView implements SurfaceHolder.Callback
{
    private MainThread thread;
    private float initEventX;
    private float initEventY;
    private final int MAX_CLICK_DURATION = 200;
    private long startClickTime;
    private Grid grid;
    private Block block;
    private boolean isPaused;

    public boolean isPaused()
    {
        return isPaused;
    }
    public Block getBlock(){
        return block;
    }
    Paint paint = new Paint();

    public GamePanelView(Context context, Grid grid)
    {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        thread = new MainThread(getHolder(), this);
        this.grid = grid;
        Point startingPoint = new Point(grid.getColumns() / 2, -2);
        new RandomBlockGenerator(startingPoint);
        block = RandomBlockGenerator.getRandom();

        paint.setColor(Color.BLACK);
    }


    public void pauseClick()
    {
        isPaused = !isPaused;
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        grid.draw(canvas);
        block.draw(canvas);
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

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
    }

    public void createNewBlock()
    {
        if (block != null)
        {
            grid.addToFixedBlocks(block);
        }
        block = RandomBlockGenerator.getRandom();
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
