package com.application.movierentalapp.model.customer;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer_review")
public class CustomerReview {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_customer_review", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "number_of_stars", nullable = false)
    private Integer numberOfStars;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    private Customer customer;

    public CustomerReview() {
    }

    public CustomerReview(Integer id, String title, Integer numberOfStars, String text) {
        this.id = id;
        this.title = title;
        this.numberOfStars = numberOfStars;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerReviews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numberOfStars=" + numberOfStars +
                ", text='" + text + '\'' +
                '}';
    }
}
