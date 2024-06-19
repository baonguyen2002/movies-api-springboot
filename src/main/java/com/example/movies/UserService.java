package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(String username, String password, String email, List<Genre> genres,  List<FavMovie> favMovies,  List<Rating> ratings) {
		User user = new User(username, password, email);
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setEmail(email);
		user.setGenres(genres);
		user.setFavMovies(favMovies);
		user.setRatings(ratings);
		return userRepository.insert(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	public User validateUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public void addFavMovie(String username, FavMovie newFavMovie) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			List<FavMovie> favMovies = user.getFavMovies();

			// Check if the newFavMovie already exists in the list
			boolean isDuplicate = favMovies.stream()
					.anyMatch(favMovie -> favMovie.getTmdbId() == newFavMovie.getTmdbId());

			if (!isDuplicate) {
				favMovies.add(newFavMovie);
				user.setFavMovies(favMovies);
				userRepository.save(user);
			} else {
				// Handle the duplicate case, e.g., throw an exception or log a warning
				throw new DuplicateMovieException("FavMovie with TMDB ID " + newFavMovie.getTmdbId() + " already exists for user " + username);
			}
		}
	}

	public void deleteFavMovieById(String username, int tmdbId) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			List<FavMovie> favMovies = user.getFavMovies();
			favMovies.removeIf(favMovie -> favMovie.getTmdbId() == tmdbId);
			user.setFavMovies(favMovies);
			userRepository.save(user);
		}
	}

	public void addRating(String username, Rating newRating) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			List<Rating> ratings = user.getRatings();

			// Check if the newRating already exists in the list
			boolean isDuplicate = ratings.stream()
					.anyMatch(rating -> rating.getTmdbId() == newRating.getTmdbId());

			if (!isDuplicate) {
				ratings.add(newRating);
				user.setRatings(ratings);
				userRepository.save(user);
			} else {
				throw new DuplicateMovieException("Rating with TMDB ID " + newRating.getTmdbId() + " already exists for user " + username);
			}
		}
	}

	public void updateRating(String username, int ratingId, Rating updatedRating) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			List<Rating> ratings = user.getRatings();
			for (int i = 0; i < ratings.size(); i++) {
				if (ratings.get(i).getTmdbId() == ratingId) {
					ratings.set(i ,updatedRating);
					break;
				}
			}
			user.setRatings(ratings);
			userRepository.save(user);
		}
	}

	public void deleteRating(String username, int ratingId) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			List<Rating> ratings = user.getRatings();
			ratings.removeIf(rating -> rating.getTmdbId() == ratingId);
			user.setRatings(ratings);
			userRepository.save(user);
		}
	}

	public List<FavMovie> getFavMovies(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return user.getFavMovies();
		}
		throw new UserNotFoundException("User with username " + username + " not found");
	}

	public List<Rating> getRatings(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return user.getRatings();
		}
		throw new UserNotFoundException("User with username " + username + " not found");
	}


	public User updateUser(User user) {
		return userRepository.save(user);
	}

}