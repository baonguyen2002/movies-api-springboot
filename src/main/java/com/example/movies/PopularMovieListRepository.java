package com.example.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularMovieListRepository extends MongoRepository<PopularMovieList, ObjectId> {
    PopularMovieList findByPage(int page);
}
