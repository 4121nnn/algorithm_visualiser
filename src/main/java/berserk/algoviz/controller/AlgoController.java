package berserk.algoviz.controller;

import berserk.algoviz.model.AlgoResult;
import berserk.algoviz.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AlgoController {

    private final SortService sortService;



    @GetMapping
    public String getSortingSteps(Model model){
        List<AlgoResult> results = sortService.getAllResults();
        model.addAttribute("results" , results);
        return "index";
    }



}
