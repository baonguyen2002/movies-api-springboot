package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document (collection = "movie-detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PopularMovie {
    @Id
    private ObjectId id;
    private int tmdb_id;
    private String poster_path;
    private String release_date;
    private String title;
    private double vote_average;
}
