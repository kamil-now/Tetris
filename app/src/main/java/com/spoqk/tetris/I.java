package com.spoqk.tetris;

import android.graphics.Color;
import android.graphics.Point;

public final class I extends TwoTypeRotationBlock
{
    public I(Point position)
    {
        super(position, Color.CYAN);
        rotation= Rotation.VERTICAL;
        updatePos(position);
    }

    @Override
    protected void updateHorizontalRotation(Point point)
    {
        bottom.updatePos(point);
        right.updatePos(new Point(point.x + 1, point.y));
        left.updatePos(new Point(point.x - 2, point.y));
        top.updatePos(new Point(point.x - 1, point.y));
    }

    @Override
    protected void updateVerticalRotation(Point point)
    {
        left.updatePos(new Point(point.x, point.y - 1));
        right.updatePos(point);
        top.updatePos(new Point(point.x, point.y - 2));
        bottom.updatePos(new Point(point.x, point.y + 1));
    }
}
