package com.application.movierentalapp.model.customer;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "number_of_stars", nullable = false)
    private Integer numberOfStars;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Review() {
    }

    public Review(Integer id, String title, Integer numberOfStars, String text) {
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
