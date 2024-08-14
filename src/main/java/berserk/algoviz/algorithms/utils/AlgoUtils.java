package berserk.algoviz.algorithms.utils;

import java.util.concurrent.ThreadLocalRandom;


public class AlgoUtils {



    public static int[] generateRandomIntegerArray(int length, int lowerBound, int upperBound){
        int[] res = new int[length];
        for(int i = 0; i < length; i++){
            res[i] = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);
        }
        return res;
    }

}
