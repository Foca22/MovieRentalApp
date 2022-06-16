package com.application.movierentalapp.dto.movie.request;

import com.application.movierentalapp.model.movie.MovieGenres;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {

    private String name;

    private Integer releaseYear;

    private float price;

    private MovieGenres genre;
}
