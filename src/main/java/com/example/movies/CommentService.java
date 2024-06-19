package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> getCommentByTMDBId(int tmdbId) {
		return commentRepository.findByTmdbId(tmdbId);
	}
	public List<Comment> getCommentsByUsername(String username) {
		return commentRepository.findByUsername(username);
	}
	public boolean deleteCommentByUsernameAndTmdbId(String username, int tmdbId) {
		int deletedCount = commentRepository.deleteByUsernameAndTmdbId(username, tmdbId);
		return deletedCount > 0;
	}
}
