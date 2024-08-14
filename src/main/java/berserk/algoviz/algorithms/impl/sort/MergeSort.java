package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.LanguagePerformance;
import berserk.algoviz.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static berserk.algoviz.enums.LanguageType.JAVA;
import static berserk.algoviz.enums.SortType.MERGE_SORT;

public class MergeSort implements Sortable {

    private List<Step> steps;

    @Override
    public AlgoResult sort(int[] nums) {
        List<AlgoResult> results = new ArrayList<>();
        steps = new ArrayList<>();
        int[] copy1 = nums.clone();
        int[] copy2 = nums.clone();
        int n = nums.length;

        long start = System.nanoTime();
        mergeSort(copy1, 0, n - 1);
        long end = System.nanoTime();

        double timing = (end - start) / 1_000_000.0;
        List<LanguagePerformance> performances = new ArrayList<>();
        performances.add(new LanguagePerformance(JAVA, timing));

        mergeSortWithSteps(copy2, 0, n - 1);
        System.out.println(Arrays.toString(nums));
       for(Step step : steps){
           System.out.println(step);
       }
        return new AlgoResult(MERGE_SORT, steps, performances, nums);
    }

    private void mergeSort(int[] arr, int left, int  right){
        if(left < right){
            int mid = (right + left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);

        }
    }

    private void merge(int[] arr, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] temp1 = new int[n1];
        int[] temp2 = new int[n2];

        System.arraycopy(arr, left, temp1, 0, n1);
        System.arraycopy(arr, mid + 1, temp2, 0, n2);

        int i = 0, j = 0, k = left;

        while(i < n1 && j < n2){
            if(temp1[i] < temp2[j]){
                arr[k++] = temp1[i++];
            }else{
                arr[k++] = temp2[j++];
            }
        }

        while(i < n1){
            arr[k++] = temp1[i++];
        }

        while(j < n2){
            arr[k++] = temp2[j++];
        }
    }

    private void mergeSortWithSteps(int[] arr, int left, int  right){
        if(left < right){
            int mid = (right + left) / 2;

            mergeSortWithSteps(arr, left, mid);
            mergeSortWithSteps(arr, mid + 1, right);

            mergeWithSteps(arr, left, mid, right);

        }
    }

    private void mergeWithSteps(int[] arr, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] temp1 = new int[n1];
        int[] temp2 = new int[n2];

        System.arraycopy(arr, left, temp1, 0, n1);
        System.arraycopy(arr, mid + 1, temp2, 0, n2);

        int i = 0, j = 0, k = left;

        while(i < n1 && j < n2){
            if(temp1[i] < temp2[j]){
                arr[k] = temp1[i];
                steps.add(new Step(k, left + i, true));
                i++;
            }else{
                arr[k] = temp2[j];
                steps.add(new Step(k, mid + j + 1, true));
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = temp1[i];
            steps.add(new Step(k, left + i, true));
            k++;
            i++;
        }

        while(j < n2){
            arr[k] = temp2[j];
            steps.add(new Step(k, mid + j + 1, true));
            j++;
            k++;
        }
    }

}
