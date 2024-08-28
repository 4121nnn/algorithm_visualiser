package berserk.algoviz.algorithms.impl;

import berserk.algoviz.algorithms.PathFind;
import berserk.algoviz.model.PathFindResult;

import java.util.*;

import static berserk.algoviz.enums.PathFindType.A_STAR;

public class AStar implements PathFind {

    private List<int[]> moves;

    @Override
    public PathFindResult find(int[][] matrix) {
        moves = new ArrayList<>();
        List<int[]> path = new ArrayList<>();
        AStarCell result = aStarAlgorithm(matrix);

        while(result != null){
            path.add(new int[]{result.row, result.col});
            result = result.parent;
        }
        Collections.reverse(path);

        return new PathFindResult(A_STAR, moves, path, matrix);
    }

    private AStarCell aStarAlgorithm(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        AStarCell start = new AStarCell(0, 0);
        AStarCell end = new AStarCell(m - 1, n - 1);
        PriorityQueue<AStarCell> q = new PriorityQueue<>((a, b) -> a.total - b.total);
        q.offer(start);
        visited[0][0] = true;

        // Map to store the best known cost to reach each cell
        Map<AStarCell, Integer> bestCosts = new HashMap<>();
        bestCosts.put(start, 0);

        while (!q.isEmpty()) {
            AStarCell cur = q.poll();
            moves.add(new int[]{cur.row, cur.col});

            if (cur.row == end.row && cur.col == end.col) {
                return cur;
            }

            for (int[] direction : directions) {
                int newRow = cur.row + direction[0];
                int newCol = cur.col + direction[1];
                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n &&
                        matrix[newRow][newCol] != 1 && !visited[newRow][newCol]) {

                    visited[newRow][newCol] = true;
                    AStarCell neighbor = new AStarCell(newRow, newCol);
                    neighbor.lengthFromStart = cur.lengthFromStart + 1;
                    neighbor.heuristic = calculateHeuristic(neighbor, end);
                    neighbor.total = neighbor.lengthFromStart + neighbor.heuristic;
                    neighbor.parent = cur;

                    if (!bestCosts.containsKey(neighbor) || neighbor.lengthFromStart < bestCosts.get(neighbor)) {

                        if(bestCosts.containsKey(neighbor)){
                            visited[newRow][newCol] = false;
                        }
                        bestCosts.put(neighbor, neighbor.lengthFromStart);
                        q.offer(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private int calculateHeuristic(AStarCell cur, AStarCell end) {
        // manhattan distance
        return Math.abs(cur.row - end.row) + Math.abs(cur.col - end.col);
    }

}
class AStarCell{
    int row;
    int col;
    int heuristic;
    int lengthFromStart;
    int total;
    AStarCell parent;
    public AStarCell(int row, int col){
        this.row = row;
        this.col = col;
        this.heuristic = 0;
        this.lengthFromStart = 0;
        this.total = 0;
        this.parent = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AStarCell aStarCell = (AStarCell) o;
        return row == aStarCell.row && col == aStarCell.col && Objects.equals(parent, aStarCell.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
