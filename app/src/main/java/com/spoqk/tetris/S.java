package com.spoqk.tetris;

import android.graphics.Color;
import android.graphics.Point;

public final class S extends TwoTypeRotationBlock
{
    public S(Point position)
    {
        super(position, Color.GREEN);
        rotation=Rotation.HORIZONTAL;
        updatePos(position);
    }

    @Override
    protected void updateHorizontalRotation(Point point)
    {
        bottom.updatePos(point);
        right.updatePos(new Point(point.x + 1, point.y - 1));
        left.updatePos(new Point(point.x - 1, point.y));
        top.updatePos(new Point(point.x, point.y - 1));
    }

    @Override
    protected void updateVerticalRotation(Point point)
    {
        left.updatePos(new Point(point.x - 1, point.y));
        right.updatePos(point);
        top.updatePos(new Point(point.x - 1, point.y - 1));
        bottom.updatePos(new Point(point.x, point.y + 1));
    }
}
