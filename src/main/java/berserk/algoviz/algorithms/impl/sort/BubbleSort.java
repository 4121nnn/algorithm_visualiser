package berserk.algoviz.algorithms.impl.sort;

import berserk.algoviz.algorithms.Algorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class BubbleSort implements Algorithm {

    @Override
    public List<int[]> start(List<Integer> nums) {
        if(nums == null) {
            System.out.println("Array is not initialized.");
            return Collections.emptyList();
        }
        System.out.println("Starting Bubble sort ... ");
        List<int[]> steps = new ArrayList<>();
        for(int i = 0; i < nums.size(); i++){
            for(int j = 1; j < nums.size(); j++){
                int[] step = new int[]{j - 1, j, 0};
                if(nums.get(j-1) >  nums.get(j)){
                    swap(nums,j-1, j);
                    step[2] = 1;
                }
                steps.add(step);
            }
        }
        return steps;
    }

    private void swap(List<Integer> nums, int i, int j){
       int temp = nums.get(i);
       nums.set(i , nums.get(j));
       nums.set(j, temp);
    }


}
