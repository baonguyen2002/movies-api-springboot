package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/popular")
public class PopularMovieListController {
    @Autowired
    private PopularMovieListService popularMovieListService;

    @GetMapping("/{page}")
    public ResponseEntity<PopularMovieList> getPopularMoviesByPage(@PathVariable int page) {
        return new ResponseEntity<>(popularMovieListService.getPopularMoviesByPage(page), HttpStatus.OK);
    }
}
