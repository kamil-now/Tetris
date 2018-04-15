package com.spoqk.tetris.fragments;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.spoqk.tetris.Grid;
import com.spoqk.tetris.MainThread;
import com.spoqk.tetris.R;
import com.spoqk.tetris.RandomBlockGenerator;
import com.spoqk.tetris.models.base.Block;

import java.util.Timer;
import java.util.TimerTask;

public final class GamePanelFragment extends Fragment
{
    private static GamePanelFragment instance;

    public static GamePanelFragment getInstance()
    {
        return instance;
    }

    private Grid grid;
    private GamePanelView gameView;

    public Grid getGrid()
    {
        return grid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        instance = this;
        grid = new Grid(20, 10, Color.GRAY);
        View rootView = inflater.inflate(R.layout.fragment_game_canvas, container, false);
        RelativeLayout relativeLayout = rootView.findViewById(R.id.container);

        gameView = new GamePanelView(getActivity(), grid);
        relativeLayout.addView(gameView);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        new Timer().scheduleAtFixedRate(
                new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        if (!gameView.isPaused())
                        {
                            boolean canMoveDown = gameView.getBlock().canMoveDown();
                            if (canMoveDown)
                                gameView.getBlock().moveDown();
                            else
                                gameView.createNewBlock();
                        }
                    }
                }, 0, 1000);
    }
}

