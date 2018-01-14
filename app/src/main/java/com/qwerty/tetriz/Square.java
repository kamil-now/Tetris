package com.qwerty.tetriz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Square
{
    private Paint paint;
    private Rect rect;
    public GridPoint actualPosition;
    public Point position;

    public Square(Point pos, Paint paint)
    {
        position = pos;
        this.paint = paint;
        rect = new Rect();
        actualPosition = new GridPoint(pos);
        updatePos(pos);
    }

    public Square(Point pos)
    {
        position = pos;
    }

    public void draw(Canvas canvas)
    {
        rect.set(actualPosition.left(), actualPosition.top(), actualPosition.right(), actualPosition.bottom());
        canvas.drawRect(rect, paint);
    }

    public void updatePos(Point pos)
    {
        position = pos;
        actualPosition.update(pos);
        rect.set(actualPosition.left(), actualPosition.top(), actualPosition.right(), actualPosition.bottom());
    }

    public boolean isOnGrid(Grid grid)
    {
        if (this.position.x < 0 && this.position.x > grid.getColumns())
        {
            return false;
        }
        return true;
    }

}
