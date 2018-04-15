package com.spoqk.tetris;

import android.graphics.Point;

import com.spoqk.tetris.fragments.GamePanelFragment;

public class GridPoint
{
    private Point point;
    private int margin = 3;
    public Point getPoint(){return new Point(top(),left());}
    public int left()
    {
        return GamePanelFragment.getInstance().getGrid().getLeftMargin() + GamePanelFragment.getInstance().getGrid().getSpacing() * point.x + margin;
    }

    public int top()
    {
        return GamePanelFragment.getInstance().getGrid().getTopMargin() + GamePanelFragment.getInstance().getGrid().getSpacing() * point.y + margin;
    }

    public int right()
    {
        return GamePanelFragment.getInstance().getGrid().getLeftMargin() + GamePanelFragment.getInstance().getGrid().getSpacing() * (point.x + 1)-margin;
    }

    public int bottom()
    {
        return GamePanelFragment.getInstance().getGrid().getTopMargin() + GamePanelFragment.getInstance().getGrid().getSpacing() * (point.y + 1)-margin;
    }

    public GridPoint(Point p)
    {
        point = p;
    }

    public void update(Point p)
    {
        point = p;
    }
}
