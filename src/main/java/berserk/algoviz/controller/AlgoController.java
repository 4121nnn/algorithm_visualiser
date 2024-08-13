package berserk.algoviz.controller;

import berserk.algoviz.enums.SortType;
import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AlgoController {

    private final SortService sortService;


    @GetMapping
    public String getIndexPage(){
        return "sort";
    }

    @PostMapping("/sort")
    public String getSortingSteps(@RequestParam List<SortType> sortTypes, Model model){
        List<AlgoResult> results = sortService.getResults(sortTypes);
        model.addAttribute("results" , results);
        return "sort";
    }



}
