package com.application.movierentalapp.model.movie;

public class GenreCount {

    private MovieGenres genre;
    private Long count;

    public GenreCount(MovieGenres genre, Long count) {
        this.genre = genre;
        this.count = count;
    }

    public MovieGenres getGenre() {
        return genre;
    }

    public void setGenre(MovieGenres genre) {
        this.genre = genre;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
