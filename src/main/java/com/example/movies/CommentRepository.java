package com.example.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
	List<Comment> findByTmdbId(int tmdb_id);
	List<Comment> findByUsername(String username);
	int deleteByUsernameAndTmdbId(String username, int tmdbId);
	@Query("{ 'username' : ?0, 'tmdbId' : ?1 }")
	List<Comment> findByUsernameAndTmdbId(String username, int tmdbId);
}
