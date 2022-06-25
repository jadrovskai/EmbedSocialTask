package com.example.embedsocialtask.service.impl;

import com.example.embedsocialtask.model.Review;
import com.example.embedsocialtask.repository.ReviewRepository;
import com.example.embedsocialtask.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    @Override
    public List<Review> filterByRating(Boolean prioritize, Boolean highestFirst, Boolean newestFirst, Integer minRating) {

        List<Review> allReviews = reviewRepository.getAllReviews();
        List<Review> minRatingFilter = allReviews.stream()
                .filter(review -> review.getRating() >= minRating)
                .collect(Collectors.toList());

        ResultList resultList = orderByText(prioritize, minRatingFilter);

        List<Review> topList = orderByRatingAndDate(highestFirst, newestFirst, resultList.getFirstOrderedList());
        List<Review> bottomList = orderByRatingAndDate(highestFirst, newestFirst, resultList.getSecondOrderedList());

        topList.addAll(bottomList);

        return topList;
    }

    private ResultList orderByText(Boolean prioritize, List<Review> orderedReviews) {
        List<Review> reviewsWithText = orderedReviews.stream()
                .filter(review -> !review.getReviewText().isEmpty())
                        .collect(Collectors.toList());

        List<Review> reviewsWithNoTexts = orderedReviews.stream()
                .filter(review -> review.getReviewText().isEmpty())
                .collect(Collectors.toList());

        if (prioritize) {
            return new ResultList(reviewsWithText, reviewsWithNoTexts);
        } else {
            return new ResultList(reviewsWithNoTexts, reviewsWithText);
        }
    }

    
    private List<Review> orderByRatingAndDate(Boolean highestFirst, Boolean newestFirst, List<Review> orderedReviews) {
        return orderedReviews.stream()
                .sorted(Comparator.comparing(Review::getRating, highestFirst ? Comparator.reverseOrder() : Comparator.naturalOrder())
                        .thenComparing(Review::getReviewCreatedOnDate, newestFirst ? Comparator.reverseOrder() : Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    private static final class ResultList {
        private final List<Review> firstOrderedList;
        private final List<Review> secondOrderedList;

        public ResultList(List<Review> firstOrderedList, List<Review> secondOrderedList) {
            this.firstOrderedList = firstOrderedList;
            this.secondOrderedList = secondOrderedList;
        }

        public List<Review> getFirstOrderedList() {
            return firstOrderedList;
        }

        public List<Review> getSecondOrderedList() {
            return secondOrderedList;
        }
    }
}
