package com.example.embedsocialtask.repository;


import com.example.embedsocialtask.bootstrap.DataHolder;
import com.example.embedsocialtask.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryReviewRepository implements ReviewRepository{

    @Override
    public List<Review> getAllReviews() {
        return DataHolder.REVIEWS;
    }
}
