package berserk.algoviz.algorithms.impl;

import berserk.algoviz.algorithms.PathFind;
import berserk.algoviz.model.Cell;
import berserk.algoviz.model.PathFindResult;

import java.util.*;

import static berserk.algoviz.enums.PathFindType.BFS;
import static berserk.algoviz.enums.PathFindType.DIJKSTRA;

public class Dijkstra implements PathFind {
    private List<int[]> moves;
    @Override
    public PathFindResult find(int[][] matrix) {
        moves = new ArrayList<>();

        Cell cell = dijkstra(matrix);
        List<int[]> shortestPath = new ArrayList<>();

        while(cell != null){
            shortestPath.add(new int[]{cell.row(), cell.col()});
            cell = cell.prev();
        }
        Collections.reverse(shortestPath);

        return new PathFindResult(DIJKSTRA, moves, shortestPath, matrix);
    }

    private Cell dijkstra(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] dist = new int[row][col];
        for(int[] a : dist) Arrays.fill(a, Integer.MAX_VALUE);
        PriorityQueue<Cell> q = new PriorityQueue<>((a,b) -> a.dist() - b.dist());
        q.offer(new Cell(0, 0, 0, null));

        while(!q.isEmpty()){
            Cell cur = q.poll();
            if (visited[cur.row()][cur.col()]) continue;
            moves.add(new int[]{cur.row(), cur.col()});

            for(int[] d : directions){
                int nr = cur.row() + d[0];
                int nc = cur.col() + d[1];

                if(nr >= 0 && nc >= 0 && nr < row && nc < col && matrix[nr][nc] != 1){
                    if(cur.dist() + 1 < dist[nr][nc]) {
                        dist[nr][nc] = cur.dist() + 1;
                        Cell cell = new Cell(nr, nc, cur.dist() + 1, cur);
                        if (matrix[nr][nc] == 2) {
                            return cell;
                        }
                        q.add(cell);
                    }
                }
            }
        }
        return null;
    }
}
