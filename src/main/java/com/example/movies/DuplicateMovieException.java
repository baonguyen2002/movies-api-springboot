package com.example.movies;

public class DuplicateMovieException extends RuntimeException {
	public DuplicateMovieException(String message) {
		super(message);
	}
}
