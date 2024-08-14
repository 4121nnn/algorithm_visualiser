package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.LanguagePerformance;
import berserk.algoviz.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static berserk.algoviz.enums.LanguageType.JAVA;
import static berserk.algoviz.enums.SortType.SELECTION_SORT;

public class SelectionSort implements Sortable {

    @Override
    public AlgoResult sort(int[] nums) {
        List<Step> steps = new ArrayList<>();
        List<LanguagePerformance>  languagePerformances = new ArrayList<>();

        int[] copyArray1 = nums.clone();
        int[] copyArray2 = nums.clone();

        long startTime = System.nanoTime();
        for(int i = 0; i < copyArray1.length - 1; i++){
            int minIndex = i;
            for(int j = i+1; j < copyArray1.length; j++){
                if(copyArray1[j] < copyArray1[minIndex]){
                    minIndex = j;
                }
            }
            swap(copyArray1, minIndex, i);
        }
        long endTime = System.nanoTime();

        double milliSeconds = (endTime - startTime) / 1_000_000.0;

        for(int i = 0; i < copyArray2.length - 1; i++){
            int minIndex = i;
            boolean isSwapped = false;
            for(int j = i + 1; j < copyArray2.length; j++){

                if(copyArray2[j] < copyArray2[minIndex]){
                    minIndex = j;
                    isSwapped = true;
                }
                if(j == copyArray2.length - 1){
                    steps.add(new Step(minIndex, i, true));
                }else{
                    steps.add(new Step(minIndex, j, false));
                }
            }
            swap(copyArray2, i, minIndex);
        }
        languagePerformances.add(new LanguagePerformance(JAVA, milliSeconds));
        return new AlgoResult(SELECTION_SORT, steps, languagePerformances, nums);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
