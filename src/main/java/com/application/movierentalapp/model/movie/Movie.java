package com.application.movierentalapp.model.movie;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movie_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    private float price;

    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    boolean rented;

    public Movie() {
    }

    public Movie(String name, Integer releaseYear, float price, MovieGenre genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.price = price;
        this.genre = genre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
