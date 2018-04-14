package com.spoqk.tetris;

import android.graphics.Point;

import com.spoqk.tetris.enums.BlockType;
import com.spoqk.tetris.models.I;
import com.spoqk.tetris.models.J;
import com.spoqk.tetris.models.L;
import com.spoqk.tetris.models.O;
import com.spoqk.tetris.models.S;
import com.spoqk.tetris.models.T;
import com.spoqk.tetris.models.Z;
import com.spoqk.tetris.models.base.Block;

import java.util.ArrayList;
import java.util.Random;

public final class RandomBlockGenerator
{
    private static BlockType[] blockTypes;
    private static Random rand;
    private static ArrayList lastTypes;
    private static Point startingPoint;

    public RandomBlockGenerator(Point startingPoint)
    {
        this.startingPoint = startingPoint;
        blockTypes = BlockType.values();
        rand = new Random();
        lastTypes = new ArrayList();
    }

    public static Block getRandom()
    {
        BlockType type = blockTypes[rand.nextInt(blockTypes.length)];
        if (lastTypes.size() >= 3)
        {
            while (lastTypes.contains(type))
                type = blockTypes[rand.nextInt(blockTypes.length)];
            lastTypes.remove(0);
            lastTypes.add(type);
        }
        else
        {
            type = blockTypes[rand.nextInt(blockTypes.length)];
            lastTypes.add(type);
        }
        return getNewBlock(type);
    }

    private static Block getNewBlock(BlockType type)
    {
        switch (type)
        {
            case T:
                return new T(startingPoint);
            case I:
                return new I(startingPoint);
            case L:
                return new L(startingPoint);
            case S:
                return new S(startingPoint);
            case Z:
                return new Z(startingPoint);
            case J:
                return new J(startingPoint);
            case O:
                return new O(startingPoint);
            default:
                return null;
        }
    }
}
