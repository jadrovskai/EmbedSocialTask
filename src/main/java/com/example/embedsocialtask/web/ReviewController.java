package com.example.embedsocialtask.web;

import com.example.embedsocialtask.model.Review;
import com.example.embedsocialtask.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/index")
    public String returnIndex(@RequestParam(required = false) Boolean prioritize,
                              @RequestParam(required = false) Boolean highestFirst,
                              @RequestParam(required = false) Boolean newestFirst,
                              @RequestParam(required = false) Integer minRating,
                              Model model) {

        if(prioritize == null && highestFirst == null && newestFirst == null && minRating == null){

            List<Review> reviews = reviewService.getAllReviews();
            model.addAttribute("reviews", reviews );
            return "index";
        }

        List<Review> reviews = reviewService.filterByRating(prioritize, highestFirst, newestFirst, minRating);

        model.addAttribute("reviews", reviews);
        return "index";
    }

}
