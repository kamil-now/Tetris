package com.spoqk.tetris.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.spoqk.tetris.fragments.GamePanelFragment;
import com.spoqk.tetris.Grid;
import com.spoqk.tetris.GridPoint;

public class Square
{
    private Paint paint;
    private Rect rect;
    private GridPoint actualPosition;
    private Point position;

    public Point getPosition()
    {
        return position;
    }

    public Square(Point pos, Paint paint)
    {
        position = pos;
        this.paint = paint;
        rect = new Rect();
        actualPosition = new GridPoint(pos);
        updateGridPosition(pos);
    }

    public Square(Point pos)
    {
        position = pos;
    }

    public void draw(Canvas canvas)
    {
        rect.set(actualPosition.left(), actualPosition.top(), actualPosition.right(), actualPosition.bottom());
        if (this.isOnGrid(GamePanelFragment.getInstance().getGrid()))
            canvas.drawRect(rect, paint);
    }

    public void updateGridPosition(Point gridPoint)
    {
        position = gridPoint;
        actualPosition.update(gridPoint);
        rect.set(actualPosition.left(), actualPosition.top(), actualPosition.right(), actualPosition.bottom());
    }

    public boolean isOnGrid(Grid grid)
    {
        if (this.position.x < 0 || this.position.x > grid.getColumns() - 1)
        {
            return false;
        }
        return true;
    }
}
