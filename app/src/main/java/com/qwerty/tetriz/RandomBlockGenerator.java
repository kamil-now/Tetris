package com.qwerty.tetriz;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

public final class RandomBlockGenerator
{
    public enum BlockType
    {
        T,
        I,
        L,
        S,
        Z,
        J,
        O
    }

    private static BlockType[] blockTypes;
    private static Random rand;
    private static ArrayList lastTypes;
    private static int startX;
    private static int startY;
    public RandomBlockGenerator(int x, int y)
    {
        startX=x;
        startY=y;
        blockTypes = BlockType.values();
        rand = new Random();
        lastTypes = new ArrayList();
    }

    public static Block getRandom()
    {
        BlockType temp = blockTypes[rand.nextInt(blockTypes.length)];
        if (lastTypes.size() >= 3)
        {
            while (lastTypes.contains(temp))
                temp = blockTypes[rand.nextInt(blockTypes.length)];
            lastTypes.remove(0);
            lastTypes.add(temp);
        } else
        {
            temp = blockTypes[rand.nextInt(blockTypes.length)];
            lastTypes.add(temp);
        }


        switch (temp)
        {
            case T:
                return new T(new Point(startX, startY));
            case I:
                return new I(new Point(startX, startY));
            case L:
                return new L(new Point(startX, startY));
            case S:
                return new S(new Point(startX, startY));
            case Z:
                return new Z(new Point(startX, startY));
            case J:
                return new J(new Point(startX, startY));
            case O:
                return new O(new Point(startX, startY));
        }
        return null;
    }
}
