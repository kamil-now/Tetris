package com.spoqk.tetris.models;

import android.graphics.Color;
import android.graphics.Point;

import com.spoqk.tetris.models.base.TwoTypeRotationBlock;

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
        bottom.updateGridPosition(point);
        right.updateGridPosition(new Point(point.x + 1, point.y - 1));
        left.updateGridPosition(new Point(point.x - 1, point.y));
        top.updateGridPosition(new Point(point.x, point.y - 1));
    }

    @Override
    protected void updateVerticalRotation(Point point)
    {
        left.updateGridPosition(new Point(point.x - 1, point.y));
        right.updateGridPosition(point);
        top.updateGridPosition(new Point(point.x - 1, point.y - 1));
        bottom.updateGridPosition(new Point(point.x, point.y + 1));
    }
}
