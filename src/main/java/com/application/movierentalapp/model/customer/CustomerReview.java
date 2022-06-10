package com.application.movierentalapp.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class CustomerReview {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID Customer Review", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Number of Stars", nullable = false)
    private Integer numberOfStars;

    @Column(name = "Text", nullable = false)
    private String text;

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
