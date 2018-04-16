package com.spoqk.tetris.models;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;

import com.spoqk.tetris.App;
import com.spoqk.tetris.R;
import com.spoqk.tetris.models.base.TwoTypeRotationBlock;


public final class Z extends TwoTypeRotationBlock
{
    public Z(Point position)
    {
        super(position, ContextCompat.getColor(App.getContext(), R.color.colorZ));
        rotation=Rotation.HORIZONTAL;
        updatePos(position);
    }


    @Override
    protected void updateHorizontalRotation(Point point)
    {
        top.updateGridPosition(point);
        right.updateGridPosition(new Point(point.x + 1, point.y + 1));
        left.updateGridPosition(new Point(point.x-1, point.y));
        bottom.updateGridPosition(new Point(point.x, point.y + 1));
    }

    @Override
    protected void updateVerticalRotation(Point point)
    {
        left.updateGridPosition(new Point(point.x - 1, point.y ));
        right.updateGridPosition(point);
        top.updateGridPosition(new Point(point.x, point.y - 1));
        bottom.updateGridPosition(new Point(point.x-1, point.y + 1));
    }

}
