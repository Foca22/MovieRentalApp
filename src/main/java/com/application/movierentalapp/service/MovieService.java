package com.application.movierentalapp.service;

import com.application.movierentalapp.dto.exceptions.MovieIsReleasedException;
import com.application.movierentalapp.dto.exceptions.MovieIsRentedException;
import com.application.movierentalapp.dto.exceptions.MovieNotFoundException;
import com.application.movierentalapp.dto.exceptions.PriceOutOfIntervalException;
import com.application.movierentalapp.dto.movie.request.CreateMovieRequest;
import com.application.movierentalapp.dto.movie.request.UpdateMovieRequest;
import com.application.movierentalapp.dto.movie.response.MovieResponse;

import java.util.List;

public interface MovieService {
    MovieResponse createMovie(CreateMovieRequest createMovieRequest) throws PriceOutOfIntervalException;

    MovieResponse updateMoviePrice(UpdateMovieRequest updateMovieRequest) throws MovieNotFoundException, PriceOutOfIntervalException;

    List<MovieResponse> getAllMovies();

    String getMovieCountByGenre();

    MovieResponse rentMovie(Integer id) throws MovieNotFoundException, MovieIsRentedException;

    MovieResponse releaseMovie(Integer id) throws MovieNotFoundException, MovieIsReleasedException;

    List<MovieResponse> getRentedMovies();
}
