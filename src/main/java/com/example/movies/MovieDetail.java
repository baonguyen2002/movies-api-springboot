package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "movie-detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetail {
    @Id
    private ObjectId id;
    @Field("tmdb_id")
    private int tmdbId;
    private List<MovieGenre> genres;
    private double vote_average;
    private int vote_count;
    private int runtime;
    private List<SpokenLanguage> spoken_languages;
    private String release_date;
    private String title;
    private String tagline;
    private String overview;
    private String poster_path;
    @DocumentReference
    private List<Comment> commentId;
}
