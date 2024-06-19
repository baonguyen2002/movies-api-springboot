package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private ObjectId id;
	private String username;
	private String password;
	private List<Genre> genres;
	@Indexed(unique = true)
	private List<FavMovie> favMovies;
	private List<Rating> ratings;
	private String email;
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Genre {
	private int genreId;
	private String genreName;
	private int score;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FavMovie {
	@Field("tmdb_id")
	@Indexed(unique = true)
	private int tmdbId;
	private String name;
	private String date;
	private String posterUrl;


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Rating {
	@Field("tmdb_id")
	private int tmdbId;
	private String name;
	private String date;
	private String posterUrl;
	private float score;
}
