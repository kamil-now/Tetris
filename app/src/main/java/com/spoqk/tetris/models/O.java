package com.spoqk.tetris.models;

import android.graphics.Color;
import android.graphics.Point;

import com.spoqk.tetris.models.base.Block;

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
        left.updateGridPosition(new Point(point.x - 1, point.y - 1));
        right.updateGridPosition(point);
        top.updateGridPosition(new Point(point.x, point.y - 1));
        bottom.updateGridPosition(new Point(point.x - 1, point.y));
    }
}
