package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.LanguagePerformance;
import berserk.algoviz.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static berserk.algoviz.enums.LanguageType.JAVA;
import static berserk.algoviz.enums.SortType.INSERTION_SORT;

public class InsertionSort implements Sortable {

    @Override
    public AlgoResult sort(int[] nums) {
        int n = nums.length;
        int[] copy1 = nums.clone();
        int[] copy2 = nums.clone();
        List<Step> steps = new ArrayList<>();
        List<LanguagePerformance>  languagePerformances = new ArrayList<>();

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
                copy2[j + 1] = copy2[j];
                steps.add(new Step(j + 1, j, true));
                j--;
            }
            copy2[j + 1] = key;
        }
        List<LanguagePerformance> performances = new ArrayList<>();
        performances.add(new LanguagePerformance(JAVA, timing));

        return new AlgoResult(INSERTION_SORT,steps, performances, nums);
    }
}
