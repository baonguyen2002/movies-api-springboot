package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	@Id
	private ObjectId id;
	@Field("tmdb_id")
	private int tmdbId;
	private String comment;
	private String sentiment;
	private String username;
}
