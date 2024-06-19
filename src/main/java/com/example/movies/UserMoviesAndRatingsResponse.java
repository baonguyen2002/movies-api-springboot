package com.example.movies;

import java.util.List;

public class UserMoviesAndRatingsResponse {
	private List<FavMovie> favMovies;
	private List<Rating> ratings;

	public UserMoviesAndRatingsResponse(List<FavMovie> favMovies, List<Rating> ratings) {
		this.favMovies = favMovies;
		this.ratings = ratings;
	}

	public List<FavMovie> getFavMovies() {
		return favMovies;
	}

	public List<Rating> getRatings() {
		return ratings;
	}
}