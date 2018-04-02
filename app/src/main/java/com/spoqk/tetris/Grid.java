package com.spoqk.tetris;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Grid
{

    private static int leftMargin;
    private static int topMargin;
    private static int columns;
    private static int rows;
    private static int spacing;
    private Paint gridPaint;
    private static List<Square> fixedBlocks;

    public static List<Square> getFixedBlocks()
    {
        return fixedBlocks;
    }

    public static int getColumns()
    {
        return columns;
    }

    public static int getRows()
    {
        return rows;
    }

    public static int getSpacing()
    {
        return spacing;
    }

    public static int getLeftMargin()
    {
        return leftMargin;
    }

    public static int getTopMargin()
    {
        return topMargin;
    }

    public Grid(int rows, int columns, int color)
    {
        this.rows = rows;
        this.columns = columns;
        gridPaint = new Paint();

        gridPaint.setColor(color);
        gridPaint.setStrokeWidth(3);

        fixedBlocks = new ArrayList();
    }


    public void draw(Canvas canvas)
    {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        spacing = (Math.min(width, height) / columns) * 1;

        //leftMargin = (int)(width*0.2);
        topMargin = (int)(height*0.1);
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);
        drawFixedBlocks(canvas);
    }

    public void addToFixedBlocks(Block block)
    {
        fixedBlocks.add(block.top);
        fixedBlocks.add(block.bottom);
        fixedBlocks.add(block.right);
        fixedBlocks.add(block.left);

        checkForFullRow();
    }

    private void drawFixedBlocks(Canvas canvas)
    {
        for (Square sqr : fixedBlocks)
        {
            sqr.draw(canvas);
        }
    }

    private void checkForFullRow()
    {
        ArrayList rowsToDelete = new ArrayList();

        for (int i = rows - 1; i > 0; --i)
        {
            int count = 0;
            for (Square sqr : fixedBlocks)
            {
                if (sqr.position.y == i)
                {
                    count++;
                    if (count == columns)
                    {
                        rowsToDelete.add(i);
                        break;
                    }
                }
            }
        }
        if (!rowsToDelete.isEmpty())
            deleteRows(rowsToDelete);
    }

    private void deleteRows(ArrayList rows)
    {
        List<Square> blocksToRemove = new ArrayList();
        List<Square> blocksToMove = new ArrayList();
        for (int i = 0; i < rows.size(); i++)
        {

            for (int j = 0; j < fixedBlocks.size(); j++)
            {
                Square item = fixedBlocks.get(j);
                if (item.position.y == (int)rows.get(i))
                {
                    blocksToRemove.add(item);
                }
                else if (item.position.y < (int)rows.get(i))
                {
                    blocksToMove.add(item);
                }
            }
        }
        for (Square item : blocksToMove)
        {
            item.updatePos(new Point(item.position.x, item.position.y + 1));
        }
        for(Square item : blocksToRemove)
        {
            fixedBlocks.remove(item);
        }
    }

    private void drawVerticalLines(Canvas canvas)
    {
        float startX, stopX, startY, stopY;
        for (int i = 0; i <= columns; i++)
        {
            startX = leftMargin + i * spacing;
            startY = topMargin;

            stopX = startX;
            stopY = startY + spacing * rows;

            canvas.drawLine(startX, startY, stopX, stopY, gridPaint);
        }
    }

    private void drawHorizontalLines(Canvas canvas)
    {
        float startX, stopX, startY, stopY;
        for (int i = 0; i <= rows; i++)
        {
            startX = leftMargin;
            startY = topMargin + i * spacing;

            stopX = startX + spacing * columns;
            stopY = startY;

            canvas.drawLine(startX, startY, stopX, stopY, gridPaint);
        }
    }
}
