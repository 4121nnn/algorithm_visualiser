package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.Move;

import java.util.ArrayList;
import java.util.List;

import static berserk.algoviz.enums.SortType.SELECTION_SORT;

public class SelectionSort implements Sortable {

    @Override
    public AlgoResult sort(int[] nums) {
        List<Move> moves = new ArrayList<>();

        int[] copy1 = nums.clone();
        int[] copy2 = nums.clone();
        int n = nums.length;

        long start = System.nanoTime();
        for(int i = 0; i < copy1.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < copy1.length; j++){
                if(copy1[j] < copy1[minIndex]){
                    minIndex = j;
                }
            }
            swap(copy1, i, minIndex);
        }
        long end = System.nanoTime();

        double timing = (end - start) / 1_000_000.0;

        for(int i = 0; i < copy2.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < copy2.length; j++){
                if(copy2[j] < copy2[minIndex]){
                    minIndex = j;
                }
                moves.add(new Move(j, copy2[j]));
            }
            moves.add(new Move(i, copy2[minIndex]));
            moves.add(new Move(minIndex, copy2[i]));
            swap(copy2, i, minIndex);
        }

        return new AlgoResult(SELECTION_SORT, moves, nums, timing);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
