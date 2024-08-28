package berserk.algoviz.algorithms.impl;

import berserk.algoviz.algorithms.PathFind;
import berserk.algoviz.model.Cell;
import berserk.algoviz.model.PathFindResult;

import java.util.*;

import static berserk.algoviz.enums.PathFindType.BFS;

public class Bfs implements PathFind {

    private List<int[]> moves;

    @Override
    public PathFindResult find(int[][] matrix){
        moves = new ArrayList<>();
        List<int[]> shortestPath = new ArrayList<>();
        Cell cell = runBfs(matrix);
        while(cell != null){
            shortestPath.add(new int[]{cell.row(), cell.col()});
            cell = cell.prev();
        }
        Collections.reverse(shortestPath);

        return new PathFindResult(BFS, moves, shortestPath, matrix);
    }

    private Cell runBfs(int[][] matrix){
        Queue<Cell> q = new LinkedList<>();

        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];

        q.add(new Cell(0, 0 ,0, new Cell(0, 0,0, null)));
        moves.add(new int[]{0, 0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            Cell cur  = q.poll();

            for(int[] direction : directions){
                int newRow = cur.row() - direction[0];
                int newCol = cur.col() - direction[1];

                if(newRow >= 0 && newRow < rowLength && newCol >= 0 && newCol < colLength &&
                        matrix[newRow][newCol] != 1 && !visited[newRow][newCol]){

                    moves.add(new int[]{newRow, newCol});

                    if(matrix[newRow][newCol] == 2){
                        return new Cell(newRow, newCol, 0,  cur);
                    }
                    visited[newRow][newCol] = true;
                    q.add(new Cell(newRow, newCol, 0,  cur));
                }
            }
        }
        return null;
    }
}
