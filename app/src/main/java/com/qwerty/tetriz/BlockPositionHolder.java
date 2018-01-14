package com.qwerty.tetriz;

import android.graphics.Point;

public final class BlockPositionHolder
{
    public Square top;
    public Square bottom;
    public Square right;
    public Square left;

    //simulate movement in given direction (for collision detection)
    public BlockPositionHolder(Block block, Direction dir)
    {
        switch (dir)
        {
            case DOWN:
            {
                this.top = new Square(new Point(block.top.position.x, block.top.position.y + 1));
                this.bottom = new Square(new Point(block.bottom.position.x, block.bottom.position.y + 1));
                this.right = new Square(new Point(block.right.position.x, block.right.position.y + 1));
                this.left = new Square(new Point(block.left.position.x, block.left.position.y + 1));
                break;
            }
            case RIGHT:
            {
                this.top = new Square(new Point(block.top.position.x + 1, block.top.position.y));
                this.bottom = new Square(new Point(block.bottom.position.x + 1, block.bottom.position.y));
                this.right = new Square(new Point(block.right.position.x + 1, block.right.position.y));
                this.left = new Square(new Point(block.left.position.x + 1, block.left.position.y));
                break;
            }
            case LEFT:
            {
                this.top = new Square(new Point(block.top.position.x - 1, block.top.position.y));
                this.bottom = new Square(new Point(block.bottom.position.x - 1, block.bottom.position.y));
                this.right = new Square(new Point(block.right.position.x - 1, block.right.position.y));
                this.left = new Square(new Point(block.left.position.x - 1, block.left.position.y));
                break;
            }
            default:
                clone(block);
        }

    }

    public boolean collides(Square sqr)
    {
        return left.position.equals(sqr.position)
                || right.position.equals(sqr.position)
                || top.position.equals(sqr.position)
                || bottom.position.equals(sqr.position);
    }
    private void clone(Block block)
    {
        this.top = new Square(block.top.position);
        this.bottom = new Square(block.bottom.position);
        this.right = new Square(block.right.position);
        this.left = new Square(block.left.position);
    }
}
