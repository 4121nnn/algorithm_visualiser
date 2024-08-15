package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.Move;

import java.util.ArrayList;
import java.util.List;

import static berserk.algoviz.enums.SortType.INSERTION_SORT;

public class InsertionSort implements Sortable {

    @Override
    public AlgoResult sort(int[] nums) {
        List<Move> moves = new ArrayList<>();
        int n = nums.length;
        int[] copy1 = nums.clone();
        int[] copy2 = nums.clone();


        long start = System.nanoTime();
        for(int i = 1; i < n; i++){
            int key = copy1[i];
            int j = i - 1;
            while(j >= 0 && copy1[j] > key){
                copy1[j + 1] = copy1[j];
                j--;
            }
            copy1[j+1] = key;
        }
        long end = System.nanoTime();

        double timing = (end - start) / 1_000_000.0;

        for(int i = 1; i < n; i++){
            int key = copy2[i];
            int j = i - 1;
            while(j >= 0 && copy2[j] > key){
                moves.add(new Move(j + 1, copy2[j]));
                copy2[j + 1] = copy2[j];
                j--;
            }
            moves.add(new Move(j + 1, key));
            copy2[j + 1] = key;
        }

        return new AlgoResult(INSERTION_SORT, moves, nums, timing);
    }
}
