package com.qwerty.tetriz;

import android.graphics.Point;

public abstract class FourTypeRotationBlock extends Block implements IRotate
{
    protected enum Rotation
    {
        DOWN,
        UP,
        LEFT,
        RIGHT,
    }

    protected Rotation rotation;

    public FourTypeRotationBlock(Point position, int color)
    {
        super(position, color);
    }

    @Override
    public void updatePos(Point point)
    {
        position = point;
        switch (rotation)
        {
            case UP:
                updateUpRotation(point);
                break;
            case RIGHT:
                updateRightRotation(point);
                break;
            case DOWN:
                updateDownRotation(point);
                break;
            case LEFT:
                updateLeftRotation(point);
                break;
        }
    }

    @Override
    public void rotate()
    {
        switch (rotation)
        {
            case UP:
                this.rotation = Rotation.RIGHT;
                updateRightRotation(this.position);
                break;
            case RIGHT:
                this.rotation = Rotation.DOWN;
                updateDownRotation(this.position);
                break;
            case DOWN:
                this.rotation = Rotation.LEFT;
                updateLeftRotation(this.position);
                break;
            case LEFT:
                this.rotation = Rotation.UP;
                updateUpRotation(this.position);
                break;
        }
        checkPositionAfterRotation();

    }

    @Override
    public void unrotate()
    {
        switch (rotation)
        {
            case UP:
                this.rotation = Rotation.LEFT;
                updateLeftRotation(this.position);
                break;
            case RIGHT:
                this.rotation = Rotation.UP;
                updateUpRotation(this.position);
                break;
            case DOWN:
                this.rotation = Rotation.RIGHT;
                updateRightRotation(this.position);
                break;
            case LEFT:
                this.rotation = Rotation.DOWN;
                updateDownRotation(this.position);
                break;
        }
    }

    protected abstract void updateUpRotation(Point point);

    protected abstract void updateRightRotation(Point point);

    protected abstract void updateDownRotation(Point point);

    protected abstract void updateLeftRotation(Point point);
}
