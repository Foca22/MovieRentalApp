package com.application.movierentalapp.model.movie;

public class GenreCount {

    private MovieGenre genre;
    private Long count;

    public GenreCount(MovieGenre genre, Long count) {
        this.genre = genre;
        this.count = count;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
