package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopularMovieListService {
    @Autowired
    private PopularMovieListRepository popularMovieListRepository;

    public PopularMovieList getPopularMoviesByPage(int page) {
        return popularMovieListRepository.findByPage(page);
    }
}
