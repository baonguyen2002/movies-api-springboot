package notNeeded_example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
//    @Autowired
//    private MovieService movieService;
//    @GetMapping
//    public ResponseEntity<List<Movie>> getAllMovies(){
//        return new ResponseEntity<List<Movie>>( movieService.allMovies(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{imdbId}")
//    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
//        return new ResponseEntity<Optional<Movie>>( movieService.singleMovie(imdbId), HttpStatus.OK);
//    }

//    @GetMapping("/{page}")
//    public ResponseEntity<Optional<Movie>> getSingle1Movie(@PathVariable String imdbId){
//        return new ResponseEntity<Optional<Movie>>( movieService.singleMovie(imdbId), HttpStatus.OK);
//    }
}
