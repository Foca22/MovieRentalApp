package com.application.movierentalapp.service;

import com.application.movierentalapp.dto.exceptions.*;
import com.application.movierentalapp.dto.movie.request.CreateMovieRequest;
import com.application.movierentalapp.dto.movie.request.UpdateMovieRequest;
import com.application.movierentalapp.dto.movie.response.MovieResponse;
import com.application.movierentalapp.model.movie.GenreCount;
import com.application.movierentalapp.model.movie.Movie;
import com.application.movierentalapp.model.movie.MovieGenre;
import com.application.movierentalapp.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepo movieRepo;

    @Override
    public MovieResponse createMovie(CreateMovieRequest createMovieRequest) throws PriceOutOfIntervalException {
        validatePrice(createMovieRequest.getReleaseYear(), createMovieRequest.getPrice());
        Movie movie = new Movie(createMovieRequest.getName(), createMovieRequest.getReleaseYear(), createMovieRequest.getPrice(), createMovieRequest.getGenre());
        Movie savedMovie = movieRepo.save(movie);
        MovieResponse movieResponse = toMovieResponse(savedMovie);
        return movieResponse;
    }

    @Transactional
    public MovieResponse updateMoviePrice(UpdateMovieRequest updateMovieRequest) throws MovieNotFoundException, PriceOutOfIntervalException {
        Movie movie = findMovieById(updateMovieRequest.getId());
        validatePrice(movie.getReleaseYear(), updateMovieRequest.getPrice());
        movie.setPrice(updateMovieRequest.getPrice());

        Movie saveUpdatedMovie = movieRepo.save(movie);
        MovieResponse movieResponse = toMovieResponse(saveUpdatedMovie);
        return movieResponse;
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepo.findAll(Sort.by("price").ascending())
                .stream()
                .map(this::toMovieResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String getMovieCountByGenre() {
        List<GenreCount> genreCountArray = movieRepo.countMoviesByGenre();
        fillRenamingGenres(genreCountArray);
        return buildGenreCountResponse(genreCountArray);
    }

    @Override
    public MovieResponse rentMovie(Integer id) throws MovieNotFoundException, MovieIsRentedException {
        Movie movie = findMovieById(id);
        if (movie.isRented()) {
            throw new MovieIsRentedException(ExceptionMessages.MOVIE_ALREADY_RENTED);
        }
        movie.setRented(true);
        Movie savedRentedMovie = movieRepo.save(movie);

        MovieResponse movieResponse = toMovieResponse(savedRentedMovie);
        return movieResponse;
    }

    @Override
    public MovieResponse releaseMovie(Integer id) throws MovieNotFoundException, MovieIsReleasedException {
        Movie movie = findMovieById(id);
        if (!movie.isRented()) {
            throw new MovieIsReleasedException(ExceptionMessages.MOVIE_ALREADY_RELEASED);
        }
        movie.setRented(false);
        Movie savedRentedMovie = movieRepo.save(movie);

        MovieResponse movieResponse = toMovieResponse(savedRentedMovie);
        return movieResponse;
    }

    @Override
    public List<MovieResponse> getRentedMovies() {
        return movieRepo.findAll()
                .stream()
                .filter(Movie::isRented)
                .map(this::toMovieResponse)
                .collect(Collectors.toList());
    }

    private MovieResponse toMovieResponse(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getName(), movie.getReleaseYear(),
                movie.getPrice(), movie.getGenre(), movie.isRented());
    }

    private Movie findMovieById(Integer id) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepo.findById(id);
        if (movieOptional.isEmpty()) {
            throw new MovieNotFoundException(ExceptionMessages.MOVIE_NOT_FOUND);
        }
        return movieOptional.get();
    }

    private void validatePrice(Integer releaseYear, float price) throws PriceOutOfIntervalException {
        if (releaseYear < 2010 && price > 5) {
            throw new PriceOutOfIntervalException(ExceptionMessages.BAD_PRICE_FOR_MOVIE_LESS_THAN_2010);
        }
        if (releaseYear >= 2010 && releaseYear <= 2020 && price > 10) {
            throw new PriceOutOfIntervalException(ExceptionMessages.BAD_PRICE_FOR_MOVIE_BETWEEN_2010_AND_2020);
        }
        if (releaseYear > 2020 && price < 13) {
            throw new PriceOutOfIntervalException(ExceptionMessages.BAD_PRICE_FOR_MOVIE_BIGGER_THAN_2020);
        }
    }

    private void fillRenamingGenres(List<GenreCount> genreCountList) {
        List<MovieGenre> movieGenres = collectGenresFromGenreCountList(genreCountList);
        addRemainsGenresToGenreCountList(genreCountList, movieGenres);
    }

    private void addRemainsGenresToGenreCountList(List<GenreCount> genreCountList, List<MovieGenre> movieGenres) {
        Arrays.stream(MovieGenre.values())
                .filter(genre -> !movieGenres.contains(genre))
                .forEach(genre -> genreCountList.add(new GenreCount(genre, 0L)));
    }

    private List<MovieGenre> collectGenresFromGenreCountList(List<GenreCount> genreCountArray) {
        return genreCountArray
                .stream()
                .map(genreCount -> genreCount.getGenre())
                .collect(Collectors.toList());
    }

    private String buildGenreCountResponse(List<GenreCount> genreCountArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genreCountArray.size(); i++) {
            GenreCount genreCount = genreCountArray.get(i);
            sb.append(genreCount.getGenre());
            sb.append(" : ");
            sb.append(genreCount.getCount());

            if (i < genreCountArray.size() - 1) {
                sb.append(",");
            }
            sb.append(" <br>");
            if (i < genreCountArray.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}













