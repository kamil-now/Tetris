package com.spoqk.tetris.models;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;

import com.spoqk.tetris.App;
import com.spoqk.tetris.R;
import com.spoqk.tetris.models.base.Block;

public final class O extends Block
{
    public O(Point position)
    {
        super(position, ContextCompat.getColor(App.getContext(), R.color.colorO));
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
