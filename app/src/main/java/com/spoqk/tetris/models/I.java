package com.spoqk.tetris.models;

import android.graphics.Color;
import android.graphics.Point;

import com.spoqk.tetris.models.base.TwoTypeRotationBlock;

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
        bottom.updateGridPosition(point);
        right.updateGridPosition(new Point(point.x + 1, point.y));
        left.updateGridPosition(new Point(point.x - 2, point.y));
        top.updateGridPosition(new Point(point.x - 1, point.y));
    }

    @Override
    protected void updateVerticalRotation(Point point)
    {
        left.updateGridPosition(new Point(point.x, point.y - 1));
        right.updateGridPosition(point);
        top.updateGridPosition(new Point(point.x, point.y - 2));
        bottom.updateGridPosition(new Point(point.x, point.y + 1));
    }
}
