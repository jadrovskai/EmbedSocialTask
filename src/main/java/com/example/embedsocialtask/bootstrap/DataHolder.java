package com.example.embedsocialtask.bootstrap;

import com.example.embedsocialtask.model.Review;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DataHolder {

    public static List<Review> REVIEWS = new ArrayList<>();

    @PostConstruct
    public void init(){
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            //read json file and convert to review list
            REVIEWS = objectMapper.readValue(
                    new ClassPathResource("data/reviews.json").getFile(),
                    new TypeReference<List<Review>>(){}
            );
            log.info("Json file successfully read!");
        } catch (IOException e) {
            log.error("Cannot read json file!", e);
        }
    }

}
