package com.application.movierentalapp.dto.movie.response;

import com.application.movierentalapp.model.movie.MovieGenres;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private Integer id;

    private String name;

    private Integer releaseYear;

    private float price;

    private MovieGenres genre;

    boolean rented;
}
