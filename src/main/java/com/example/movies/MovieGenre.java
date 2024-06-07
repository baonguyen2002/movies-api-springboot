package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGenre {
    @Field("id")
    private int genreId;
    private String name;
}
