package com.application.movierentalapp.model.movie;

import com.application.movierentalapp.model.customer.Customer;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movie_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name="price", nullable = false)
    private float price;

    @Enumerated(EnumType.STRING)
    private MovieGenres genre;

    @Column(name = "rented")
    boolean rented;

    @ManyToMany(mappedBy = "rentedMovies")
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany(mappedBy = "moviesByGenre")
    private Set<MovieGenre> movieGenres = new HashSet<>();

    public Movie() {
    }

    public Movie(String name, Integer releaseYear, float price, MovieGenres genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.price = price;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public void setGenre(MovieGenres genre) {
        this.genre = genre;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", price=" + price +
                ", genre=" + genre +
                ", rented=" + rented +
                '}';
    }
}
