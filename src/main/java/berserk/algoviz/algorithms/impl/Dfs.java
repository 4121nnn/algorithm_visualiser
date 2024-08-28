package berserk.algoviz.algorithms.impl;

import berserk.algoviz.algorithms.PathFind;
import berserk.algoviz.model.PathFindResult;
import berserk.algoviz.model.Point;

import java.util.*;

import static berserk.algoviz.enums.PathFindType.BFS;
import static berserk.algoviz.enums.PathFindType.DFS;

public class Dfs implements PathFind {

    private List<int[]> moves;
    int rowLength;
    int colLength ;
    boolean[][] visited;
    Point foundPath;

    @Override
    public PathFindResult find(int[][] matrix){
        moves = new ArrayList<>();
        rowLength = matrix.length;
        colLength = matrix[0].length;
        visited = new boolean[rowLength][colLength];
        List<int[]> shortestPath = new ArrayList<>();
        dfs(matrix, new Point(0,0,null));
        while(foundPath != null){
            shortestPath.add(new int[]{foundPath.row(), foundPath.col()});
            foundPath = foundPath.prev();
        }
        Collections.reverse(shortestPath);

        return new PathFindResult(DFS, moves, shortestPath, matrix);
    }

    private void dfs(int[][] matrix, Point cur){
        if(foundPath != null) return;
        moves.add(new int[]{0, 0});
        visited[0][0] = true;

        for(int[] direction : directions){
            int newRow = cur.row() - direction[0];
            int newCol = cur.col() - direction[1];

            if(newRow >= 0 && newRow < rowLength && newCol >= 0 && newCol < colLength &&
                    matrix[newRow][newCol] != 1 && !visited[newRow][newCol]){

                moves.add(new int[]{newRow, newCol});

                if(matrix[newRow][newCol] == 2){
                    foundPath =  new Point(newRow, newCol, cur);
                }
                visited[newRow][newCol] = true;
                dfs(matrix, new Point(newRow, newCol, cur));
            }
        }
    }
}
