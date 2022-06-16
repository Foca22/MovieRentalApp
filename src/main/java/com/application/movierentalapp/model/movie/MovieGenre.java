package com.application.movierentalapp.model.movie;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class MovieGenre {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movie_genre_id", updatable = false, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MovieGenres genre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movies_by_genre",
            joinColumns = {@JoinColumn(name = "movie_genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")})
    private Set<Movie> moviesByGenre = new HashSet<>();

    public MovieGenre() {
    }

    public MovieGenre(Integer movieGenreId, MovieGenres genre) {
        this.id = movieGenreId;
        this.genre = genre;
    }

    public Integer getMovieGenreId() {
        return id;
    }

    public void setMovieGenreId(Integer movieGenreId) {
        this.id = movieGenreId;
    }

    public MovieGenres getGenre() {
        return genre;
    }

    public void setGenre(MovieGenres genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MovieGenre{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
