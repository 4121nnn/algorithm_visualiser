package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static berserk.algoviz.enums.SortType.QUICK_SORT;

public class QuickSort implements Sortable {

    private List<Move> moves;
    @Override
    public AlgoResult sort(int[] nums) {
        moves = new ArrayList<>();
        int[] copy1 = nums.clone();
        int[] copy2 = nums.clone();
        int n = nums.length;

        long start = System.nanoTime();
        quickSort(copy1, 0, n - 1);
        long end = System.nanoTime();

        double timing = (end - start) / 1_000_000.0;

        quickSortWithSteps(copy2, 0 , n - 1);

        return new AlgoResult(QUICK_SORT, moves, nums, timing);
    }

    private void quickSort(int[] nums, int left, int right){
        if(left < right){
            int partitionInd = partition(nums, left, right);

            quickSort(nums, left, partitionInd - 1);
            quickSort(nums, partitionInd + 1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivotInd = (right + left) / 2;
        if(nums[left] < nums[right] && nums[right] < nums[pivotInd]){
            pivotInd = right;
        }else if(nums[pivotInd] < nums[left] && nums[left] < nums[right]){
            pivotInd = left;
        }
        int temp = nums[pivotInd];
        nums[pivotInd] = nums[right];
        nums[right] = temp;
        int pivot = nums[right];
        int i = left;

        for(int j = left; j < right; j++){
            if(nums[j] < pivot){
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }

        nums[right] = nums[i];
        nums[i] = pivot;

        return i;
    }

    private void quickSortWithSteps(int[] nums, int left, int right){
        if(left < right){
            int partitionInd = partitionWithSteps(nums, left, right);

            quickSortWithSteps(nums, left, partitionInd - 1);
            quickSortWithSteps(nums, partitionInd + 1, right);
        }
    }

    private int partitionWithSteps(int[] nums, int left, int right) {
        int pivotInd = (right + left) / 2;
        if(nums[left] < nums[right] && nums[right] < nums[pivotInd]){
            pivotInd = right;
        }else if(nums[pivotInd] < nums[left] && nums[left] < nums[right]){
            pivotInd = left;
        }
        moves.add(new Move(pivotInd, nums[right]));
        moves.add(new Move(right, nums[pivotInd]));
        int temp = nums[pivotInd];
        nums[pivotInd] = nums[right];
        nums[right] = temp;
        int pivot = nums[right];
        int i = left;

        for(int j = left; j < right; j++){
            if(nums[j] < pivot){
                moves.add(new Move(i, nums[j]));
                moves.add(new Move(j, nums[i]));
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }

        moves.add(new Move(right, nums[i]));
        moves.add(new Move(i, pivot));
        nums[right] = nums[i];
        nums[i] = pivot;

        return i;
    }

}
