package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.LanguagePerformance;
import berserk.algoviz.model.Step;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static berserk.algoviz.enums.LanguageType.JAVA;
import static berserk.algoviz.enums.SortType.BUBBLE_SORT;

@Component
@NoArgsConstructor
public class BubbleSort implements Sortable {

    @Override
    public AlgoResult sort(int[] nums) {
        List<Step> steps = new ArrayList<>();
        List<LanguagePerformance>  languagePerformances = new ArrayList<>();

        int[] copyArray1 = nums.clone();
        int[] copyArray2 = nums.clone();
        int n = nums.length;
        boolean swapped;

        long startTime = System.nanoTime();
        for(int i = 0; i < n; i++){
            swapped = false;
            for(int j = 1; j < n; j++){
                if(copyArray1[j-1] >  copyArray1[j]){
                    swap(copyArray1,j-1, j);
                    swapped = true;
                }
            }
            if(!swapped) break;
        }
        long endTime = System.nanoTime();

        double milliSeconds = (endTime - startTime) / 1_000_000.0;

        for(int i = 0; i < n; i++){
            swapped = false;
            for(int j = 1; j < n; j++){
                boolean swappedForData = false;
                if(copyArray2[j-1] >  copyArray2[j]){
                    swap(copyArray2,j-1, j);
                    swapped = true;
                    swappedForData = true;
                }
                steps.add(new Step(j - 1, j, swappedForData));
            }
            if(!swapped) break;
        }
        languagePerformances.add(new LanguagePerformance(JAVA, milliSeconds));

        return new AlgoResult(BUBBLE_SORT, steps, languagePerformances, nums);
    }

    private void swap(int[] nums, int i, int j){
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }


}
