package com.qwerty.tetriz;

import android.graphics.Color;
import android.graphics.Point;


public class Z extends TwoTypeRotationBlock
{
    public Z(Point position)
    {
        super(position, Color.RED);
        rotation=Rotation.HORIZONTAL;
        updatePos(position);
    }


    @Override
    protected void updateHorizontalRotation(Point point)
    {
        top.updatePos(point);
        right.updatePos(new Point(point.x + 1, point.y + 1));
        left.updatePos(new Point(point.x-1, point.y));
        bottom.updatePos(new Point(point.x, point.y + 1));
    }

    @Override
    protected void updateVerticalRotation(Point point)
    {
        left.updatePos(new Point(point.x - 1, point.y ));
        right.updatePos(point);
        top.updatePos(new Point(point.x, point.y - 1));
        bottom.updatePos(new Point(point.x-1, point.y + 1));
    }

}
