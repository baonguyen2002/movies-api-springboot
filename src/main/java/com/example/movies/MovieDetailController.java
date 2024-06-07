package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movie")
public class MovieDetailController {
    @Autowired
    private MovieDetailService movieDetailService;

    @GetMapping("/{tmdbId}")
    public ResponseEntity<MovieDetail> getMovieDetailByTmdb_Id(@PathVariable int tmdbId){
        return new ResponseEntity<>(movieDetailService.getMovieDetail(tmdbId), HttpStatus.OK);
    }

    @GetMapping("/search-by-genre")
    public ResponseEntity<List<MovieDetail>> getMovieDetailsByGenreIds(@RequestParam List<Integer> genreIds) {
        return new ResponseEntity<>(movieDetailService.getMovieDetailsByGenreIds(genreIds), HttpStatus.OK);
    }

    @GetMapping("/search-by-title")
    public ResponseEntity<List<MovieDetail>> getMovieDetailsByTitle(@RequestParam String title) {
        return new ResponseEntity<>(movieDetailService.searchMovieDetailsByTitle(title), HttpStatus.OK);
    }
}



//List<MovieDetail> movieDetails = movieDetailService.getMovieDetailsByGenreIds(genreIds);
//        List<MovieDetailDTO> movieDetailDTOs = movieDetails.stream()
//                .map(MovieDetailMapper::toDTO)
//                .collect(Collectors.toList());
