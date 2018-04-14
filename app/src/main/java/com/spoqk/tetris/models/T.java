package com.spoqk.tetris.models;
import android.graphics.Color;
import android.graphics.Point;

import com.spoqk.tetris.models.base.FourTypeRotationBlock;

public final class T extends FourTypeRotationBlock
{

    public T(Point position)
    {
        super(position, Color.MAGENTA);
        rotation= Rotation.UP;
        updatePos(position);
    }

    @Override
    protected void updateUpRotation(Point point)
    {
        left.updateGridPosition(new Point(point.x - 1, point.y));
        right.updateGridPosition(new Point(point.x + 1, point.y));
        top.updateGridPosition(new Point(point.x, point.y - 1));
        bottom.updateGridPosition(point);
    }

    @Override
    protected void updateDownRotation(Point point)
    {
        left.updateGridPosition(new Point(point.x - 1, point.y));
        right.updateGridPosition(new Point(point.x + 1, point.y));
        top.updateGridPosition(point);
        bottom.updateGridPosition(new Point(point.x, point.y + 1));
    }

    @Override
    protected void updateRightRotation(Point point)
    {
        left.updateGridPosition(point);
        right.updateGridPosition(new Point(point.x + 1, point.y));
        top.updateGridPosition(new Point(point.x, point.y - 1));
        bottom.updateGridPosition(new Point(point.x, point.y + 1));
    }

    @Override
    protected void updateLeftRotation(Point point)
    {
        left.updateGridPosition(new Point(point.x - 1, point.y));
        right.updateGridPosition(point);
        top.updateGridPosition(new Point(point.x, point.y - 1));
        bottom.updateGridPosition(new Point(point.x, point.y + 1));
    }
}

