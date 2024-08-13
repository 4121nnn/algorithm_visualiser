package berserk.algoviz.controller;

import berserk.algoviz.algorithms.Algorithm;
import berserk.algoviz.algorithms.impl.sort.BubbleSort;
import berserk.algoviz.algorithms.utils.AlgoUtils;
import berserk.algoviz.controller.payload.AlgoPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AlgoController {

    @GetMapping()
    public String getSortingSteps(Model model){
        Algorithm algorithm = new BubbleSort();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            list.add(i * 4 + 50);
        }
        Collections.shuffle(list);
        List<int[]> steps =  algorithm.start(new ArrayList<>(list));
        model.addAttribute("steps", steps);
        model.addAttribute("nums", list);
        return "sort";
    }



}
