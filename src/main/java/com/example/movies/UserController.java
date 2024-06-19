package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload) {

		List<Genre> genres = new ArrayList<>();
		List<FavMovie> favMovies = new ArrayList<>();
		List<Rating> ratings = new ArrayList<>();
		genres.add(new Genre(28, "Action", 200));
		genres.add(new Genre(12, "Adventure", 200));
		genres.add(new Genre(16, "Animation", 200));
		genres.add(new Genre(35, "Comedy", 200));
		genres.add(new Genre(80, "Crime", 200));
		genres.add(new Genre(99, "Documentary", 200));
		genres.add(new Genre(18, "Drama", 200));
		genres.add(new Genre(10751, "Family", 200));
		genres.add(new Genre(14, "Fantasy", 200));
		genres.add(new Genre(36, "History", 200));
		genres.add(new Genre(27, "Horror", 200));
		genres.add(new Genre(10402, "Music", 200));
		genres.add(new Genre(9648, "Mystery", 200));
		genres.add(new Genre(10749, "Romance", 200));
		genres.add(new Genre(878, "Science Fiction", 200));
		genres.add(new Genre(10770, "TV Movie", 200));
		genres.add(new Genre(53, "Thriller", 200));
		genres.add(new Genre(37, "Western", 200));
		genres.add(new Genre(10752, "War", 200));
		User user = userService.createUser(payload.get("username"), payload.get("password"), payload.get("email"),genres, favMovies, ratings);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody Map<String, String> payload) {
		User user = userService.validateUser(payload.get("username"), payload.get("password"));
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	@PostMapping("/{username}/favmovie")
	public ResponseEntity<User> addFavMovie(@PathVariable String username, @RequestBody Map< String, String> payload) {
		int tmdbId = Integer.parseInt(payload.get("id"));
		String name = payload.get("name");
		String date = payload.get("date");
		String posterUrl = payload.get("poster");
		FavMovie newFavMovie = new FavMovie(tmdbId, name,date,posterUrl);
		userService.addFavMovie(username, newFavMovie);
		User user = userService.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{username}/favmovie/{id}")
	public ResponseEntity<User> deleteFavMovie(@PathVariable String username, @PathVariable int id) {
		userService.deleteFavMovieById(username, id);
		User user = userService.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PostMapping("/{username}/rating")
	public ResponseEntity<User> addRating(@PathVariable String username, @RequestBody Map< String, String> payload) {
		int tmdbId = Integer.parseInt(payload.get("id"));
		float score = Float.parseFloat(payload.get("score"));
		String name = payload.get("name");
		String date = payload.get("date");
		String posterUrl = payload.get("poster");
		Rating newRating = new Rating(tmdbId, name,date,posterUrl, score);
		userService.addRating(username, newRating);
		User user = userService.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PutMapping("/{username}/rating/{id}")
	public ResponseEntity<User> updateRating(@PathVariable String username, @PathVariable int id, @RequestBody Map< String, String> payload) {
		int tmdbId = Integer.parseInt(payload.get("id"));
		float score = Float.parseFloat(payload.get("score"));
		String name = payload.get("name");
		String date = payload.get("date");
		String posterUrl = payload.get("poster");
		Rating updatedRating = new Rating(tmdbId, name,date,posterUrl, score);
		userService.updateRating(username, id, updatedRating);
		User user = userService.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{username}/rating/{id}")
	public ResponseEntity<User> deleteRating(@PathVariable String username, @PathVariable int id) {
		userService.deleteRating(username, id);
		User user = userService.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/{username}/favmovie")
	public ResponseEntity<List<FavMovie>> getUserFavMovies(@PathVariable String username) {
		List<FavMovie> favMovies = userService.getFavMovies(username);
		return ResponseEntity.ok(favMovies);
	}

	@GetMapping("/{username}/rating")
	public ResponseEntity<List<Rating>> getUserRatings(@PathVariable String username) {
		List<Rating> ratings = userService.getRatings(username);
		return ResponseEntity.ok(ratings);
	}

	@GetMapping("/{username}/favandratings")
	public ResponseEntity<UserMoviesAndRatingsResponse> getUserMoviesAndRatings(@PathVariable String username) {
		try {
			List<FavMovie> favMovies = userService.getFavMovies(username);
			List<Rating> ratings = userService.getRatings(username);
			UserMoviesAndRatingsResponse response = new UserMoviesAndRatingsResponse(favMovies, ratings);
			return ResponseEntity.ok(response);
		} catch (UserNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{username}/genre/{genreId}")
	public ResponseEntity<User> updateGenreScore(@PathVariable String username, @PathVariable int genreId,  @RequestBody Map<String, String> payload) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Genre> genres = user.getGenres();
		for (Genre genre : genres) {
			if (genre.getGenreId() == genreId) {
				int scoreIncrement = Integer.parseInt(payload.get("scoreIncrement"));
				genre.setScore(genre.getScore() + scoreIncrement);
				User updatedUser = userService.updateUser(user);
				return new ResponseEntity<>(updatedUser, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{username}/genre")
	public ResponseEntity<User> updateGenreScores(@PathVariable String username, @RequestBody Map<Integer, Integer> scoreIncrements) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Genre> genres = user.getGenres();
		for (Map.Entry<Integer, Integer> entry : scoreIncrements.entrySet()) {
			int genreId = entry.getKey();
			int scoreIncrement = entry.getValue();

			for (Genre genre : genres) {
				if (genre.getGenreId() == genreId) {
					genre.setScore(genre.getScore() + scoreIncrement);
					break;
				}
			}
		}

		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@GetMapping("/{username}/top-genres")
	public ResponseEntity<List<Integer>> getTopGenres(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Genre> genres = user.getGenres();

		// Check if all genres have a score of 200
		boolean allGenresHave200Score = genres.stream().allMatch(genre -> genre.getScore() == 200);
		if (allGenresHave200Score) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}

		// Check if all genres have the same score, but it's not 200
		boolean allGenresHaveSameScore = genres.stream().allMatch(genre -> genre.getScore() == genres.get(0).getScore());
		if (allGenresHaveSameScore && genres.get(0).getScore() != 200) {
			List<Integer> topGenreIds = new ArrayList<>();
			Random random = new Random();
			for (int i = 0; i < 3; i++) {
				int randomIndex = random.nextInt(genres.size());
				topGenreIds.add(genres.get(randomIndex).getGenreId());
			}
			return new ResponseEntity<>(topGenreIds, HttpStatus.OK);
		}

		genres.sort((g1, g2) -> Integer.compare(g2.getScore(), g1.getScore())); // Sort genres in descending order by score

		List<Integer> topGenreIds = new ArrayList<>();
		for (Genre genre : genres) {
			if (topGenreIds.size() < 3) {
				topGenreIds.add(genre.getGenreId());
			} else {
				break;
			}
		}

		return new ResponseEntity<>(topGenreIds, HttpStatus.OK);
	}
//@GetMapping("/{username}/top-genres")
//public ResponseEntity<List<Integer>> getTopGenres(@PathVariable String username) {
//	User user = userService.findByUsername(username);
//	if (user == null) {
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
//
//	List<Genre> genres = user.getGenres();
//	genres.sort((g1, g2) -> Integer.compare(g2.getScore(), g1.getScore())); // Sort genres in descending order by score
//
//	List<Integer> topGenreIds = new ArrayList<>();
//	for (Genre genre : genres) {
//		if (topGenreIds.size() < 3) {
//			topGenreIds.add(genre.getGenreId());
//		} else {
//			break;
//		}
//	}
//
//	return new ResponseEntity<>(topGenreIds, HttpStatus.OK);
//}
}