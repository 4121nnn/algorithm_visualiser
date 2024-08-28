package berserk.algoviz.algorithms;

import berserk.algoviz.model.PathFindResult;

public interface PathFind {

    final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    PathFindResult find(int[][] matrix);
}
