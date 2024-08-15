package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.model.Move;

import java.util.ArrayList;
import java.util.List;

import static berserk.algoviz.enums.SortType.MERGE_SORT;

public class MergeSort implements Sortable {

    private List<Move> moves;

    @Override
    public AlgoResult sort(int[] nums) {
        moves = new ArrayList<>();
        int[] copy1 = nums.clone();
        int[] copy2 = nums.clone();
        int n = nums.length;

        long start = System.nanoTime();
        mergeSort(copy1, 0, n - 1);
        long end = System.nanoTime();

        double timing = (end - start) / 1_000_000.0;


        mergeSortWithSteps(copy2, 0, n - 1);

        return new AlgoResult(MERGE_SORT, moves, nums, timing);
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

//        System.arraycopy(arr, left, temp1, 0, n1);
//        System.arraycopy(arr, mid + 1, temp2, 0, n2);
        for(int i = 0; i < n1; i++){
            temp1[i] = arr[left + i];
            moves.add(new Move(left + i, arr[left + i]));
        }
        for(int i = 0; i < n2; i++){
            temp2[i] = arr[mid + i + 1];
            moves.add(new Move(mid + i + 1, arr[mid + i + 1]));
        }


        int i = 0, j = 0, k = left;

        while(i < n1 && j < n2){
            if(temp1[i] < temp2[j]){
                arr[k] = temp1[i];
                moves.add(new Move(k, arr[k]));
                i++;
            }else{
                arr[k] = temp2[j];
                moves.add(new Move(k, arr[k]));
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = temp1[i];
            moves.add(new Move(k, arr[k]));
            k++;
            i++;
        }

        while(j < n2){
            arr[k] = temp2[j];
            moves.add(new Move(k, arr[k]));
            j++;
            k++;
        }
    }

}
