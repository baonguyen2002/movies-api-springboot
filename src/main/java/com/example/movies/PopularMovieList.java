package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "popular-movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularMovieList {
    @Id
    private ObjectId id;
    private int page;
    private int total_pages;
    //@DocumentReference
    private List<PopularMovie> results;
}
