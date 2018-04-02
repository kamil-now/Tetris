package com.spoqk.tetris;

import android.graphics.Color;
import android.graphics.Point;

public final class O extends Block
{
    public O(Point position)
    {
        super(position, Color.YELLOW);
        updatePos(position);
    }

    @Override
    public void updatePos(Point point)
    {
        position = point;
        left.updatePos(new Point(point.x - 1, point.y - 1));
        right.updatePos(point);
        top.updatePos(new Point(point.x, point.y - 1));
        bottom.updatePos(new Point(point.x - 1, point.y));
    }
}
