package berserk.algoviz.controller;

import berserk.algoviz.enums.PathFindType;
import berserk.algoviz.model.PathFindResult;
import berserk.algoviz.service.PathFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static berserk.algoviz.enums.PathFindType.DIJKSTRA;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AlgoController {

    private final PathFindService pathFindService;


    @GetMapping
    public String getIndex(Model model){
        PathFindResult result = pathFindService.getByType(DIJKSTRA);
        model.addAttribute("result" , result);
        return "index";
    }

    @GetMapping("path-find")
    public String getPathFindSteps(@RequestParam("name") PathFindType name, Model model){
        PathFindResult result = pathFindService.getByType(name);
        model.addAttribute("result" , result);
        return "path-find";
    }
}