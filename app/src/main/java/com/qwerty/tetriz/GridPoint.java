package com.qwerty.tetriz;

import android.graphics.Point;

public class GridPoint
{
    private Point point;
    private int margin = 3;
    public Point getPoint(){return new Point(top(),left());}
    public int left()
    {
        return GamePanel.getInstance().getGrid().getLeftMargin() + GamePanel.getInstance().getGrid().getSpacing() * point.x + margin;
    }

    public int top()
    {
        return GamePanel.getInstance().getGrid().getTopMargin() + GamePanel.getInstance().getGrid().getSpacing() * point.y + margin;
    }

    public int right()
    {
        return GamePanel.getInstance().getGrid().getLeftMargin() + GamePanel.getInstance().getGrid().getSpacing() * (point.x + 1)-margin;
    }

    public int bottom()
    {
        return GamePanel.getInstance().getGrid().getTopMargin() + GamePanel.getInstance().getGrid().getSpacing() * (point.y + 1)-margin;
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
