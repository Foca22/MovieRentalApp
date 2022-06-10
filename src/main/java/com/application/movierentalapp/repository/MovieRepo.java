package com.application.movierentalapp.repository;

import com.application.movierentalapp.model.movie.GenreCount;
import com.application.movierentalapp.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query("SELECT new com.application.movierentalapp.model.movie.GenreCount(m.genre, COUNT(m.genre)) FROM Movie AS m GROUP BY m.genre")
    List<GenreCount> countMoviesByGenre();
}
