package com.application.movierentalapp.service;

import com.application.movierentalapp.dto.exceptions.*;
import com.application.movierentalapp.dto.movie.request.CreateMovieRequest;
import com.application.movierentalapp.dto.movie.request.UpdateMovieRequest;
import com.application.movierentalapp.dto.movie.response.MovieResponse;
import com.application.movierentalapp.model.movie.GenreCount;
import com.application.movierentalapp.model.movie.Movie;
import com.application.movierentalapp.model.movie.MovieGenre;
import com.application.movierentalapp.repository.MovieRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    MovieServiceImpl movieService;

    @Mock
    MovieRepo movieRepo;

    private static final Integer movieId = 1;
    private static final String movieName = "The Godfather";
    private static final Integer releaseYear = 1972;
    private static final float price = 4;
    private static final MovieGenre genre = MovieGenre.DRAMA;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateMovieWhenYearIsLowerThan2010AndPriceIsLowerThanFive() throws PriceOutOfIntervalException {
        CreateMovieRequest createMovieRequest = new CreateMovieRequest(movieName, releaseYear, price, genre);

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> {
            Movie movie = (Movie) a.getArguments()[0];
            movie.setId(movieId);
            return movie;
        });

        MovieResponse movieResponse = movieService.createMovie(createMovieRequest);

        assertEquals(movieId, movieResponse.getId());
        assertEquals(movieName, movieResponse.getName());
        assertEquals(releaseYear, movieResponse.getReleaseYear());
        assertEquals(price, movieResponse.getPrice());
        assertEquals(genre, movieResponse.getGenre());
    }

    @Test
    public void shouldCreateMovieWhenYearIsBetween2010And2020AndPriceIsLowerThanTen() throws PriceOutOfIntervalException {
        CreateMovieRequest createMovieRequest = new CreateMovieRequest(movieName, 2012, 7, genre);

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> {
            Movie movie = (Movie) a.getArguments()[0];
            movie.setId(movieId);
            return movie;
        });

        MovieResponse movieResponse = movieService.createMovie(createMovieRequest);

        assertEquals(movieId, movieResponse.getId());
        assertEquals(movieName, movieResponse.getName());
        assertEquals(2012, movieResponse.getReleaseYear());
        assertEquals(7, movieResponse.getPrice());
        assertEquals(genre, movieResponse.getGenre());
    }

    @Test
    public void shouldCreateMovieWhenYearBiggerThan2020AndPriceIsBiggerThanThirteen() throws PriceOutOfIntervalException {
        CreateMovieRequest createMovieRequest =
                new CreateMovieRequest(movieName, 2022, 15, genre);

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> {
            Movie movie = (Movie) a.getArguments()[0];
            movie.setId(movieId);
            return movie;
        });

        MovieResponse movieResponse = movieService.createMovie(createMovieRequest);

        assertEquals(movieId, movieResponse.getId());
        assertEquals(movieName, movieResponse.getName());
        assertEquals(2022, movieResponse.getReleaseYear());
        assertEquals(15, movieResponse.getPrice());
        assertEquals(genre, movieResponse.getGenre());
    }

    @Test
    public void shouldNotCreateMovieWhenYearIsLowerThan2010AndPriceIsBiggerThanFive() {
        CreateMovieRequest createMovieRequest = new CreateMovieRequest(movieName,
                2000, 7, genre);

        Exception exception = assertThrows(PriceOutOfIntervalException.class, () -> movieService.createMovie(createMovieRequest));

        String expectedMessage = ExceptionMessages.BAD_PRICE_FOR_MOVIE_LESS_THAN_2010;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotCreateMovieWhenYearIsBetween2010And2020AndPriceIsBiggerThanTen() {
        CreateMovieRequest createMovieRequest = new CreateMovieRequest(movieName,
                2015, 12, genre);

        Exception exception = assertThrows(PriceOutOfIntervalException.class, () -> movieService.createMovie(createMovieRequest));

        String expectedMessage = ExceptionMessages.BAD_PRICE_FOR_MOVIE_BETWEEN_2010_AND_2020;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotCreateMovieWhenYearIsBiggerThan2020AndPriceIsLessThanThirteen() {
        CreateMovieRequest createMovieRequest = new CreateMovieRequest(movieName,
                2022, 3, genre);

        Exception exception = assertThrows(PriceOutOfIntervalException.class, () -> movieService.createMovie(createMovieRequest));

        String expectedMessage = ExceptionMessages.BAD_PRICE_FOR_MOVIE_BIGGER_THAN_2020;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldUpdatePriceWhenYearIsLowerThan2010AndPriceIsLowerThanFive() throws MovieNotFoundException, PriceOutOfIntervalException {
        Movie movie = new Movie(movieName, releaseYear, price, genre);
        movie.setId(movieId);

        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, 2);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.of(movie));

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> (Movie) a.getArguments()[0]);

        MovieResponse movieResponse = movieService.updateMoviePrice(updateMovieRequest);

        assertEquals(movieId, movieResponse.getId());
        assertEquals(2, movieResponse.getPrice());
    }

    @Test
    public void shouldUpdatePriceWhenYearIsBetween2010And2020AndPriceIsLowerThanTen() throws MovieNotFoundException, PriceOutOfIntervalException {
        Movie movie = new Movie(movieName, 2012, price, genre);
        movie.setId(movieId);

        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, 8);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.of(movie));

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> (Movie) a.getArguments()[0]);

        MovieResponse movieResponse = movieService.updateMoviePrice(updateMovieRequest);

        assertEquals(movieId, movieResponse.getId());
        assertEquals(8, movieResponse.getPrice());
    }

    @Test
    public void shouldUpdatePriceWhenYearBiggerThan2020AndPriceIsBiggerThanThirteen() throws MovieNotFoundException, PriceOutOfIntervalException {
        Movie movie = new Movie(movieName, 2022, 17, genre);
        movie.setId(movieId);

        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, 25);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.of(movie));

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> (Movie) a.getArguments()[0]);

        MovieResponse movieResponse = movieService.updateMoviePrice(updateMovieRequest);

        assertEquals(movieId, movieResponse.getId());
        assertEquals(25, movieResponse.getPrice());
    }

    @Test
    public void shouldNotUpdatePriceWhenYearIsLowerThan2010AndPriceIsBiggerThanFive() {
        Movie movie = new Movie(movieName, releaseYear, price, genre);
        movie.setId(movieId);

        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, 25);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.of(movie));

        Exception exception = assertThrows(PriceOutOfIntervalException.class, () -> movieService.updateMoviePrice(updateMovieRequest));

        String expectedMessage = ExceptionMessages.BAD_PRICE_FOR_MOVIE_LESS_THAN_2010;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotUpdatePriceWhenYearIsBetween2010And2020AndPriceIsBiggerThanTen() {
        Movie movie = new Movie(movieName, 2015, 16, genre);
        movie.setId(movieId);

        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, 25);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.of(movie));

        Exception exception = assertThrows(PriceOutOfIntervalException.class, () -> movieService.updateMoviePrice(updateMovieRequest));

        String expectedMessage = ExceptionMessages.BAD_PRICE_FOR_MOVIE_BETWEEN_2010_AND_2020;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotUpdatePriceWhenYearIsBiggerThan2020AndPriceIsLessThanThirteen() {
        Movie movie = new Movie(movieName, 2022, 16, genre);
        movie.setId(movieId);

        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, 5);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.of(movie));

        Exception exception = assertThrows(PriceOutOfIntervalException.class, () -> movieService.updateMoviePrice(updateMovieRequest));

        String expectedMessage = ExceptionMessages.BAD_PRICE_FOR_MOVIE_BIGGER_THAN_2020;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldNotUpdatePriceIfMovieNotFound() {
        UpdateMovieRequest updateMovieRequest =
                new UpdateMovieRequest(movieId, price);

        when(movieRepo.findById(updateMovieRequest.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(MovieNotFoundException.class, () -> movieService.updateMoviePrice(updateMovieRequest));

        String expectedMessage = ExceptionMessages.MOVIE_NOT_FOUND;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldGetAllMovies() {
        Movie movie1 = new Movie(movieName, releaseYear, price, genre);
        Movie movie2 = new Movie("movieName2", 1980, 5, MovieGenre.THRILLER);

        when(movieRepo.findAll(any(Sort.class))).thenReturn(Arrays.asList(movie1, movie2));

        List<MovieResponse> result = movieService.getAllMovies();

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMovieCountByGenre() {
        GenreCount genreCount1 = new GenreCount(MovieGenre.COMEDY, 1L);
        GenreCount genreCount2 = new GenreCount(MovieGenre.DRAMA, 4L);

        List<GenreCount> genreCounts = new ArrayList<>();
        genreCounts.add(genreCount1);
        genreCounts.add(genreCount2);

        when(movieRepo.countMoviesByGenre()).thenReturn(genreCounts);

        String expected = "COMEDY : 1, <br>\n" +
                "DRAMA : 4, <br>\n" +
                "THRILLER : 0 <br>";

        String result = movieService.getMovieCountByGenre();

        assertEquals(expected, result);
    }

    @Test
    public void shouldRentMovie() throws MovieNotFoundException, MovieIsRentedException {
        Movie movie = new Movie(movieName, releaseYear, price, genre);

        when(movieRepo.findById(movie.getId())).thenReturn(Optional.of(movie));

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> {
            Movie savedMovie = (Movie) a.getArguments()[0];
            savedMovie.setId(movieId);
            savedMovie.setRented(true);
            return savedMovie;
        });

        MovieResponse movieResponse = movieService.rentMovie(movie.getId());

        assertEquals(movieId, movieResponse.getId());
        assertTrue(true, String.valueOf(movieResponse.isRented()));
    }

    @Test
    public void shouldThrowExceptionForAnAlreadyRentedMovie() {
        Movie movie = new Movie(movieName, releaseYear, price, genre);
        movie.setRented(true);
        when(movieRepo.findById(movie.getId())).thenReturn(Optional.of(movie));

        Exception exception = assertThrows(MovieIsRentedException.class, () -> movieService.rentMovie(movie.getId()));

        String expectedMessage = ExceptionMessages.MOVIE_ALREADY_RENTED;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReleaseMovie() throws MovieNotFoundException, MovieIsReleasedException {
        Movie movie = new Movie(movieName, releaseYear, price, genre);
        movie.setRented(true);

        when(movieRepo.findById(movie.getId())).thenReturn(Optional.of(movie));

        when(movieRepo.save(any(Movie.class))).thenAnswer(a -> {
            Movie savedMovie = (Movie) a.getArguments()[0];
            savedMovie.setId(movieId);
            savedMovie.setRented(false);
            return savedMovie;
        });

        MovieResponse movieResponse = movieService.releaseMovie(movie.getId());

        assertEquals(movieId, movieResponse.getId());
        assertFalse(false, String.valueOf(movieResponse.isRented()));
    }

    @Test
    public void shouldThrowExceptionForAnAlreadyReleasedMovie() {
        Movie movie = new Movie(movieName, releaseYear, price, genre);

        when(movieRepo.findById(movie.getId())).thenReturn(Optional.of(movie));

        Exception exception = assertThrows(MovieIsReleasedException.class, () -> movieService.releaseMovie(movie.getId()));

        String expectedMessage = ExceptionMessages.MOVIE_ALREADY_RELEASED;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldGetAllRentedMovies() {
        Movie movie1 = new Movie(movieName, releaseYear, price, genre);
        movie1.setRented(true);
        Movie movie2 = new Movie("movieName2", 1980, 5, MovieGenre.THRILLER);

        when(movieRepo.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        List<MovieResponse> result = movieService.getRentedMovies();

        assertEquals(1, result.size());
        assertEquals(movieName, result.get(0).getName());
    }
}