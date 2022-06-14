package com.application.movierentalapp.model.movie;

import com.application.movierentalapp.model.customer.Customer;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_movie", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    private float price;

    @Enumerated(EnumType.STRING)
    private MovieGenres genre;

    boolean rented;

    @ManyToMany(mappedBy = "rentedMovies")
    private Set<Customer> customers;

    @ManyToMany(mappedBy = "movieListByGenre")
    private Set<MovieGene> movieGeneSet;

    public Movie() {
    }

    public Movie(String name, Integer releaseYear, float price, MovieGenres genre) {
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

    public MovieGenres getGenre() {
        return genre;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
