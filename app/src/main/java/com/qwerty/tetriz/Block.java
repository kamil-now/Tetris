package com.qwerty.tetriz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;


public abstract class Block
{
    protected Point position;
    protected Square top;
    protected Square bottom;
    protected Square right;
    protected Square left;
    protected Paint paint;


    public Block(Point position, int color)
    {
        paint = new Paint();
        paint.setColor(color);
        this.position = position;

        top = new Square(position, paint);
        bottom = new Square(position, paint);
        right = new Square(position, paint);
        left = new Square(position, paint);
    }

    public abstract void updatePos(Point point);

    public void draw(Canvas canvas)
    {
        top.draw(canvas);
        bottom.draw(canvas);
        left.draw(canvas);
        right.draw(canvas);
    }

    public boolean moveDown()
    {
        if (canMoveDown())
        {
            updatePos(new Point(position.x, position.y + 1));
            return true;
        }
        return false;
    }

    public boolean moveRight()
    {
        if (canMoveRight())
        {
            updatePos(new Point(position.x + 1, position.y));
            return true;
        }
        return false;
    }

    public boolean moveLeft()
    {
        if (canMoveLeft())
        {
            updatePos(new Point(position.x - 1, position.y));
            return true;
        }
        return false;
    }

    //on slide down event
    public void drop()
    {
        while (canMoveDown())
        {
            this.moveDown();
        }
    }

    //collision detection with other blocks on the grid
    protected boolean collidesWithFixedBlock(Direction direction)
    {
        List<Square> tempList = GamePanel.getInstance().getGrid().getFixedBlocks();
        BlockPositionHolder temp = new BlockPositionHolder(this, direction);
        for (Square fixed : tempList)
        {
            if (temp.collides(fixed))
                return true;
        }
        return false;
    }

    protected boolean isOnGrid(Grid grid)
    {
        return top.isOnGrid(grid)
                && bottom.isOnGrid(grid)
                && right.isOnGrid(grid)
                && left.isOnGrid(grid);
    }

    private boolean canMoveRight()
    {
        return right.position.x + 1 < GamePanel.getInstance().getGrid().getColumns() && !collidesWithFixedBlock(Direction.RIGHT);
    }

    private boolean canMoveLeft()
    {
        return left.position.x > 0 && !collidesWithFixedBlock(Direction.LEFT);
    }

    private boolean canMoveDown()
    {
        return (left.position.y + 1 < GamePanel.getInstance().getGrid().getRows()
                && right.position.y + 1 < GamePanel.getInstance().getGrid().getRows()
                && top.position.y + 1 < GamePanel.getInstance().getGrid().getRows()
                && bottom.position.y + 1 < GamePanel.getInstance().getGrid().getRows()) && !collidesWithFixedBlock(Direction.DOWN);
    }
}


