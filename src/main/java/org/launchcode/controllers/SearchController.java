package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> results = new ArrayList<>();

        if(!searchType.equals("all")) {
            results = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        else {
            results = JobData.findByValue(searchTerm);
        }

        model.addAttribute("results", results);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }

}
