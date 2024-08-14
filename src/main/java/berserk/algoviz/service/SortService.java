package berserk.algoviz.service;

import berserk.algoviz.algorithms.Sortable;
import berserk.algoviz.algorithms.impl.sort.BubbleSort;
import berserk.algoviz.algorithms.impl.sort.InsertionSort;
import berserk.algoviz.algorithms.impl.sort.MergeSort;
import berserk.algoviz.algorithms.impl.sort.SelectionSort;
import berserk.algoviz.enums.SortType;
import berserk.algoviz.model.AlgoResult;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

import static berserk.algoviz.enums.SortType.*;

@Service
public class SortService {
    private Random random;
    private Map<SortType, Sortable> sortableList;

    @PostConstruct
    public void init(){
        random = new Random();
        sortableList = new HashMap<>();
        sortableList.put(BUBBLE_SORT, new BubbleSort());
        sortableList.put(SELECTION_SORT, new SelectionSort());
        sortableList.put(INSERTION_SORT, new InsertionSort());
        sortableList.put(MERGE_SORT, new MergeSort());
    }

    public List<AlgoResult> getAllResults() {
        int[] array = generateArray(100);
        List<AlgoResult> algoResults = new ArrayList<>();

        for(SortType sortType : sortableList.keySet()){
            algoResults.add(sortableList.get(sortType).sort(array.clone()));
        }
        return algoResults;
    }

    private int[] generateArray(int length){
        int[] array = new int[length];

        for(int i = 0; i < length; i++) array[i] = i + 1;

        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(array, i, j);
        }

        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



}
