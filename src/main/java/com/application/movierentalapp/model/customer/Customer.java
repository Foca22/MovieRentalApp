package com.application.movierentalapp.model.customer;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "first name", nullable = false)
    private String firstName;

    @Column(name = "Last Name", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false)
    private String email;

    //nr de tel(pt mod de contact)

    //sintaxa diferita la many-to-many - vezi cum se face!!!!
    @ManyToMany
    @JoinColumn(name = "ID Customer")
    private List<CustomerReview> customerReviewsList;

    public Customer() {
    }

    public Customer(Integer id, String firstName, String lastName, Integer CNP, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<CustomerReview> getCustomerReviewsList() {
        return customerReviewsList;
    }

    public void setCustomerReviewsList(List<CustomerReview> customerReviewsList) {
        this.customerReviewsList = customerReviewsList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", customerReviewsList=" + customerReviewsList +
                '}';
    }
}
