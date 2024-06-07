package com.example.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDetailRepository extends MongoRepository<MovieDetail, ObjectId> {
    MovieDetail findByTmdbId(int tmdbId);
    List<MovieDetail> findByGenres(List<Integer> genreIds);
}