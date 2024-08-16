package berserk.algoviz.model;

import berserk.algoviz.enums.PathFindType;

import java.util.List;

public record PathFindResult(PathFindType type, List<int[]> moves, List<int[]> shortestPath, int[][] matrix) {
}
