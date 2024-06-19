package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/id/{tmdbId}")
	public ResponseEntity<List<Comment>> getCommentByTMDBId(@PathVariable int tmdbId) {
		return new ResponseEntity<>(commentService.getCommentByTMDBId(tmdbId), HttpStatus.OK);
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<List<Comment>> getCommentsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(commentService.getCommentsByUsername(username), HttpStatus.OK);
	}
	@DeleteMapping("/{username}/{tmdbId}")
	public ResponseEntity<Void> deleteCommentByUsernameAndTmdbId(@PathVariable String username, @PathVariable int tmdbId) {
		if (commentService.deleteCommentByUsernameAndTmdbId(username, tmdbId)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
