package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service
public class MovieDetailService {
    @Autowired
    private MovieDetailRepository movieDetailRepository;


    @Autowired
    private MongoTemplate mongoTemplate;


    public MovieDetail getMovieDetail(int tmdbId){
        return movieDetailRepository.findByTmdbId(tmdbId);
    }

//    public List<MovieDetail> getMovieDetailsByGenreIds(List<Integer> genreIds) {
//        return movieDetailRepository.findByGenresIdIn(genreIds);
//    }

    //OR conditional
    public List<MovieDetail> getMovieDetailsByGenreIds(List<Integer> genreIds) {
        Criteria criteria = Criteria.where("genres.id").in(genreIds);
        Query query = new org.springframework.data.mongodb.core.query.Query(criteria);

        return mongoTemplate.find(query, MovieDetail.class);
    }

    //AND conditional
//    public List<MovieDetail> getMovieDetailsByGenreIds(List<Integer> genreIds) {
//        Criteria criteria = new Criteria();
//        for (Integer genreId : genreIds) {
//            criteria = criteria.and("genres.id").is(genreId);
//        }
//        Query query = new Query(criteria);
//
//        return mongoTemplate.find(query, MovieDetail.class);
//    }

    public List<MovieDetail> searchMovieDetailsByTitle(String titleQuery) {
        Criteria criteria = Criteria.where("title").regex(titleQuery, "i");
        Query query = new Query(criteria);

        return mongoTemplate.find(query, MovieDetail.class);
    }
}
