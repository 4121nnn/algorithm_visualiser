package berserk.algoviz.service;

import berserk.algoviz.algorithms.PathFind;
import berserk.algoviz.algorithms.impl.pathfind.BfsPathFind;
import berserk.algoviz.model.PathFindResult;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class PathFindService {

    private Random random;
    private List<PathFind> pathFindList;

    @PostConstruct
    public void init(){
        random = new Random();
        pathFindList = new ArrayList<>();
        pathFindList.add(new BfsPathFind());
    }

    public List<PathFindResult> getAllResults() {
        int[][] matrix = generateMatrix(40, 40);
        List<PathFindResult> results = new ArrayList<>();
        for(PathFind pathFind: pathFindList){
            results.add(pathFind.find(matrix));
        }
        return results;
    }

    private int[][] generateMatrix(int row, int col){
        int[][] matrix = new int[row][col];
        matrix[row-1][col-1] = 2;
        int x = row * col / 4;
        for(int  i = 0; i < x; i++){
            int r = random.nextInt(row);
            int c = random.nextInt(col);
            matrix[r][c] = 1;
        }
        matrix[0][0] = 0;
        matrix[row-1][col-1] = 2;
        return matrix;
    }



}
