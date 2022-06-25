package com.example.embedsocialtask.service;


import com.example.embedsocialtask.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews();

    List<Review> filterByRating(Boolean prioritize, Boolean highestFirst, Boolean newestFirst, Integer minRating);
}
