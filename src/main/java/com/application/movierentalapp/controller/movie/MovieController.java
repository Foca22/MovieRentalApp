package com.application.movierentalapp.controller.movie;

import com.application.movierentalapp.dto.exceptions.MovieIsReleasedException;
import com.application.movierentalapp.dto.exceptions.MovieIsRentedException;
import com.application.movierentalapp.dto.exceptions.MovieNotFoundException;
import com.application.movierentalapp.dto.exceptions.PriceOutOfIntervalException;
import com.application.movierentalapp.dto.movie.request.CreateMovieRequest;
import com.application.movierentalapp.dto.movie.request.UpdateMovieRequest;
import com.application.movierentalapp.dto.movie.response.MovieResponse;
import com.application.movierentalapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping()
    ResponseEntity createMovie(@RequestBody CreateMovieRequest createMovieRequest) {
        try {
            MovieResponse movieResponse = movieService.createMovie(createMovieRequest);
            return ResponseEntity.ok(movieResponse);
        } catch (PriceOutOfIntervalException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    ResponseEntity updateMovie(@RequestBody UpdateMovieRequest updateMovieRequest) {
        try {
            MovieResponse movieResponse = movieService.updateMoviePrice(updateMovieRequest);
            return ResponseEntity.ok(movieResponse);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PriceOutOfIntervalException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    ResponseEntity getAllMovies() {
        List<MovieResponse> movieResponseList = movieService.getAllMovies();
        return ResponseEntity.ok(movieResponseList);
    }

    @GetMapping("countByGenre")
    ResponseEntity getMovieCountByGenre() {
        String movieCountByGenre = movieService.getMovieCountByGenre();
        return ResponseEntity.ok(movieCountByGenre);
    }

    @PutMapping("rent/{id}")
    ResponseEntity rentMovie(@PathVariable Integer id) {
        try {
            MovieResponse movieResponse = movieService.rentMovie(id);
            return ResponseEntity.ok(movieResponse);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MovieIsRentedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("release/{id}")
    ResponseEntity releaseMovie(@PathVariable Integer id) {
        try {
            MovieResponse movieResponse = movieService.releaseMovie(id);
            return ResponseEntity.ok(movieResponse);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MovieIsReleasedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("rented")
    ResponseEntity getRentedMovies() {
        List<MovieResponse> movieResponseList = movieService.getRentedMovies();
        return ResponseEntity.ok(movieResponseList);
    }

}
