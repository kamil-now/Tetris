package com.qwerty.tetriz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;


public abstract class Block implements IRotate
{
    protected Point  position;
    protected Square top;
    protected Square bottom;
    protected Square right;
    protected Square left;
    protected Paint  paint;


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
    @Override
    public void unrotate(){}
    @Override
    public void rotate()
    {
        if (!isOnGrid(GamePanel.getInstance().getGrid()))
        {
            checkPositionAfterRotation();
        }
        if (collidesWithFixedBlock(Direction.NONE))
        {
            unrotate();
        }
    }
    public abstract void updatePos(Point point);

    public void draw(Canvas canvas)
    {
        top.draw(canvas);
        bottom.draw(canvas);
        left.draw(canvas);
        right.draw(canvas);
    }

    public void moveDown()
    {
        updatePos(new Point(position.x, position.y + 1));
    }

    public void moveRight()
    {
        updatePos(new Point(position.x + 1, position.y));
    }

    public void moveLeft()
    {
        updatePos(new Point(position.x - 1, position.y));
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
        List<Square>        tempList = GamePanel.getInstance().getGrid().getFixedBlocks();
        BlockPositionHolder temp     = new BlockPositionHolder(this, direction);
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

    protected void checkPositionAfterRotation()
    {
        Grid grid = GamePanel.getInstance().getGrid();
        if (!left.isOnGrid(grid))
        {
            tryCorrectToRight(grid);
        }
        else if (!right.isOnGrid(grid))
        {
            tryCorrectToLeft(grid);
        }
    }

    private void tryCorrectToRight(Grid grid)
    {
        int i = 0;
        do
        {
            this.moveRight();
            i++;
        }
        while (!left.isOnGrid(grid));

        if (this.collidesWithFixedBlock(Direction.NONE))
        {
            for (; i > 0; --i)
            {
                this.moveLeft();
            }
        }
    }

    private void tryCorrectToLeft(Grid grid)
    {
        int i = 0;
        do
        {
            this.moveLeft();
            i++;
        }
        while (!right.isOnGrid(grid));

        if (this.collidesWithFixedBlock(Direction.NONE))
        {
            for (; i > 0; --i)
            {
                this.moveRight();
            }
        }
    }

    public boolean canMoveRight()
    {
        return right.position.x + 1 < GamePanel.getInstance().getGrid().getColumns() && !collidesWithFixedBlock(Direction.RIGHT);
    }

    public boolean canMoveLeft()
    {
        return left.position.x > 0 && !collidesWithFixedBlock(Direction.LEFT);
    }

    public boolean canMoveDown()
    {
        return (left.position.y + 1 < GamePanel.getInstance().getGrid().getRows()
                && right.position.y + 1 < GamePanel.getInstance().getGrid().getRows()
                && top.position.y + 1 < GamePanel.getInstance().getGrid().getRows()
                && bottom.position.y + 1 < GamePanel.getInstance().getGrid().getRows()) && !collidesWithFixedBlock(Direction.DOWN);
    }
}


