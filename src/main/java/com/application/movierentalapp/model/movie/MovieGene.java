package com.application.movierentalapp.model.movie;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie_genre")
public class MovieGene {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_movie_genre", updatable = false, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MovieGenres genre;

    //vezi cum se face relatia!!!!
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movies_by_movie_genre",
            joinColumns = {@JoinColumn(name = "id_movie")},
            inverseJoinColumns = {@JoinColumn(name = "id_movie_genre")}
    )
    private Set<Movie> movieListByGenre;



    public MovieGene() {
    }

    public MovieGene(Integer movieGenreId, MovieGenres genre, Set<Movie> movieListByGenre) {
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

    public MovieGenres getGenre() {
        return genre;
    }

    public void setGenre(MovieGenres genre) {
        this.genre = genre;
    }

    public Set<Movie> getMovieListByGenre() {
        return movieListByGenre;
    }

    public void setMovieListByGenre(Set<Movie> movieListByGenre) {
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
