package com.application.movierentalapp.model.customer;

import com.application.movierentalapp.model.movie.Movie;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_customer", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_ame", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false)
    private Integer telephoneNumber;

    @OneToMany
    @JoinColumn(name = "id_customer_review")
    private List<CustomerReview> customerReviewsList;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "customer_movies",
            joinColumns = {@JoinColumn(name = "id_customer")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private Set<Movie> rentedMovies;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, Integer telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<CustomerReview> getCustomerReviewsList() {
        return customerReviewsList;
    }

    public void setCustomerReviewsList(List<CustomerReview> customerReviewsList) {
        this.customerReviewsList = customerReviewsList;
    }

    public Set<Movie> getMovies() {
        return rentedMovies;
    }

    public void setMovies(Set<Movie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", customerReviewsList=" + customerReviewsList +
                ", movies=" + rentedMovies +
                '}';
    }
}
