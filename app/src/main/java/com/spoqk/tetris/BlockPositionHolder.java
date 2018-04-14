package com.spoqk.tetris;

import android.graphics.Point;

import com.spoqk.tetris.enums.Direction;
import com.spoqk.tetris.models.Square;
import com.spoqk.tetris.models.base.Block;

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
                this.top = new Square(new Point(block.top.getPosition().x, block.top.getPosition().y + 1));
                this.bottom = new Square(new Point(block.bottom.getPosition().x, block.bottom.getPosition().y + 1));
                this.right = new Square(new Point(block.right.getPosition().x, block.right.getPosition().y + 1));
                this.left = new Square(new Point(block.left.getPosition().x, block.left.getPosition().y + 1));
                break;
            }
            case RIGHT:
            {
                this.top = new Square(new Point(block.top.getPosition().x + 1, block.top.getPosition().y));
                this.bottom = new Square(new Point(block.bottom.getPosition().x + 1, block.bottom.getPosition().y));
                this.right = new Square(new Point(block.right.getPosition().x + 1, block.right.getPosition().y));
                this.left = new Square(new Point(block.left.getPosition().x + 1, block.left.getPosition().y));
                break;
            }
            case LEFT:
            {
                this.top = new Square(new Point(block.top.getPosition().x - 1, block.top.getPosition().y));
                this.bottom = new Square(new Point(block.bottom.getPosition().x - 1, block.bottom.getPosition().y));
                this.right = new Square(new Point(block.right.getPosition().x - 1, block.right.getPosition().y));
                this.left = new Square(new Point(block.left.getPosition().x - 1, block.left.getPosition().y));
                break;
            }
            default:
                clone(block);
        }

    }

    public boolean collides(Square sqr)
    {
        return left.getPosition().equals(sqr.getPosition())
                || right.getPosition().equals(sqr.getPosition())
                || top.getPosition().equals(sqr.getPosition())
                || bottom.getPosition().equals(sqr.getPosition());
    }
    private void clone(Block block)
    {
        this.top = new Square(block.top.getPosition());
        this.bottom = new Square(block.bottom.getPosition());
        this.right = new Square(block.right.getPosition());
        this.left = new Square(block.left.getPosition());
    }
}
