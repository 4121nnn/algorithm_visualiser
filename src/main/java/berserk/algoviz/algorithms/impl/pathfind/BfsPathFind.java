package berserk.algoviz.algorithms.impl.pathfind;

import berserk.algoviz.algorithms.PathFind;
import berserk.algoviz.model.PathFindResult;
import berserk.algoviz.model.Point;

import java.util.*;

import static berserk.algoviz.enums.PathFindType.BFS;

public class BfsPathFind implements PathFind {

    private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private List<int[]> moves;
    private List<int[]> shortestPath;

    @Override
    public PathFindResult find(int[][] matrix){
        moves = new ArrayList<>();
        shortestPath = new ArrayList<>();
        Point point = runBfs(matrix);
        while(point != null){
            shortestPath.add(new int[]{point.row(), point.col()});
            point = point.prev();
        }
        Collections.reverse(shortestPath);

        return new PathFindResult(BFS, moves, shortestPath, matrix);
    }

    private Point runBfs(int[][] matrix){
        Queue<Point> q = new LinkedList<>();

        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];

        q.add(new Point(0, 0 , new Point(0, 0, null)));
        moves.add(new int[]{0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            Point cur  = q.poll();


            for(int[] direction : directions){
                int newRow = cur.row() - direction[0];
                int newCol = cur.col() - direction[1];

                if(newRow >= 0 && newRow < rowLength && newCol >= 0 && newCol < colLength &&
                        matrix[newRow][newCol] != 1 && !visited[newRow][newCol]){

                    moves.add(new int[]{newRow, newCol});

                    if(matrix[newRow][newCol] == 2){
                        return new Point(newRow, newCol, cur);
                    }
                    visited[newRow][newCol] = true;
                    q.add(new Point(newRow, newCol, cur));
                }
            }
        }
        return null;
    }




}
