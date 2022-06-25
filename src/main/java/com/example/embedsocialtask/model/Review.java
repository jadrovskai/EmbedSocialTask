package com.example.embedsocialtask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;
    private String reviewId;
    private String reviewFullText;
    private String reviewText;
    private Integer numLikes;
    private Integer numComments;
    private Integer numShares;
    private Integer rating;
    private String reviewCreatedOn;
    private Instant reviewCreatedOnDate;
    private Long reviewCreatedOnTime;
    private Long reviewerId;
    private String reviewerUrl;
    private String reviewerName;
    private String reviewerEmail;
    private String sourceType;
    private Boolean isVerified;
    private String source;
    private String sourceName;
    private String sourceId;
    private List<String> tags = new ArrayList<>();
    private String href;
    private String logoHref;
    private List<String> photos = new ArrayList<>();


}
