package com.spoqk.tetris;
import android.graphics.Color;
import android.graphics.Point;


public final class J extends FourTypeRotationBlock
{
    public J(Point position)
    {
        super(position, Color.rgb(255,165,0));
        rotation= Rotation.UP;
        updatePos(position);
    }

    @Override
    protected void updateUpRotation(Point point)
    {
        left.updatePos(new Point(point.x - 1, point.y + 1));
        right.updatePos(point);
        top.updatePos(new Point(point.x, point.y - 1));
        bottom.updatePos(new Point(point.x, point.y + 1));
    }

    @Override
    protected void updateRightRotation(Point point)
    {
        left.updatePos(new Point(point.x - 1, point.y));
        right.updatePos(new Point(point.x + 1, point.y));
        top.updatePos(new Point(point.x - 1, point.y - 1));
        bottom.updatePos(point);
    }

    @Override
    protected void updateDownRotation(Point point)
    {
        left.updatePos(point);
        right.updatePos(new Point(point.x + 1, point.y - 1));
        top.updatePos(new Point(point.x, point.y - 1));
        bottom.updatePos(new Point(point.x, point.y + 1));
    }

    @Override
    protected void updateLeftRotation(Point point)
    {
        left.updatePos(new Point(point.x - 1, point.y));
        right.updatePos(new Point(point.x + 1, point.y));
        top.updatePos(point);
        bottom.updatePos(new Point(point.x + 1, point.y + 1));

    }
}
