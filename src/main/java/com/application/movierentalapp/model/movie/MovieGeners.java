package com.application.movierentalapp.model.movie;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class MovieGeners {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID Movie Genere", updatable = false, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    //vezi cum se face relatia!!!!
    @ManyToMany
    @JoinColumn(name = "ID Movie Genere")
    private List<Movie> movieListByGenre;

    public MovieGeners() {
    }

    public MovieGeners(Integer movieGenreId, MovieGenre genre, List<Movie> movieListByGenre) {
        this.id = movieGenreId;
        this.genre = genre;
        this.movieListByGenre = movieListByGenre;
    }

    public Integer getMovieGenreId() {
        return id;
    }

    public void setMovieGenreId(Integer movieGenreId) {
        this.id = movieGenreId;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public List<Movie> getMovieListByGenre() {
        return movieListByGenre;
    }

    public void setMovieListByGenre(List<Movie> movieListByGenre) {
        this.movieListByGenre = movieListByGenre;
    }

    @Override
    public String toString() {
        return "MovieGeners{" +
                "movieGenreId=" + id +
                ", genre=" + genre +
                ", movieListByGenre=" + movieListByGenre +
                '}';
    }
}
