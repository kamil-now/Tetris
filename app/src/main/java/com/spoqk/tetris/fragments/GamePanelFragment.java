package com.spoqk.tetris.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.spoqk.tetris.Grid;
import com.spoqk.tetris.R;

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
        grid = new Grid(20, 10, Color.WHITE);
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
                        boolean canMoveDown = gameView.getBlock().canMoveDown();
                        if (canMoveDown)
                            gameView.getBlock().moveDown();
                        else
                            gameView.createNewBlock();
                    }
                }, 0, 1000);
    }
}

