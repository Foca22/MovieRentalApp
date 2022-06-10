package com.application.movierentalapp.dto.movie.request;

import com.application.movierentalapp.model.movie.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {
    private String name;

    private Integer releaseYear;

    private float price;

    private MovieGenre genre;
}
